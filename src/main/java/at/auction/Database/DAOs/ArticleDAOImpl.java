package at.auction.Database.DAOs;

import at.auction.Database.DBModel;
import at.auction.Database.DTOs.ArticleDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{


    @Override
    public ArticleDTO get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ArticleDTO> getAll() throws SQLException {
        Connection con = DBModel.getInstance().getConnection();
        List<ArticleDTO> list = new ArrayList<>();
        String sql = "select * from article;";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int articleID = rs.getInt("articleID");
            int userID = rs.getInt("userID");
            String label = rs.getString("label");
            String description = rs.getString("description");
            byte free = rs.getByte("free");
            double startPrice = rs.getDouble("startPrice");
            String cancellationState = rs.getString("cancellationState");

            list.add(new ArticleDTO(articleID, userID, label, description, null, null, free, startPrice, cancellationState));
        }

        return list;
    }

    @Override
    public void save(ArticleDTO articleDTO) throws SQLException {

    }

    @Override
    public void insert(ArticleDTO articleDTO) throws SQLException {

    }

    @Override
    public void remove(ArticleDTO articleDTO) throws SQLException {

    }
}
