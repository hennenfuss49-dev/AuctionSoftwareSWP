package at.auction.Database.DAOs;

import at.auction.Database.DBModel;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    public UserDTO get(String email, String password) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        UserDTO user = null;
        String sql = "select * from user where email = ? and password = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            user = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        }

        return user;
    }

    @Override
    public UserDTO get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<UserDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(UserDTO userDTO) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        String sql = """
                INSERT INTO user (firstName, lastName, EMail, Password, userState)
                VALUES
                    (?, ?, ?, ?, ?)
                on duplicate key update firstName = values(firstName), lastName = values(lastName),
                                        email = values(email), password = values(password), userState = values(userState);
                """;
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, userDTO.firstName());
        ps.setString(2, userDTO.lastName());
        ps.setString(3, userDTO.email());
        ps.setString(4, userDTO.password());
        ps.setString(5, userDTO.userState());

        ps.executeUpdate();
    }

    @Override
    public void insert(UserDTO userDTO) throws SQLException {

    }

    @Override
    public void remove(UserDTO userDTO) throws SQLException {

    }
}
