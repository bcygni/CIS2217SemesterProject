import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

//Ryan Betts
//January 29th, 2018
//Facebook Class
//This program defines the Facebook class which stores a list of FacebookUser objects
//A Facebook object is created at the start of the program. The Facebook object is serialized/saved
//to "facebook.dat" so the list of users can be reloaded even after closing the program
//Also added a couple new custom exceptions IncorrectPasswordException, UsernameAlreadyExistsException
//and UsernameNotFoundException which are used now and could be useful in the future maybe.

public class Main {

    //Scanner for keyboard input use throughout main
    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {

        //Return the Facebook object stored in "facebook.dat" or return a blank Facebook object if the
        //data cannot be loaded successfully
        Facebook fb = loadData();

        int selection;  //store user's menu choice

        //Menu Loop
        //Displays the menu continuously until the user selects to quit
        //Data is only saved when user quits through the menu
        while (true) {

            System.out.println("\n\nMenu");
            System.out.println("1. List Users");
            System.out.println("2. Add User");
            System.out.println("3. Delete User");
            System.out.println("4. Get Password Hint");
            System.out.println("5. Quit");

            System.out.print("Make a selection: ");
            try {
                selection = in.nextInt();
                in.nextLine();

            } catch (InputMismatchException e){
                in.nextLine();
                selection = 0;
            }

            switch (selection) {
                case (1):
                    listUsers(fb);
                    break;
                case (2):
                    addUser(fb);
                    break;
                case (3):
                    deleteUser(fb);
                    break;
                case (4):
                    getPasswordHint(fb);
                    break;
                case (5):
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection.\n");

            }
            //Save the Facebook object at the bottom of every loop through the menu
            saveData(fb);
        }
    }


    //Serializes a Facebook Object to "facebook.dat"
    private static void saveData(Facebook fb){

        try {
            FileOutputStream fileOut = new FileOutputStream("facebook.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(fb);
            out.close();
            fileOut.close();

        } catch (IOException e){
            e.printStackTrace();
            System.out.println("Error saving data.");
        }

    }

    //Loads a Facebook object that was created on a previous run of the program
    //and returns it
    private static Facebook loadData(){

        Facebook fb = new Facebook();

        try {
            FileInputStream fileIn = new FileInputStream("facebook.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            fb = (Facebook)in.readObject();     //cast as a Facebook object
            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("facebook.dat could not be loaded. New Facebook Object created.");
        }

        return fb;

    }

    //Lists the users stored in the Facebook object
    private static void listUsers(Facebook fb){
        fb.displayUserList();
    }

    //Adds a new FacebookUser also only allows a unique username to be added
    private static void addUser(Facebook fb){
        System.out.print("Enter username of user to add: ");
        String username = in.nextLine();

        try {
            fb.addNewUser(username);
        } catch (UsernameAlreadyExistsException e){
            System.out.println("Sorry, that username is already taken.");
        }
    }

    //Deletes a FacebookUser by username entered.
    // Requires the password for that username before it can be deleted
    private static void deleteUser(Facebook fb){
        System.out.print("Enter username of user to delete: ");
        String username = in.nextLine();

        try {
            fb.deleteUser(username);
            System.out.println("User deleted.");
        } catch (IncorrectPasswordException e) {
            System.out.println("Incorrect Password. Cannot delete user.");
        } catch (UsernameNotFoundException e) {
            System.out.println("Username was not found. Cannot delete.");
        }
    }

    //Input a username to get the password hint for that FacebookUser
    private static void getPasswordHint(Facebook fb){
        System.out.print("Enter username of user to get password hint for: ");
        String username = in.nextLine();

        try {
            fb.getPasswordHint(username);
        } catch (UsernameNotFoundException e){
            System.out.println("Username was not found. Cannot display password hint");
        }

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
