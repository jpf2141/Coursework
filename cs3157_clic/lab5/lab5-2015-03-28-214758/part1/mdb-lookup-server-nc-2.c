
/*
 * mdb-lookup-server-nc-1.c
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>

static void die(const char *s)
{
    perror(s);
    exit(1);
}

int main(int argc, char **argv)
{
        
        char input[1000];
        int testInput;
        printf("%s", "port number: ");
        fflush(stdout);

        while(fgets(input, sizeof(input), stdin) != NULL) {

                pid_t pid;      //declare pid
                if((testInput = atoi(input) != 0)) { 
                        pid = fork();
                }
                else {  //no input, dont fork, just check for terminated processes
                        pid = (pid_t) 1;
                }

                if (pid < 0) {
	                die("fork failed");
                } else if (pid == 0) {
                        //child process    

                        fprintf(stderr, "[pid=%d] ", (int)getpid());
	                fprintf(stderr, "mdb-lookup-server started on port %s\n", input);
                        execl("./mdb-lookup-server-nc.sh", "mdb-lookup-server-nc.sh", 
		                input , (char *)0);
	                die("execl failed");
                } else {
	                // parent process
	                sleep(1);
                        while((pid = waitpid((pid_t) -1 , NULL, WNOHANG)) > 0) {

	                        fprintf(stderr, "[pid=%d] ", (int)pid);
	                        fprintf(stderr, "mdb-lookup-server terminated\n");
                
                        }
                         
                        printf("%s", "port number: ");
                }       
        }
        return 0;
}

