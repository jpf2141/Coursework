

Josh Fram
jpf2141
Lab 9

This lab works exactly as specified in the lab instructions. There is an
option in the makefile to run all 5 test drivers with valgrind; simply
execute "make valgrind"






Part 1:
a)


BASIC4TRACE: (0x7fffd9f0f940)->MyString(const char *)          	13, constructor, x
BASIC4TRACE: (0x7fffd9f0f950)->MyString(const char *)          	14, constructor, y
BASIC4TRACE: (0x7fffd9f0f980)->MyString(const MyString&)       	16, copy constructor, a
BASIC4TRACE: (0x7fffd9f0f970)->MyString(const MyString&)       	16, copyconstructor, b
BASIC4TRACE: (0x7fffd9f0f8f0)->MyString(const char *)          	7, constructor, t
BASIC4TRACE: op+(const MyString&, const MyString&)         	entering operator +
BASIC4TRACE: (0x7fffd9f0f8a0)->MyString()              		8, default constructor, temp (holds a + t)
BASIC4TRACE: (0x7fffd9f0f900)->MyString(const MyString&)       	8, copy constructor, u1 from return temp 
BASIC4TRACE: (0x7fffd9f0f8a0)->~MyString()             		8, destructor, temp
BASIC4TRACE: op+(const MyString&, const MyString&)         	entering operator+
BASIC4TRACE: (0x7fffd9f0f8a0)->MyString()              		8,default constructor, temp (holds temp + b)
BASIC4TRACE: (0x7fffd9f0f910)->MyString(const MyString&)        8, copy constructor, u2 from return temp (in operator+)
BASIC4TRACE: (0x7fffd9f0f8a0)->~MyString()             		8, destructor, temp
BASIC4TRACE: (0x7fffd9f0f990)->MyString(const MyString&)        8, copy constructor, u3 from return a + t + b
BASIC4TRACE: (0x7fffd9f0f910)->~MyString()             		8, destructor, u2
BASIC4TRACE: (0x7fffd9f0f900)->~MyString()             		8, destructor, u1
BASIC4TRACE: (0x7fffd9f0f8f0)->~MyString()              	8, destructor, t
BASIC4TRACE: (0x7fffd9f0f960)->MyString(const MyString&)        16, copy constructor, z   
BASIC4TRACE: (0x7fffd9f0f990)->~MyString()             		16, destructor, u3
BASIC4TRACE: (0x7fffd9f0f970)->~MyString()             		16,destructor, b
BASIC4TRACE: (0x7fffd9f0f980)->~MyString()             		16,destructor, a
one and two							cout << z << endl;
BASIC4TRACE: (0x7fffd9f0f960)->~MyString()             		18, destructor, z
BASIC4TRACE: (0x7fffd9f0f950)->~MyString()             		18, destructor, y
BASIC4TRACE: (0x7fffd9f0f940)->~MyString()             		18, destructor, x


b)

When we made this change, variables MyString x and MyString y from main were passed by
reference into add(). This means that the copy constructor is not called 
on line 16 to create MyString a and MyString b; instead MyString x and
MyString y are referenced as a and b in the add() method. This also means that the
destructor is not called on MyString a and MyString b when the add() 
function exits, because the MyString objects are not constructed 
iin that function's scope. 


BASIC4TRACE: (0x7fffc2c89280)->MyString(const char *)          
BASIC4TRACE: (0x7fffc2c89290)->MyString(const char *)         
BASIC4TRACE: (0x7fffc2c89230)->MyString(const char *)     
BASIC4TRACE: op+(const MyString&, const MyString&)       
BASIC4TRACE: (0x7fffc2c891e0)->MyString()              
BASIC4TRACE: (0x7fffc2c89240)->MyString(const MyString&)              
BASIC4TRACE: (0x7fffc2c891e0)->~MyString()           
BASIC4TRACE: op+(const MyString&, const MyString&)        
BASIC4TRACE: (0x7fffc2c891e0)->MyString()             
BASIC4TRACE: (0x7fffc2c89250)->MyString(const MyString&)          
BASIC4TRACE: (0x7fffc2c891e0)->~MyString()            
BASIC4TRACE: (0x7fffc2c892b0)->MyString(const MyString&)          
BASIC4TRACE: (0x7fffc2c89250)->~MyString()            
BASIC4TRACE: (0x7fffc2c89240)->~MyString()           
BASIC4TRACE: (0x7fffc2c89230)->~MyString()       
BASIC4TRACE: (0x7fffc2c892a0)->MyString(const MyString&)          
BASIC4TRACE: (0x7fffc2c892b0)->~MyString()          
one and two
BASIC4TRACE: (0x7fffc2c892a0)->~MyString()        
BASIC4TRACE: (0x7fffc2c89290)->~MyString()        
BASIC4TRACE: (0x7fffc2c89280)->~MyString()    


c)

From man g++:
The C++ standard allows an implementation to omit creating a temporary which is
only used to initialize another object of the same type.  Specifying this
option disables that optimization, and forces G++ to call the copy 
constructor in all cases.

Without this flaf, part c would have an additonal 2 copy constructors, and 2
additional corresponding copy constructors than in part B, because u34 and z
don't need copy constructors called on them.
