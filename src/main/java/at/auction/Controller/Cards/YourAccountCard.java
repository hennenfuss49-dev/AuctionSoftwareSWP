package at.auction.Controller.Cards;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.CardDisplayState;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DAOs.BidDAOImpl;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.BidDTO;
import at.auction.Database.DTOs.UserDTO;
import at.auction.Database.DisplayableCard;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourAccountCard implements Initializable {
    public AnchorPane card;
    public VBox elListCenter;
    public Text txtEmail;
    public TextField inpFirstName;
    public Button btnEditFirstName;
    public TextField inpLastName;
    public Button btnEditLastName;
    public Button btnSave;
    public FlowPane displayProduct;
    public FlowPane displayBids;

    private final ArticleDAOImpl articleDAO;
    private final BidDAOImpl bidDAO;

    private ArrayList<DisplayableCard> cards;
    public YourAccountCard() {
        articleDAO = new ArticleDAOImpl();
        bidDAO = new BidDAOImpl();
        cards = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inpFirstName.setDisable(true);
        inpLastName.setDisable(true);
        performValueUpdate();
        btnEditFirstName.setOnAction(e -> inpFirstName.setDisable(false));
        btnEditLastName.setOnAction(e -> inpLastName.setDisable(false));
        btnSave.setOnAction(e -> onBtnSave());
        displayUsersArticles();
    }

    private void onBtnSave(){
        inpFirstName.setDisable(true);
        inpLastName.setDisable(true);
        AuthenticationModel.getInstance().updateUserName(inpFirstName.getText(), inpLastName.getText());
        performValueUpdate();
    }

    private void performValueUpdate(){
        try{
            txtEmail.setText(AuthenticationModel.getInstance().getUser().email());
            inpFirstName.setText(AuthenticationModel.getInstance().getUser().firstName());
            inpLastName.setText(AuthenticationModel.getInstance().getUser().lastName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void displayUsersArticles() {
        cards.clear();
        try {
            cards.addAll(articleDAO.getAllFromUserId(AuthenticationModel.getInstance().getUser().userId()));
            displayProduct.getChildren().clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        cards.forEach(el -> {
            ArticleCard card = new ArticleCard();

            try {
                card.setCard(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/ArticleCard.fxml"))));
                card.setElList((VBox) card.getCard().getChildren().get(0));
                card.setComponents((Text) card.getElList().getChildren().get(0), (Text) card.getElList().getChildren().get(1), (Text) card.getElList().getChildren().get(2), (Text) card.getElList().getChildren().get(3), (Text) ((HBox)card.getElList().getChildren().get(4)).getChildren().get(0), (Text) ((HBox)card.getElList().getChildren().get(4)).getChildren().get(1));
                card.setData((ArticleDTO) el);
                card.setCardDTO((ArticleDTO) el);
                card.getCard().setScaleY(.8);
                card.getCard().setScaleX(.8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            displayProduct.getChildren().add(card.getCard());
        });
    }

    public AnchorPane getCard() {
        return card;
    }

    public VBox getElListCenter() {
        return elListCenter;
    }

    public void setElListCenter(VBox elListCenter) {
        this.elListCenter = elListCenter;
    }

    public void setCard(AnchorPane card) {
        this.card = card;
    }
}
