
                                      The Banquet Planning Problem
                                      ----------------------------

You are given n guests to be seated at m tables in a banquet (where n % m = 0). Each guest has to be assigned
to a table and each table must contain the same number of guests. There are constraints of the form
together(i,j) and apart(i,j) where together(i,j) means that guests i and j must be seated at the same table 
and apart(i,j) that guests i and j must sit at different tables. By default, a guest can sit at a table with any
other guest (i.e. by default, guests are sociable).


Problem Instances
-----------------
There is a directory of problem instances, called data. Below is instance 12-4-04-03.txt where the instance has
12 guests to be seated at 4 tables (each of size 3) and guests 3 and 9 must sit at the same table, 5 and 9 at the 
same table, guests 2 and 8 at different tables and 6 and 8 at different tables

12 4
together 3 9
together 5 9
apart 2 8
apart 6 8


Running your program
--------------------
The program must run from the command line and take the input problem as an argument.
To compile the program do as follows.
 
  > javac *.java

To run your program with a problem do as follows

  > java BanquetPlanner 12-4-04-03.txt 


This will generate results on the screen

  > java BanquetPlanner 12-4-03.txt > sol-12-4-04-03.txt

This will generate a result and save it in the file sol-12-4-04-03.txt

  > java BanquetPlanner data/100-20-00.txt

This runs the planner on a data set in the data directory


Solution Format and Verification
--------------------------------
Below is a solution (one of many) to the above problem. The first line is table 0 and it has guests 3, 5 and 9. 
The next line is table 1 with guests 8, 10 and 11 ... and so on. The last line is run time statistics 
(8 decisions were made in 4 milliseconds)

0 3 5 9
1 8 10 11
2 2 6 7
3 0 1 4
nodes: 8   cpu: 4

Supplied is a program that will be used to verify that a solution is indeed a solution. This is run as follows

 > java Verify 12-4-04-03.txt sol-12-4-04-03.txt



What you have to do
-------------------
1. Modify the program BanquetPlanner.java so that it finds a solution to instances in the data directory 
   and produces verifiable results. To do this you will have to do the following

   - create appropriate constrained integer variables
   - add appropriate constraints to the model
   - modify the method result to produce verifiable results from the solver

2. Write a short report (a .txt file will suffice, one page maximum) that should contain the following
     - description of your model
     - typical performance
     - possible enhancements or alternative models that might be considered

3. email BanquetPlanner.java and report.txt as attachements to pat@dcs.gla.ac.uk with subject
   CP(M) ex01 including in the body of the email your name, course of study and matriculation number


NOTE 1: this is 5% of the course mark and should take no more than 1 day of your time 
NOTE 2: you have to email me only 2 files as above
NOTE 3: your program must run on the command line as follows

        > java BanquetPlanner data/200-50-02.txt

NOTE 3: failure to comply with the instructions will result in zero marks.



Patrick Prosser
