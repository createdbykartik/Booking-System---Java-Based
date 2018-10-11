
import java.util.*;
import java.io.*;

public class FileUtility
{
    public FileUtility()
    {
    }

    /**
     * Reads data from file returning the lines as a list, or null if error
     * 
     */
    public static ArrayList<String> readFromFile(String fileName) throws FileNotFoundException,IOException
    {
            ArrayList<String> tempData = null;
            //System.out.println("Read Data Working.");
            try
            {
                tempData = new ArrayList<String>();
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                
                String temp ; 
                temp = in.readLine();
                temp = in.readLine();
                while ((temp =  in.readLine())!= null)
                {
                    tempData.add(temp);
                    //System.out.println(temp);
                }
                in.close();
                 
            }
            catch (FileNotFoundException e)
            {
                KeyBoard.println("File Not Found!"+e.getMessage());
            }
           
             return tempData;
    }
    
    
    /**
     * Write data to file 
     * 
     */
    public static void writeToFile(String fileName, ArrayList<String> data)
    {
         //System.out.println("Write Data Working");
        try
        {
            PrintWriter wWrite = new PrintWriter(new FileWriter(fileName,true));
            String sep = "";
            for(String s : data)
            {
                wWrite.print(sep);
                wWrite.print(s);
                sep = ",";
            }
            wWrite.print("\r\n");
            wWrite.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
