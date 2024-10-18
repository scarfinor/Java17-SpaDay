package org.launchcode.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    @NotBlank
    @Size(min = 5, max = 15)
    private String username;

    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @NotNull(message = "User passwords do not match!")
    @Size(min = 6)
    private String verifyPassword;

    public User() {

    }

    public User(String username, String email, String password) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.verifyPassword = getVerifyPassword();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public @NotBlank @NotNull(message = "User passwords do not match!") @Size(min = 6) String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(@NotBlank @NotNull(message = "User passwords do not match!") @Size(min = 6) String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    private void checkPassword() {
    if ((!(password == verifyPassword)) && (password.equals(null) && verifyPassword.equals(null))) {
        verifyPassword = null;
    }
    }
}

