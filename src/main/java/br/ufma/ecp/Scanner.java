package br.ufma.ecp;

import static br.ufma.ecp.token.TokenType.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import br.ufma.ecp.token.Token;
import br.ufma.ecp.token.TokenType;

class Scanner {

    private byte[] input;
    private int current; 

    private static final Map<String, TokenType> keywords;
 
    static {
        keywords = new HashMap<>();
        keywords.put("while", TokenType.WHILE);
        keywords.put("int", TokenType.INT);
        keywords.put("class", TokenType.CLASS);
        keywords.put("constructor", TokenType.CONSTRUCTOR);
        keywords.put("function", TokenType.FUNCTION);
        keywords.put("method", TokenType.METHOD);
        keywords.put("field", TokenType.FIELD);
        keywords.put("static", TokenType.STATIC);
        keywords.put("var", TokenType.VAR);
        keywords.put("char", TokenType.CHAR);
        keywords.put("boolean", TokenType.BOOLEAN);
        keywords.put("void", TokenType.VOID);
        keywords.put("true", TokenType.TRUE);
        keywords.put("false", TokenType.FALSE);
        keywords.put("null", TokenType.NULL);
        keywords.put("this", TokenType.THIS);
        keywords.put("let", TokenType.LET);
        keywords.put("do", TokenType.DO);
        keywords.put("if", TokenType.IF);
        keywords.put("else", TokenType.ELSE);
        keywords.put("return", TokenType.RETURN);
  }

    public Scanner (byte[] input) {
        this.input = input;
    }

    private void skipWhitespace() {
        char ch = peek();
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            advance();
            ch = peek();
        }
    }


    private char peek () {
        if (current < input.length)
           return (char)input[current];
       return '\0';
    }

    private void advance()  {
        char ch = peek();
        if (ch != '\0') {
            current++;
        }
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
                c == '_';
}
    
private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || Character.isDigit((c));
}

    private Token number() {
        int start = current ;
        while (Character.isDigit(peek())) {
            advance();
        }
        
        String n = new String(input, start, current-start)  ;
        return new Token(TokenType.NUMBER, n);
    }

    private Token identifier() {
        int start = current;
        while (isAlphaNumeric(peek())) advance();
    
        String id = new String(input, start, current-start)  ;
        TokenType type = keywords.get(id);
        if (type == null) type = TokenType.IDENT;
        return new Token(type, id);
    }


    public Token nextToken () {

        skipWhitespace();

        char ch = peek();

        if (isAlpha(ch)) {
            return identifier();
        }

        if (ch == '0') {
            advance();
            return new Token (TokenType.NUMBER, Character.toString(ch));
        }  else if (Character.isDigit(ch))
            return number();
            
        else if (Character.isDigit(ch))
            return number();
        

        switch (ch) {
            case '/':
            advance();
            return new Token (TokenType.SLASH,"/");

            case '+':
                advance();
                return new Token (TokenType.PLUS,"+");
            case '-':
                advance();
                return new Token (TokenType.MINUS,"-"); 
            case '*':
                advance();
                return new Token (TokenType.ASTERISK,"*"); 
            case '.':
                advance();
                return new Token (TokenType.DOT,"."); 
            case '&':
                advance();
                return new Token (TokenType.AND,"&"); 
            case '|':
                advance();
                return new Token (TokenType.OR,"|"); 
            case '~':
                advance();
                return new Token (TokenType.NOT,"~"); 

            case '>':
                advance();
                return new Token (TokenType.GT,">"); 
            case '<':
                advance();
                return new Token (TokenType.LT,"<"); 
            case '=':
                advance();
                return new Token (TokenType.EQ,"="); 
    
            case '(':
                advance();
                return new Token (TokenType.LPAREN,"("); 
            case ')':
                advance();
                return new Token (TokenType.RPAREN,")"); 
            case '{':
                advance();
                return new Token (TokenType.LBRACE,"{"); 
            case '}':
                advance();
                return new Token (TokenType.RBRACE,"}"); 
            case '[':
                advance();
                return new Token (TokenType.LBRACKET,"["); 
            case ']':
                advance();
                return new Token (TokenType.RBRACKET,"]"); 
            case ';':
                advance();
                return new Token (TokenType.SEMICOLON,";"); 
            case ',':
                advance();
                return new Token (TokenType.COMMA,",");

                case '\0':
                    return new Token (TokenType.EOF,"EOF");
                default:
                    throw new Error("lexical error at " + ch);
        }
    }

    

}
