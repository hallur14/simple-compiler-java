package Assignment2;

import java.util.*;

public class Interpreter
{
    private static String input;
    private static Stack stack = new Stack();
    private static HashMap<String, String> hMap = new HashMap<String, String>();
    private static String temp1;
    private static String temp2;
    private static int value1;
    private static int value2;

    public Interpreter()
    {
        input = "";
        temp1 = "";
        temp2 = "";
        value1 = 0;
        value2 = 0;
    }

    private static void calculate(char op)
    {
        temp1 = temp2 = "";
        value1 = value2 = 0;
        temp1 = (String) stack.pop();
        temp2 = (String) stack.pop();
        if(hMap.containsKey(temp1) || hMap.containsKey(temp2))
        {
            if(hMap.containsKey(temp1) && hMap.containsKey(temp2))
            {
                value1 = Integer.parseInt(hMap.get(temp1));
                value2 = Integer.parseInt(hMap.get(temp2));
                if(op == '+')
                {
                    hMap.put(temp1, Integer.toString(value1 + value2));
                }
                if(op == '-')
                {
                    hMap.put(temp1, Integer.toString(value2 - value1));
                }
                if(op == '*')
                {
                    hMap.put(temp1,Integer.toString(value1 * value2));
                }

                stack.push(temp1);
            }
            else if(hMap.containsKey(temp1)) {
                value1 = Integer.parseInt(hMap.get(temp1));
                value2 = Integer.parseInt(temp2);
                if(op == '+')
                {
                    hMap.put(temp1, Integer.toString(value1 + value2));
                }
                if(op == '-')
                {
                    hMap.put(temp1, Integer.toString(value2 - value1));
                }
                if(op == '*')
                {
                    hMap.put(temp1,Integer.toString(value1 * value2));
                }
                stack.push(temp1);
            }
            else
            {
                value1 = Integer.parseInt(temp1);
                value2 = Integer.parseInt(hMap.get(temp2));
                if(op == '+')
                {
                    hMap.put(temp1, Integer.toString(value1 + value2));
                }
                if(op == '-')
                {
                    hMap.put(temp1, Integer.toString(value2 - value1));
                }
                if(op == '*')
                {
                    hMap.put(temp1,Integer.toString(value1 * value2));
                }
                stack.push(temp2);
            }
        }
        else
        {
            value1 = Integer.parseInt(temp1);
            value2 = Integer.parseInt(temp2);
            if(op == '+')
            {
                value1 = value1 + value2;
            }
            if(op == '-')
            {
                value1 = value2 - value1;
            }
            if(op == '*')
            {
                value1 = value1 * value2;
            }
                stack.push(Integer.toString(value1));
        }
    }

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        while(in.hasNextLine())
        {
            input = in.nextLine();

            Vector<String> stringArr = new Vector<String>(Arrays.asList(input.split(" ")));
            if(stringArr.get(0).equals("PUSH"))
            {
                stack.push(stringArr.get(1));
            }
            else if(stringArr.get(0).equals("ASSIGN"))
            {
                temp1 = temp2 = "";
                value1 = value2 = 0;
                temp1 = (String) stack.pop();
                temp2 = (String) stack.pop();
                if(hMap.containsKey(temp1))
                {
                    temp1 = hMap.get(temp1);
                }
                if(hMap.containsKey(temp2))
                {
                    temp2 = hMap.get(temp2);
                }
                hMap.put(temp2, temp1);
            }
            else if(stringArr.get(0).equals("ADD"))
            {
                calculate('+');
            }
            else if (stringArr.get(0).equals("SUB"))
            {
                calculate('-');
            }
            else if (stringArr.get(0).equals("MULT"))
            {
                calculate('*');
            }
            else if (stringArr.get(0).equals("PRINT"))
            {
                String temp1 = (String) stack.pop();
                System.out.println(hMap.get(temp1));
            }
            else
            {
                System.out.println("Interpreter Error!");
            }
        }
    }
}