#include "mylist.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>




void removeAllNodes(struct List *list) {
        while(popFront(list));
}

void reverseList(struct List *list) {
        struct Node *prv = NULL;
        struct Node *cur = list->head;
        struct Node *nxt;
                    
        while (cur != NULL) {
                nxt = cur->next;    //make the next pointer point to the next node
                cur->next = prv;
                prv = cur;
                cur = nxt;
        }
        list->head = prv;
}




struct Node *addAfter(struct List *list,
        struct Node *prevNode, void *data) {
        
        struct Node *node=malloc(sizeof(struct Node));   //declare new node
        if (node == NULL)
        {
                perror("malloc returned NULL");
                exit(1);
        }
        if(prevNode == NULL) {  //if there is no previous node, point the
                list->head = node;  //list head at the node we just created
        }
        else {
                prevNode->next = node;  //otherwise, point the previous node's
                                        //next pointer at the node we just made
        }
        node->data = data;     //point the new node's data pointer to the address
                                //where the data we just passed in is
        node->next = NULL;  //set current node's next pointer to null
                                                                                                        
        if(node->data != data) {
                return NULL;
        }
        else {
            return node;
        }
}


void *popFront(struct List *list) {
        if(isEmptyList(list)) {
                return NULL;
        }

        struct Node *node = list->head;     //node pointer to the current head of the list
        void *headDP = node->data;  //save the address where the head's data is stored at

        struct Node *node2 = node->next;    //2nd node pointer to the 2nd node in the list
        list->head = node2;         //point the list head to the address of the 2nd node

        free(node);         //free the memory allocated for the head node
        return headDP;  //return the 'data' pointer that was stored in the node.
}


struct Node *findNode(struct List *list, const void *dataSought,
                int (*compar)(const void *, const void *)) {

        struct Node *node = list->head;     //first, assign *node to point
                                        //at the head of the list
        while(node != NULL) {
                void *dataFound = node->data;
                int match = compar(dataSought, dataFound);
                if(match == 0) {
                        return node;
                }
                else {
                        node = node->next;
                } 
        }
        return NULL;
}


int compareDouble(const void *data1, const void *data2) {
        double dataSought = *(double *)data1;   //cast data1 to pointer to a double, dereference
        double dataFound = *(double *)data2;    //cast data2 to pointer to a double, dereference
        if(dataSought == dataFound) {
                return 0;
        }
        else {
                return 1;
        }
}


void flipSignDouble(void *data) {
        double nodeData = *(double *)data;  //cast data to type double, then dereference it
        double flippedData = nodeData*-1;   //flip data
    
        *(double *)data = flippedData;      //assign flipped data to be pointed at by *data
                                        //data must be cast as a pointer to a double for
                                        //this operation
}


struct Node *addFront(struct List *list, void *data) {
        
        struct Node *node = malloc(sizeof(struct Node));   //declare new node
                if (node == NULL) {
                        perror("malloc returned NULL");
                        exit(1);
                }
        //before we do anything else, we declare the current node's
        //next pointer to be the current head's address:
        node->next = list->head;
        // then we allocate space for the new node to be plopped
        list->head = node;      //point the list's head to this new node's address
                                //because we are adding at the front
        node->data = data;      //point the node's data        
        return list->head;      //returns the pointer to the head of the list (first node)
}

void traverseList(struct List *list, void (*f)(void *)) {
        
        struct Node *node = list->head;
        while (node != NULL) {
                void *num = node->data;
                f(num);
                node = node->next;
        }
}
