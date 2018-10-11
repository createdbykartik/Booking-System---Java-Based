/**
 * Court class holds the court bookings and other attribute to build a court. 
 * 
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */
import java.util.*;
import java.time.*;

public class Court
{
    
    private ArrayList<Booking> courtBookings;
    private int courtNumber;
  
    public Court(int cNumber)
    {
         this.courtNumber = cNumber; 
        courtBookings = new ArrayList<Booking>();
    }
    /**
     * Method returns the court ID
     * @return courtNumber
     */
    
    public int getCourtId()
    {
        return courtNumber;
    }
    
    /**
     * Method adds booking to the collection
     * @param Booking b 
     */
    public void addBooking(Booking b)
    {
        courtBookings.add(b);
    }
    
    /**
     * Method holds the collection of court bookings.
     *@return courtBookings.
     *
     */
    public ArrayList<Booking> getBookings()
    {
        return courtBookings;
    } 
    
    /**
     *  Method returns the object value in form of a string.
     *  return courtNumber
     */
    public String toString()
    {
        return "Court Number:"+courtNumber;
    }  

}
