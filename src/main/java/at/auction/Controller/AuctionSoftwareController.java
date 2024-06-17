package at.auction.Controller;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.WindowBar.WindowBarHandler;
import at.auction.Model.CardDisplayModel;
import at.auction.Model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AuctionSoftwareController implements Initializable {
    public BorderPane parent;
    public VBox vboxSelectionList;
    public Button btnShowAllArticles;
    public Button btnShowAccount;
    public Button btnSell;
    public Button btnLogOut;
    public AnchorPane windowBar;
    public Button btnCloseWindow;
    public Button btnMinimizeWindow;
    public Button btnMaximizeWindow;
    public Text txtWindowBarTitle;
    public FlowPane cardDisplayPane;
    public VBox singleCardDisplay;
    public Text txtWelcome;

    private WindowBarHandler windowBarHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowBarHandler = new WindowBarHandler(btnCloseWindow, btnMinimizeWindow, btnMaximizeWindow, txtWindowBarTitle, windowBar);
        windowBarHandler.handleWindowBar();
        windowBarHandler.setWindowBarTitle("Auction Software");

        btnSell.setOnAction(e-> onBtnSell());
        btnShowAllArticles.setOnAction(e-> onBtnShowAllArticles());
        btnShowAccount.setOnAction(e-> onBtnShowAccount());
        btnLogOut.setOnAction(e -> onBtnLogOut());
        CardDisplayModel.getInstance().getCardDisplayController().setCardDisplay(cardDisplayPane);
        CardDisplayModel.getInstance().getCardDisplayController().setSingleCardDisplay(singleCardDisplay);
        CardDisplayModel.getInstance().getCardDisplayController().onCardDisplayStateChange(CardDisplayState.ARTICLES);
        CardDisplayModel.getInstance().getCardDisplayController().cardDisplayStateProperty().addListener(e -> {
            CardDisplayModel.getInstance().getCardDisplayController().onCardDisplayStateChange(CardDisplayModel.getInstance().getCardDisplayController().cardDisplayStateProperty().get());
        });
        setUserSpecificFields();
    }

    private void setUserSpecificFields(){
        txtWelcome.setText("Hello, " + AuthenticationModel.getInstance().getUser().firstName());
    }

    private void onBtnLogOut(){
        Model.getInstance().getViewFactory().switchStage("Login", 300, 535, false);
        AuthenticationModel.getInstance().logoutUser();
    }
    private void onBtnShowAllArticles(){
        CardDisplayModel.getInstance().getCardDisplayController().cardDisplayStateProperty().set(CardDisplayState.ARTICLES);
    }
    private void onBtnShowAccount(){
        CardDisplayModel.getInstance().getCardDisplayController().cardDisplayStateProperty().set(CardDisplayState.ACCOUNT);
    }
    private void onBtnSell(){
        CardDisplayModel.getInstance().getCardDisplayController().cardDisplayStateProperty().set(CardDisplayState.SELL);
    }
}
