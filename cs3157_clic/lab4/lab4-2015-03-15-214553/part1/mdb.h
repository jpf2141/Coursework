/*
 * mdb.h
 */

#ifndef _MDB_H_
#define _MDB_H_

#include <stdio.h>
#include <stdlib.h>
#include "mylist.h"
#include <string.h>

struct MdbRec {
    char name[16];
    char  msg[24];
};

void freeBufs(struct MdbRec **heapPtrArr, int size);
void parseFile(FILE *f, char *filename);
int loadmdb(FILE *fp, struct List *dest);
void freemdb(struct List *list);

#endif /* _MDB_H_ */
