package Assignment2;

import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Lexer
{
    private static Vector<String> stringVector = new Vector<String>();
    private static Token tokenHolder;
    private static int position;

    public Lexer()
    {
        stringVector = getInput();
        tokenHolder = new Token(Token.TokenCode.INT ,"1337");
        position = 0;
    }

    // Reads input, adds all lines to a single string and removes whitespace.
    // Processed by checkChars before being returned.
    private static Vector<String> getInput()
    {
        Scanner in = new Scanner(System.in);
        String allLines = "";

        while(in.hasNextLine())
        {
            allLines += in.nextLine();
        }

        allLines = allLines.replaceAll("\\s+","");
        allLines += " ";

        return checkChars(allLines);
    }

    //Separates the tokens in "stringToEdit" and adds each instance to the tokenVector.
    private static Vector<String> checkChars(String stringToEdit)
    {
        Vector<String> tokenVector = new Vector<String>();

        for(int i = 0; i < stringToEdit.length(); i++)
        {
            String tempString = "";

            if(Pattern.matches("[A-Za-z]+", Character.toString(stringToEdit.charAt(i)))
                    && i < stringToEdit.length() - 1)
            {
                tempString += Character.toString(stringToEdit.charAt(i));
                i++;

                while(Pattern.matches("[A-Za-z]+", Character.toString(stringToEdit.charAt(i)))
                        && i < stringToEdit.length() - 1)
                {
                    tempString += Character.toString(stringToEdit.charAt(i));
                    i++;
                    if(tempString.equals("print"))
                    {
                        break;
                    }
                }
                tokenVector.add(tempString);
                i--;
            }
            else if(Pattern.matches("[0-9]+", Character.toString(stringToEdit.charAt(i)))
                    && i < stringToEdit.length() - 1)
            {
                tempString += Character.toString(stringToEdit.charAt(i));
                i++;

                while(Pattern.matches("[0-9]+", Character.toString(stringToEdit.charAt(i)))
                        && i < stringToEdit.length() - 1)
                {
                    tempString += Character.toString(stringToEdit.charAt(i));
                    i++;
                }
                tokenVector.add(tempString);
                i--;
            }
            else if(stringToEdit.charAt(i) == '+' || stringToEdit.charAt(i) == '-' || stringToEdit.charAt(i) == '*' ||
                    stringToEdit.charAt(i) == '=' || stringToEdit.charAt(i) == '(' || stringToEdit.charAt(i) == ')' ||
                    stringToEdit.charAt(i) == ';')
            {
                tempString += Character.toString(stringToEdit.charAt(i));
                tokenVector.add(tempString);
            }
        }

        return tokenVector;
    }

    // Generates the next token for the parser.
    public Token nextToken()
    {
        if(stringVector.get(position).equals("end"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.END;
            position++;
        }
        else if(stringVector.get(position).equals("print"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.PRINT;
            position++;
        }
        else if(stringVector.get(position).equals("+"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.ADD;
            position++;
        }
        else if(stringVector.get(position).equals("-"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.SUB;
            position++;
        }
        else if(stringVector.get(position).equals("*"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.MULT;
            position++;
        }
        else if(stringVector.get(position).equals("="))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.ASSIGN;
            position++;
        }
        else if(stringVector.get(position).equals(";"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.SEMICOL;
            position++;
        }
        else if(stringVector.get(position).equals(")"))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.RPAREN;
            position++;
        }
        else if(stringVector.get(position).equals("("))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.LPAREN;
            position++;
        }
        else if(Pattern.matches("[a-zA-Z]+", stringVector.get(position)))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.ID;
            position++;
        }
        else if(Pattern.matches("[0-9]+", stringVector.get(position)))
        {
            tokenHolder.lexeme = stringVector.get(position);
            tokenHolder.tcode = Token.TokenCode.INT;
            position++;
        }
        else
        {
            System.out.println("Lexer error!");
            System.out.println("Syntax error!");
            System.exit(0);
        }

        return tokenHolder;
    }
}