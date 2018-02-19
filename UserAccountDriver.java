//Ryan Betts
//January 15th, 2018
//UserAccount Class
//This program tests the newly created UserAccount class

public class UserAccountDriver {

    public static void main(String[] args){

        UserAccount account1 = new UserAccount("JoeT", "redfish1230");
        UserAccount account2 = new UserAccount("SallyG", "bluefish545");

        System.out.println(account1.toString());
        System.out.println(account2.toString());

        checkAccountsAreEqual(account1, account2);
        checkAccountsAreEqual(account2, account2);

        enterPassword(account1, "redfish1230");
        enterPassword(account2, "bluefish546");

        account1.deactivateAccount();
        account2.deactivateAccount();

    }


    public static void checkAccountsAreEqual(UserAccount acc1, UserAccount acc2){

        if(acc1.equals(acc2)){
            System.out.println("Account " + acc1.toString() + " is the same as " + "Account " + acc2.toString());
        }
        else{
            System.out.println("Account " + acc1.toString() + " is not the same as " + "Account " + acc2.toString());
        }
    }

    public static void enterPassword(UserAccount account, String password){

        if(account.checkPassword(password)){
            System.out.println("Password is correct.");
        }
        else{
            System.out.println("Password is incorrect.");
        }
    }


}
