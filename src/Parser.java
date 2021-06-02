package Assignment2;

public class Parser
{
    private Token nextToken;
    private Lexer lexer;

    public Parser(Lexer myLexer)
    {
        this.lexer = myLexer;
    }

    public void parse()
    {
        nextToken = lexer.nextToken();
        Statements();
    }

    private void Statements()
    {
        if(nextToken.tcode == Token.TokenCode.END)
        {
            return;
        }
        Statement();

        if(nextToken.tcode == Token.TokenCode.SEMICOL)
        {
            nextToken = lexer.nextToken();
            Statements();
        }
        else
        {
            System.out.println("Syntax error!");
        }
    }

    private void Statement()
    {
        if(nextToken.tcode == Token.TokenCode.ID)
        {
            System.out.println("PUSH " + nextToken.lexeme);
            nextToken = lexer.nextToken();
            if(nextToken.tcode == Token.TokenCode.ASSIGN)
            {
                nextToken = lexer.nextToken();
                Expr();
                System.out.println("ASSIGN");
            }
        }
        else if(nextToken.tcode == Token.TokenCode.PRINT)
        {
            nextToken = lexer.nextToken();
            System.out.println("PUSH " + nextToken.lexeme);
            System.out.println("PRINT");
            nextToken = lexer.nextToken();
        }
        else
        {
            System.out.println("Syntax error!");
        }
    }

    private void Expr()
    {
        Term();
        if(nextToken.tcode == Token.TokenCode.ADD)
        {
            nextToken = lexer.nextToken();
            if(nextToken.tcode == Token.TokenCode.ID ||  nextToken.tcode == Token.TokenCode.INT ||
                    nextToken.tcode == Token.TokenCode.LPAREN)
            {
                Expr();
                System.out.println("ADD");
            }
            else
            {
                System.out.println("Syntax error!");
            }
        }
        else if(nextToken.tcode == Token.TokenCode.SUB)
        {
            nextToken = lexer.nextToken();
            if(nextToken.tcode == Token.TokenCode.ID ||  nextToken.tcode == Token.TokenCode.INT ||
                    nextToken.tcode == Token.TokenCode.LPAREN)
            {
                Expr();
                System.out.println("SUB");
            }
            else
            {
                System.out.println("Syntax error!");
            }
        }
    }

    private void Term()
    {
        Factor();
        nextToken = lexer.nextToken();
        if(nextToken.tcode == Token.TokenCode.MULT)
        {
            nextToken = lexer.nextToken();
            if(nextToken.tcode == Token.TokenCode.ID || nextToken.tcode == Token.TokenCode.INT ||
                    nextToken.tcode == Token.TokenCode.LPAREN)
            {
                Term();
                System.out.println("MULT");
            }
            else
            {
                System.out.println("Syntax error!");
            }
        }
    }

    private void Factor()
    {
        if(nextToken.tcode == Token.TokenCode.ID ||  nextToken.tcode == Token.TokenCode.INT)
        {
            System.out.println("PUSH " + nextToken.lexeme);
        }
        else if(nextToken.tcode == Token.TokenCode.LPAREN)
        {
            nextToken = lexer.nextToken();
            Expr();
            if(nextToken.tcode != Token.TokenCode.RPAREN)
            {
                System.out.println("Syntax error!");
            }
        }
        else
        {
            System.out.println("Syntax error!");
        }
    }
}
