public class UsernameAlreadyExistsException extends Exception {

    public String username;

    public UsernameAlreadyExistsException(String username){
        this.username = username;
    }

}
