
/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Start
{


    public static void main(String[] args) throws Exception
    {
        Club swinClub = new Club("Swinburne Country Club");
        
        swinClub.readSports();
        swinClub.readMembers();
        swinClub.readBookings();
        
        UserInterface consoleApp = new UserInterface(swinClub);
        consoleApp.run();
        swinClub.writeBookings();
    } 


}
