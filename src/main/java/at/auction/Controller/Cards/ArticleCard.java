package at.auction.Controller.Cards;

import at.auction.Controller.CardDisplayState;
import at.auction.Database.DTOs.ArticleDTO;
import at.auction.Model.CardDisplayModel;
import javafx.beans.property.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ArticleCard implements Initializable {

    public Text txtLabel;
    public Text txtDescription;
    public Text txtStartPrice;
    public Text txtCancellationState;
    public Text txtStartTime;
    public Text txtEndTime;
    public AnchorPane card;

    public VBox elList;


    private final ObjectProperty<ArticleDTO> cardDTO;

    public void setData(ArticleDTO cardValues) {
        txtLabel.textProperty().bind(new SimpleStringProperty(cardValues.label()));
        txtDescription.textProperty().bind(new SimpleStringProperty(cardValues.description()));
        txtStartPrice.textProperty().bind(new SimpleStringProperty("$" + cardValues.currentPrice()));
        txtCancellationState.textProperty().bind(new SimpleStringProperty(cardValues.cancellationState()));
        txtStartTime.textProperty().bind(new SimpleStringProperty(String.valueOf(cardValues.startTime())));
        txtEndTime.textProperty().bind(new SimpleStringProperty(String.valueOf(cardValues.endTime())));
        cardDTO.set(new ArticleDTO(cardValues.articleId(), cardValues.userId(), cardValues.label(), cardValues.description(), cardValues.startTime(), cardValues.endTime(), cardValues.free(), cardValues.currentPrice(), cardValues.cancellationState()));

        card.setOnMouseClicked(e-> {
            onMouseClicked();
        });
    }

    public ArticleCard(){
        cardDTO = new SimpleObjectProperty<>(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    private void onMouseClicked(){
        CardDisplayState.setCurrentArticle(cardDTO.get());
        CardDisplayModel.getInstance().getCardDisplayController().onCardDisplayStateChange(CardDisplayState.ARTICLE);
    }

    public void setComponents(Text txtLabel, Text txtDescription, Text txtStartPrice, Text txtCancellationState, Text txtStartTime, Text txtEndTime){
        this.txtLabel = txtLabel;
        this.txtDescription = txtDescription;
        this.txtStartPrice = txtStartPrice;
        this.txtCancellationState = txtCancellationState;
        this.txtStartTime = txtStartTime;
        this.txtEndTime = txtEndTime;
    }

    public AnchorPane getCard() {
        return card;
    }

    public VBox getElList() {
        return elList;
    }

    public void setElList(VBox elList) {
        this.elList = elList;
    }

    public void setCard(AnchorPane card) {
        this.card = card;
    }

    public void setCardDTO(ArticleDTO cardDTO) {
        this.cardDTO.set(cardDTO);
    }
}
