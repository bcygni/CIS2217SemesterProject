import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Facebook implements Serializable {

    static Scanner in = new Scanner(System.in);

    private ArrayList<FacebookUser> users;

    public Facebook() {
        users = new ArrayList<>();

    }

    //Public methods that are used directly in driver program ***************************************

    public void displayAllUsers() {
        if (users.size() > 0) {
            for (FacebookUser fbUser : users) {
                System.out.println(fbUser.toString());
            }
        } else {
            System.out.println("Error - No users to display!");
        }
    }


    public void addNewFacebookUser() {
        System.out.print("Enter username for new account: ");
        String username = in.nextLine();

        if (checkIfUsernameExists(username, users)) {
            System.out.println("Sorry! That username is already in use.");
        }
        else{
            System.out.print("\nEnter password for new account: ");
            String password = in.nextLine();
            System.out.print("\nEnter password hint: ");
            String passwordHint = in.nextLine();
            users.add(new FacebookUser(username, password, passwordHint));
        }


    }

    //Removes the FacebookUser associated with the username from the users ArrayList
    //Requires the password for the UserAccount in order to remove it
    public void deleteFacebookUser() {

        try {
            FacebookUser userToDelete = getUser("Enter username of user to delete: ");
            enterPassword(userToDelete);
            removeUser(userToDelete);
            System.out.println(userToDelete.toString() + " was successfully deleted.");

        } catch (UsernameNotFoundException u) {
            System.out.println("Error: " + u.username + " was not found. Cannot delete");

        } catch (IncorrectPasswordException i) {
            System.out.println("Error: Password is incorrect. Cannot delete.");
        }

    }


    public void getPasswordHint() {

        try {
            FacebookUser user = getUser("Enter username to get password hint: ");
            user.getPasswordHelp();

        } catch (UsernameNotFoundException u) {
            System.out.println("Error: " + u.username + " was not found. Cannot display password hint.");
        }

    }



    public void addFriend() {

        try {

            FacebookUser userAddingFriend = getUser("Enter username that will be adding a new friend: ");
            enterPassword(userAddingFriend);
            FacebookUser friendToAdd = getUser("Enter username of user to add to friends list: ");
            userAddingFriend.friend(friendToAdd);  //call the friend method to add the new friend to that users friends
            System.out.println(friendToAdd.toString() + " is now a friend.");

        } catch (UsernameNotFoundException u) {
            System.out.println("Error: " + u.username + " could not be found.");

        } catch (IncorrectPasswordException i) {
            System.out.println("Error: Incorrect password.");

        } catch (UsernameAlreadyExistsException a) {
            System.out.println("Error: " + a.username + " is already a friend.");

        } catch (SameUsernameException s) {
            System.out.println("Error: You cannot add yourself as a friend!");
        }

    }


    public void removeFriend() {

        try {

            FacebookUser userRemovingFriend = getUser("Enter username that will be removing a friend: ");
            System.out.println("Password.");
            enterPassword(userRemovingFriend);
            FacebookUser friendToRemove = getUserFromList(
                    "Enter username of user to remove from friends list: ", userRemovingFriend.getFriends() );
            userRemovingFriend.unfriend(friendToRemove);  //call the unfriend method to remove friend from friends list
            System.out.println(friendToRemove.toString() + " has been removed from friends list.");
            
        } catch (UsernameNotFoundException u) {
            System.out.println("Error: " + u.username + " could not be found.");

        } catch (IncorrectPasswordException i) {
            System.out.println("Error: Incorrect password.");

        }

    }


    public void displayFriendsList() {

        try {

            FacebookUser user = getUser("Enter username of user to display friends list: ");
            enterPassword(user);
            displayUserList(user.getFriends());

        } catch (UsernameNotFoundException u) {
            System.out.println("Error: " + u.username + " could not be found. ");

        } catch (IncorrectPasswordException i) {
            System.out.println("Error: Incorrect password.");
        }
    }



    private void enterPassword(FacebookUser user) throws IncorrectPasswordException{
        System.out.print("Enter password: ");
        String password = in.nextLine();

        if(!user.checkPassword(password)){
            throw new IncorrectPasswordException();
        }
    }



    //Private Methods that take Strings as arguments ******************************************
    //**************************************************************************************************


    //Returns true if the username is already in the userList, false if it is not in the userList
    private boolean checkIfUsernameExists(String username, ArrayList<FacebookUser> userList) {
        for (FacebookUser user : userList) {
            if (user.toString().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //Prompts the user for a username and then returns the FacebookUser object associated with that username
    private FacebookUser getUser(String prompt)
        throws UsernameNotFoundException {
        System.out.print(prompt);
        String username = in.nextLine();
        
        for (FacebookUser user : users) {
            if (user.toString().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException(username);
    }

    
    private FacebookUser getUserFromList(String prompt, ArrayList<FacebookUser> userList)
            throws UsernameNotFoundException {
    	
        System.out.print(prompt);   	
        String username = in.nextLine();
        
        for (FacebookUser user : userList) {
            if (user.toString().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException(username);
    }


    public void listAllUsersAndFriends(){
        for (FacebookUser user : users) {
            System.out.println("\n\n\n\t\t" + user.toString());
            displayUserList(user.getFriends());
            }
    }


    public void displayRecommendations(){

        ArrayList<FacebookUser> recommendations = new ArrayList<>();

        try {
            FacebookUser user = getUser("Enter username of user to display recommended friends: ");
            enterPassword(user);
            displayUserList(getRecommendations(user, recommendations, user));
        } catch (UsernameNotFoundException u){
            System.out.println("Error: " + u.username + " could not be found.");
        } catch (IncorrectPasswordException i){
            System.out.println("Error: Incorrect password.");
        }

    }


    private ArrayList<FacebookUser> getRecommendations(FacebookUser user,
                                                       ArrayList<FacebookUser> recommendations, FacebookUser origUser){

        for(FacebookUser recFriend : user.getFriends()){

            //If the recommendations list already contains the new recommended friend, return the recommendations list
            //Because all of their friends (and friends of friends) should already be in the list
            if(recommendations.contains(recFriend)){
                //System.out.println(recFriend.toString() + " is already in the recommended friends list");
                return recommendations;
            }

            //Otherwise, add the new recommended friend to recommendations and then get that user's friends of friends
            //by recursively calling the getRecommendations method on the new recommended friend
            else {

                //if the current user is the original user, don't add their friends as recommended friends
                if(!user.equals(origUser)) {
                    //System.out.println("We recommend " + user.toString() + "'s friend " + recFriend.toString());
                    recommendations.add(recFriend);

                }

                //Recursive call of getRecommendations
                getRecommendations(recFriend, recommendations, origUser);

                //if the original user's friends already contains the recommended friend, remove that user from
                //the recommended friends so as to not recommend a user that is already a friend or recommend themselves
                if(origUser.getFriends().contains(recFriend) || recFriend.toString().equals(origUser.toString())){
                    recommendations.remove(recFriend);
                }
            }
        }

        return recommendations;
    }




    //Methods that take FacebookUser objects or lists as arguments and directly affect an ArrayList of FacebookUsers

    private void displayUserList(ArrayList<FacebookUser> userList) {
        if (userList.size() > 0) {
            for (FacebookUser fbUser : userList) {
                System.out.println(fbUser.toString());
            }
        } else {
            System.out.println("Error - No users to display!");
        }
    }

    private void removeUser(FacebookUser userToRemove) {
        users.remove(userToRemove);
    }

    private void removeUser(FacebookUser userToRemove, ArrayList<FacebookUser> userList){
        userList.remove(userToRemove);
    }

    private void addUser(FacebookUser userToAdd){
        users.add(userToAdd);
    }

    private void addUser(FacebookUser userToAdd, ArrayList<FacebookUser> userList){
        userList.add(userToAdd);
    }


}
