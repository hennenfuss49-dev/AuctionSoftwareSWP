package at.auction.Database;

import at.auction.Database.DAOs.ArticleDAO;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DTOs.ArticleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class Articles {
    ArrayList<ArticleDTO> articleList = new ArrayList<>();

    public ArrayList<ArticleDTO> getArticleList(){
        articleList.clear();
        try {
            articleList.addAll(new ArticleDAOImpl().getAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articleList;
    }
}
