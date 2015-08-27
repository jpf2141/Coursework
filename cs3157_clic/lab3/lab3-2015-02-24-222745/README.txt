
  - Josh Fram
  - jpf2141
  - Lab3

  

Part 1 and Part 2 work exactly as specified in the lab instructions


Part 1 Valgrind Output:

==14560== Memcheck, a memory error detector
==14560== Copyright (C) 2002-2011, and GNU GPL'd, by Julian Seward et al.
==14560== Using Valgrind-3.7.0 and LibVEX; rerun with -h for copyright info
==14560== Command: ./mylist-test
==14560== 
testing addFront(): 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 
testing flipSignDouble(): -9.0 -8.0 -7.0 -6.0 -5.0 -4.0 -3.0 -2.0 -1.0 
testing flipSignDouble() again: 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 
testing findNode(): OK
popped 9.0, the rest is: [ 8.0 7.0 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 8.0, the rest is: [ 7.0 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 7.0, the rest is: [ 6.0 5.0 4.0 3.0 2.0 1.0 ]
popped 6.0, the rest is: [ 5.0 4.0 3.0 2.0 1.0 ]
popped 5.0, the rest is: [ 4.0 3.0 2.0 1.0 ]
popped 4.0, the rest is: [ 3.0 2.0 1.0 ]
popped 3.0, the rest is: [ 2.0 1.0 ]
popped 2.0, the rest is: [ 1.0 ]
popped 1.0, the rest is: [ ]
testing addAfter(): 1.0 2.0 3.0 4.0 5.0 6.0 7.0 8.0 9.0 
popped 1.0, and reversed the rest: [ 9.0 8.0 7.0 6.0 5.0 4.0 3.0 2.0 ]
popped 9.0, and reversed the rest: [ 2.0 3.0 4.0 5.0 6.0 7.0 8.0 ]
popped 2.0, and reversed the rest: [ 8.0 7.0 6.0 5.0 4.0 3.0 ]
popped 8.0, and reversed the rest: [ 3.0 4.0 5.0 6.0 7.0 ]
popped 3.0, and reversed the rest: [ 7.0 6.0 5.0 4.0 ]
popped 7.0, and reversed the rest: [ 4.0 5.0 6.0 ]
popped 4.0, and reversed the rest: [ 6.0 5.0 ]
popped 6.0, and reversed the rest: [ 5.0 ]
popped 5.0, and reversed the rest: [ ]
==14560== 
==14560== HEAP SUMMARY:
==14560==     in use at exit: 0 bytes in 0 blocks
==14560==   total heap usage: 18 allocs, 18 frees, 288 bytes allocated
==14560== 
==14560== All heap blocks were freed -- no leaks are possible
==14560== 
==14560== For counts of detected and suppressed errors, rerun with: -v
==14560== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 2 from 2)



Part2 Valgrind Output: 


==14629== Memcheck, a memory error detector
==14629== Copyright (C) 2002-2011, and GNU GPL'd, by Julian Seward et al.
==14629== Using Valgrind-3.7.0 and LibVEX; rerun with -h for copyright info
==14629== Command: ./revecho hello world dude
==14629== 
dude
world
hello

dude found

==14629== 
==14629== HEAP SUMMARY:
==14629==     in use at exit: 0 bytes in 0 blocks
==14629==   total heap usage: 3 allocs, 3 frees, 48 bytes allocated
==14629== 
==14629== All heap blocks were freed -- no leaks are possible
==14629== 
==14629== For counts of detected and suppressed errors, rerun with: -v
==14629== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 2 from 2)
