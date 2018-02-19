import java.util.ArrayList;
import java.util.Collections;

//Ryan Betts
//January 21th, 2018
//UserAccount Class
//This program tests the FacebookUser class which is a subclass of UserAccount

public class Driver {

    public static void main(String[] args){

    	FacebookUser account1 = new FacebookUser("JoeT", "redfish1230");
    	FacebookUser account2 = new FacebookUser("SallyG", "bluefish545");
    	FacebookUser account3 = new FacebookUser("KatieS", "yellowfish231");
    	FacebookUser account4 = new FacebookUser("PeterL", "purplefish512");
    	FacebookUser account5 = new FacebookUser("DonaldT", "greenfish619");
    	FacebookUser account6 = new FacebookUser("HillaryC", "orangefish444");
    	FacebookUser account7 = new FacebookUser("AlexR", "catfish123");

    	
        account1.setPasswordHint("this is a password hint");
        account1.getPasswordHelp();

        account1.friend(account2);
        account1.friend(account3);
        account1.friend(account4);
        account1.friend(account5);
        account1.friend(account6);
        account1.friend(account7);
        
        //Try to add a friend for an account that is already in the friends list
        //Try to add itself as a friend
        account1.friend(account2);
        account1.friend(account1);
        
        //Remove a friend
        account1.defriend(account2);
        //Try to remove a friend that is not in friends list
        account1.defriend(account2);
        
        
        
        ArrayList<FacebookUser> acc1Friends = account1.getFriends();
        
        System.out.println("\n\nBefore sorting - here is " + account1.toString() + "'s friends.");
        displayUsers(acc1Friends);
        
        sortUsers(acc1Friends);
        System.out.println("\n\nAfter sorting: ");
        displayUsers(acc1Friends);
        
        
        System.out.println("\n\nOriginal list (to show a true deep copy was created): ");
        displayUsers(account1.getFriends());
        
        
        account1.defriend(account7);
        
        System.out.println("\n\nOriginal list after removing a friend): ");
        displayUsers(account1.getFriends());        
        
        
    }


    //Sorts an ArrayList of FacebookUsers
    public static void sortUsers(ArrayList<FacebookUser> UsersToSort){
    	Collections.sort(UsersToSort);
    	System.out.println("\nList has been sorted");
    }
    
    //Display an ArrayList of FacebookUsers
    public static void displayUsers(ArrayList<FacebookUser> UsersToDisplay){
    	for(FacebookUser user : UsersToDisplay){
    		System.out.println(user.toString());
    	}
    }   
    
    
    
    
    
    //
    //
    //Old Methods from previous homework assignment below...
    //
    //
    public static void checkAccountsAreEqual(UserAccount acc1, UserAccount acc2){

        if(acc1.equals(acc2)){
            System.out.println("Account " + acc1.toString() + " is the same as " + "Account " + acc2.toString());
        }
        else{
            System.out.println("Account " + acc1.toString() + " is not the same as " + "Account " + acc2.toString());
        }
    }

    public static void enterPassword(UserAccount account, String password){

        if(account.checkPassword(password)){
            System.out.println("Password is correct.");
        }
        else{
            System.out.println("Password is incorrect.");
        }
    }


}
