package br.ufma.ecp.token;

import java.util.List;
import java.util.Map;

public enum TokenType {
    // symbols
    LPAREN,RPAREN,
    LBRACE, RBRACE,
    LBRACKET,RBRACKET,

    COMMA, SEMICOLON, DOT,
  
    PLUS,  MINUS,ASTERISK, SLASH,

    AND, OR, NOT,

    LT, GT, EQ,

     // Literals.
     NUMBER,
     IDENT,
     STRING,

     // keywords
    WHILE, CLASS,CONSTRUCTOR,FUNCTION,
    METHOD,FIELD,STATIC,VAR,INT,
    CHAR,BOOLEAN,VOID,TRUE,FALSE,
    NULL,THIS,LET,DO,IF,ELSE, RETURN,

     EOF;

     static public boolean isSymbol (String c) {
        String symbols = "{}()[].,;+-*/&|<>=~";
        return symbols.indexOf(c) > -1;

    }

     //static public boolean isSymbol (char c) {
       // String symbols = "{}()[].,;+-*/&|<>=~";
        //return symbols.indexOf(c) > -1;

    //}


    /*static public boolean isKeyword (TokenType type) {
        List<TokenType> keywords  = 
            List.of(
                METHOD,
                WHILE,
                IF,
                CLASS,
                CONSTRUCTOR
            );
            return keywords.contains(type);
    }*/

}
