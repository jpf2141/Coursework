//
//  strlist.cpp
//  lab10
//

#include "strlist.h"
#include "mystring.h"
#include "mylist.h"


static void deleteString(void *s) {
     delete (MyString *)s;
}

static void appendList(struct List *target, struct List *source) {
    reverseList(target);    //reverse so we can add to the front
    //without messing up the ordering
    
    struct Node *sourceNode = source->head;


    while(sourceNode) {
        MyString *targetString = new MyString(*(MyString *)sourceNode->data);
        ::addFront(target, targetString);
        sourceNode = sourceNode->next;
    }
    
    reverseList(target);    //
}

// default constructor

StrList::StrList()
{
    initList(&this->list);
}

// destructor
StrList::~StrList()
{
    traverseList(&this->list, &deleteString);
    removeAllNodes(&this->list);
}

// copy constructor

StrList::StrList(const StrList& myList)
{
    initList(&this->list);     //initialize target list
    
    appendList(&this->list, (List *)&myList.list);
    
}

// assignment operator
StrList& StrList::operator=(const StrList& rhs)
{
    if (this == &rhs) {
        return *this;
    }
    
    // remove old nodes
    traverseList(&this->list, &deleteString);
    removeAllNodes(&this->list);
    
    // copy 
    appendList(&this->list, (List *)&rhs.list);
    return *this;
}

// returns the number of nodes in the list

int StrList::size() const {
    
    int size = 0;
    
    struct Node *currentNode = this->list.head;
    while (currentNode) {
        ++size;
        currentNode = currentNode->next;
    }
    return size;
}


// adds a string to the front of the list

void StrList::addFront(const MyString& str) {
    MyString *newStr = new MyString(str);
    if (::addFront(&list, newStr) == NULL)
        exit(1);
}

// Pops a string from the front of the list and returns it.
// The result of popping from an empty list is undefined.
//
//this popFront()'s from myList.list (this->list)
//then constructs a MyString object, which is returned

MyString StrList::popFront() {
    
    MyString *popped = (MyString *)::popFront(&this->list);
    MyString returnStr(*popped);
    delete popped;
    return returnStr;
}


// reverse the elements in the list

void StrList::reverse() {
    
    ::reverseList(&this->list);
    
}


// operator+=
// The result of "sl += sl" is undefined.
StrList& StrList::operator+=(const StrList& list) {
    appendList(&this->list, (List *)&list.list);
    return *this;
}


//operator+
StrList operator+(const StrList& list1, const StrList& list2) {
    StrList resultList;
    appendList(&resultList.list, (List *)&list1);
    appendList(&resultList.list, (List *)&list2);
    return resultList;
}

//operator<<
ostream& operator<<(ostream& os, const StrList& list) {
    os << "{"; //open brace
    
    struct Node *currentNode = this->list.list.head;
    while(currentNode) {
        os << *(MyString *)currentNode->data << " ";
        currentNode = currentNode->next;
    }
    os << "}";
    return os;
}

// operator[]:
// The result of accessing beyond the last element is undefined.
MyString& StrList::operator[](int idx) {
    
    struct Node *node = this->list.head;
    for (int i = 0; i < idx; i++) {
        node = node->next;
    }
    return *(MyString *)node->data;
}






















