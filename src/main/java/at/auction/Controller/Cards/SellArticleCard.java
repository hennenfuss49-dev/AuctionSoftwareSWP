package at.auction.Controller.Cards;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.CardDisplayState;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DBModel;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.UserDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ResourceBundle;

public class SellArticleCard implements Initializable {
    public AnchorPane card;
    public VBox elListCenter;

    private final ArticleDAOImpl articleDAO;
    public TextField inpTitle;
    public TextField inpStartPrice;
    public TextField inpEndDate;
    public TextArea txtDescription;
    public Button btnLaunchProduct;

    public SellArticleCard() {
        articleDAO = new ArticleDAOImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLaunchProduct.setOnAction(e -> onBtnLaunchProduct());
    }

    private void onBtnLaunchProduct(){
        java.sql.Date startDate = new Date(Instant.now().toEpochMilli());
        String endDateString = inpEndDate.getText();
        try {
            System.out.println(new ArticleDTO(0, AuthenticationModel.getInstance().getUser().userId(), inpTitle.getText(), txtDescription.getText(), startDate, Date.valueOf(endDateString), (byte) 1, Double.parseDouble(inpStartPrice.getText()), "active"));
            articleDAO.save(new ArticleDTO(0, AuthenticationModel.getInstance().getUser().userId(), inpTitle.getText(), txtDescription.getText(), startDate, Date.valueOf(endDateString), (byte) 1, Double.parseDouble(inpStartPrice.getText()), "active"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCard(AnchorPane card) {
        this.card = card;
    }

    public AnchorPane getCard() {
        return card;
    }
}
