/* Generated By:JavaCC: Do not edit this line. PromelaConstants.java */
package spins.promela.compiler.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface PromelaConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PROCTYPE = 1;
  /** RegularExpression Id. */
  int INIT = 2;
  /** RegularExpression Id. */
  int NEVER = 3;
  /** RegularExpression Id. */
  int TRACE = 4;
  /** RegularExpression Id. */
  int NOTRACE = 5;
  /** RegularExpression Id. */
  int TYPEDEF = 6;
  /** RegularExpression Id. */
  int MTYPE = 7;
  /** RegularExpression Id. */
  int BIT = 8;
  /** RegularExpression Id. */
  int BOOL = 9;
  /** RegularExpression Id. */
  int BYTE = 10;
  /** RegularExpression Id. */
  int PID = 11;
  /** RegularExpression Id. */
  int SHORT = 12;
  /** RegularExpression Id. */
  int INT = 13;
  /** RegularExpression Id. */
  int CHAN = 14;
  /** RegularExpression Id. */
  int PRIORITY = 15;
  /** RegularExpression Id. */
  int PROVIDED = 16;
  /** RegularExpression Id. */
  int HIDDEN = 17;
  /** RegularExpression Id. */
  int SHOW = 18;
  /** RegularExpression Id. */
  int XR = 19;
  /** RegularExpression Id. */
  int XS = 20;
  /** RegularExpression Id. */
  int OF = 21;
  /** RegularExpression Id. */
  int EVAL = 22;
  /** RegularExpression Id. */
  int IF = 23;
  /** RegularExpression Id. */
  int FI = 24;
  /** RegularExpression Id. */
  int DO = 25;
  /** RegularExpression Id. */
  int OD = 26;
  /** RegularExpression Id. */
  int ATOMIC = 27;
  /** RegularExpression Id. */
  int D_STEP = 28;
  /** RegularExpression Id. */
  int ELSE = 29;
  /** RegularExpression Id. */
  int BREAK = 30;
  /** RegularExpression Id. */
  int GOTO = 31;
  /** RegularExpression Id. */
  int PRINT = 32;
  /** RegularExpression Id. */
  int ASSERT = 33;
  /** RegularExpression Id. */
  int LEN = 34;
  /** RegularExpression Id. */
  int TIMEOUT = 35;
  /** RegularExpression Id. */
  int NP_ = 36;
  /** RegularExpression Id. */
  int ENABLED = 37;
  /** RegularExpression Id. */
  int PC_VALUE = 38;
  /** RegularExpression Id. */
  int RUN = 39;
  /** RegularExpression Id. */
  int FULL = 40;
  /** RegularExpression Id. */
  int EMPTY = 41;
  /** RegularExpression Id. */
  int NFULL = 42;
  /** RegularExpression Id. */
  int NEMPTY = 43;
  /** RegularExpression Id. */
  int TRUE = 44;
  /** RegularExpression Id. */
  int FALSE = 45;
  /** RegularExpression Id. */
  int SKIP_ = 46;
  /** RegularExpression Id. */
  int UNLESS = 47;
  /** RegularExpression Id. */
  int VAR_PID = 48;
  /** RegularExpression Id. */
  int VAR_NR_PR = 49;
  /** RegularExpression Id. */
  int LCURLY = 50;
  /** RegularExpression Id. */
  int RCURLY = 51;
  /** RegularExpression Id. */
  int ASSIGN = 52;
  /** RegularExpression Id. */
  int LPAREN = 53;
  /** RegularExpression Id. */
  int RPAREN = 54;
  /** RegularExpression Id. */
  int LBRACK = 55;
  /** RegularExpression Id. */
  int RBRACK = 56;
  /** RegularExpression Id. */
  int OPTION = 57;
  /** RegularExpression Id. */
  int COLON = 58;
  /** RegularExpression Id. */
  int SEMICOLON = 59;
  /** RegularExpression Id. */
  int COMMA = 60;
  /** RegularExpression Id. */
  int RARROW = 61;
  /** RegularExpression Id. */
  int CH_SEND_SORTED = 62;
  /** RegularExpression Id. */
  int CH_READ = 63;
  /** RegularExpression Id. */
  int CH_READ_RAND = 64;
  /** RegularExpression Id. */
  int AT = 65;
  /** RegularExpression Id. */
  int BNOT = 66;
  /** RegularExpression Id. */
  int LNOT = 67;
  /** RegularExpression Id. */
  int MINUS = 68;
  /** RegularExpression Id. */
  int TIMES = 69;
  /** RegularExpression Id. */
  int DIVIDE = 70;
  /** RegularExpression Id. */
  int MODULO = 71;
  /** RegularExpression Id. */
  int PLUS = 72;
  /** RegularExpression Id. */
  int LSHIFT = 73;
  /** RegularExpression Id. */
  int RSHIFT = 74;
  /** RegularExpression Id. */
  int LT = 75;
  /** RegularExpression Id. */
  int LTE = 76;
  /** RegularExpression Id. */
  int GT = 77;
  /** RegularExpression Id. */
  int GTE = 78;
  /** RegularExpression Id. */
  int EQ = 79;
  /** RegularExpression Id. */
  int NEQ = 80;
  /** RegularExpression Id. */
  int BAND = 81;
  /** RegularExpression Id. */
  int XOR = 82;
  /** RegularExpression Id. */
  int BOR = 83;
  /** RegularExpression Id. */
  int LAND = 84;
  /** RegularExpression Id. */
  int LOR = 85;
  /** RegularExpression Id. */
  int INCR = 86;
  /** RegularExpression Id. */
  int DECR = 87;
  /** RegularExpression Id. */
  int DOT = 88;
  /** RegularExpression Id. */
  int IDENTIFIER = 98;
  /** RegularExpression Id. */
  int NUMBER = 99;
  /** RegularExpression Id. */
  int DEFINE = 188;
  /** RegularExpression Id. */
  int PARAM = 193;
  /** RegularExpression Id. */
  int STRING = 215;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_STRING = 1;
  /** Lexical state. */
  int IN_COMMENT = 2;
  /** Lexical state. */
  int IN_COMMENT2 = 3;
  /** Lexical state. */
  int PREPROCESSOR_INCLUDE_FILE = 4;
  /** Lexical state. */
  int PREPROCESSOR_INCLUDE = 5;
  /** Lexical state. */
  int PREPROCESSOR_DEFINE_REST = 6;
  /** Lexical state. */
  int PREPROCESSOR_DEFINE_PARAM2 = 7;
  /** Lexical state. */
  int PREPROCESSOR_DEFINE_PARAM1 = 8;
  /** Lexical state. */
  int PREPROCESSOR_DEFINE_PARAM = 9;
  /** Lexical state. */
  int PREPROCESSOR_DEFINE = 10;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_REST = 11;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_TEXT = 12;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_PARAM2 = 13;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_PARAM1 = 14;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_PARAM0 = 15;
  /** Lexical state. */
  int PREPROCESSOR_INLINE_PARAM = 16;
  /** Lexical state. */
  int PREPROCESSOR_INLINE = 17;
  /** Lexical state. */
  int PREPROCESSOR_LINE = 18;
  /** Lexical state. */
  int PREPROCESSOR_FILE = 19;
  /** Lexical state. */
  int PREPROCESSOR_ELIF_SKIP = 20;
  /** Lexical state. */
  int PREPROCESSOR_SKIP = 21;
  /** Lexical state. */
  int PREPROCESSOR_IFNDEF = 22;
  /** Lexical state. */
  int PREPROCESSOR_IFDEF = 23;
  /** Lexical state. */
  int PREPROCESSOR_IF = 24;
  /** Lexical state. */
  int PREPROCESSOR_NDEFINED = 25;
  /** Lexical state. */
  int PREPROCESSOR_DEFINED = 26;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"proctype\"",
    "\"init\"",
    "\"never\"",
    "\"trace\"",
    "\"notrace\"",
    "\"typedef\"",
    "\"mtype\"",
    "\"bit\"",
    "\"bool\"",
    "\"byte\"",
    "\"pid\"",
    "\"short\"",
    "\"int\"",
    "\"chan\"",
    "\"priority\"",
    "\"provided\"",
    "\"hidden\"",
    "\"show\"",
    "\"xr\"",
    "\"xs\"",
    "\"of\"",
    "\"eval\"",
    "\"if\"",
    "\"fi\"",
    "\"do\"",
    "\"od\"",
    "\"atomic\"",
    "\"d_step\"",
    "\"else\"",
    "\"break\"",
    "\"goto\"",
    "\"printf\"",
    "\"assert\"",
    "\"len\"",
    "\"timeout\"",
    "\"np_\"",
    "\"enabled\"",
    "\"pc_value\"",
    "\"run\"",
    "\"full\"",
    "\"empty\"",
    "\"nfull\"",
    "\"nempty\"",
    "\"true\"",
    "\"false\"",
    "\"skip\"",
    "\"unless\"",
    "\"_pid\"",
    "\"_nr_pr\"",
    "\"{\"",
    "\"}\"",
    "\"=\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"::\"",
    "\":\"",
    "\";\"",
    "\",\"",
    "\"->\"",
    "\"!!\"",
    "\"?\"",
    "\"??\"",
    "\"@\"",
    "\"~\"",
    "\"!\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"+\"",
    "\"<<\"",
    "\">>\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"==\"",
    "\"!=\"",
    "\"&\"",
    "\"^\"",
    "\"|\"",
    "\"&&\"",
    "\"||\"",
    "\"++\"",
    "\"--\"",
    "\".\"",
    "\"inline\"",
    "\"defined\"",
    "\"ndef\"",
    "\"(\"",
    "\" \"",
    "<token of kind 94>",
    "\"(\"",
    "\" \"",
    "<token of kind 97>",
    "<IDENTIFIER>",
    "<NUMBER>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "\"/*\"",
    "\"//\"",
    "\"#if\"",
    "\"#ifdef\"",
    "\"#ifndef\"",
    "\"#else\"",
    "\"#elif\"",
    "\"#endif\"",
    "\"#line\"",
    "\"#file\"",
    "\"#define\"",
    "\"#include\"",
    "\"#\"",
    "<token of kind 117>",
    "\"\\\\\\n\"",
    "\"\\\\\\r\"",
    "\"\\\\\\r\\n\"",
    "<token of kind 121>",
    "<token of kind 122>",
    "<token of kind 123>",
    "<token of kind 124>",
    "<token of kind 125>",
    "<token of kind 126>",
    "\"#else\"",
    "\"#elif\"",
    "\"#endif\"",
    "<token of kind 130>",
    "<token of kind 131>",
    "\"\\\\\\n\"",
    "\"\\\\\\r\"",
    "\"\\\\\\r\\n\"",
    "<token of kind 135>",
    "<token of kind 136>",
    "\"\\\\\\n\"",
    "\"\\\\\\r\"",
    "\"\\\\\\r\\n\"",
    "<token of kind 140>",
    "<token of kind 141>",
    "\"\\\\\\n\"",
    "\"\\\\\\r\"",
    "\"\\\\\\r\\n\"",
    "<token of kind 145>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "<token of kind 151>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "\"(\"",
    "<token of kind 158>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "<token of kind 164>",
    "\")\"",
    "<token of kind 166>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "<token of kind 172>",
    "<token of kind 173>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\r\\n\"",
    "\",\"",
    "\")\"",
    "<token of kind 181>",
    "<token of kind 182>",
    "\"{\"",
    "\"{\"",
    "\"}\"",
    "<token of kind 186>",
    "<token of kind 187>",
    "<DEFINE>",
    "<token of kind 189>",
    "\"(\"",
    "<token of kind 191>",
    "<token of kind 192>",
    "<PARAM>",
    "<token of kind 194>",
    "<token of kind 195>",
    "\",\"",
    "\")\"",
    "<token of kind 198>",
    "<token of kind 199>",
    "\"\\\\\\n\"",
    "\"\\\\\\r\"",
    "\"\\\\\\r\\n\"",
    "<token of kind 203>",
    "<token of kind 204>",
    "\"\\\"\"",
    "\"\\\"\"",
    "<token of kind 207>",
    "<token of kind 208>",
    "<token of kind 209>",
    "\"/*\"",
    "<token of kind 211>",
    "\"*/\"",
    "\"\\\"\"",
    "<token of kind 214>",
    "\"\\\"\"",
  };

}
