/**
*Sport class is the parent class and holds the sport attributes. Also holds the collection of courts
*@author: Kartik Chadha
*@version: 0.1
*
*/

import java.util.*;
import java.io.*;
public class Sport
{
    private String sportName;
    private double usagefee;
    private double insurance; 
    private double affiliation_fees;
    protected int duration;
    private ArrayList<Court> courts;
    public Sport()
    {
        sportName = "Incorrect Sport Name";
    }

    public Sport(String sportName,
                 double usagefee,
                 double insurance, 
                 double affiliation_fees,
                 ArrayList<Court> courts)
        {        
            this.sportName = sportName;
            this.usagefee = usagefee;
            this.insurance = insurance;
            this.affiliation_fees = affiliation_fees; 
            this.courts = courts;
        }
    /**
     * Method returns the duration of the booking
     * @return duration
     */    
    public int getDuration()
    {
        return duration; 
    }
    
    /**
     * Method returns the Useage fee of the booking
     * @return usagefee
     */    
    public double getUsageFee()
    {
        return usagefee;
    }
    
    /**
     * Method returns the insurance fee of the booking
     * @return insurance 
     */    
    public double getInsuranceFee()
    {
        return insurance;
    }
    
    /**
     * Method returns the Affilliation fees of the booking
     * @return affiliation_fees
     */    
    public double getAffiliationFee()
    {
        return affiliation_fees;
    }
    
    /**
     * Method returns the Sport Name 
     * @return sportName
     */    
    public String getSportName()
    {
        return sportName;
    }
    
    /**
     * Method returns the collection of courts object
     * @return courts
     */    
    public ArrayList<Court> getAllCourts()
    {
        return courts;
    }
    
    /**
     * Method returns the Object details of the Sport
     * @return Sport Details.
     */    
    
    public String toString()
    {
        return "Sport Name:"+sportName+"Usage Fee:"+usagefee+"Insurance:"+insurance+"Affiliation Fees:"+affiliation_fees+courts;
    }
}