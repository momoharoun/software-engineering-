package com.example.recipico.model;


// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity(name = "User")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false,  unique = true)
    private String email;

    @Column(name = "passsword", nullable = false)
    private String password;

    // // to define the recipes of each user
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Recipe> user_recipes = new ArrayList<>(); 

    // Constructors 
    public User(){
        // just for hibernate
    }


    public User(String email, String pass){
        this.email = email;
        this.password = pass;
    }

    public User( String username, String email, String pass){
        this.username = username;
        this.email = email;
        this.password = pass;
    }

    public User(String fname, String lname, String username, String email, String pass){
        this.firstName = fname;
        this.lastName = lname;
        this.username = username;
        this.email = email;
        this.password = pass;
    }

    // setters and getters
    public Long getId() {
        return userId;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // public void setPassword(String password) {
    //     // Hash the password using a suitable hashing algorithm, e.g., bcrypt
    //     String hashedPassword = new BCryptPasswordEncoder().encode(password);
    //     this.password = hashedPassword;
    // }

    // public boolean verifyPassword(String password) {
    //     // Compare the provided password with the stored hashed password
    //     return new BCryptPasswordEncoder().matches(password, this.password);
    // }
}
