/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util;

/**
 * Contains all ASCII codes from 0-127 as static integers.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class ASCII
{
	/**
	 * Null code. 
	 * @since 1.0.0
	 */
	public final static int NULL 		= 0;
	
	/**
	 * Start of heading.
	 * @since 1.0.0
	 */
	public final static int SOH 		= 1;
	
	/**
	 * Start of text. 
	 * @since 1.0.0
	 */
	public final static int STX 		= 2;
	
	/**
	 * End of text.
	 * @since 1.0.0
	 */
	public final static int ETX 		= 3;
	
	/**
	 * End of transmission.
	 * @since 1.0.0
	 */
	public final static int EOT 		= 4;
	
	/**
	 * Enquiry.
	 * @since 1.0.0
	 */
	public final static int ENQ 		= 5;
	
	/**
	 * Acknowledge.
	 * @since 1.0.0
	 */
	public final static int ACK 		= 6;
	
	/**
	 * Bell.
	 * @since 1.0.0
	 */
	public final static int BEL 		= 7;
	
	/**
	 * Backspace.
	 * @since 1.0.0
	 */
	public final static int BACKSPACE	= 8;
	
	/**
	 * Horizontal tab.
	 * @since 1.0.0
	 */
	public final static int TAB 		= 9;
	
	/**
	 * New line(NL) line feed.
	 * @since 1.0.0
	 */
	public final static int LF 			= 10;
	
	/**
	 * Vertical tab.
	 * @since 1.0.0
	 */
	public final static int VT	 		= 11;
	
	/**
	 * New page(NP) form feed.
	 * @since 1.0.0
	 */
	public final static int FF 			= 12;
	
	/**
	 * Carriage return.
	 * @since 1.0.0
	 */
	public final static int CR 			= 13;
	
	/**
	 * Shift out.
	 * @since 1.0.0
	 */
	public final static int SO 			= 14;
	
	/**
	 * Shift in.
	 * @since 1.0.0
	 */
	public final static int SI 			= 15;
	
	/**
	 * (DLE) Data link escape. Also used in the Windows OS for the Shift key.
	 * @since 1.0.0
	 */
	public final static int DLE 		= 16;
	public final static int SHIFT 		= 16;
	
	/**
	 * (DC1) Device control 1. Also used in the Windows OS for the Ctrl key.
	 * @since 1.0.0
	 */
	public final static int DC1 		= 17;
	public final static int CTRL 		= 17;
	
	/**
	 * (DC2) Device control 2. Also used in the Windows OS for the Alt key.
	 * @since 1.0.0
	 */
	public final static int DC2 		= 18;
	public final static int ALT 		= 18;
	
	/**
	 * (DC3) Device control 3. Also used in the Windows OS for the Pause key.
	 * @since 1.0.0
	 */
	public final static int DC3 		= 19;
	public final static int PAUSE 		= 19;
	
	/**
	 * (DC4) Device control 4.
	 * @since 1.0.0
	 */
	public final static int DC4 		= 20;
	
	/**
	 * Negative acknowledge.
	 * @since 1.0.0
	 */
	public final static int NAK 		= 21;
	
	/**
	 * Synchronous idle.
	 * @since 1.0.0
	 */
	public final static int SYN 		= 22;
	
	/**
	 * End of transmission block.
	 * @since 1.0.0
	 */
	public final static int ETB 		= 23;
	
	/**
	 * Cancel.
	 * @since 1.0.0
	 */
	public final static int CAN 		= 24;
	
	/**
	 * End of medium.
	 * @since 1.0.0
	 */
	public final static int EM 			= 25;
	
	/**
	 * Substitute.
	 * @since 1.0.0
	 */
	public final static int SUB 		= 26;
	
	/**
	 * Escape.
	 * @since 1.0.0
	 */
	public final static int ESC 		= 27;
	
	/**
	 * File separator.
	 * @since 1.0.0
	 */
	public final static int FS 			= 28;
	
	/**
	 * Group separator.
	 * @since 1.0.0
	 */
	public final static int GS 			= 29;
	
	/**
	 * Record separator.
	 * @since 1.0.0
	 */
	public final static int RS 			= 30;
	
	/**
	 * Unit separator.
	 * @since 1.0.0
	 */
	public final static int US 			= 31;
	
	/**
	 * Space.
	 * @since 1.0.0
	 */
	public final static int SPACE 			= 32;
	
	/**
	 * Exclamation mark. <pre>!</pre>
	 * @since 1.0.0
	 */
	public final static int EXCLAMATION 	= 33;
	
	/**
	 * Quotation. <pre>"</pre>
	 * @since 1.0.0
	 */
	public final static int QUOTATION		= 34;
	
	/**
	 * Number sign, pound, hash mark. <pre>#</pre>
	 * @since 1.0.0
	 */
	public final static int NUMBER 			= 35;
	
	/**
	 * Dollar sign. <pre>$</pre>
	 * @since 1.0.0
	 */
	public final static int DOLLAR 			= 36;
	
	/**
	 * Percent. <pre>%</pre>
	 * @since 1.0.0
	 */
	public final static int PERCENT			= 37;
	
	/**
	 * Ampersand. <pre>&</pre>
	 * @since 1.0.0
	 */
	public final static int AMPERSAND 		= 38;
	
	/**
	 * Apostrophe. <pre>'</pre>
	 * @since 1.0.0
	 */
	public final static int APOSTROPHE		= 39;
	
	/**
	 * Round bracket left. <pre>(</pre>
	 * @since 1.0.0
	 */
	public final static int R_BRACKET1 		= 40;
	
	/**
	 * Round bracket right. <pre>)</pre>
	 * @since 1.0.0
	 */
	public final static int R_BRACKET2		= 41;
	
	/**
	 * Asterisk. <pre>*</pre>
	 * @since 1.0.0
	 */
	public final static int ASTERISK		= 42;
	
	/**
	 * Plus symbol. <pre>+</pre>
	 * @since 1.0.0
	 */
	public final static int PLUS			= 43;
	
	/**
	 * Comma. <pre>,</pre>
	 * @since 1.0.0
	 */
	public final static int COMMA			= 44;
	
	/**
	 * Hyphen, or minus symbol. <pre>-</pre>
	 * @since 1.0.0
	 */
	public final static int HYPHEN 			= 45;
	
	/**
	 * Period, dot. <pre>.</pre>
	 * @since 1.0.0
	 */
	public final static int PERIOD 			= 46;
	
	/**
	 * Forward slash. <pre>/</pre>
	 * @since 1.0.0
	 */
	public final static int SLASH			= 47;
	
	/**
	 * Number 0.
	 * @since 1.0.0
	 */
	public final static int n0 			= 48;
	
	/**
	 * Number 1.
	 * @since 1.0.0
	 */
	public final static int n1 			= 49;
	
	/**
	 * Number 2.
	 * @since 1.0.0
	 */
	public final static int n2 			= 50;
	
	/**
	 * Number 3.
	 * @since 1.0.0
	 */
	public final static int n3 			= 51;
	
	/**
	 * Number 4.
	 * @since 1.0.0
	 */
	public final static int n4 			= 52;
	
	/**
	 * Number 5.
	 * @since 1.0.0
	 */
	public final static int n5  		= 53;
	
	/**
	 * Number 6.
	 * @since 1.0.0
	 */
	public final static int n6  		= 54;
	
	/**
	 * Number 7.
	 * @since 1.0.0
	 */
	public final static int n7  		= 55;
	
	/**
	 * Number 8.
	 * @since 1.0.0
	 */
	public final static int n8  		= 56;
	
	/**
	 * Number 9.
	 * @since 1.0.0
	 */
	public final static int n9  		= 57;
	
	/**
	 * Colon. <pre>:</pre>
	 * @since 1.0.0
	 */
	public final static int COLON 			= 58;
	
	/**
	 * Semicolon. <pre>;</pre>
	 * @since 1.0.0
	 */
	public final static int SEMICOLON		= 59;
	
	/**
	 * Less than. <pre>{@code <}</pre>
	 * @since 1.0.0
	 */
	public final static int LESSTHAN 		= 60;
	
	/**
	 * Equals. <pre>=</pre>
	 * @since 1.0.0
	 */
	public final static int EQUALS			= 61;
	
	/**
	 * Greater than. <pre>{@code >}</pre>
	 * @since 1.0.0
	 */
	public final static int GREATERTHAN		= 62;
	
	/**
	 * Question mark. <pre>?</pre>
	 * @since 1.0.0
	 */
	public final static int QUESTION		= 63;
	
	/**
	 * At sign. <pre>@</pre>
	 * @since 1.0.0
	 */
	public final static int AT_SIGN	= 64;
	
	/**
	 * Uppercase A.
	 * @since 1.0.0
	 */
	public final static int A = 65;
	
	/**
	 * Uppercase B.
	 * @since 1.0.0
	 */
	public final static int B = 66;
	
	/**
	 * Uppercase C.
	 * @since 1.0.0
	 */
	public final static int C = 67;
	
	/**
	 * Uppercase D.
	 * @since 1.0.0
	 */
	public final static int D = 68;
	
	/**
	 * Uppercase E.
	 * @since 1.0.0
	 */
	public final static int E = 69;
	
	/**
	 * Uppercase F.
	 * @since 1.0.0
	 */
	public final static int F = 70;
	
	/**
	 * Uppercase G.
	 * @since 1.0.0
	 */
	public final static int G = 71;
	
	/**
	 * Uppercase H.
	 * @since 1.0.0
	 */
	public final static int H = 72;
	
	/**
	 * Uppercase I.
	 * @since 1.0.0
	 */
	public final static int I = 73;
	
	/**
	 * Uppercase J.
	 * @since 1.0.0
	 */
	public final static int J = 74;
	
	/**
	 * Uppercase K.
	 * @since 1.0.0
	 */
	public final static int K = 75;
	
	/**
	 * Uppercase L.
	 * @since 1.0.0
	 */
	public final static int L = 76;
	
	/**
	 * Uppercase M.
	 * @since 1.0.0
	 */
	public final static int M = 77;
	
	/**
	 * Uppercase N.
	 * @since 1.0.0
	 */
	public final static int N = 78;
	
	/**
	 * Uppercase O.
	 * @since 1.0.0
	 */
	public final static int O = 79;
	
	/**
	 * Uppercase P.
	 * @since 1.0.0
	 */
	public final static int P = 80;
	
	/**
	 * Uppercase Q.
	 * @since 1.0.0
	 */
	public final static int Q = 81;
	
	/**
	 * Uppercase R.
	 * @since 1.0.0
	 */
	public final static int R = 82;
	
	/**
	 * Uppercase S.
	 * @since 1.0.0
	 */
	public final static int S = 83;
	
	/**
	 * Uppercase T.
	 * @since 1.0.0
	 */
	public final static int T = 84;
	
	/**
	 * Uppercase U.
	 * @since 1.0.0
	 */
	public final static int U = 85;
	
	/**
	 * Uppercase V.
	 * @since 1.0.0
	 */
	public final static int V = 86;
	
	/**
	 * Uppercase W.
	 * @since 1.0.0
	 */
	public final static int W = 87;
	
	/**
	 * Uppercase X.
	 * @since 1.0.0
	 */
	public final static int X = 88;
	
	/**
	 * Uppercase Y.
	 * @since 1.0.0
	 */
	public final static int Y = 89;
	
	/**
	 * Uppercase Z.
	 * @since 1.0.0
	 */
	public final static int Z = 90;
	
	/**
	 * Square bracket left. <pre>[</pre>
	 * @since 1.0.0
	 */
	public final static int S_BRACKET1 	= 91;
	
	/**
	 * Backslash. <pre>\</pre>
	 * @since 1.0.0
	 */
	public final static int BACKSLASH 	= 92;
	
	/**
	 * Square bracket right. <pre>]</pre>
	 * @since 1.0.0
	 */
	public final static int S_BRACKET2 	= 93;
	
	/**
	 * Circumflex, hat, or caret. <pre>^</pre>
	 * @since 1.0.0
	 */
	public final static int CIRCUMFLEX 	= 94;
	
	/**
	 * Understrike, or underscore. <pre>_</pre>
	 * @since 1.0.0
	 */
	public final static int UNDERSCORE 	= 95;
	
	/**
	 * Grave symbol. <pre>`</pre>
	 * @since 1.0.0
	 */
	public final static int GRAVE = 96;
	
	/**
	 * Lowercase a.
	 * @since 1.0.0
	 */
	public final static int a = 97;
	
	/**
	 * Lowercase b.
	 * @since 1.0.0
	 */
	public final static int b = 98;
	
	/**
	 * Lowercase c.
	 * @since 1.0.0
	 */
	public final static int c = 99;
	
	/**
	 * Lowercase d.
	 * @since 1.0.0
	 */
	public final static int d = 100;
	
	/**
	 * Lowercase e.
	 * @since 1.0.0
	 */
	public final static int e = 101;
	
	/**
	 * Lowercase f.
	 * @since 1.0.0
	 */
	public final static int f = 102;
	
	/**
	 * Lowercase g.
	 * @since 1.0.0
	 */
	public final static int g = 103;
	
	/**
	 * Lowercase h.
	 * @since 1.0.0
	 */
	public final static int h = 104;
	
	/**
	 * Lowercase i.
	 * @since 1.0.0
	 */
	public final static int i = 105;
	
	/**
	 * Lowercase j.
	 * @since 1.0.0
	 */
	public final static int j = 106;
	
	/**
	 * Lowercase k.
	 * @since 1.0.0
	 */
	public final static int k = 107;
	
	/**
	 * Lowercase l.
	 * @since 1.0.0
	 */
	public final static int l = 108;
	
	/**
	 * Lowercase m.
	 * @since 1.0.0
	 */
	public final static int m = 109;
	
	/**
	 * Lowercase n.
	 * @since 1.0.0
	 */
	public final static int n = 110;
	
	/**
	 * Lowercase o.
	 * @since 1.0.0
	 */
	public final static int o = 111;
	
	/**
	 * Lowercase p. Also used for the F1 key.
	 * @since 1.0.0
	 */
	public final static int p = 112;
	public final static int F1 = 112;
	
	/**
	 * Lowercase q. Also used for the F2 key.
	 * @since 1.0.0
	 */
	public final static int q = 113;
	public final static int F2 = 113;
	
	/**
	 * Lowercase r. Also used for the F3 key.
	 * @since 1.0.0
	 */
	public final static int r = 114;
	public final static int F3 = 114;
	
	/**
	 * Lowercase s. Also used for the F4 key.
	 * @since 1.0.0
	 */
	public final static int s = 115;
	public final static int F4 = 115;
	
	/**
	 * Lowercase t. Also used for the F5 key.
	 * @since 1.0.0
	 */
	public final static int t = 116;
	public final static int F5 = 116;
	
	/**
	 * Lowercase u. Also used for the F6 key.
	 * @since 1.0.0
	 */
	public final static int u = 117;
	public final static int F6 = 117;
	
	/**
	 * Lowercase v. Also used for the F7 key.
	 * @since 1.0.0
	 */
	public final static int v = 118;
	public final static int F7 = 118;
	
	/**
	 * Lowercase w. Also used for the F8 key.
	 * @since 1.0.0
	 */
	public final static int w = 119;
	public final static int F8 = 119;
	
	/**
	 * Lowercase x. Also used for the F9 key.
	 * @since 1.0.0
	 */
	public final static int x = 120;
	public final static int F9 = 120;
	
	/**
	 * Lowercase y. Also used for the F10 key.
	 * @since 1.0.0
	 */
	public final static int y = 121;
	public final static int F10 = 121;
	
	/**
	 * Lowercase z.
	 * @since 1.0.0
	 */
	public final static int z = 122;
	
	/**
	 * Curly bracket left. <pre>{</pre>
	 * @since 1.0.0
	 */
	public final static int C_BRACKET1 	= 123;
	
	/**
	 * Vertical bar. <pre>|</pre>
	 * @since 1.0.0
	 */
	public final static int VERTICALBAR = 124;
	
	/**
	 * Curly bracket right. <pre>}</pre>
	 * @since 1.0.0
	 */
	public final static int C_BRACKET2 	= 125;
	
	/**
	 * Tilde symbol. <pre>~</pre>
	 * @since 1.0.0
	 */
	public final static int TILDE 		= 126;
	
	/**
	 * Delete.
	 * @since 1.0.0
	 */
	public final static int DEL			= 127;
}
