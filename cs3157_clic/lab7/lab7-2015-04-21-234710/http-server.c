
/*
 * mdb-lookup-server.c
 *
 * This program is constructed by merging mdb-lookup.c and
 * TCPEchoServer.c.
 *
 * The comments marked with "CHANGE" indicate the places that were
 * modified from TCPEchoServer.c and mdb-lookup.c.
 */


#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), bind(), and connect() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_ntoa() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <signal.h>     /* for signal() */
#include <netdb.h>      /* for gethostbyname() */

#define MAXPENDING 5    /* Maximum outstanding connection requests */
#define IOBUFSIZE 4096  /*Send and Recieve buffer size */ 

#define KeyMax 5

static void die(const char *message)
{
    perror(message);
    exit(1); 
}

static void logRequest(char *clnt_IP, char *method, char *requestURI, char *httpVersion, char *status) 
{
    printf("%s \"%s %s %s\" %s\n", clnt_IP, method, requestURI, httpVersion, status);
}

void errResponse(int clntSocket, char *status) {
    char outBuf[IOBUFSIZE];
    int outLen;
    int sendLen;

    //build header
    outLen = sprintf(outBuf,      
            "HTTP/1.0 %s\r\n\r\n<html><body><h1>%s</h1></body></html>",         //HTTP newlines!
            status, status);              
    if((sendLen = send(clntSocket, outBuf, outLen, 0) != outLen)) {    //send header
               perror("send() failed");
               return;
        } 
}


// CHANGE: added an additional parameter
void HandleTCPClient(int clntSocket, int mdbSock, struct sockaddr_in *clntAddr, char *web_root);


