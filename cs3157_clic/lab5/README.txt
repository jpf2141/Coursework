Josh Fram
jpf2141
Lab 5

My code works exactly as specified in the lab instructions.
  I use sleep(1) to ensure that the terminated children print before the
  port number prompt is printed. 


Part B) 
Process Tree:



PPID   PID  PGID   SID TTY      TPGID STAT   UID   TIME COMMAND
 1011 21201 21201 21201 ?           -1 Ss       0   0:00  \_ sshd: jpf2141 [priv]
21201  5501 21201 21201 ?           -1 S    16025   0:00  |   \_ sshd:jpf2141@pts/32
 5501  5661  5661  5661 pts/32   32077 Ss   16025   0:00  |       \_ -bash
 5661 32077 32077  5661 pts/32   32077 S+   16025   0:00  |           \_./mdb-lookup-server-nc-1 1111
32077 32079 32077  5661 pts/32   32077 S+   16025   0:00  |               \_/bin/sh ./mdb-lookup-server-nc.sh 1111
32079 32126 32077  5661 pts/32   32077 S+   16025   0:00  |                  \_ cat mypipe-32079
32079 32127 32077  5661 pts/32   32077 S+   16025   0:00  |                  \_ nc -l 1111
32079 32128 32077  5661 pts/32   32077 S+   16025   0:00  |                  \_ /bin/sh /home/jae/cs3157-pub/bin/mdb-lookup-cs3157
32128 32132 32077  5661 pts/32   32077 S+   16025   0:00  |                      \_ /home/jae/cs3157-pub/bin/mdb-lookup /home/jae/cs3157-pub/bin/mdb-cs3157



Shell Scripts:

./mdb-lookup-server-nc.sh




