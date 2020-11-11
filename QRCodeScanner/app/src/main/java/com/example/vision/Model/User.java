package com.example.vision.Model;
import com.google.gson.annotations.SerializedName;

public class User{
    @SerializedName("_id")
    private String _id;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Username")
    private String Username;
    @SerializedName("Password")
    private String Password;

    public User(String _id,String name, String email, String username, String password) {
        this._id = _id;
        this.Name = name;
        this.Email = email;
        this.Username = username;
        this.Password = password;
    }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    public String getName() { return Name; }

    public void setName(String name) { this.Name = name; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { this.Email = email; }

    public String getUsername() { return Username; }

    public void setUsername(String username) { this.Username = username; }

    public String getPassword() { return Password; }

    public void setPassword(String password) { this.Password = password; }

    public User() {
    }
}
