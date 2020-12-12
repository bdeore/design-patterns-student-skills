## Student Skills using Observer Pattern
-----------------------------------------------------------------------

This project demonstrates the use of observer pattern with system for storing student records. student records are stored in AVL tree and replicas are made consistent upon changes to any one replica using observer pattern.    

Following are the commands & instructions to run the project using ANT.

Note: build.xml is present in [studentskills/src](./studentskills/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskills/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modifications.txt" -Dout1="tree1_output.txt" -Dout2="tree2_output.txt" -Dout3="tree3_output.txt" -Derror="errors_log.txt" -Ddebug="debug_log.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:

Data Structure type: AVL Tree.

Justification: AVL is a balanced binary tree. which means following time complexities are guaranteed:

```insertion, access, search, deletion: O(log n)```

whereas, simple binary search tree time complexity can be O(n) in cases where tree is skewed on one side.
for storing references I'm using ArrayList.

Note: TreeHelper class creates and manages AVL tree replicas. recursive methods defined in BalancedTree class are used for all the operations.    

Date:  July 6, 2020


