package Models;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean admin;
    
    public User() {
        //Default constructor
    }
    
    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
    
    public User(int id, String firstname, String lastname, String email, String password, boolean admin) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
    
    public void makeAdmin() {
        this.admin = true;
    }
    
    public void removeAdmin() {
        this.admin = false;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getFirstName() {
        return this.firstname;
    }
    
    public String getLastName() {
        return this.firstname;
    }
    
    public String getFullName() {
        return this.firstname + " " + this.lastname;
    }
}
