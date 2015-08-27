
/*
 * mdb.h
 */

#ifndef _MDB_H_
#define _MDB_H_

struct MdbRec {
    char name[16];
    char  msg[24];
};



void DieWithError(char *errorMessage);
int mdbLookup(int clntSock, char *dbName);
#endif /* _MDB_H_ */

