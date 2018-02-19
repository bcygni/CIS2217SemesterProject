public class UsernameNotFoundException extends Exception {

    public String username;

    public UsernameNotFoundException(String username){
        this.username = username;
    }
}
