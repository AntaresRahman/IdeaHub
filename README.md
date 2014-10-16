IdeaHub
Antares Rahman, Momin Javed, Alejandro Hernandez, George Sarkar
COM 212 Data Structures
Instructor: Gary Parker
October 16, 2014
=======

IdeaHub is a final project for our course on Data Structures. It was developed in Spring 2014.

IdeaHub allows a user to create a database containing information about ideas provided by students. It provides a user-friendly GUI. We have implemented from scratch and used several data structures to maintain the program. Data structures include:

- 2 nodes:
  NodeS (student node) holds information of a student. The user can add a new student to the record. Information includes Last        Name, Number (ID), SSN, and Email Address. The user can also change information such as Last Name and Email, or delete any        record of the student. Any student record deletion is effective only after IdeaHub is restarted. 
  
  NodeI (idea node) holds information of an idea. The user can add a new idea to the record using a student's Number and              SSN (so that it is linked to the particular student's record). The user gets to input the Idea and put a Rating on it.
           
- 1 queue:
  Queue holds the latest 10 ideas provided by a particular student. Any ideas provided by the student after the latest 10 are         disarded from the Queue. The user has the option to view the last 10 ideas of each student in the record. Queue is used as an     instance variable in NodeS. So each student has a Queue of last 10 ideas.

- 1 heap:
  HeapI (priority queue) contains NodeI, and ideas with higher rating have higher priority. The user can use the Heap to sell the     best idea available in the record.

- 2 BSTs: Two binary search trees had to be implemented as per our instructor's demand. Both the binary search trees hold NodeS.   However, they are used slightly differently.
  BST stores NodeS ordered by their SSNs. BST is used to pull information about a student for the user to allow display of          record and alterations to student information (Last Name and Email).
  
  BSTn stores NodeS ordered by their Student Numbers. BSTn is used to pull out the Email Address of a student for the user.
  
IdeaHub is quite rigid with the use of Exception Handling. It also includes two .txt files:

- save.txt:
  Stores and saves data from the program on closing IdeaHub so that the database can be retrieved when IdeaHub is restarted.
  
- instructions.txt:
  This is a user manual that briefly describes to the user how to use IdeaHub.
  
  
  
