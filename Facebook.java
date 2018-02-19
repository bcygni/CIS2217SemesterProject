import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Facebook implements Serializable {

    private ArrayList<FacebookUser> users;

    public Facebook() {
        users = new ArrayList<>();

    }

    //Displays the usernames for all FacebookUser objects in the users ArrayList
    public void displayUserList() {

        if(users.size() > 0) {
            for (FacebookUser fbUser : users) {
                System.out.println(fbUser.toString());
            }
        }
        else{
            System.out.println("Error - No users to display!");
        }

    }


    //Creates a new FacebookUser and adds it the the users list (only allows unique usernames)
    //Throws UsernameAlreadyExistsException if the the new FacebookUser's username is not unique
    public void addNewUser(String username) throws UsernameAlreadyExistsException {

        if(checkIfUsernameExists(username)){
            throw new UsernameAlreadyExistsException();
        }

        Scanner in = new Scanner(System.in);
        System.out.print("\nEnter password for new account: ");
        String password = in.nextLine();
        System.out.print("\nEnter password hint: ");
        String passwordHint = in.nextLine();
        users.add(new FacebookUser(username, password, passwordHint));

    }

    //Displays the password hint for the UserAccount associated with the username argument
    public void getPasswordHint(String username) throws UsernameNotFoundException {
        for (FacebookUser user : users) {
            if (user.toString().equals(username)) {
                user.getPasswordHelp();
                return;
            }
        }
        throw new UsernameNotFoundException();
    }


    //Removes the FacebookUser associated with the username from the users ArrayList
    //Requires the password for the UserAccount in order to remove it
    //Throws IncorrectPasswordException is the password entered is incorrect
    //Throws UsernameNotFoundException if the username is not in the users ArrayList
    public void deleteUser(String username) throws IncorrectPasswordException, UsernameNotFoundException{
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).toString().equals(username)){
                Scanner in = new Scanner(System.in);
                System.out.print("Enter password to continue: ");
                String password = in.nextLine();

                if(users.get(i).checkPassword(password)){
                    users.remove(i);
                    return;
                }
                else{
                    throw new IncorrectPasswordException();
                }
            }
        }
        throw new UsernameNotFoundException();
    }


    //returns true if the username can be found in users, false if the username does not exist
    private boolean checkIfUsernameExists(String username){
        for (FacebookUser user : users) {
            if (user.toString().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
