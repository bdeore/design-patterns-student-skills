# CSX42: Assignment 3
**Name:** Bhagwan Sanjay Deore

-----------------------------------------------------------------------

Following are the commands, and the instructions to run ANT on your project.


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

Exception classes and utility classes used in this assignment are taken from previous assignment.

citations: 
## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date:  July 6, 2020


