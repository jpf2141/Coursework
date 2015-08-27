
  - Josh Fram
  - jpf2141
  - lab2


Part 1 Description: My code works exactly as specified in the lab.
Part 2 Description: My code works exactly as specified in the lab.
 
Valgrind output for Part1:
==13822== Memcheck, a memory error detector
==13822== Copyright (C) 2002-2011, and GNU GPL'd, by Julian Seward et al.
==13822== Using Valgrind-3.7.0 and LibVEX; rerun with -h for copyright info
==13822== Command: ./isort
==13822== 
Please enter the array size: 
Original Array:
73
95
50
90
49
Array in ascending order:
49
50
73
90
95
Array in descending order:
95
90
73
50
49
==13822== 
==13822== HEAP SUMMARY:
==13822==     in use at exit: 0 bytes in 0 blocks
==13822==   total heap usage: 3 allocs, 3 frees, 60 bytes allocated
==13822== 
==13822== All heap blocks were freed -- no leaks are possible
==13822== 
==13822== For counts of detected and suppressed errors, rerun with: -v
==13822== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 2 from 2)


Valgrind output for Part2:
==14255== Memcheck, a memory error detector
==14255== Copyright (C) 2002-2011, and GNU GPL'd, by Julian Seward et al.
==14255== Using Valgrind-3.7.0 and LibVEX; rerun with -h for copyright info
==14255== Command: ./twecho hello world dude
==14255== 
hello HELLO
world WORLD
dude DUDE
==14255== 
==14255== HEAP SUMMARY:
==14255==     in use at exit: 0 bytes in 0 blocks
==14255==   total heap usage: 5 allocs, 5 frees, 70 bytes allocated
==14255== 
==14255== All heap blocks were freed -- no leaks are possible
==14255== 
==14255== For counts of detected and suppressed errors, rerun with: -v
==14255== ERROR SUMMARY: 0 errors from 0 contexts (suppressed: 2 from 2)