int main(int argc, char *argv[])
{
    int servSock;                    /* Socket descriptor for server */
    int clntSock;                    /* Socket descriptor for client */
    struct sockaddr_in echoServAddr; /* Local address */
    struct sockaddr_in echoClntAddr; /* Client address */
    unsigned short echoServPort;     /* Server port */
    unsigned int clntLen;            /* Length of client address data struct */

    // CHANGE: ignore SIGPIPE so that we don't terminate when we call
    // send() on a disconnected socket.

    if (signal(SIGPIPE, SIG_IGN) == SIG_ERR) 
	die("signal() failed");

    // CHANGE: progarm takes two parameters

    if (argc != 5)  
    {
        fprintf(stderr, "Usage:  %s <server_port> <web_root> <mdb-lookup-host> <mdb-lookup-port>>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[1]);                       //1st arg: local port 
    char *web_root = argv[2];                           //root file path
    char *mdb_lookup_server = argv[3];                  //mdb_lookup_server hostname
    unsigned short mdb_server_port = atoi(argv[4]);
    
    ////////////////////////////////////////////
    /* Construct mdb-server address structure */
    int mdbSock;                                        //socket for mdb_lookup server
    struct sockaddr_in mdbserverAddr;                   //address structure for mdb-lookup server
    char *mdb_server_ip;                                //mdb_lookup_server ip address

    struct hostent *mdbHE;                              //code from lab 6 instructions - gets IP
    if((mdbHE = gethostbyname(mdb_lookup_server)) == NULL) {
                die("Failed to get server IP address");
    }
    mdb_server_ip = inet_ntoa(*(struct in_addr *)mdbHE->h_addr);

    //Create Socket for mdb_lookup_server
    if((mdbSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
                die("mdb socket() failed");

    //construct mdb_server address structure
    memset(&mdbserverAddr, 0, sizeof(mdbserverAddr));
    mdbserverAddr.sin_family = AF_INET;
    mdbserverAddr.sin_addr.s_addr = inet_addr(mdb_server_ip);
    mdbserverAddr.sin_port = htons(mdb_server_port);

    //make connection to mdb_server
    if(connect(mdbSock, (struct sockaddr *)&mdbserverAddr, sizeof(mdbserverAddr)) < 0)
                die("Connection to mdb failed");



    ////////////////////////////////////////////
    /* Create socket for incoming connections */
    if ((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        die("socket() failed");

    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));     //Zero out structure
    echoServAddr.sin_family = AF_INET;                  //Internet address family
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY);   //Any incoming interface
    echoServAddr.sin_port = htons(echoServPort);        //Local port

    /* Bind to the local address */
    if (bind(servSock, (struct sockaddr *)&echoServAddr, 
		sizeof(echoServAddr)) < 0)
        die("bind() failed");

    /* Mark the socket so it will listen for incoming connections */
    if (listen(servSock, MAXPENDING) < 0)
        die("listen() failed");

    for (;;) /* Run forever */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

        /* Wait for a client to connect */
        if ((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, 
                               &clntLen)) < 0)
            die("accept() failed");

        /* clntSock is connected to a client! */
        
        /*
        fprintf(stderr, "\nconnection started from: %s\n", 
		inet_ntoa(echoClntAddr.sin_addr));
        */

        HandleTCPClient(clntSock, mdbSock, &echoClntAddr, web_root);
        close(clntSock);        //close the client socket after the client disconnects
                                //but leave the mdbServer socket alone, because
                                //it is still needed for future connections

        /*       
	fprintf(stderr, "connection terminated from: %s\n", 
		inet_ntoa(echoClntAddr.sin_addr));
        */
    }
    /* NOT REACHED */
}


void HandleTCPClient(int clntSocket, int mdbSocket, struct sockaddr_in *clntAddr, char *web_root_init) {

    FILE *input; 
    if ((input =  fdopen(clntSocket, "r")) == NULL) { 
        die("fdopen failed");
    }

    /* get first line of the request */
    char requestLine[IOBUFSIZE];
    char outBuf[IOBUFSIZE];
    int outLen;
    fgets(requestLine, IOBUFSIZE, input);
    
    char web_root[500];
    strcpy(web_root, web_root_init);                            
    char *clnt_IP = inet_ntoa(clntAddr->sin_addr);              //ip addrress of the client
    char *token_separators = "\t \r\n";                         //tab, space, new line tokens
    char *method = strtok(requestLine, token_separators);       //method type (error check for GET only)
    char *requestURI = strtok(NULL, token_separators);          //original request file path
    char *end;                                                  //last section of URI request, used for error checking 
    char *httpVersion = strtok(NULL, token_separators);         //httpVersion of the request
    char *status;                                               //server response status
    int rqType = 1;                                             //type of request 
                                                                //1 for static, 2 for mdb-lookup w/ table, 0 for mdb-lookup w/ form only

    /* check request for errors*/
    if(method == NULL || strcmp(method, "GET") != 0) {    //checks for NULL string or  GET method
        status = "501 Not Implemented";
        logRequest(clnt_IP, method, requestURI, httpVersion, status); 
        errResponse(clntSocket, status);
        fclose(input);
        return; 
    }

    if(requestURI != NULL) {    //makes sure request URI isnt null

        //check for mdb-lookup URI request
        if(strcmp(requestURI, "/mdb-lookup") == 0) {
                const char *htmlForm = 
                "<html><body>\n<h1>mdb-lookup</h1>\n"
                "<p>\n"
                "<form method=GET action=/mdb-lookup>\n"
                "lookup: <input type=text name=key>\n"
                "<input type=submit>\n"
                "</form>\n"
                "<p></body></html>\n";
        
                status = "200 OK";
                outLen = sprintf(outBuf, "HTTP/1.0 %s\r\n\r\n%s", status, htmlForm);
                rqType = 0;     //change rqType to 0
        }        
        else if(strncmp(requestURI, "/mdb-lookup?key", 15) == 0) {
                const char *htmlForm = 
                "<html><body>\n<h1>mdb-lookup</h1>\n"
                "<p>\n"
                "<form method=GET action=/mdb-lookup>\n"
                "lookup: <input type=text name=key>\n"
                "<input type=submit>\n"
                "</form>\n" "<p>\n<p>"
                "<table border>\n";
         
                status = "200 OK";
                outLen = sprintf(outBuf, "HTTP/1.0 %s\r\n\r\n%s", status, htmlForm);
                rqType = 2;     //change rqType to 2

        }
        else {

                strcat(web_root, requestURI);                  //append requestURI to the web_root
                char startURI = requestURI[0];
                if('/' != startURI) {                           //checks requestURI starts with '/'
                        status = "400 Bad Request";
                        logRequest(clnt_IP, method, requestURI, httpVersion, status);
                        errResponse(clntSocket, status);
                        fclose(input);
                        return;

                }
                if(strstr(web_root, "/../") != NULL) {        //checks if "/../" is present in the string
                        status = "400 Bad Request";
                        logRequest(clnt_IP, method, requestURI, httpVersion, status);
                        errResponse(clntSocket, status);
                        fclose(input);
                        return;

                }
                end = &requestURI[strlen(requestURI) - 3]; 
                if(strcmp(end, "/..") == 0) { 
                        status = "400 Bad Request";
                        logRequest(clnt_IP, method, requestURI, httpVersion, status); 
                        errResponse(clntSocket, status);
                        fclose(input);  
                        return;

                }
                else if(requestURI[strlen(requestURI)-1] == '/') {      //if the request ends with a /
                                                                //dont set a status yet, just change modded uri
                        strcat(web_root, "index.html");         //append index.html to end of file pat
                }
        }
    }
    else {      //if requestURI is null
        strcat(web_root, "/index.html");
    }


    //check for correct HTTP protocol
    if(httpVersion == NULL || ((strcmp(httpVersion, "HTTP/1.0") != 0) && (strcmp(httpVersion, "HTTP/1.1") != 0))) {           
        status = "501 Not Implemented";
        logRequest(clnt_IP, method, requestURI, httpVersion, status);
        errResponse(clntSocket, status);
        fclose(input);
        return;
    }


    /* build response */
    FILE *outFile = fopen(web_root, "r+");      //open file
    if(rqType == 1 && outFile == NULL) {        //if we are looking for a static file and it is NULL, 
        status = "404 Not Found";               //then we havent found it :( 
        logRequest(clnt_IP, method, requestURI, httpVersion, status);
        errResponse(clntSocket, status);
        fclose(input); 
        return;
    }


    //if weve made it this far, we can set status to 200 OK
    //and log the 200 OK request to stdout
    status = "200 OK";          //finally!
    logRequest(clnt_IP, method, requestURI, httpVersion, status);       
    fflush(stdout);

    //build responses
    if(rqType == 0) {           //weve hit a plain mdb-lookup prompt request, so outBuf has a header & body
        if(send(clntSocket, outBuf, outLen, 0) != outLen) {             //send header and mdb-lookup form
            fclose(input);
            die("send() has failed");
        }
        //thats it, rqType == 0 just needs the form to be sent
    }

    else if (rqType == 1) {                      //serving a static page, havent constructed header yet
        outLen = sprintf(outBuf, "HTTP/1.0 %s\r\n\r\n", status);        //construct header
        if(send(clntSocket, outBuf, outLen, 0) != outLen) {             //send header
            fclose(input);
            die("send() has failed");
        }
        memset(outBuf, '\0', strlen(outBuf));   //reset outBuf
        
        //now get and send body text
        //read into outBuf:
        while((outLen = fread(outBuf, 1, sizeof(outBuf), outFile)) > 0) { 
                if(ferror(outFile)) {
                        perror ("send() failed"); 
                        fclose(outFile);
                        fclose(input);
                        return;
                }
                //send outBuf to client:
                if(send(clntSocket, outBuf, outLen, 0) != outLen) {
                        fclose(outFile);
                        fclose(input);
                        return;
                }
        }
        fclose(outFile);    
    }

    //mdb lookup table
    else if(rqType == 2) {      //mdb-lookup request with search key
        char mdbBuf[IOBUFSIZE];
        char mdbBuf_out[IOBUFSIZE];
        char *lookupKey = strstr(requestURI, "=") + 1;
        
        //printf("%s\n", lookupKey);
        sprintf(mdbBuf, "%s\n", lookupKey);     //read the lookup key into mdbBuf
        int mdbBufLen = strlen(mdbBuf);         //so it can be sent to mdbServer

        //send header and mdb-lookup form to browser
        if(send(clntSocket, outBuf, outLen, 0) != outLen) {
                 fclose(input);
                 die("send() #1 to clntSocket failed");
        }

        //send lookupKey to mdbLookup server
        if(send(mdbSocket, mdbBuf, mdbBufLen, 0) != mdbBufLen) { 
                fclose(input);
                die("send() #2 to mdbsocket failed");
        }
        memset(mdbBuf, '\0', sizeof(mdbBuf));   //reset mdbBuf so we can read results in
        char *getRes;
        
        FILE *mdbSocketFile;
        if((mdbSocketFile = fdopen(mdbSocket, "r")) == NULL) {
                fclose(input);
                die("send() #3 to mdbsocket failed");
        }

        //send mdbLookup table to browser
        int yellowOn = 1;                       //turn yellow background on for first iteration
        //recieve mdbLookup response from mdbLookup server
        while((getRes = fgets(mdbBuf, sizeof(mdbBuf), mdbSocketFile)) && 
                strcmp(getRes, "\n") != 0) {    //ignore newlines 
                if(yellowOn) {                  //print with yellow background
                        sprintf(mdbBuf_out, "<tr><td %s>%s\n", "bgcolor=yellow", mdbBuf);
                        yellowOn = 0;           //turn yellow background off for next iteration
                }
                else{
                        sprintf(mdbBuf_out, "<tr><td>%s\n", mdbBuf);
                        yellowOn = 1;           //turn yellow background on for next iteration
                }
                if(send(clntSocket, mdbBuf_out, strlen(mdbBuf_out), 0) != strlen(mdbBuf_out)) {
                        fclose(mdbSocketFile);
                        fclose(input);
                        die("send() to clntSocket failed");
                }
                memset(mdbBuf_out, '\0', sizeof(mdbBuf_out));
        }
        
        //send table footer
        sprintf(mdbBuf_out, "</table></p></body></html>\n");
        if(send(clntSocket, mdbBuf_out, strlen(mdbBuf_out), 0) != strlen(mdbBuf_out)) {
                fclose(mdbSocketFile);
                fclose(input);
                die("send() to clntSocket failed at footer");
        }
        if(ferror(mdbSocketFile)) { 
                fclose(mdbSocketFile);
                fclose(input);
                die("send() to clntSocket failed at fread");
        }
       // fclose(mdbSocketFile);
    }
    //fclose(mdbSocketFile);
    fclose(input);
}

