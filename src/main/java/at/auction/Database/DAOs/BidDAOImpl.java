package at.auction.Database.DAOs;

import at.auction.Database.DBModel;
import at.auction.Database.DTOs.BidDTO;
import at.auction.Database.DTOs.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BidDAOImpl implements BidDAO{

    @Override
    public BidDTO get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<BidDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(BidDTO bidDTO) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        String sql = "insert into bid(userId, articleId, price, timestamp) VALUES (?, ?, ?, ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, bidDTO.userID());
        ps.setInt(2, bidDTO.articleID());
        ps.setDouble(3, bidDTO.amount());
        ps.setTimestamp(4, bidDTO.timestamp());

        ps.executeUpdate();
    }

    @Override
    public void insert(BidDTO bidDTO) throws SQLException {

    }

    @Override
    public void remove(BidDTO bidDTO) throws SQLException {

    }
}
