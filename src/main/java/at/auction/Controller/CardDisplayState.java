package at.auction.Controller;

import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.UserDTO;

public enum CardDisplayState {
    ARTICLES,
    ACCOUNT,
    ARTICLE,
    SELL;

    private static ArticleDTO currentArticle;

    public static void setCurrentArticle(ArticleDTO article){
        currentArticle = article;
    }

    public static ArticleDTO getCurrentArticle() {
        return currentArticle;
    }
}
