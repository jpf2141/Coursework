

/*mdb-lookup-server.c*/

#include <stdio.h>      /* for printf() and fprintf() */
#include <sys/socket.h> /* for socket(), bind(), and connect() */
#include <arpa/inet.h>  /* for sockaddr_in and inet_ntoa() */
#include <stdlib.h>     /* for atoi() and exit() */
#include <string.h>     /* for memset() */
#include <unistd.h>     /* for close() */
#include "mdb.h"
#include "mylist.h"
#include <signal.h>


#define MAXPENDING 5    /* Maximum outstanding connection requests */
#define KeyMax 5

void die(char *errorMessage);
void DieWithError(char *errorMessage);  /* Error handling function */
void HandleTCPClient(int clntSocket);   /* TCP client handling function */
int loadmdb(FILE *fp, struct List *dest);
void freemdb(struct List *list);


int main(int argc, char *argv[]) 
{
     // ignore SIGPIPE so that we don’t terminate when we call
     // send() on a disconnected socket.
     if (signal(SIGPIPE, SIG_IGN) == SIG_ERR)
         die("signal() failed");


    int servSock;                    /* Socket descriptor for server */
    int clntSock;                    /* Socket descriptor for client */
    struct sockaddr_in echoServAddr; /* Local address */
    struct sockaddr_in echoClntAddr; /* Client address */
    unsigned short echoServPort;     /* Server port */
    unsigned int clntLen;            /* Length of client address data structure */

    if (argc != 3)     /* Test for correct number of arguments */
    {
        fprintf(stderr, "Usage:  %s <Database Name> <Server Port>\n", argv[0]);
        exit(1);
    }

    echoServPort = atoi(argv[2]);  /* Second arg:  local port */

    /* Create socket for incoming connections */
    if ((servSock = socket(PF_INET, SOCK_STREAM, IPPROTO_TCP)) < 0)
        DieWithError("socket() failed");
      
    /* Construct local address structure */
    memset(&echoServAddr, 0, sizeof(echoServAddr));   /* Zero out structure */
    echoServAddr.sin_family = AF_INET;                /* Internet address family */
    echoServAddr.sin_addr.s_addr = htonl(INADDR_ANY); /* Any incoming interface */
    echoServAddr.sin_port = htons(echoServPort);      /* Local port */

    /* Bind to the local address */
    if (bind(servSock, (struct sockaddr *) &echoServAddr, sizeof(echoServAddr)) < 0)
        DieWithError("bind() failed");

    /* Mark the socket so it will listen for incoming connections */
    if (listen(servSock, MAXPENDING) < 0)
        DieWithError("listen() failed");

    for (;;) /* Run forever */
    {
        /* Set the size of the in-out parameter */
        clntLen = sizeof(echoClntAddr);

        /* Wait for a client to connect */
        if ((clntSock = accept(servSock, (struct sockaddr *) &echoClntAddr, 
                               &clntLen)) < 0)
            DieWithError("accept() failed");

        /* clntSock is connected to a client! */

        printf("Handling client %s\n", inet_ntoa(echoClntAddr.sin_addr));
        mdbLookup(clntSock, argv[1]);
        //HandleTCPClient(clntSock);
        
        printf("Terminated Connection with client %s\n\n", inet_ntoa(echoClntAddr.sin_addr));
    }
    /* NOT REACHED */
}




int mdbLookup(int clntSock, char *dbName)
{
    /*
     * open the database file specified in the command line
     */


    char *filename = dbName;
    FILE *fp = fopen(filename, "rb");
    if (fp == NULL) 
        DieWithError(filename);

    /*
     * read all records into memory
     */

    struct List list;
    initList(&list);

    int loaded = loadmdb(fp, &list);
    if (loaded < 0)
        DieWithError("loadmdb");
    
    fclose(fp);

    /*
     * lookup loop
     */

    char line[1000];
    char outArr[60];
    char key[KeyMax + 1];
    int charCount = 0;

    //printf("lookup: ");
    //fflush(stdout);
    
    FILE *input = fdopen(clntSock, "r"); 
    while (fgets(line, sizeof(line), input) != NULL) {

        // must null-terminate the string manually after strncpy().
        strncpy(key, line, sizeof(key) - 1);
        key[sizeof(key) - 1] = '\0';

        // if newline is there, remove it.
        size_t last = strlen(key) - 1;
        if (key[last] == '\n')
            key[last] = '\0';

        // traverse the list, printing out the matching records
        struct Node *node = list.head;
        int recNo = 1;
        while (node) {
            struct MdbRec *rec = (struct MdbRec *)node->data;
            if (strstr(rec->name, key) || strstr(rec->msg, key)) {
                //printf("%4d: {%s} said {%s}\n", recNo, rec->name, rec->msg);
                if((charCount = sprintf(outArr, "%4d: {%s} said {%s}\n", recNo, rec->name, rec->msg)) < 0) {
                        DieWithError("sprintf() failed");
                }
                if (send(clntSock, outArr, charCount, 0) != charCount) {
                        DieWithError("send() failed");
                }
                //memset(&outArr, '\0', sizeof(outArr));   /* Zero out structure */
                //charCount = 0;  //reset charCount to 0
            }

            node = node->next;
            recNo++;
        }

        char newLine[1];
        sprintf(newLine, "\n");
        if (send(clntSock, newLine, 1, 0) != 1) {
                DieWithError("newline send() failed");
        }
    }
    // see if fgets() produced error
    if (ferror(stdin)) 
        DieWithError("stdin");

    /*
     * clean up and quit
     */
    fclose(input);
    freemdb(&list);
    return 0;
}
