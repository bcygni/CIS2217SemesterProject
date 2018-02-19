import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.*;

//Ryan Betts
//February 11th, 2018
//Utility Methods
//Run the main method in Utilities to test


public class Main {


    public static void main(String[] args) {

        //Return the Facebook object stored in "facebook.dat" or return a blank Facebook object if the
        //data cannot be loaded successfully
        Facebook fb = loadData();
        Scanner in = new Scanner(System.in);
        int selection = 0;  //store user's menu choice

        //Menu Loop
        //Displays the menu continuously until the user selects to quit
        //Data is only saved when user quits through the menu
        while (true) {

            System.out.println("\n\nMenu");
            System.out.println("1. List Users");
            System.out.println("2. Add User");
            System.out.println("3. Delete User");
            System.out.println("4. Get Password Hint");
            System.out.println("5. Add Friend");
            System.out.println("6. Remove Friend");
            System.out.println("7. List Friends");
            System.out.println("8. Friend Recommendations");
            System.out.println("9. Quit");

            System.out.print("Make a selection: ");
            if(in.hasNextInt()) {
                selection = in.nextInt();
            }
            else {
                in.nextLine();
                selection = 0;
            }

            switch (selection) {
                case (1):
                    fb.displayAllUsers();
                    break;
                case (2):
                    fb.addNewFacebookUser();
                    break;
                case (3):
                    fb.deleteFacebookUser();
                    break;
                case (4):
                    fb.getPasswordHint();
                    break;
                case (5):
                    fb.addFriend();
                    break;
                case (6):
                    fb.removeFriend();
                    break;
                case (7):
                    fb.displayFriendsList();
                    break;
                case (8):
                    fb.displayRecommendations();
                    break;
                case (9):
                    System.out.println("Goodbye!");
                    System.exit(0);
                case(111):
                    fb.listAllUsersAndFriends();
                    break;
                default:
                    System.out.println("Invalid selection.\n");

            }
            //Save the Facebook object at the bottom of every loop through the menu
            saveData(fb);
        }
    }


    //Serializes a Facebook Object to "facebook.dat"
    private static void saveData(Facebook fb) {

        try {
            FileOutputStream fileOut = new FileOutputStream("facebook.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fb);
            out.close();
            fileOut.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving data.");
        }

    }

    //Loads a Facebook object that was created on a previous run of the program
    //and returns it
    private static Facebook loadData() {

        Facebook fb = new Facebook();

        try {
            FileInputStream fileIn = new FileInputStream("facebook.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fb = (Facebook) in.readObject();     //cast as a Facebook object
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("facebook.dat could not be loaded. New Facebook Object created.");
        }

        return fb;

    }


    //
    //
    //Old Methods from previous homework assignment below...
    //
    //

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

    public static void checkAccountsAreEqual(UserAccount acc1, UserAccount acc2){

        if(acc1.equals(acc2)){
            System.out.println("Account " + acc1.toString() + " is the same as " + "Account " + acc2.toString());
        }
        else{
            System.out.println("Account " + acc1.toString() + " is not the same as " + "Account " + acc2.toString());
        }
    }

}
