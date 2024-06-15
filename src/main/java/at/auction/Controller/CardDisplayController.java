package at.auction.Controller;

import at.auction.Controller.Cards.ArticleCard;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DisplayableCard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class CardDisplayController {
    private final FlowPane cardDisplay;

    private final ArticleDAOImpl articleDAO;

    private ArrayList<DisplayableCard> cards;

    public CardDisplayController(FlowPane cardDisplay) {
        this.cards = new ArrayList<>();
        this.cardDisplay = cardDisplay;
        this.articleDAO = new ArticleDAOImpl();

    }

    public void onCardDisplayStateChange(CardDisplayState state){
        switch(state){
            case ARTICLES -> displayAllArticles();
            case OWN_ARTICLES -> displayOwnArticles();
            case YOUR_BIDS -> displayBids();
        }
        System.out.println(cards);
    }

    private void displayAllArticles() {
        cards.clear();
        try {
            cards.addAll(articleDAO.getAll());
            cardDisplay.getChildren().clear();
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            cardDisplay.getChildren().add(card.getCard());
        });
    }

    private void displayOwnArticles() {
        cards.clear();
        cardDisplay.getChildren().clear();
    }

    private void displayBids() {
        cards.clear();
        cardDisplay.getChildren().clear();
    }


}
