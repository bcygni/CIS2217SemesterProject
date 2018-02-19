public class SameUsernameException extends Exception {

    public String username;

    public SameUsernameException(String username){
        this.username = username;
    }

}
