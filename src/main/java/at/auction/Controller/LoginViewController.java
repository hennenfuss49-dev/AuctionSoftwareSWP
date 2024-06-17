package at.auction.Controller;

import at.auction.Auth.AuthenticationModel;
import at.auction.Controller.WindowBar.WindowBarHandler;
import at.auction.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {
    public AnchorPane windowBar;
    public Text txtWindowBarTitle;
    public Button btnCloseWindow;
    public Button btnMinimizeWindow;
    public Text txtWindowBarTitle1;
    public Text txtError;
    public TextField inpEmail;
    public TextField inpPassword;
    public Button btnLogin;
    public Button btnCreateAccount;

    private WindowBarHandler windowBarHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        windowBarHandler = new WindowBarHandler(btnCloseWindow, btnMinimizeWindow, null, txtWindowBarTitle, windowBar);
        windowBarHandler.handleWindowBar();
        windowBarHandler.setWindowBarTitle("Login");

        btnLogin.setOnAction(e -> onBtnLogin());
        btnCreateAccount.setOnAction(e -> onBtnCreateAccount());
    }

    private void onBtnLogin(){
        if(AuthenticationModel.getInstance().authenticateUser(inpEmail.getText(), inpPassword.getText())){
            txtError.setText("");
            Model.getInstance().getViewFactory().switchStage("App", 1040, 535, false);
        }else{
            txtError.setText("Error occurred -> Wrong email or password!");
        }
//        AuthenticationModel.getInstance().authenticateUser("john.doe@example.com", "password123");
//        Model.getInstance().getViewFactory().switchStage("App", 1040, 535, false);
    }

    private void onBtnCreateAccount(){
        if(AuthenticationModel.getInstance().createUser(inpEmail.getText(), inpPassword.getText())){
            txtError.setText("");
            Model.getInstance().getViewFactory().switchStage("App", 1040, 535, false);
        }else{
            txtError.setText("Error occurred -> EMail is already used!");
        }
    }
}
