import java_cup.runtime.*; // defines the Symbol class
// The generated scanner will return a Symbol for each token that it finds.
// A Symbol contains an Object field named value; that field will be of type
// TokenVal, defined below.
//
// A TokenVal object contains the line number on which the token occurs as
// well as the number of the character on that line that starts the token.
// Some tokens (literals and IDs) also include the value of the token.
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
}
class IntLitTokenVal extends TokenVal {
  // new field: the value of the integer literal
    int intVal;
  // constructor
    IntLitTokenVal(int line, int ch, int val) {
        super(line, ch);
        intVal = val;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    	idVal = val;
    }
}
class StrLitTokenVal extends TokenVal {
  // new field: the value of the string literal
    String strVal;
  // constructor
    StrLitTokenVal(int line, int ch, String val) {
        super(line, ch);
        strVal = val;
    }
}
// The following class is used to keep track of the character number at which
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NOT_ACCEPT,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
<<<<<<< HEAD
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NOT_ACCEPT,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NOT_ACCEPT,
=======
		/* 15 */ YY_NOT_ACCEPT,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
>>>>>>> Did some fuckin poo poo stuff
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
<<<<<<< HEAD
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"21:9,27,22,21:2,19,21:18,27,35,18,29,21:2,36,21,30:3,33,30,34,30,28,26:10,2" +
"1,30,31,38,32,21:2,25:25,23,21,20,21:2,25,21,13,1,15,8,11,12,24,17,4,24:2,3" +
",24,5,2,24:2,9,14,6,10,7,16,24:3,30,37,30,21:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,57,
"0,1,2,3,1,4,5,6,7,1:3,3,8,1:2,3:2,9,10,11,12,11,13,14,15,16,17,18,19,20,21," +
"22,23,24,7,25,26,27,28,29,30,31,32,33,34,35,36,7,37,38,39,40,29,41,42,43")[0];

	private int yy_nxt[][] = unpackFromString(44,39,
"1,2,48:2,13,48,50,51,48,52,48,53,54,48,55,33,56,48,3,-1,14:2,4,48:3,5,6,7,2" +
"0,15,21,25,28,30,32,24,27,32,-1:40,48,34,48:15,-1:5,35,48,35:2,-1:13,12:17," +
"9,12,18,12,10,17,12:4,-1,12:10,-1:26,5,-1:39,6,-1:39,22,-1:11,48:17,-1:5,35" +
",48,35:2,-1:13,48:4,19,48:6,8,48:5,-1:5,35,48,35:2,-1:13,12:17,16,12,18,12," +
"10,17,12:4,-1,12:10,-1,48:5,8,48:11,-1:5,35,48,35:2,-1:13,22:21,11,22:16,-1" +
":31,15,-1:6,15,-1,48:4,8,48:12,-1:5,35,48,35:2,-1:48,15,-1:34,15,-1:5,15,-1" +
",48:2,8,48:14,-1:5,35,48,35:2,-1:49,15,-1:34,15,-1:6,48:10,8,48:6,-1:5,35,4" +
"8,35:2,-1:46,15,-1:5,48:7,8,48:9,-1:5,35,48,35:2,-1:50,15,-1,48,42,48,23,48" +
":13,-1:5,35,48,35:2,-1:13,48,26,48:15,-1:5,35,48,35:2,-1:13,48:9,29,48:7,-1" +
":5,35,48,35:2,-1:13,48:3,31,48:13,-1:5,35,48,35:2,-1:13,48:5,44,48:11,-1:5," +
"35,48,35:2,-1:13,48:13,29,48:3,-1:5,35,48,35:2,-1:13,48:2,39,48:14,-1:5,35," +
"48,35:2,-1:13,48:8,49,48:8,-1:5,35,48,35:2,-1:13,48:9,19,48:7,-1:5,35,48,35" +
":2,-1:13,48:3,45,48:13,-1:5,35,48,35:2,-1:13,48:9,46,48:7,-1:5,35,48,35:2,-" +
"1:13,48:2,29,48:14,-1:5,35,48,35:2,-1:13,48:8,23,48:8,-1:5,35,48,35:2,-1:13" +
",48:14,19,48:2,-1:5,35,48,35:2,-1:13,48:9,47,48:7,-1:5,35,48,35:2,-1:13,48:" +
"8,36,48:8,-1:5,35,48,35:2,-1:13,48,37,48:15,-1:5,35,48,35:2,-1:13,48:10,38," +
"48:6,-1:5,35,48,35:2,-1:13,48:12,40,48:4,-1:5,35,48,35:2,-1:13,48:5,41,48:1" +
"1,-1:5,35,48,35:2,-1:13,48:16,43,-1:5,35,48,35:2,-1:12");
=======
		/* 44 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"21:9,26,25,21:2,19,21:18,26,29,18,27,21:2,29,21,27:3,29:4,28,24:10,21,29:4," +
"21:2,23:26,27,20,27:2,23,21,13,1,15,8,11,12,22,17,4,22:2,3,22,5,2,22:2,9,14" +
",6,10,7,16,22:3,29,27,29,21:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,45,
"0,1,2,3,4,1,5,1:2,6,1,3,7,1,3,8,9,10,11,12,13,14,15,6,16,17,18,19,20,21,22," +
"23,24,25,26,27,6,28,29,30,31,20,32,33,34")[0];

	private int yy_nxt[][] = unpackFromString(35,30,
"1,2,36:2,12,36,38,39,36,40,36,41,42,36,43,21,44,36,3,-1,13:2,36:2,4,5,6,7:2" +
",8,-1:31,36,22,36:15,-1:4,36,23:2,-1:6,11:17,10,11,15,11:4,-1,11:2,-1,11,-1" +
":24,4,-1:31,6,-1:4,36:17,-1:4,36,23:2,-1:6,36:4,16,36:6,9,36:5,-1:4,36,23:2" +
",-1:6,11:17,14,11,15,11:4,-1,11:2,-1,11,-1,36:5,9,36:11,-1:4,36,23:2,-1:6,3" +
"6:4,9,36:12,-1:4,36,23:2,-1:6,36:2,9,36:14,-1:4,36,23:2,-1:6,36:10,9,36:6,-" +
"1:4,36,23:2,-1:6,36:7,9,36:9,-1:4,36,23:2,-1:6,36,30,36,17,36:13,-1:4,36,23" +
":2,-1:6,36,18,36:15,-1:4,36,23:2,-1:6,36:9,19,36:7,-1:4,36,23:2,-1:6,36:3,2" +
"0,36:13,-1:4,36,23:2,-1:6,36:5,32,36:11,-1:4,36,23:2,-1:6,36:13,19,36:3,-1:" +
"4,36,23:2,-1:6,36:2,27,36:14,-1:4,36,23:2,-1:6,36:8,37,36:8,-1:4,36,23:2,-1" +
":6,36:9,16,36:7,-1:4,36,23:2,-1:6,36:3,33,36:13,-1:4,36,23:2,-1:6,36:9,34,3" +
"6:7,-1:4,36,23:2,-1:6,36:2,19,36:14,-1:4,36,23:2,-1:6,36:8,17,36:8,-1:4,36," +
"23:2,-1:6,36:14,16,36:2,-1:4,36,23:2,-1:6,36:9,35,36:7,-1:4,36,23:2,-1:6,36" +
":8,24,36:8,-1:4,36,23:2,-1:6,36,25,36:15,-1:4,36,23:2,-1:6,36:10,26,36:6,-1" +
":4,36,23:2,-1:6,36:12,28,36:4,-1:4,36,23:2,-1:6,36:5,29,36:11,-1:4,36,23:2," +
"-1:6,36:16,31,-1:4,36,23:2,-1:5");
>>>>>>> Did some fuckin poo poo stuff

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Symbol(sym.EOF);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -3:
<<<<<<< HEAD
						break;
					case 3:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -4:
						break;
					case 4:
						{ CharNum.num = 1; }
					case -5:
						break;
					case 5:
						{ 
            	int val = (new Integer(yytext())).intValue(); // if int overflows -- this may crash before the if check
		if((val > 0 && val <= Integer.MAX_VALUE) && (val < 0 && val >= Integer.MIN_VALUE)){
			ErrMsg.fatal(yyline+1, CharNum.num,
                         "integer overflow, exceeds " + Integer.MAX_VALUE);
            		CharNum.num += yytext().length();
		}
		IntLitTokenVal intLitToken = new IntLitTokenVal(yyline+1, CharNum.num, val);
            	Symbol S = new Symbol(sym.INTLITERAL,intLitToken);
            	CharNum.num += yytext().length();
            	return S;
          }
					case -6:
						break;
					case 6:
						{ CharNum.num += yytext().length(); }
					case -7:
						break;
					case 7:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -8:
						break;
					case 8:
						{
		String reservedWord = yytext().toString();
		Symbol S = null;
		TokenVal reservedWordToken = null;
		switch (reservedWord) {
		   case "bool":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.BOOL,  reservedWordToken);
			CharNum.num += yytext().length();	
			break;
		   case "int":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.INT,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "void":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.VOID,  reservedWordToken);
			CharNum.num += yytext().length();		
			break;
		   case "true":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TRUE,  reservedWordToken);
			CharNum.num += yytext().length();	
			break;
		   case "false":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.FALSE,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "struct":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.STRUCT,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "cin":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.CIN,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "cout":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COUT,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "if":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.IF,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "else":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ELSE,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "while":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.BOOL,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "return":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RETURN,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   default:
			break;
		}
		return S;				
	}
					case -9:
=======
>>>>>>> Did some fuckin poo poo stuff
						break;
					case 9:
						{	
		String val = yytext();
		StrLitTokenVal strLitToken = new StrLitTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.STRINGLITERAL, strLitToken);
		CharNum.num += yytext().length();
		return S;
	  }
					case -10:
						break;
<<<<<<< HEAD
					case 10:
						{	
		ErrMsg.fatal(yyline+1, CharNum.num,
                         "unterminated string literal ignored");
            		CharNum.num += yytext().length();
	  }
					case -11:
						break;
					case 11:
						{ CharNum.num = 1; }
					case -12:
						break;
					case 13:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -13:
=======
					case 4:
						{  
            	double bigVal = (new Double(yytext())).doubleValue();
			System.out.println("fuck you");
		if(bigVal > Integer.MAX_VALUE) {
			ErrMsg.fatal(yyline+1, CharNum.num,
                         "integer overflow, exceeds " + Integer.MAX_VALUE);
            		CharNum.num += yytext().length();
		} else{
			System.out.println("fuck you");
			int val = (new Integer(yytext())).intValue();
			IntLitTokenVal intLitToken = new IntLitTokenVal(yyline+1, CharNum.num, val);
            		Symbol S = new Symbol(sym.INTLITERAL,intLitToken);
            		CharNum.num += yytext().length();
            		return S;
		}
          }
					case -5:
						break;
					case 5:
						{ CharNum.num = 1; }
					case -6:
						break;
					case 6:
						{ CharNum.num += yytext().length(); }
					case -7:
>>>>>>> Did some fuckin poo poo stuff
						break;
					case 14:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -14:
						break;
					case 15:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -15:
						break;
<<<<<<< HEAD
					case 16:
=======
					case 9:
						{
		String reservedWord = yytext();
		Symbol S = null;
		TokenVal reservedWordToken = null;
		switch (reservedWord) {
		   case "bool":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.BOOL,  reservedWordToken);
			CharNum.num += yytext().length();	
			break;
		   case "int":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.INT,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "void":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.VOID,  reservedWordToken);
			CharNum.num += yytext().length();		
			break;
		   case "true":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TRUE,  reservedWordToken);
			CharNum.num += yytext().length();	
			break;
		   case "false":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.FALSE,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "struct":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.STRUCT,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "cin":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.CIN,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "cout":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COUT,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "if":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.IF,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "else":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ELSE,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   case "while":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WHILE,  reservedWordToken);
			CharNum.num += yytext().length();
		   	break;
		   case "return":
			reservedWordToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RETURN,  reservedWordToken);
			CharNum.num += yytext().length();
			break;
		   default:
			break;
		}
		return S;				
	}
					case -10:
						break;
					case 10:
>>>>>>> Did some fuckin poo poo stuff
						{	
		String val = yytext();
		StrLitTokenVal strLitToken = new StrLitTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.STRINGLITERAL, strLitToken);
		CharNum.num += yytext().length();
		return S;
	  }
<<<<<<< HEAD
					case -16:
						break;
					case 17:
						{	
		ErrMsg.fatal(yyline+1, CharNum.num,
                         "unterminated string literal ignored");
            		CharNum.num += yytext().length();
	  }
					case -17:
						break;
					case 19:
=======
					case -11:
						break;
					case 12:
>>>>>>> Did some fuckin poo poo stuff
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
<<<<<<< HEAD
					case -18:
=======
					case -12:
						break;
					case 13:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -13:
>>>>>>> Did some fuckin poo poo stuff
						break;
					case 20:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -19:
						break;
					case 21:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -20:
						break;
					case 23:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -21:
						break;
					case 24:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -22:
						break;
					case 25:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -23:
						break;
					case 26:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -24:
						break;
					case 27:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -25:
						break;
					case 28:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -26:
						break;
					case 29:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -27:
						break;
					case 30:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -28:
						break;
					case 31:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -29:
						break;
					case 32:
						{ 
	    String reservedSymbol = yytext().toString();
	    Symbol S = null;
	    TokenVal symbolToken = null; 
	    switch(reservedSymbol) {
		case "{":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LCURLY,  symbolToken);
			CharNum.num++;	
			break;
		case "}":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RCURLY,  symbolToken);
			CharNum.num++;
			break;
		case "(":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LPAREN,  symbolToken);
			CharNum.num++;		
			break;
		case ")":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.RPAREN,  symbolToken);
			CharNum.num++;	
			break;
		case ";":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.SEMICOLON,  symbolToken);
			CharNum.num++;
			break;
		case ",":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.COMMA,  symbolToken);
			CharNum.num++;
		   	break;
		case ".":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DOT,  symbolToken);
			CharNum.num++;
			break;
		case "<<": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.WRITE,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case ">>": 
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.READ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "++":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUSPLUS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "--":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUSMINUS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "+":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.PLUS,  symbolToken);
			CharNum.num++;
			break;
		case "-":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.MINUS,  symbolToken);
			CharNum.num++;
			break;
		case "*":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.TIMES,  symbolToken);
			CharNum.num++;
		   	break;
		case "/":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.DIVIDE,  symbolToken);
			CharNum.num++;
			break;
		case "!":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOT,  symbolToken);
			CharNum.num++;
			break;
		case "&&":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.AND,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "||":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.OR,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "==":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.EQUALS,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case "!=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.NOTEQUALS,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "<":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESS,  symbolToken);
			CharNum.num++;
		   	break;
		case ">":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATER,  symbolToken);
			CharNum.num++;
			break;
		case "<=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.LESSEQ,  symbolToken);
			CharNum.num += yytext().length();
		   	break;
		case ">=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.GREATEREQ,  symbolToken);
			CharNum.num += yytext().length();
			break;
		case "=":
			symbolToken = new TokenVal(yyline+1, CharNum.num);
			S = new Symbol(sym.ASSIGN,  symbolToken);
			CharNum.num++;
			break;
		default:
			break;
	    }
            return S;
          }
					case -30:
						break;
					case 33:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -31:
						break;
					case 34:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -32:
						break;
					case 35:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -33:
						break;
					case 36:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -34:
						break;
					case 37:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -35:
						break;
					case 38:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -36:
						break;
					case 39:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -37:
						break;
					case 40:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -38:
						break;
					case 41:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -39:
						break;
					case 42:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -40:
						break;
					case 43:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -41:
						break;
					case 44:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -42:
						break;
					case 45:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -43:
						break;
					case 46:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -44:
						break;
					case 47:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -45:
						break;
					case 48:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -46:
						break;
					case 49:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -47:
						break;
					case 50:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -48:
						break;
					case 51:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -49:
						break;
					case 52:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -50:
						break;
					case 53:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -51:
						break;
					case 54:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -52:
						break;
					case 55:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -53:
						break;
					case 56:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -54:
						break;
					case 16:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -15:
						break;
					case 17:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -16:
						break;
					case 18:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -17:
						break;
					case 19:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -18:
						break;
					case 20:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -19:
						break;
					case 21:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -20:
						break;
					case 22:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -21:
						break;
					case 23:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -22:
						break;
					case 24:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -23:
						break;
					case 25:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -24:
						break;
					case 26:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -25:
						break;
					case 27:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -26:
						break;
					case 28:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -27:
						break;
					case 29:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -28:
						break;
					case 30:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -29:
						break;
					case 31:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -30:
						break;
					case 32:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -31:
						break;
					case 33:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -32:
						break;
					case 34:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -33:
						break;
					case 35:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -34:
						break;
					case 36:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -35:
						break;
					case 37:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -36:
						break;
					case 38:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -37:
						break;
					case 39:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -38:
						break;
					case 40:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -39:
						break;
					case 41:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -40:
						break;
					case 42:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -41:
						break;
					case 43:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -42:
						break;
					case 44:
						{	
		String val = yytext().toString();
		IdTokenVal idToken = new IdTokenVal(yyline+1, CharNum.num, val);
		Symbol S = new Symbol(sym.ID, idToken);
		CharNum.num += yytext().length();
		return S;
	     }
					case -43:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
