/**
 * BasketBall is a child class of Sport. Holds the attributes to build the basketball object.
 *@author Kartik Chadha 
 *@version 0.1
 * 
 */

import java.util.*;
/**
 * Write a description of class BasketBall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BasketBall extends Sport
{
                  public BasketBall(
                  String Name,
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
