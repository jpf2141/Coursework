/*
 * strlist.h
 */

#ifndef __STRLIST_H__
#define __STRLIST_H__

/*
 Copy the mystring.h/cpp files from lab9/solutions directory.
 */

#include "mystring.h"

/*
 Note that extern "C" is required when you are linking with an
 object file or a library compiled in C from C++.
 
 Use the usual -I, -L, -l flags in your Makefile to link with
 libmylist.a in your lab3/solutions directory.  Do NOT copy over
 any of the old linked list files into lab10 directory.
 */

extern "C" {
#include "mylist.h"
}

class StrList {
    
public:
    
    // TODO: The basic 4.
    /*
     Don't worry about efficiency in this assignment.  Do what's
     the easiest.  For example, in order to append elements from
     one List to another using the C linked list API, you can
     reverse the target list, add elements using addFront, and
     then reverse it again when you're done.
     
     In fact, you'll have to implement many member functions
     rather inefficiency due to the deficiency of the old list
     API.
     */
    
    //done
    // default constructor
    StrList();
    
    //done
    // constructor
    StrList(const struct List);
    
    //done
    // destructor
    ~StrList();
    
    //done
    // copy constructor
    StrList(const StrList& list);
    
    
    //done
    // isEmpty() function
    /*
     I'm giving away this function to show you that you'll have
     to cast away the const-ness of the data member when
     necessary.
     */
    int isEmpty() const { return isEmptyList((List *)&list); }
    
    
    //done
    // TODO: size() function
    // returns the number of nodes in the list
    int size() const;
    
    
    //declared
    // TODO: addFront() function
    // adds a string to the front of the list
    /*
     Note that in order to call the global addFront() function
     (which has the same name with the member function that
     you're writing) you have to append "::" in front, as in
     "::addFront(&list, ......)".
     */
    void addFront(const MyString& str);
    
    
    //done
    // TODO: popFront() function
    // Pops a string from the front of the list and returns it.
    // The result of popping from an empty list is undefined.
    MyString popFront();
    
    
    //done
    // TODO: reverse() function
    // reverse the elements in the list
    void reverse();
    
    //done
    // TODO: operator+=
    // The result of "sl += sl" is undefined.
    StrList& operator+=(const StrList& list);
    
    //done (possible errors?)
    // TODO: operator+
    friend StrList operator+(const StrList& list1, const StrList& list2);
    
    // assignment operator
    StrList& operator=(const StrList& list);
    
    
    //done
    // TODO: operator<<
    // Prints the content of the given list in the following
    // format:
    //
    //     { one two three }
    //
    // assuming you had the three strings ("one", "two", "three")
    // in the list.
    friend ostream& operator<<(ostream& os, const StrList& list);
    
    
    // TODO: operator[]
    // This function takes O(n) time.
    // The result of accessing beyond the last element is undefined.
    MyString& operator[](int idx);
    
    // TODO: operator[] const
    // This function takes O(n) time.
    // The result of accessing beyond the last element is undefined.
    const MyString& operator[](int idx) const {
        return ((StrList&)*this)[idx];
    }
    
    
    
private:
    
    // This class contains the old C list structure as its single
    // data member.  Do NOT add any data member.
    
    struct List list;
};

#endif
