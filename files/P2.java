import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol


/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
	//Test all tokens
        testAllTokens();
        CharNum.num = 1;
	//Test bad tokens
	testBadInput();
	CharNum.num = 1;
	//Test empty tokens
	testEmptyFile();
	CharNum.num = 1;
	//Test ignored tokens
	testIgnoredInput();
	CharNum.num = 1;
	//Test Character Numbers
	testCharacterNumbers();
	CharNum.num = 1;
    }

    /**
     * testCharacterNumbers tests the charnum and linenum of the scanner over the given tokens
     * from an input file, allTokens.in. This writes the expected output to allTokens.out
     * and compares those values against the stored tokens originally read.
     */
    private static void testCharacterNumbers() throws IOException{
	FileReader inFile = null;
        PrintWriter outFile = null;
	TokenVal[] tokenArray = new TokenVal[10000];
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
	int index = 0;
	while(token.sym != sym.EOF){
	    TokenVal tokenVal = (TokenVal) token.value;
	    tokenArray[index] = tokenVal;
	    switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
	    case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL:
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
	    case sym.ASSIGN:
                outFile.println("=");
                break;
	   default:
		outFile.println("UNKNOWN TOKEN");
            } // end switch
	    token = scanner.next_token();
	    index++;
	}

	outFile.close();
	FileReader readOutFile = null;
	try {
	    readOutFile = new FileReader("allTokens.out");
	}
	catch(FileNotFoundException ex) {
	    System.err.println("File allTokens.out not found.");
            System.exit(-1);
	}	
	Yylex newScanner = new Yylex(readOutFile);
	Symbol newToken = newScanner.next_token();
	int i = 0;
	while(newToken.sym != sym.EOF){
	    TokenVal tokenValToCompare = tokenArray[i];
	    TokenVal currentToken = (TokenVal)newToken.value;
	    if((currentToken.linenum != tokenValToCompare.linenum) 
		|| (currentToken.charnum != tokenValToCompare.charnum))
	    {
		System.err.println("Error in scanning characters linenum or charnum. Exiting...");
		System.exit(-1);
	    }
	    i++;
	    newToken = newScanner.next_token();
	}

    }

    /*
     * testIgnoredInput tests ignored input tokens such as comments and ensures that they are not 
     * written towards an output file. If so, the output file will display a success message and
     * we can verify this be running a diff command upon an expected output file and and intermediary
     * file that we created
     */
    private static void testIgnoredInput() throws IOException{
	FileReader inFile = null;
	FileReader intermediaryFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("ignoredTokens.in");
            outFile = new PrintWriter(new FileWriter("ignoredTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File ignoredTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("ignoredTokens.out cannot be opened.");
            System.exit(-1);
        }

	Yylex scanner = new Yylex(inFile);
	Symbol token = scanner.next_token();
	if(token.sym == sym.EOF){
	    try {
	        intermediaryFile = new FileReader("intermediaryFile.in");
	    } catch (FileNotFoundException ex) {
		System.err.println("File intermediaryFile.in not found.");
		System.exit(-1);
	    }
	    Yylex altScanner = new Yylex(intermediaryFile);
	    Symbol altToken = altScanner.next_token();
            outFile.println(((StrLitTokenVal)altToken.value).strVal);    	
	}else{
	    outFile.println("failed to ignore characters");
	}
	    outFile.close();
    }

    /*
     * testEmptyFile is a function that ensures that no tokens besides and EOF token is read for an empty file
     */
    private static void testEmptyFile() throws IOException{
	FileReader inFile = null;
	try{
	    inFile = new FileReader("emptyFile.in");
	} catch (FileNotFoundException ex){
	    System.err.println("File emptyFile.in");
	    System.exit(-1);
	}

	Yylex scanner = new Yylex(inFile);
	Symbol eofToken = scanner.next_token();
	if(eofToken.sym != sym.EOF){
		System.err.println("Try to read non-existent symbols from emptyFile.in");
	}
    }

    /*
     * testBadInput tests badInput values and displays the correct error message to an error message file
     * called errors.txt
     * checks for invaled input
     */
    private static void testBadInput() throws IOException{
	FileReader inFile = null;
        PrintWriter outFile = null;


	//Set error statements to go to a new filestream (instead of System.err)
	System.setErr(new PrintStream(new FileOutputStream("errors.txt")));	
	
        try {
            inFile = new FileReader("badTokens.in");
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        }
	Yylex scanner = new Yylex(inFile); //Needs an input file with bad input
	Symbol badToken = scanner.next_token();
	while(badToken.sym != sym.EOF){
		badToken = scanner.next_token();
	}

    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
	    case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL:
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
	    case sym.ASSIGN:
                outFile.println("=");
                break;
	   default:
		outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
