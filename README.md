atmRMI
============================
This project is for practice of using java RMI to build a bank ATM application.
It's for programming assigntment for COSC-560 Software Systems.
This project is created in Intellij Idea, so there are some project files besides the source code file. This project has also passed the test under Linux shell environment with only the "src/" folder, so only the files under this folder will be explained here.

File List under "src/"
============================
Makefile
Compile the source code into java executable. 

CODE.java
An enum file which is used to define the common communication protocol between the client and server. If client and server are in different path/machine, this file need to reside both in server side and client side.

bankInterface.java
Interface file that defines the server APIs for exporting.

bank.java
Server side class that implementing the APIs. Besides the bank transaction APIs, there are some other functions which reads the initial account information from the text file, write the changed information back to text file after any changes, and a constructor function that initiate the account information in the server.

bankServer.java
Main class of server, register and bind the server class.

bankClient.java
Client side source code, make remote procedure call to server and print out hint messages after receiving returned code from server.

bankdata.txt
Database of this project, will be load into memory after the server is started, and will synchronize its value every time the memory of the server is modified. 

*.class
Binary files that are compiled from *.java source file. Note that bank_Stub.class is produced by rmic instead of compiled from the source code directly.
