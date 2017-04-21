# Basic-Order-of-Operations-Calculator
Assignment for UNH CS416 - basic calculations including order of operations - run from interpreter.java

PROJECT ASSIGNMENT:

CS 416 Program 6P – Expression Interpreter
March 27, 2017
∗ due Wednesday, April 5 at 23:59. Thu -3, Fri -7, Sat, Sun -10
Goals:
  Implement a basic expression interpreter.
The Problem
  You need to implement a program that will read a simple computer language that can be used to define a
  series of arithmetic expressions that can include assignments to variables. You only need to handle the binary
  operators: = + - * / %. Operands can be variable names or numeric constants. The expression can also include
  parentheses. The assignment operator can only have a variable name as its left operand. You must implement a
  symbol table that will be used to store and access the values of variables.
The Program
1. If the program is started with a command line argument, the argument should be the name of a file that
  contains 1 statement per line. The program should open the file, read each line and print out a representation
  of the expression tree for the line, or a meaningful error message if the line does not represent a valid
  expression. After reading the entire file, your program should terminate. If the argument is not a valid file,
  you should terminate. In particular, you should execute no interactive code if there are any command
  line arguments. This behavior is in the starting code. DO NOT CHANGE IT!
2. If there are no command line arguments, the starter code uses a JFileChooser to prompt the user to specify a
  file to be opened and processed in the same manner as in batch mode.
3. After processing the file chosen in step 2 above, the program prompts the user repeatedly to enter a
  statement using a JOptionPane.showInputDialog until the user picks Cancel or enters an empty string.
    The program should be robust. It should be able to recognize input that does not represent a valid statement
  and it should try to print an error message that explains what kind of error has been detected. You may assume
  that all tokens are separated by blanks. You may assume that any token that is a single character that is one of
  the characters, “= ( ) + - * / %” can be treated as an “operator”. Any other token will be interpreted as an
  “operand”, but it must satisfy the rules for a Java identifier or numeric constant or you should generate an error.
    Invalid expressions are discovered when you need to pop operands off a stack and there are not enough
  there. It is also an invalid expression if there is more than one operand left on the stack when you have finished
  processing the last token. If an assignment operator is used, it must be the root of the tree and its left operand
  must be a variable.
  

Class/Program Design
1. The starter code includes a beginning implementation of Interpreter.java, which must be the main class. As
  delivered, the code provides a complete implementation of the code needed in interactive mode (in the
  interactive method). That method, however, relies on two “stub” methods, processFile and processLine that
  you need to complete. The constructor has code to check the command line arguments to determine whether
  to run in batch or interactive mode, but you need to add some preceding code to create symbol table and tree
  building objects.
2. A skeleton of a SymbolTable class is also provided in a form that is suitable for the Singleton design pattern.
  That is, only one instance of the class can ever be created. It is both created and accessed using the
  instance() static method call: SymbolTable.instance(). Your implementation should use a Java Hashtable or
  HashMap and you will need to figure out how to store the information you need into the table.
3. Tree representation
    a. You need a class to represent an operand (a variable or number) that will be a leaf of the expression tree.
    b. You need a class to represent an operator that will be an internal node of the expression tree.
    c. You need an abstract class that can represent either an operand or an operator.
4. Some class must be responsible for implementing the algorithm to convert from infix to expression tree.
5. Some class needs to be responsible for interpreting the expression tree and updating variable values in the
symbol table.

Notes
  1. You need to print a tree in some way that allows its structure to be seen, such as using extra indentation for
  each new tree level.
  2. Scanner is the easiest way to read input files using the methods, hasNextLine() and nextLine().
  3. Once you have a line, it is easiest to parse the expression by using another Scanner constructed with the line
  as its argument. Since all we care about are space-separated tokens, you only need to use hasNext() and
  next() methods with this Scanner.
  4. Lab 9 has some code that does some similar processing.
  5. Represent all constants and variables as float (or Float/Number).
  6. Reuse code! For example, there are 2 different places where you need to read a file and 3 different places
  that need to parse a String containing an expression. Be sure you design good methods than can be shared in
  all these cases.
  7. Keep methods short by creating utility methods that implement specific logical parts of the algorithm.
