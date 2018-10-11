/**
 * Booking holds the attributes required to make a booking object and implements comparable interface. 
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */
import java.time.*;

public class Booking implements Comparable<Booking>
{
    private int memberId;
    private LocalDateTime bookingDate;
    private LocalDateTime startTime;
    private LocalTime endTime;
    private int duration;
    Member bookedBy;
    Court bookedFor;
    
    public Booking(
                   int memberId, 
                   LocalDateTime bookingDatenTime,
                   int duration,
                   Member bookedBy,
                   Court bookedFor)
    {
          this.memberId = memberId;
          this.bookingDate = bookingDatenTime;
          this.startTime = startTime;
          //this.endTime = endTime;
          this.duration = duration;
          this.bookedBy = bookedBy;
          this.bookedFor = bookedFor;
    }  
    
    /**
     * Method takes in the time and date of the booking with duration and check with existing bookings, if it overlaps the existing bookings,it returns false. Else true
     * @param aDateTime holds the date and time of the booking
     * @param duration holds the duration of the booking
     * @return false
     * 
     */
    public boolean isExisting(LocalDateTime aDateTime,int duration)
    {
        if ((aDateTime.isBefore(bookingDate) && aDateTime.plusMinutes(duration).isBefore(bookingDate)) || (aDateTime.isAfter(bookingDate.plusMinutes(duration)) ))
        {
            return false;
        }
        else
        return true;
    }
    
    /**
     * Method compares the booking object with booking date and returns the comparable value.
     * @return compare value
     * 
     */
    public int compareTo(Booking aBook)
    {
        return getBookingDate().compareTo(aBook.getBookingDate());
    }
    
    /**
     * Method returns the court object
     * @return bookedFor
     */
    public Court getCourt()
    {
        return bookedFor;
    }
    
    /**
     * Method getMemberId returns the member ID  
     * @return MemberId
     */
    public int getMemberId()
    {
        return memberId;
    }
    
    /**
     * Method returns the booking date
     * @return bookingDate
     */
    public LocalDateTime getBookingDate()
    {
        return bookingDate;
    }
    
    
    /**
     * Method returns the booking duration
     * @return duration
     */
    public int getDuration()
    {
        return duration;
    }
   
    /**
     * Method returns the booking start time 
     * @return startTime
     */
    public LocalDateTime getStartTime()
    {
        return startTime;
    }
    
    /**
     * Method returns the member Object 
     * @return bookedBy
     */
    public Member getMember()
    {
        return bookedBy;
    }
    
    
    /**
     * Method returns End Time 
     * @return endTime;
     */
    public LocalTime getEndTime()
    {
        return endTime;
    }    
    /**
     * Method return the booking object details.
     * @return BookingDetails.
     */
    public String toString()
    {
        return " Member ID: "+getMemberId()+" Booking Date: "+bookingDate.getMonth()+" "+ bookingDate.getDayOfMonth()+", "+ bookingDate.getHour()+":"+bookingDate.getMinute()+" Start Time: "+bookingDate.getHour()+":"+bookingDate.getMinute();
    }
}
