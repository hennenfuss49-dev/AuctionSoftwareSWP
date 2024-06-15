package at.auction.Auth;

import at.auction.Database.DAOs.UserDAO;
import at.auction.Database.DAOs.UserDAOImpl;
import at.auction.Database.DTOs.UserDTO;
import at.auction.View.ViewFactory;

import java.sql.SQLException;

public class AuthenticationModel {

    private UserDTO user;
    private final UserDAOImpl userDAO;

    static AuthenticationModel model;

    public AuthenticationModel(){
        userDAO = new UserDAOImpl();
        user = null;
    }

    public UserDTO getUser() {
        return user;
    }

    public boolean authenticateUser(String email, String password){
        try {
            user = userDAO.get(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user != null;
    }

    public void logoutUser(){
        user = null;
    }

    public static AuthenticationModel getInstance(){
        if(model == null)
            model = new AuthenticationModel();
        return model;
    }

}
