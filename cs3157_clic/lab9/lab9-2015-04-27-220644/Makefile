CC  = g++
CXX = g++ -O0

# Comment out one of the following 2 lines to enable/disable BASIC4TRACE.
#INCLUDES = -DBASIC4TRACE
INCLUDES =

CFLAGS   = -g -Wall $(INCLUDES)
CXXFLAGS = -g -Wall $(INCLUDES)

LDFLAGS = -g
LDLIBS  =

executables = test1 test2 test3 test4 test5
objects = mystring.o test1.o test2.o test3.o test4.o test5.o

.PHONY: default
default: $(executables)

$(executables): mystring.o

$(objects): mystring.h

.PHONY: clean
clean:
	rm -f *~ a.out core $(objects) $(executables)

.PHONY: valgrind
valgrind: 
	echo test5:
	valgrind --leak-check=yes ./test5
	echo test4:
	valgrind --leak-check=yes ./test4
	echo test3:
	valgrind --leak-check=yes ./test3
	echo test2:
	valgrind --leak-check=yes ./test2
	echo test1:
	valgrind --leak-check=yes ./test1


.PHONY: all
all: clean default
