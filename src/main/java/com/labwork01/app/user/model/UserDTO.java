package com.labwork01.app.user.model;

public class UserDTO {
    private Long id;
    private String userName;
    private String dateOfBirth;
    private String phoneNumber;
    private String password;
    public UserDTO(){

    }
    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.dateOfBirth = user.getDateOfBirth();
        this.phoneNumber = user.getPhoneNumber();
        this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
