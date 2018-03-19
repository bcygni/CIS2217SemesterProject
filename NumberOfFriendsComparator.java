import java.util.Comparator;

public class NumberOfFriendsComparator implements Comparator<FacebookUser> {

    @Override
    public int compare(FacebookUser u1, FacebookUser u2) {

        //if u1 has a greater number of friends than u2, a positive number will be returned
        int compare = u2.getNumberOfFriends() - u1.getNumberOfFriends();

        //if the two FacebookUsers have the same number of friends, sort alphabetically
        if(compare == 0){
            return u1.toString().compareToIgnoreCase(u2.toString());
        }

        return compare;
    }
}
