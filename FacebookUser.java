import java.util.ArrayList;

public class FacebookUser extends UserAccount implements Comparable<FacebookUser> {

    private String passwordHint;
    private ArrayList<FacebookUser> friends;


    //Constructor for creating a FacebookUser with a username and password
    FacebookUser(String username, String password) {
        super(username, password);
        friends = new ArrayList<FacebookUser>();
    }


    //Constructor for creating a FacebookUser with username, password, and password hint
    FacebookUser(String username, String password, String hint) {
        super(username, password);
        passwordHint = hint;
        friends = new ArrayList<FacebookUser>();
    }

    //Sets passwordHint field
    public void setPasswordHint(String hint){
        passwordHint = hint;
    }

    //Displays passwordHint
    public void getPasswordHelp(){
        System.out.println("Password hint: " + passwordHint);
    }


    //Adds a FacebookUser to the friends ArrayList
    //First checks if the FacebookUser is already in the friends ArrayList
    //and checks if you are trying to add yourself as a friend
    //if it passes these checks, the newFriend is added to the friends ArrayList
    public void friend(FacebookUser newFriend){

        if(friends.contains(newFriend)){
            System.out.println("Error: " + newFriend.toString() + " is already a friend.");
        }
        else if(this.equals(newFriend)){
            System.out.println("Error: You cannot add yourself as a friend.");
        }
        else{
            friends.add(newFriend);
            System.out.println(newFriend.toString() + " is now a friend!");
        }
    }


    //Removes a FacebookUser from the friends ArrayList
    //First checks if the formerFriend is in friends
    //If it is remove formerFriend from friends, otherwise display an error message
    public void defriend(FacebookUser formerFriend){
        if(friends.contains(formerFriend)){
            friends.remove(formerFriend);
            System.out.println(formerFriend.toString() + " has been removed from friends list.");
        }
        else{
            System.out.println("Error: " + formerFriend.toString() + " is not a friend.");
        }
    }



    //getFriends() returns an ArrayList deep copy of the friends ArrayList
    public ArrayList<FacebookUser> getFriends(){
        ArrayList<FacebookUser> copy = new ArrayList<FacebookUser>();
        copy.addAll(friends);
        return copy;
    }


    //Allows the sort method to compare usernames ignoring casing
    @Override
    public int compareTo(FacebookUser f){
        return this.toString().compareToIgnoreCase(f.toString());

    }

}
