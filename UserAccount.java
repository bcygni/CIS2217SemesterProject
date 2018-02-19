import java.util.Objects;

public class UserAccount {

    private String username;
    private String password;
    private boolean active; //indicates whether or not the account is currently active

    //Constructor to initialize the username and password and make the account active
    UserAccount(String username, String password){
        this.username = username;
        this.password = password;
        active = true;
    }

    //Returns true if the argument is the same as the account's password, false otherwise
    public boolean checkPassword(String password){

        if(this.password.equals(password)){
            return true;
        }
        return false;
    }

    //Sets active to false
    public void deactivateAccount(){
        System.out.println(username + ": account has been deactivated.");
        active = false;
    }

    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}