"# My notes" 


CLASSES AND OBJECTS
Oject is an instance of a class
Class is a template of an object
Packages and Import statements are the only things outside of classes in Java
Strings are " " double quotes and arrays can be created without the 'new' syntax

references are like pointers
Objects are always created on the "Heap"
References can be on a stack or a heap
References:
    allow access to an object
    have either a memory address or null
    can point to two different "Person" class
    == compares references

Instance Vs Static variables
Instance
    you want instance, each object gets its own copy of all insance variables in a class
    Example: Allows 2 date objects to represent different dates
Static
    not associated with objects
    associated with the class not the object
    Example: if the variable of a Date class was static, all dates in a program would represent the same date

Methods are inside the object
Insance v static METHODS
Method is a function inside of a class
funciton is something that is not inside of a class, there are no function outside of a class in JAVA
Instance method:
    methods are associated with a specific instance(object)
Static method:
    methods are associated with a class (not an instance)

Getters/Setters(Accessors/Mutators)
    methods for getting and setting instance variables
    make variables private and only allow access through getters and setters
>> you can get your ide to generate code > code > ...
>> you just need package and import and class name and privates

Constructors
    code executed at object creation time
    must match the class name
    like a method without a return type
    all classes have at least one (can have multiple)
    constructors invoke each other with 'this(...)'
    constructors invoke parent constructor with 'super(...)'
    this(...) or super(...) is ALWAYS the first statement
Inheritance
    inherit members of a parent (super) class without explicitly writing them in the child sub class
    use the 'extends' keyword
    use hte 'is-a' rule
    inheritance is all instance variables
    what is not inherited?
        constructors
        static variables and methods
        private methods 

OBJECT OVERRIDES in the SPEC in chess-moves.md
when you do get row do -1 from the row in the column
chess board is (0, to 7)

Day 3
RECORDS
Java Records 
    POJOs are common Java classes that only exist
    publin record Pet(...)
records are immutable you cannot change
automatic getters

INTERFACES AND ABSTRACT CLASSES
Exceptions
    Abnormal conditions that can occur in Java class
    Allow you to simplify your code
    RuntimeException means you have a bug
    Try/Catch Blocks
    Catching but not resolving an exception is called swallowing an exception and is bad
Handle or Declare Rule
    applies to checked exceptions
    not apply to errors or RuntimeExceptions
    Handle: try Exception
    Declare: throuw Exception
Finally Blocks
    put code that is guaranteed to be executed
Try with Resources
    try()
    you can open resources in try() and they will auto-close

