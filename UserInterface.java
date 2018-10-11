/** 
 * UserInterface class interacts with users and takes in the input. Processes it, talks to othe classes and delivers the desired output.  
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class UserInterface
{

    private Club swinClub;
    private Member mDetails;
    private Court cDetails;
    private Sport sDetails;
    public UserInterface(Club swinClub)
    {
        this.swinClub = swinClub; 
    }
    /**
     * Method run is a switch case that holds the methods and delivers the output the user wants. 
     */
    public void run()
    {
        while(true)
            switch (menu() ) 
            {
                    case 1:
                        showAvailableCourts();
                        break;
                    case 2:
                        makeBooking();
                        break;
                    case 3:
                        showMemberBookings();
                        break;
                     case 4:
                        showCourtBookings();
                      case 5:
                        deleteBooking();
                        break;      
                     case 6:
                        return;   
                     default:
                        System.out.println ( "Invalid option" );
                        break;
            }
    }
    /**
     * Method menu displays the options to the users with user can interact.
     * @return KeyBoard.getInteger
     * 
     */
    private int menu()
    {   
            KeyBoard.println ("|--------------------------------------------------|");
             KeyBoard.println ("| 1 - Show Available Courts                       |");
             KeyBoard.println ("| 2 - Make Booking for Member                     |");
             KeyBoard.println ("| 3 - Show Member Bookings                        |");
             KeyBoard.println ("| 4 - Show Court Bookings                         |");
             KeyBoard.println ("| 5 - Delete Booking                              |");
             KeyBoard.println ("| 6 - Exit                                        |");
              KeyBoard.println("|-------------------------------------------------|");
            return  KeyBoard.getInteger("Select Option: ");
     }
     
    /**
     * Method showAvailableCourts takes in sport name, booking date and duration for which member wants to book a court and displays which courts are available on the given time.
     * @param int counter is flag variable that traversers till all inputs are met and end as soon as the counter reaches 8
     * @param tempGame used to store the sport user wants to play
     * @param tempBookingDatenTime stores in the booking date and time 
     * @param timeFormat is a datetimeformatter variable that helps parse the data in a given format
     * @param dateTime holds the formatted date and time of the booking user wants to make
     * @param tempDuration holds the total duration user wants to play the sport
     * @param endTime holds the end time of the booking made by the user.
     * 
     */ 
    private void showAvailableCourts() 
    {
            try
            {
                int counter = -1 ;
                while(counter!=8)
                {
                //KeyBoard.getString("Hello! Press Enter to Continue.\n");
                ArrayList<Court> courtList = new ArrayList<Court>();
                String tempGame = KeyBoard.getString("Enter the Sport you wish to play:");
        
                String tempBookingDatenTime = KeyBoard.getString(("Enter Booking Date:"));
                
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(tempBookingDatenTime,format);
                
                if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now().plusDays(7)))
                {
                    KeyBoard.println("Alert: A Booking can only be made 7 days from current date."); break;
                }
                
                int tempDuration = KeyBoard.getInteger("Enter booking Duration(mins):");
                LocalDateTime endTime = dateTime.plusMinutes(tempDuration);
        
                if (tempDuration <= swinClub.getSportByName(tempGame).getDuration())
                {
                    ArrayList<Court> tempCourts = swinClub.getAvailableCourts(tempGame,dateTime,endTime.getMinute());
          
                        for(Court aCourt : tempCourts)
                        { 
                            System.out.println("Court Number: "+aCourt.getCourtId()+": Available.");
                        }
                        
                }
                else
                {
                    KeyBoard.println("No booking can be made for "+tempDuration+"hours, for  "+tempGame); 
                }   
                    counter = 8;
            }
        }
        catch(Exception e)
        {
            KeyBoard.println("Error"+e.getMessage());
        }
    }
    
    /**
     * Method showAvailableCourts takes in sport name, booking date and duration for which member wants to book a court and displays which courts are available on the given time.
     * Once All Conditions are met, court is booked for the sport member wishes to play.
     * @param tempGame used to store the sport user wants to play
     * @param tempBookingDatenTime stores in the booking date and time 
     * @param timeFormat is a datetimeformatter variable that helps parse the data in a given format
     * @param dateTime holds the formatted date and time of the booking user wants to make
     * @param tempDuration holds the total duration user wants to play the sport
     * @param endTime holds the end time of the booking made by the user.
     * @param doesPlay is a flag variable that sets to true if member plays the sport.
     * 
     */
    
    private void makeBooking()
    {
        try
            {
                
                ArrayList<Court> courtList = new ArrayList<Court>();
                int tempMemberId = KeyBoard.getInteger("Enter your Member ID:"); 
                boolean doesPlay = true;
                    if (swinClub.findMember(tempMemberId).getMemberNumber()==tempMemberId)
                    {
                            if (swinClub.findMember(tempMemberId).isFinancial())
                            {
                                String tempGame = KeyBoard.getString("Enter the Sport you wish to play:");
                            if (!swinClub.findSport(tempGame).getSportName().equalsIgnoreCase("Incorrect Sport Name"))
                            {
                        
                                String tempBookingDatenTime = KeyBoard.getString(("Enter Booking Date:"));
                
                                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");
                                LocalDateTime dateTime = LocalDateTime.parse(tempBookingDatenTime,format);
                
                                int tempDuration = KeyBoard.getInteger("Enter booking Duration(mins):");
                        
                                LocalDateTime endTime = dateTime.plusMinutes(tempDuration);
                                
                        if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now().plusDays(7)) && (dateTime.getHour()>06) && (dateTime.getHour()<22))
                        {
                            KeyBoard.println("Alert: A Booking can only be made 7 days from current date."); 
                        }
                
                      else
                       {
                           
                        for(Member mDetails : swinClub.getAllMembers())
                         {
                           //ArrayList<Member> temp = swinClub.getAllMembers();      
                           if (!mDetails.doesPlay(tempGame).equalsIgnoreCase(tempGame))   //checking if member plays the entered game or no.
                                    {
                                        KeyBoard.println("Alert: you are not enrolled for this game."); 
                                        doesPlay = false; break;
                                    }
                         else
                          {
                            if (tempDuration <= swinClub.getSportByName(tempGame).getDuration() && doesPlay)
                            
                            {
                                ArrayList<Court> tempCourts = swinClub.getAvailableCourts(tempGame,dateTime,endTime.getMinute());
                        
                                for(Court aCourt : tempCourts)
                                {
                                    System.out.println("Court Number: "+aCourt.getCourtId()+": Available."); 
                                }
                            
                                ArrayList<Booking>bList = new ArrayList<Booking>();
                                int getCourtNumber = KeyBoard.getInteger("Select the Court Number:");
                                
                                Court aCourt = swinClub.findCourt(getCourtNumber);
                                Member aMember = swinClub.findMember(tempMemberId);
                                
                                Booking bookingMade = new Booking(tempMemberId,dateTime,tempDuration,aMember,aCourt);
                                aCourt.addBooking(bookingMade);
                                aMember.addBooking(bookingMade);
                                
                                KeyBoard.println("Booking Details:"+bookingMade);
                                KeyBoard.println("Booking Made Successfully.");break;
                        }
                          else
                            {
                                KeyBoard.println("No booking can be made for"+tempDuration+"hours, for  "+tempGame);  
                            }
                      }
                    }
                  } 
                }
                   else
                     
                    {
                        KeyBoard.println("No Such Sport Exists! Thankyou for suggestions anyway.");
                    }
                }
                else
                {
                    KeyBoard.println("Alert Member is not Financial.");
                }
            }
                else
                {
                    KeyBoard.println("Alert Member ID Not Found.");
                }
             } 
            
                    catch(Exception e)
                    {
                        KeyBoard.println("Error"+e.getMessage());
                    }
            }
    /**
     * Takes in the member ID and searches for the members, when found displays the bookings made by that member
     * @param memberId is integer that holds the member ID.
     * @param aBook is instance of type booking and it loops through collection of booking details and prints the details required.
     * 
     */    
    public void showMemberBookings()
    {
        int memberId = KeyBoard.getInteger("Enter Member ID:");
        
        if (swinClub.findMember(memberId).getMemberNumber()==memberId)
         {
                Member memberDetails = swinClub.findMember(memberId);
                ArrayList<Booking> bDetails = memberDetails.getBookings();
                if (!bDetails.isEmpty())
                {
                    KeyBoard.println("-----------------------Booking Details:-------------------------------------------------------------------------------------");
                    KeyBoard.println(String.format("%-20s%-30s%-30s%-20s","Member ID","Booking Date and Time","Duration","Court Number"));
                    for (Booking aBook : bDetails)
                    {
                        KeyBoard.println(String.format("%-20s%-30s%-30s%-20s",aBook.getMemberId(),aBook.getBookingDate().getMonth()+","+aBook.getBookingDate().getDayOfMonth()+","+aBook.getBookingDate().getHour()+":"+aBook.getBookingDate().getMinute(),aBook.getDuration(),aBook.getCourt().getCourtId()));
                        KeyBoard.println("----------------------------------------------------------------------------------------------------------------------------");break;
                    }
                }
                 else
                 {
                     KeyBoard.println("Member has no bookings");
                 }
           }
                else
                    {
                        KeyBoard.println("No Member Found");
                    }
    }
        
    /**
     * Takes in the court ID and searches for the courtss, when found displays the bookings made in that court.
     * @param memberId is integer that holds the member ID.
     * @param aBook is instance of type booking and it loops through collection of booking details and prints the details required.
     * 
     */     
    private void showCourtBookings()
    {
        int courtId = KeyBoard.getInteger("Enter Court ID:");
        if (swinClub.findCourt(courtId).getCourtId()==courtId)
        {
            Court aCourt = swinClub.findCourt(courtId);
            ArrayList<Booking> bDetails = aCourt.getBookings();
            if(!bDetails.isEmpty())
            {
                
                    KeyBoard.println("-----------------------Court Details:-------------------------------------------------------------------------------------");
                    KeyBoard.println(String.format("%-20s%-30s%-30s%-20s","Court ID","Booking Date and Time","Duration","Member ID"));
                    for (Booking aBook : bDetails)
                    {
                        KeyBoard.println(String.format("%-20s%-30s%-30s%-20s",aBook.getCourt().getCourtId(),aBook.getBookingDate().getMonth()+","+aBook.getBookingDate().getDayOfMonth()+","+aBook.getBookingDate().getHour()+":"+aBook.getBookingDate().getMinute(),aBook.getDuration(),aBook.getMemberId()));
                        KeyBoard.println("----------------------------------------------------------------------------------------------------------------------------");break;
                    }
            }
            else
            {
                KeyBoard.println("Court has no bookings");
            }
        }
        else
        {
            KeyBoard.println("No Court Found");
        }
    }
    /**
     * Method deleteBooking takes in the member ID to search for the member whose bookings are supposed to deleted. 
     * Once found the booking object of that member is removed from the collection of bookings.
     * @param memberId stores the member ID
     * @param tempDeleteId stores the member ID whose details are being deleted. 
     * 
     */  
    private void deleteBooking()
    {
        int memberId = KeyBoard.getInteger("Enter Member ID:");
        
        if (swinClub.findMember(memberId).getMemberNumber()==memberId)
         {
                Member memberDetails = swinClub.findMember(memberId);
                ArrayList<Booking> bookDetails = memberDetails.getBookings();
                if (!bookDetails.isEmpty())
                {
                    KeyBoard.println("-----------------------Booking Details:-------------------------------------------------------------------------------------");
                    KeyBoard.println(String.format("%-20s%-30s%-30s%-20s","Member ID","Booking Date and Time","Duration","Court Number"));
                    for (Booking aBook : bookDetails)
                    {
                        KeyBoard.println(String.format("%-20s%-30s%-30s%-20s",aBook.getMemberId(),aBook.getBookingDate().getMonth()+","+aBook.getBookingDate().getDayOfMonth()+","+aBook.getBookingDate().getHour()+":"+aBook.getBookingDate().getMinute(),aBook.getDuration(),aBook.getCourt().getCourtId()));
                        KeyBoard.println("----------------------------------------------------------------------------------------------------------------------------");
                    }
                    int tempDeleteId = KeyBoard.getInteger("Enter the Member ID you wish to delete:");
                    if(swinClub.findMember(tempDeleteId).getMemberNumber()==tempDeleteId)
                    {
                        memberDetails.removeBooking(memberDetails.getIndexOf(tempDeleteId));
                        KeyBoard.println("Booking Removed! Successfully.");
                    }
                    else
                    {
                        KeyBoard.println("Invalid Member ID");
                    }
                }
                 else
                 {
                     KeyBoard.println("Member has no bookings");
                 }
           }
                else
                    {
                        KeyBoard.println("No Member Found");
                    }
    }
   
} // end class
