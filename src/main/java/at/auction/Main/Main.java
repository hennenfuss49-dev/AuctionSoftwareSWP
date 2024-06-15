package at.auction.Main;

import at.auction.Auth.AuthenticationModel;
import at.auction.Database.Articles;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Model.getInstance().getViewFactory().switchStage("Login", 300, 535, false);
    }
}
