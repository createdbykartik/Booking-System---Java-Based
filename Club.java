/**
 * Swinburne Country Club holds details of Members,Sports,Courts and bookings that are made. it is responsible to read sports,members,courts,bookings and write new bookings.
 * @author Kartik Chadha 
 * @version 0.1
 * 
 */

import java.util.*;
import java.io.*;
import java.time.*;

    public class Club
    {
    private String clubName;
    private ArrayList<Member>memberList;
    private ArrayList<Sport>sportList;
    private ArrayList<Court>courtList;
    private ArrayList<Booking>bookingList;
    private FileUtility FUDetails;
    private Sport sDetails;
    private Booking bDetails;
    private Court cDetails;
    private Tennis tTennis;
    private Squash sSquash;
    public Club(String name)
    {
        this.clubName = name;
        sportList = new ArrayList<Sport>();
        memberList = new ArrayList<Member>();
       courtList = new ArrayList<Court>();
       bookingList = new ArrayList<Booking>();
    }
    /**
     * Method readSports reads the file content in sports.txt and seperates in (,) delimiter and adds new Court and new Sport. 
     * @param sRead String variable that holds value inside for each loop.
     * @param check flag variable used to traverse through the courts. 
     */
    public void readSports() throws Exception
    {
            ArrayList<String>rRead = FUDetails.readFromFile("sports.txt");
            for (String sRead : rRead)
            {
                ArrayList<Court> crtlst = new ArrayList<Court>();
                //System.out.println(sRead);
                int check = 4;
                String[] data= sRead.split(",");
                while(check!= data.length)
                {

                    crtlst.add(new Court(Integer.parseInt(data[check])));
                    courtList = crtlst;
                    check++;
                    
                }
                
                if (data[0].equalsIgnoreCase("tennis"))
                {
                    sportList.add(new Tennis(data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),courtList)); // Tennis being made  
                }
                else if (data[0].equalsIgnoreCase("squash"))
                {
                    sportList.add(new Squash(data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),courtList)); // Squash being made.
                }
                else if (data[0].equalsIgnoreCase("basketball"))
                {
                    sportList.add(new BasketBall(data[0],Double.parseDouble(data[1]),Double.parseDouble(data[2]),Double.parseDouble(data[3]),courtList)); // basketball made.
                }
                    else if (data[0].equals(""))
                    {
                        throw new FileNotFoundExp("Corrupted Field");  //Exception thrown for corrupted file.
                    }
                }
            }   
    /**
     * Method readMembers reads member details from file and seperates in (,) delimiter and adds new member. 
     * @param String sRead used to hold each value inside the loop.
     * @param data[] is a String array splits each element using (,) seperator. 
     * 
     */

    public void readMembers()
    {
         try
        {
            ArrayList<String>rRead = FUDetails.readFromFile("members.txt");

            for (String sRead : rRead)
            {
                String data[] = sRead.split(",");
                memberList.add(new Member(Integer.parseInt(data[0]),data[1],Boolean.parseBoolean(data[2]),sportList));  //Member being made. 
            }
        }
       catch(Exception e)
        {
           KeyBoard.println("test Members"+e.getMessage());             
        }
    }
    
    /**
     * Method readBookings reads booking details from file and seperates in (,) delimiter and adds new booking. 
     * @param String sRead used to hold each value inside the loop.
     * @param data[] is a String array splits each element using (,) seperator. 
     * 
     */
   public void readBookings()
    {
        try
        {   
            ArrayList<String>rRead = FUDetails.readFromFile("bookings.txt");
            ArrayList<Booking>bookingList = new ArrayList<Booking>();
            for (String sRead : rRead)
            {
                    String data[] = sRead.split(",");
                    Court aCourt = findCourt(Integer.parseInt(data[4]));
                    Member aMember = findMember(Integer.parseInt(data[0]));
                    bookingList.add(new Booking(Integer.parseInt(data[0]),LocalDateTime.parse(data[1]),Integer.parseInt(data[3]),aMember,aCourt));
                
            }
            
            for (Booking aBook : bookingList)
            {
                aBook.getMember().addBooking(aBook);
                aBook.getCourt().addBooking(aBook);
            }
        }
        catch(Exception e)
        {
            KeyBoard.println("Error:"+e.getMessage());
        }
    }
    
    /**
     * Method WriteBookings writes member details from file and seperates in (,) delimiter and writes to file. 
     * 
     */
    
    public void writeBookings()
    {
      try
      {
          ArrayList<String>wWrite = new ArrayList<String>();
          ArrayList<Booking>bList = new ArrayList<Booking>();
          for(Member mMember : memberList)
          {
               if (!mMember.getBookings().isEmpty())
               {
                    ArrayList<Booking> bookingList = mMember.getBookings();
                    for (Booking aBook : mMember.getBookings())
                    {
                        bList.add(aBook);
                        bookingList = bList;
                    }
               }
          }
          for(Booking aBook : bList)
          {
              wWrite.add(aBook.getMemberId()+"");
                      wWrite.add(aBook.getBookingDate()+"");
                      wWrite.add(aBook.getDuration()+"");
                      wWrite.add(aBook.getCourt().getCourtId()+"");
                      FUDetails.writeToFile("bookings.txt",wWrite);
          }
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
    /**
     * Method findCourt takes in int and looks for specific court with associated court number.
     * @param int courtId stores the court Id as parameter
     * @return aCourt, court reference to associated court ID.
     * 
     */  
   public Court findCourt(int courtId)
    {
        for(Sport aSport : sportList)
        {
            for(Court aCourt : aSport.getAllCourts())
            {
                if (aCourt.getCourtId()==(courtId))
                {
                    return aCourt;
                }
            }   
        }
        return new Court(0);
    }
    
    /**
     * Method findMember takes in int and looks for specific member with associated member number.
     * @param int memberId stores the member Id as parameter
     * @return aMember reference to member object with associated member ID.
     * 
     */    
    public Member findMember(int memId)
    {
        for (Member aMember : memberList)
        {
            if (aMember.getMemberNumber()==(memId))
            {
                return aMember;
            }
        }
        Member mMember = new Member(0,"",true,sportList);
        return mMember;
    }
    
    /**
     * Method findSport takes in String and looks for specific sport with associated sport name.
     * @param String sName is holds the sport name.
     * @return aSport reference to Sport object associated with that sport name.
     * 
     */
    
    public Sport findSport(String sName) 
    {
            for (Sport aSport : sportList)
        {
            if (aSport.getSportName().equalsIgnoreCase(sName))
            {
                return aSport;
            }
        }
        return new Sport();
    }
    
    /**
     * Method getAvailableCourts takes in sport name, time for booking and booking duration traverses through and stores in temporary array list. 
     * @param boolean isAvailable is a flag variable used to set when court is avaiable or no. 
     * @param aName is of type sport and holds the sport name the members wishes to play. 
     * @return tempCourt temporary arraylist of type courts. 
     * 
     */
    public ArrayList<Court> getAvailableCourts(String sportName,LocalDateTime aTime,int duration) 
    {
        ArrayList<Court> tempCourt = new ArrayList<Court>();
        ArrayList<Court> tempOccBooking = new ArrayList<Court>();
        boolean isAvailable = false;
        Sport aName = findSport(sportName);
        //System.out.println("Name:"+aName);

        for(Court aCourt : aName.getAllCourts())         
        {
            //System.out.println(aCourt.getBookings());
            if(aCourt.getBookings().isEmpty())
                {
                    tempCourt.add(aCourt);
                    
                    //System.out.println("empty court"+aCourt.getCourtId());
                }
            else
            {
                for(Booking aBook : aCourt.getBookings())
                {
                
                    if (!aBook.isExisting(aTime,duration))          
                    {
                        tempCourt.add(aCourt);          
                        isAvailable = true;
                        //System.out.println("Court:"+tempCourt);
                    }
                    else
                    {
                        tempOccBooking.add(aCourt);
                        isAvailable = false;
                    }
                }
            }
        }
        return tempCourt;
    }
    
    /**
     * Method getAllMembers returns a collection of member, present in the list.
     * @return memberList 
     * 
     */
    public ArrayList<Member> getAllMembers()
    {
        return memberList;
    }
    /**
     * Method getSport returns sport object of the selected sport.
     * @return sDetails
     */
    
    public Sport getSport()
    {
        return sDetails;
    }
    
     /**
     * Method findSport takes in String and looks for specific sport with associated sport name.
     * @param String sName is holds the sport name.
     * @return aSport reference to Sport object associated with that sport name.
     * 
     */
    
    public Sport getSportByName(String sName) 
    {
        try
        {
            ArrayList<String>rRead = FUDetails.readFromFile("sports.txt");
            for(Sport aSport : sportList)
            {
                String data= aSport.getSportName();
                if (data.equalsIgnoreCase(sName))
                {
                    return aSport;
                }
                
            }
         
        }
            catch(IOException e)
        {
            KeyBoard.println("Get Sports By Name Error:"+e.getMessage());
        }
        Sport aSport = new Sport("",0.00,0.00,0.00,courtList); 
        return aSport;
    }
    /**
     * Method getAllSports returns a collection of sports present in the sport list.
     * @return sportList
     */
    public ArrayList<Sport> getAllSports()
    {
        return sportList;
    }
    /**
     * Method toString returns the club object details. 
     * @return ClubDetails
     */
    public String toString()
    {
        return "Club Name:"+clubName+"has Sports:"+sportList+" with courts"+ courtList+" and members: "+ memberList;
    }
    
}