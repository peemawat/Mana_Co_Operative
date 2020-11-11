package com.example.vision;

public class UserForSQLite {
    private String Name;
    private String Email;
    private String Username;
    private String Password;

    public UserForSQLite(String name, String email, String username, String password) {
        this.Name = name;
        this.Email = email;
        this.Username = username;
        this.Password = password;
    }

    public String getName() { return Name; }

    public void setName(String name) { this.Name = name; }

    public String getEmail() { return Email; }

    public void setEmail(String email) { this.Email = email; }

    public String getUsername() { return Username; }

    public void setUsername(String username) { this.Username = username; }

    public String getPassword() { return Password; }

    public void setPassword(String password) { this.Password = password; }

    public UserForSQLite() {
    }
}
