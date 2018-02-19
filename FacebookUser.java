import java.util.ArrayList;

public class FacebookUser extends UserAccount implements Comparable<FacebookUser> {

	String passwordHint;
	ArrayList<FacebookUser> friends;
	
	
	FacebookUser(String username, String password) {
		super(username, password);
		friends = new ArrayList<FacebookUser>();
	}


	//Sets passwordHint field
	public void setPasswordHint(String hint){
		passwordHint = hint;
	}
	
	//Displays passwordHint
	public void getPasswordHelp(){
		System.out.println(passwordHint);
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
		for(FacebookUser fbUser : friends){
			copy.add(fbUser);
		}
		return copy;
	}
	

	//Allows the sort method to compare usernames ignoring casing
	public int compareTo(FacebookUser f){
		return this.toString().compareToIgnoreCase(f.toString());
		
	}

}
