/**
 * Tennis class is a child class of Sport and holds attributes to make an object of tennis
 * 
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */
import java.util.*;
public class Tennis extends Sport
{   

    public Tennis(String Name,
                  double usagefee,
                  double insurance, 
                  double affiliation_fees,
                  ArrayList<Court> courts)
    {
        super(Name,usagefee,insurance,affiliation_fees,courts);
        duration = 120;
    }
    
    public String toString()
    {
        return super.toString();
    }

}
