###
# This Makefile can be used to make a scanner for the cats language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = ~cs536-1/public/tools/deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: cats.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) cats.jlex.java

cats.jlex.java: cats.jlex sym.class
	java -cp $(CP) JLex.Main cats.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2 
	diff allTokens.in allTokens.out
	diff allTokens.out allTokens_expected.out
	diff ignoredTokens.out ignoredTokens_expected.out
	diff errors.txt errors_expected.txt 
###
# clean up
###

clean:
	rm -f *~ *.class cats.jlex.java

cleantest:
	rm -f allTokens.out
