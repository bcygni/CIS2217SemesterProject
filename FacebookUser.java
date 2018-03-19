import java.util.ArrayList;
import java.util.TreeSet;


public class FacebookUser extends UserAccount implements Comparable<FacebookUser> {

    private String passwordHint;
    private ArrayList<FacebookUser> friends;
    private TreeSet<String> likes;


    //Constructor for creating a FacebookUser with a username and password
    FacebookUser(String username, String password) {
        super(username, password);
        friends = new ArrayList<FacebookUser>();
        likes = new TreeSet<String>();
    }

    //Constructor for creating a FacebookUser with username, password, and password hint
    FacebookUser(String username, String password, String hint) {
        super(username, password);
        passwordHint = hint;
        friends = new ArrayList<FacebookUser>();
        likes = new TreeSet<String>();
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
    public void friend(FacebookUser newFriend) throws UsernameAlreadyExistsException, SameUsernameException{

        if(friends.contains(newFriend)){
            throw new UsernameAlreadyExistsException(newFriend.toString());
        }
        else if(this.equals(newFriend)){
            throw new SameUsernameException(newFriend.toString());
        }
        else{
            friends.add(newFriend);
        }
    }


    //Removes a FacebookUser from the friends ArrayList
    public void unfriend(FacebookUser formerFriend) {
        friends.remove(formerFriend);
    }


    //Returns true if the thing is successfully added to the likes list, otherwise returns false
    public Boolean addLike(String thing){
        return likes.add(thing);
    }

    //Prints the likes TreeSet
    public void printUserLikes() {
        if (likes.size() > 0) {
            Utilities.printTreeSet(likes);
        } else {
            System.out.println("Error - No likes to display!");
        }

        System.out.println("\nTotal Likes: " + likes.size());

    }


    public int getNumberOfFriends(){
        return this.friends.size();
    }

    //getFriends() returns an ArrayList deep copy of the friends ArrayList
    public ArrayList<FacebookUser> getFriends(){
        return Utilities.copyList(friends);
    }

    public TreeSet<String> getLikes(){
        return likes;
    }


    //Allows the sort method to compare usernames ignoring casing
    @Override
    public int compareTo(FacebookUser f){
        return this.toString().compareToIgnoreCase(f.toString());

    }

}
