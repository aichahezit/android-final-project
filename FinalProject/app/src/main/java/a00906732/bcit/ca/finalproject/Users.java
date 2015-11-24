package a00906732.bcit.ca.finalproject;

/**
 * Created by Ha on 11/23/2015.
 */
public class Users {

    public String username;
    public String password;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
