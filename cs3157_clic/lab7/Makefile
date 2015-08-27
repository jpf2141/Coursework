
#
# Makefile for lab 6, part 1
#

CC  = gcc
CXX = g++

INCLUDES = 
CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = 
LDLIBS = 


http-server: http-server.o 

# header dependency
http-server.o: http-server.c


.PHONY: clean
clean:
	rm -f *.o *~ a.out core http-server
.PHONY: valgrind
valgrind: 
	valgrind --leak-check=full --track-origins=yes --show-reachable=yes ./http-server 1112 /home/jpf2141/html localhost 9999

.PHONY: all
all: clean default

