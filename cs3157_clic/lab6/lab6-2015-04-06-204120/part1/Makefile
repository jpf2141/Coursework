
#
# Makefile for lab 6, part 1
#

CC  = gcc
CXX = g++

INCLUDES = -I../../lab3/part1
CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = -g -L../../lab3/part1
LDLIBS = -lmylist


mdb-lookup-server: mdb-lookup-server.o mdb.o DieWithError.o

# header dependency
mdb-lookup-server.o: mdb.h mdb-lookup-server.c
mdb.o: mdb.h mdb.c
DieWithError.o: DieWithError.c


.PHONY: clean
clean:
	rm -f *.o *~ a.out core mdb-lookup-server

.PHONY: valgrind
valgrind: 
	valgrind --leak-check=full --show-reachable=yes ./mdb-lookup-server my-mdb 1111

.PHONY: all
all: clean default

