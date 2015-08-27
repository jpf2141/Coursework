

#include <string>
#include <deque>     //we use the "deque" container from the standard C++ library.
#include <algorithm>
#include <iostream>

using namespace std;

template <typename T>
class TList;


//template function definition
template <typename T>
TList<T> operator+(const TList<T>& list1, const TList<T>& list2)
{
    TList<T> t(list1);
    t += list2;
    return t;
}

//template function definition
template <typename T>
ostream& operator<<(ostream& os, const TList<T>& rhs)
{
    os << "{ ";
    for (auto i = rhs.list.deque::begin(); i != rhs.list.deque::end(); ++i)
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
        return this->list.deque::size();
    }
    
    void addFront(const T& t) {
        this->list.deque::push_front(t);
    }
    
    T popFront() {
        T t(this->list.deque::front());
        this->list.deque::pop_front();
        return t;
    }
    
    void reverse() {
        ::reverse(list.begin(), list.end());
    }
    
    //operators
    TList<T>& operator+=(const TList<T>& rhs) {
        for (auto i = rhs.list.deque::begin(); i != rhs.list.deque::end(); ++i)
            list.deque::push_back(*i);
        return *this;
    }
    
    friend ostream& operator<< <T>(ostream& os, const TList<T>& rhs);
    
    friend TList<T> (::operator+<T>)(const TList<T>& lhs, const TList<T>& rhs);
    
    T& operator[](int idx) {
        auto iter = list.deque::begin();
        for (int i = 0; i < idx; i++)
            ++iter;
        return *iter;
    }
    
    const T& operator[](int idx) const {
        return ((TList&)*this)[idx];
    }
    
private:
    deque<T> list;
    
};









