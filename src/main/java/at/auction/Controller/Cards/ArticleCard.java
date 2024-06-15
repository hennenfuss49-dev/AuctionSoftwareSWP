package at.auction.Controller.Cards;

import at.auction.Database.DTOs.ArticleDTO;
import javafx.beans.property.SimpleBooleanProperty;
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

    private ArticleDTO cardValues;

    private final SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);

    public void setData(ArticleDTO cardValues) {
        this.cardValues = cardValues;
        txtLabel.setText(cardValues.label());
        txtDescription.setText(cardValues.description());
        txtStartPrice.setText(String.valueOf(cardValues.startPrice()));
        txtCancellationState.setText(String.valueOf(cardValues.cancellationState()));
        txtStartTime.setText(String.valueOf(cardValues.startTime()));
        txtEndTime.setText(String.valueOf(cardValues.endTime()));
    }

    public ArticleCard(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        card.setOnMouseClicked(e-> {
            isSelected.set(!isSelected.get());
        });
        isSelected.addListener(e ->{
            if(isSelected.get()){
                elList.setPrefWidth(790);
                card.setPrefWidth(800);
                card.setPrefHeight(400);
                txtDescription.setScaleX(1);
                txtDescription.setScaleY(1);
            }else{
                elList.setPrefWidth(240);
                card.setPrefWidth(250);
                card.setPrefHeight(200);
                txtDescription.setScaleX(0);
                txtDescription.setScaleY(0);
            }
        });
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
}
