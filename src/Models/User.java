package Models;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean admin;
    
    public void User() {
        //Default constructor
    }
    
    public void User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
    
    public void makeAdmin() {
        this.admin = true;
    }
    
    public void removeAdmin() {
        this.admin = false;
    }
}
