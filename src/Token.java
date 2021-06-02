package Assignment2;

public class Token
{
    enum TokenCode{ID, ASSIGN, SEMICOL, INT, ADD, SUB,
        MULT, LPAREN, RPAREN, PRINT, END, ERROR };

    TokenCode tcode;
    String lexeme;

    public Token(TokenCode tc, String lex)
    {
        this.tcode = tc;
        this.lexeme = lex;
    }
}
