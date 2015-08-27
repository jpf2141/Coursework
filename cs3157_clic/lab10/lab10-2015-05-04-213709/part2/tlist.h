

#include <string>
#include <list>     //we use the "list" container from the standard C++ library.
#include <algorithm>
#include <iostream>

using namespace std;

//class template definition
template <typename T>
class TList;

//template friend function definition
template <typename T>
TList<T> operator+(const TList<T>& list1, const TList<T>& list2)
{
    TList<T> t(list1);
    t += list2;
    return t;
}

//template friend function definition
template <typename T>
ostream& operator<<(ostream& os, const TList<T>& rhs)
{
    os << "{ ";
    for (auto i = rhs.list.list::begin(); i != rhs.list.list::end(); ++i)
        os << *i << " ";
    os << "}";
    return os;
}


template <typename T>
class TList
{
public:
    
    //compiler basic 4 ok
    
    
    //member functions
    int isEmpty() const {
        return this->list.list::empty();
    }
    
    int size() const {
        return this->list.list::size();
    }
    
    void addFront(const T& t) {
        this->list.list::push_front(t);
    }
    
    T popFront() {
        T t(this->list.list::front());
        this->list.list::pop_front();
        return t;
    }
    
    void reverse() {
        this->list.list::reverse();
    }
    
    //operators
    TList<T>& operator+=(const TList<T>& rhs) {
        for (auto i = rhs.list.list::begin(); i != rhs.list.list::end(); ++i)
            this->list.list::push_back(*i);
        return *this;
    }
    
    //defined above
    friend ostream& operator<< <T>(ostream& os, const TList<T>& rhs);
    
    //defined above
    friend TList<T> operator+ <T>(const TList<T>& lhs, const TList<T>& rhs);
    
    T& operator[](int idx) {
        auto iterator = this->list.list::begin();
        for (int i = 0; i < idx; i++)
            ++iterator;
        return *iterator;
    }
    
    const T& operator[](int idx) const {
        return ((TList&)*this)[idx];
    }
    
private:
    list<T> list;

};









