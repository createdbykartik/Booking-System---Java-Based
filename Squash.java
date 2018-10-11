/**
 * Squash is a child class of Sport and holds the attributes to build an object of Squash.
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */
import java.util.*;
public class Squash extends Sport
{
    public Squash(String Name,
				  double usagefee,
    			  double insurance, 
   				  double affiliation_fees,
				  ArrayList<Court> courts )
    {
        super(Name,usagefee,insurance,affiliation_fees,courts);
        duration = 60;   
   }
	
	
    public String toString()
    {
        return super.toString();
    }

}
