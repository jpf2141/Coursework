
#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), connect(), send(), and recv() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_addr() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include <netdb.h>      
#include <sys/types.h> 




void DieWithError(char *errorMessage);  /* Error handling function */

int main(int argc, char *argv[])
{
    char *filePath;                  /* File Path */
    int header = 0;                  /* Boolean to check if current texter is header or body */
    int lineNo = 0;
    char text[1000];
    char headerMSG[200];

    int sock;                        /* Socket descriptor */
    struct sockaddr_in echoServAddr; /* Echo server address */
    unsigned short echoServPort;     /* Echo server port */
    char *serv;                       /* server name */
    char *servIP;                    /* Server IP address (dotted quad) */
    unsigned int headerLen;          /* Length of string to echo */

    if (argc != 4)    /* Test for correct number of arguments */
    {
       fprintf(stderr, "Usage: %s <www.example.com> 80 </path/to/file.html>\n",
               argv[0]);
       exit(1);
    }

    serv = argv[1];             /* First arg: server name */
    //get server ip from server name
    struct hostent *he;
    if ((he = gethostbyname(serv)) == NULL) {
         DieWithError("gethostbyname failed");
    }
    servIP = inet_ntoa(*(struct in_addr *)he->h_addr);
    
    echoServPort = atoi(argv[2]); /* Use given port, if any */
    filePath = argv[3];
    char *title = strrchr(filePath, '/') + 1;
    if(strlen(title) < 1) {
        title = "index.html";
    }
    FILE *fileName = fopen(title, "w");

    /* Create a reliable, stream socket using TCP */
    if ((sock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");


    /* Construct the server address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));     /* Zero out structure */
    echoServAddr.sin_family      = AF_INET;             /* Internet address family */
    echoServAddr.sin_addr.s_addr = inet_addr(servIP);   /* Server IP address */
    echoServAddr.sin_port        = htons(echoServPort); /* Server port */

    /* Establish the connection to the echo server */
    if (connect(sock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("connect() failed");
   
    /*Create header text */ 
    sprintf(headerMSG, "GET %s HTTP/1.0\r\nHost: %s:%d\r\n\r\n",       //line 1 and 2, ended by \n and \n\r\n   
            filePath, serv, echoServPort);    
    headerLen = strlen(headerMSG);          /* Determine input length */

    //printf("%d", headerLen); 
    /* Send the string to the server */
    if (send(sock, headerMSG, headerLen, 0) != headerLen)
        DieWithError("send() sent a different number of bytes than expected");

    /* Wrap the socket in a FILE descriptor */ 
    FILE *response = fdopen(sock, "r");
    /* Read until the end of the Header section of the transmission */ 
    while(fgets(text, sizeof(text), response) != NULL) {
        if(lineNo == 0) {
                if(strstr(text, "200") == NULL) {
                    printf("%s", text);

                    close(sock);
                    fclose(response);
                    exit(1);
                }
                lineNo++;
        }
        else {
            if(header) {
                fprintf(fileName, "%s", text);
            }
            if((strcmp(text, "\r\n") == 0) && !header) { 
                header = 1;
            }
        }
    }

    //cleanup
    fclose(fileName);
    fclose(response);
    close(sock);
    exit(0);
}
