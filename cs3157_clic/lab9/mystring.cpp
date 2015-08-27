#include <cstring>
#include <cstdio>
#include "mystring.h"




// operator +=

MyString& MyString::operator+=(const MyString& rhs) {
    this->len += rhs.len;
    char *newStr = new char[len+1];
    
    strcpy(newStr, data);
    strcat(newStr, rhs.data);
    
    delete[] this->data;
    this->data = newStr;
    return *this;
}


// operator+

MyString operator+(const MyString& s1, const MyString& s2)
{
#ifdef BASIC4TRACE
    fprintf(stderr,
            "BASIC4TRACE: op+(const MyString&, const MyString&)\n");
#endif
    
    MyString temp(s1);
    
    temp += s2;
    
    return temp;
}


// operator <

bool operator<(const MyString& lhs, const MyString& rhs) {
    char *leftData = lhs.data;  //get left side data
    char *rightData = rhs.data; //get right side data
    
    //strcmp the strings
    int less = strcmp(leftData, rightData);     //if <0, left is less than right
    if(less < 0) {
        return true;
    }
    else return false; //if they are equal or right is bigger than left
}

//operator >

bool operator>(const MyString& lhs, const MyString& rhs) {
    return rhs < lhs;   //return the result of rhs < lhs, using < operator
}

//operator ==

bool operator==(const MyString& lhs, const MyString& rhs) {
    if((!(lhs < rhs)) && (!(rhs < lhs))) {  //if lhs is not less than rhs,
        return true;                    //and rhs is not less than lhs
    }                                   //then they are equal, unless < or >
    else return false;                  //operators are broken???
}

//operator !=

bool operator!=(const MyString& lhs, const MyString& rhs) {
    if(lhs == rhs) {
        return false;
    }
    else return true;
}

//operator <=

bool operator<=(const MyString& lhs, const MyString& rhs) {
    if((lhs < rhs) || (lhs == rhs)) {
        return true;
    }
    else return false;
}

//operator >=

bool operator>=(const MyString& lhs, const MyString& rhs) {
    if((lhs > rhs) || (lhs == rhs)) {
        return true;
    }
    else return false;
}





// default constructor
MyString::MyString()
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString()\n", this);
#endif
    
    data = new char[1];
    data[0] = '\0';
    
    len = 0;
}

// constructor

MyString::MyString(const char* p)
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString(const char *)\n", this);
#endif
    
    if (p) {
        len = strlen(p);
        data = new char[len+1];
        strcpy(data, p);
    } else {
        data = new char[1];
        data[0] = '\0';
        len = 0;
    }
}

// destructor

MyString::~MyString()
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->~MyString()\n", this);
#endif
    
    delete[] data;
}

// copy constructor

MyString::MyString(const MyString& s)
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->MyString(const MyString&)\n", this);
#endif
    
    len = s.len;
    
    data = new char[len+1];
    strcpy(data, s.data);
}




// copy assignment

MyString& MyString::operator=(const MyString& rhs)
{
#ifdef BASIC4TRACE
    fprintf(stderr, "BASIC4TRACE: (%p)->op=(const MyString&)\n", this);
#endif
    
    if (this == &rhs) {
        return *this;
    }
    
    // first, deallocate memory that 'this' used to hold
    
    delete[] data;
    
    // now copy from rhs
    
    len = rhs.len;
    
    data = new char[len+1];
    strcpy(data, rhs.data);
    
    return *this;
}



// put-to operator

ostream& operator<<(ostream& os, const MyString& s)
{
    os << s.data;
    return os;
}

// get-from operator

istream& operator>>(istream& is, MyString& s)
{
    // this is kinda cheating, but this is just to illustrate how this
    // function can work.
    
    string temp;
    is >> temp;
    
    delete[] s.data;
    
    s.len = strlen(temp.c_str());
    s.data = new char[s.len+1];
    strcpy(s.data, temp.c_str());
    
    return is;
}

// operator[] - in real life this function should be declared inline

char& MyString::operator[](int i)
{
    return data[i];
}

// operator[] const - in real life this should be inline

const char& MyString::operator[](int i) const
{
    // illustration of casting away constness
    return ((MyString&)*this)[i];
}


