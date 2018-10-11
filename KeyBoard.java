
import java.util.*;
import java.io.*;
import java.time.*;


public class KeyBoard
{
    // instance variables - replace the example below with your own
    private static Scanner in = new Scanner(System.in);
    
    public static String getString(String prompt)
    {
        System.out.print(prompt + " " );
        return in.nextLine();
    }
    
    public static Double getDouble(String prompt)
    {
        Double d = 0.00;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                d = Double.parseDouble(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Double");
            }
        }
        return d;
    }
    
    public static Integer getInteger(String prompt)
    {
        Integer i = 0;
        while(true)
        {
            try
            {
                System.out.print(prompt + " ");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid Integer");
            }
        }
        return i;
    }
        
    public static void println(String toPrint)
    {
        System.out.println(toPrint);
    }
    
    public static Integer getInteger()
    {
        Integer i = 0;
        while (true)
        {
            try
            {
                System.out.print("Please enter an integer");
                i = Integer.parseInt(in.nextLine());
                break;
            }
            catch(Exception e)
            {
                System.out.println("Not a valid integer");
            }
        }
        return i;
    }
}
