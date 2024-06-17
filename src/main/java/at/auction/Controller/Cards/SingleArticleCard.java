package at.auction.Controller.Cards;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.CardDisplayState;
import at.auction.Database.DAOs.ArticleDAO;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DAOs.BidDAOImpl;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DTOs.BidDTO;
import at.auction.Database.DTOs.UserDTO;
import javafx.beans.property.SimpleObjectProperty;
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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Instant;
import java.util.Date;
import java.util.ResourceBundle;

public class SingleArticleCard implements Initializable {
    public AnchorPane card;
    public ImageView imgThumbnail;
    public TextArea txtDescription;
    public VBox elListLeft;
    public VBox elListCenter;
    public Text txtLabel;
    public Text txtCurrentPrice;
    public Button btnQuickBid;
    public TextField inpBidAmount;
    public Button btnSubmitBid;
    public Text txtRemainingTime;
    public VBox elListRight;
    public Text txtHighBidder;
    public Text txtAmountOfBids;

    private final ArticleDAOImpl articleDAO;
    private final BidDAOImpl bidDAO;
    private UserDTO highBidder;
    private int bidCount;
    private double highestBid;

    public void setData() {
        txtLabel.textProperty().bind(new SimpleStringProperty(CardDisplayState.getCurrentArticle().label()));
        txtDescription.textProperty().bind(new SimpleStringProperty(CardDisplayState.getCurrentArticle().description()));
        Date endTime = CardDisplayState.getCurrentArticle().endTime();
        txtRemainingTime.textProperty().bind(new SimpleStringProperty(String.valueOf(endTime)));
    }

    public SingleArticleCard() {
        articleDAO = new ArticleDAOImpl();
        bidDAO = new BidDAOImpl();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnQuickBid.setOnAction(e -> onBtnQuickBid());
        btnSubmitBid.setOnAction(e -> onBtnSubmitBid());
        performValueUpdate();
    }

    private void performValueUpdate(){
        try {
            highestBid = articleDAO.getHighestBid(CardDisplayState.getCurrentArticle().articleId());
            bidCount = articleDAO.getBidCount(CardDisplayState.getCurrentArticle().articleId());
            highBidder = articleDAO.getHighBidder(CardDisplayState.getCurrentArticle().articleId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        btnQuickBid.setText("Quick Bid $" + (highestBid + 100));
        inpBidAmount.setPromptText(String.valueOf(highestBid));
        txtCurrentPrice.textProperty().bind(new SimpleStringProperty("$" + highestBid));
        if(highBidder != null)
            txtHighBidder.setText(highBidder.firstName() + " " + highBidder.lastName());
        txtAmountOfBids.setText(String.valueOf(bidCount));
    }

    private void onBtnQuickBid(){
        try {
            bidDAO.save(new BidDTO(0, AuthenticationModel.getInstance().getUser().userId(), CardDisplayState.getCurrentArticle().articleId(), highestBid + 100, Timestamp.from(Instant.now())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        performValueUpdate();
    }

    private void onBtnSubmitBid(){
        double amount = Double.parseDouble(inpBidAmount.getText());
        try {
            bidDAO.save(new BidDTO(0, AuthenticationModel.getInstance().getUser().userId(), CardDisplayState.getCurrentArticle().articleId(), amount, Timestamp.from(Instant.now())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        performValueUpdate();
    }

    public void setComponents(Text txtLabel, TextArea txtDescription, Text txtCurrentPrice, Text txtRemainingTime){
        this.txtLabel = txtLabel;
        this.txtDescription = txtDescription;
        this.txtCurrentPrice = txtCurrentPrice;
        this.txtRemainingTime = txtRemainingTime;
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
