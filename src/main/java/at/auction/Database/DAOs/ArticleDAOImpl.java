package at.auction.Database.DAOs;

import at.auction.Auth.AuthenticationModel;
import at.auction.Database.DBModel;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{


    @Override
    public ArticleDTO get(int id) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        ArticleDTO article = null;
        String sql = "select * from article where articleId = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            double currentPrice = Math.max(rs.getDouble(10), rs.getDouble(8));
            article = new ArticleDTO(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getByte(7), currentPrice, rs.getString(9));
        }

        return article;
    }

    public int getBidCount(int id) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        int count = 0;
        String sql = "select IFNULL(count(*), 0) from bid where articleId = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }

    public double getHighestBid(int id) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        int highestBid = 0;
        String sql = "select IFNULL(max(price), (select startPrice from Article where articleId = ?)) from bid where articleId = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.setInt(2, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            highestBid = rs.getInt(1);
        }
        return highestBid;
    }

    public UserDTO getHighBidder(int id) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        UserDTO highBidder = null;
        String sql = "select * from user where userID = (select userId from bid where price = (select max(price) from bid where articleId = ?));";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            highBidder = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        }

        return highBidder;
    }

    @Override
    public List<ArticleDTO> getAll() throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        List<ArticleDTO> list = new ArrayList<>();
        String sql = "select * from article where cancellationState IN('active');";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int articleID = rs.getInt("articleID");
            int userID = rs.getInt("userID");
            String label = rs.getString("label");
            String description = rs.getString("description");
            byte free = rs.getByte("free");
            double currentPrice = Math.max(rs.getDouble(10), rs.getDouble(8));
            String cancellationState = rs.getString("cancellationState");

            list.add(new ArticleDTO(articleID, userID, label, description, rs.getDate(5), rs.getDate(6), free, currentPrice, cancellationState));
        }

        return list;
    }

    public List<ArticleDTO> getAllFromUserId(int userId) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        List<ArticleDTO> list = new ArrayList<>();
        String sql = "select * from article where cancellationState IN('active') and userID = ?;";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int articleID = rs.getInt("articleID");
            int userID = rs.getInt("userID");
            String label = rs.getString("label");
            String description = rs.getString("description");
            byte free = rs.getByte("free");
            double currentPrice = Math.max(rs.getDouble(10), rs.getDouble(8));
            String cancellationState = rs.getString("cancellationState");

            list.add(new ArticleDTO(articleID, userID, label, description, rs.getDate(5), rs.getDate(6), free, currentPrice, cancellationState));
        }

        return list;
    }

    @Override
    public void save(ArticleDTO articleDTO) throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        String sql = "insert into article(userID, label, description, startTime, endTime, free, startPrice, cancellationState)\n" +
                "VALUES (?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, articleDTO.userId());
        ps.setString(2, articleDTO.label());
        ps.setString(3, articleDTO.description());
        ps.setDate(4, articleDTO.startTime());
        ps.setDate(5, articleDTO.endTime());
        ps.setByte(6, articleDTO.free());
        ps.setDouble(7, articleDTO.currentPrice());
        ps.setString(8, articleDTO.cancellationState());

        ps.executeUpdate();

    }

    @Override
    public void insert(ArticleDTO articleDTO) throws SQLException {

    }

    @Override
    public void remove(ArticleDTO articleDTO) throws SQLException {

    }
}
