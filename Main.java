import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;

//Ryan Betts
//March 18th, 2018
//Liking Things - 2 new menu options added, 1 for Liking Something and 1 for listing likes.
//Specific users can now have their own individual "Likes" list which can be added to.
//The Likes list only allows unique items and is automatically sorted alphabetically using a TreeSet

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
            System.out.println("1. List Users Alphabetically");
            System.out.println("2. List Users by Number of Friends");
            System.out.println("3. Add User");
            System.out.println("4. Delete User");
            System.out.println("5. Get Password Hint");
            System.out.println("6. Add Friend");
            System.out.println("7. Remove Friend");
            System.out.println("8. List Friends");
            System.out.println("9. Friend Recommendations");
            System.out.println("10. Like Something");
            System.out.println("11. List Likes");
            System.out.println("12. Quit");

            System.out.print("Make a selection: ");
            if(in.hasNextInt()) {
                selection = in.nextInt();
            }
            else {
                in.nextLine();
                selection = -1;
            }

            switch (selection) {
                case (1):
                    fb.displayAllUsers(0);
                    break;
                case (2):
                    fb.displayAllUsers(1);
                    break;
                case (3):
                    fb.addNewFacebookUser();
                    break;
                case (4):
                    fb.deleteFacebookUser();
                    break;
                case (5):
                    fb.getPasswordHint();
                    break;
                case (6):
                    fb.addFriend();
                    break;
                case (7):
                    fb.removeFriend();
                    break;
                case (8):
                    fb.displayFriendsList();
                    break;
                case (9):
                    fb.displayRecommendations();
                    break;
                case (10):
                    fb.addLike();
                    break;
                case (11):
                    fb.displayLikes();
                    break;
                case (12):
                    System.out.println("Goodbye!");
                    System.exit(0);
                case(111):
                    fb.listAllUsersAndFriends();
                    break;
                case(222):
                    fb = importUsers("import_users.txt", fb);
                    break;
                case(333):
                    fb = importFriends("import_friends.txt", fb);
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
            //e.printStackTrace();
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
            //e.printStackTrace();
            System.out.println("facebook.dat could not be loaded. New Facebook Object created.");
        }

        return fb;

    }

    //Imports new FacebookUsers from a text file
    private static Facebook importUsers(String textFile, Facebook fb) {

        try {

            FileReader fileReader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] tempArray;

            while(true) {
                line = bufferedReader.readLine();
                if (line != null) {

                    //Split the line by tabs and store into tempArray
                    tempArray = line.split("\t");

                    //Create new FacebookUser objects and add them the users list
                    try{
                        fb.addNewFacebookUser(tempArray[0], tempArray[1], tempArray[2]);
                    } catch (UsernameAlreadyExistsException e){
                        System.out.println(e + " is already a username. Cannot import.");
                    }

                } else {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error. Could not import users from " + textFile);
        }

        return fb;
    }

    //Imports friend lists from a text file
    private static Facebook importFriends(String textFile, Facebook fb) {

        try {
            FileReader fileReader = new FileReader(textFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String[] tempArray;
            String usernameAdding;


            while(true) {
                line = bufferedReader.readLine();
                if (line != null) {

                    //Split the line by tabs and store into tempArray
                    tempArray = line.split("\t");
                    usernameAdding = tempArray[0];

                    for (String userAdded : tempArray){
                        fb.addFriend(usernameAdding, userAdded);
                    }
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error. Could not import users from " + textFile);
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
