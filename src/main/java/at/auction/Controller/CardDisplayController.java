package at.auction.Controller;

import at.auction.Controller.Cards.ArticleCard;
import at.auction.Controller.Cards.SellArticleCard;
import at.auction.Controller.Cards.SingleArticleCard;
import at.auction.Controller.Cards.YourAccountCard;
import at.auction.Database.DAOs.ArticleDAOImpl;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Database.DisplayableCard;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CardDisplayController {
    private FlowPane cardDisplay;
    private VBox singleCardDisplay;

    private final ArticleDAOImpl articleDAO;

    private ArrayList<DisplayableCard> cards;

    private final ObjectProperty<CardDisplayState> cardDisplayState = new SimpleObjectProperty<>(CardDisplayState.ARTICLES);

    public CardDisplayController() {
        this.cards = new ArrayList<>();
        this.articleDAO = new ArticleDAOImpl();

    }

    public void onCardDisplayStateChange(CardDisplayState state){
        cardDisplayState.set(state);
        switch(cardDisplayState.getValue()){
            case ARTICLES -> displayAllArticles();
            case ACCOUNT -> displayAccount();
            case SELL -> displaySell();
            case ARTICLE -> displayArticle();
        }
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
                card.setCardDTO((ArticleDTO) el);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            cardDisplay.getChildren().add(card.getCard());
        });
    }

    private void displayAccount() {
        cards.clear();
        cardDisplay.getChildren().clear();
        YourAccountCard card = new YourAccountCard();
        try {
            card.setCard(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/YourAccountCard.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardDisplay.getChildren().add(card.getCard());
    }

    private void displaySell() {
        cards.clear();
        cardDisplay.getChildren().clear();
        SellArticleCard card = new SellArticleCard();
        try {
            card.setCard(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/SellArticleCard.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardDisplay.getChildren().add(card.getCard());
    }

    private void displayArticle() {
        cards.clear();
        cardDisplay.getChildren().clear();
        SingleArticleCard card = new SingleArticleCard();
        try {
            card.setCard(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/SingleArticleCard.fxml"))));
            card.setElListCenter((VBox) card.getCard().getChildren().get(1));
            card.setComponents((Text) card.getElListCenter().getChildren().get(0),(TextArea) ((VBox) card.card.getChildren().get(0)).getChildren().get(1), (Text) ((HBox)card.getElListCenter().getChildren().get(1)).getChildren().get(1), (Text) ((HBox)card.getElListCenter().getChildren().get(6)).getChildren().get(1));
            card.setData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        cardDisplay.getChildren().add(card.getCard());
    }

    public ObjectProperty<CardDisplayState> cardDisplayStateProperty() {
        return cardDisplayState;
    }

    public void setCardDisplay(FlowPane cardDisplay) {
        this.cardDisplay = cardDisplay;
    }
    public void setSingleCardDisplay(VBox singleCardDisplay) {
        this.singleCardDisplay = singleCardDisplay;
    }
}
