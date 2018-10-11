/** Member class holds the attributes to construct a member and holds a collection of bookings made by the member. 
 * 
 * @author: Kartik Chada
 * @version: 0.1
 * 
 */

import java.util.*;
import java.io.*;

public class Member
{
    private String memberName;
    private int memberNumber;
    private boolean financial;
    private ArrayList<Sport>sportsPlayed;
    FileUtility FUDetails;
    private ArrayList<Booking>bookings;
    public Member(int memberNumber,
                  String memberName,
                  boolean financial,
                  ArrayList<Sport>sports
                  )
    {
        this.memberNumber = memberNumber;
        this.memberName = memberName;
        this.financial = financial;
        this.sportsPlayed = sports;
        bookings = new ArrayList<Booking>();
    }
   /**
    * Method returns the member name 
    * @return memberName
    */ 
    public String getMemberName()
    {
        return memberName; 
    }
    /**
     * Method adds booking to collection. Takes in booking object as parameter.
     * @param aBook
     */
    public void addBooking(Booking aBook)
    {
        bookings.add(aBook);
        //return bookings;
    }
    /**
     * Method removes booking from the collection, takes in the member ID whose booking is supposed to be removed. 
     * @param memberId holds the member ID 
     */
    public void removeBooking(int memberId)
    {
        bookings.remove(memberId);
    }
    
    /**
     * Method returns index of the object present in the collection. 
     * @param memberId holds the member ID
     * @return index of booking object which is requested.
     */
    public int getIndexOf(int memberId)
    {
        for(Booking aBook : bookings)
        {
            if (aBook.getMember().getMemberNumber()==memberId)
            {
                return bookings.indexOf(aBook);
            }
        }
        return 1;
    }
    /**
     * Method sorts the collection in asscending order. 
     */
    public void sortBooking()
    {
        Collections.sort(bookings);
    }
    
    /**
     * Method returns the booking collection object.
     * @return bookings
     */
    public ArrayList<Booking> getBookings()
    {
        return bookings;
    } 
    
    /**
     * Method doesPlay tells the user interface whether the member plays the opted sport or no.
     * @param String sName
     * @return sport Details.
     */
    public String doesPlay(String sName)
    {
         try
        {
            ArrayList<String>rRead = FUDetails.readFromFile("members.txt");
            
            for (String sRead : rRead)
            {
                String data[] = sRead.split(",");
                if (data[3].equalsIgnoreCase(sName))
                {
                    //KeyBoard.println(data[3]);
                    return "Tennis"; 
                }
                else if (data[3].equalsIgnoreCase(sName))
                {
                    return "Squash";
                }
                else if (data[3].equalsIgnoreCase(sName))
                {
                    return "Basketball";
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Error!:"+e.getMessage());
        }
        return "No Games Assigned.";
    }
    
    /**
     * Method returns the memberNumber 
     * @return memberNumber;
     * 
     */
    public int getMemberNumber()
    {
       return memberNumber;
    }
    
    /**
     * Method checks for whether the member is financia or no. 
     * @return true.
     */
    public boolean isFinancial()
    {
        if (financial)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /**
     * Method prints the object details of member
     * @return Member Details.
     */
    public String toString()
    {
        return "Member Name:"+getMemberName()+"Member Number:"+getMemberNumber();
    }
    
}