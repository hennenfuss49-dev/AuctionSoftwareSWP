package at.auction.Controller;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.WindowBar.WindowBarHandler;
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
    public Button btnShowOwnArticles;
    public Button btnShowBids;
    public Button btnLogOut;
    public AnchorPane windowBar;
    public Button btnCloseWindow;
    public Button btnMinimizeWindow;
    public Button btnMaximizeWindow;
    public Text txtWindowBarTitle;
    public FlowPane cardDisplayPane;
    public Text txtWelcome;

    private WindowBarHandler windowBarHandler;
    private CardDisplayController cardDisplayController;

    private final ObjectProperty<CardDisplayState> cardDisplayState = new SimpleObjectProperty<>(CardDisplayState.ARTICLES);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowBarHandler = new WindowBarHandler(btnCloseWindow, btnMinimizeWindow, btnMaximizeWindow, txtWindowBarTitle, windowBar);
        windowBarHandler.handleWindowBar();
        windowBarHandler.setWindowBarTitle("Auction Software");

        btnShowBids.setOnAction(e-> onBtnShowBids());
        btnShowAllArticles.setOnAction(e-> onBtnShowAllArticles());
        btnShowOwnArticles.setOnAction(e-> onBtnShowOwnArticles());
        btnLogOut.setOnAction(e -> onBtnLogOut());

        cardDisplayController = new CardDisplayController(cardDisplayPane);
        cardDisplayController.onCardDisplayStateChange(CardDisplayState.ARTICLES);
        cardDisplayState.addListener(e -> {
            cardDisplayController.onCardDisplayStateChange(cardDisplayState.getValue());
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
        cardDisplayState.set(CardDisplayState.ARTICLES);
    }
    private void onBtnShowOwnArticles(){
        cardDisplayState.set(CardDisplayState.OWN_ARTICLES);
    }
    private void onBtnShowBids(){
        cardDisplayState.set(CardDisplayState.YOUR_BIDS);
    }
}
