package at.auction.Controller.WindowBar;

import at.auction.Model.Model;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class WindowBarHandler {

    private final Button btnCloseWindow;
    private final Button btnMinimizeWindow;
    private final Button btnMaximizeWindow;
    private final Text txtWindowBarTitle;
    private final AnchorPane windowBar;

    private double x, y;

    public WindowBarHandler(Button btnCloseWindow, Button btnMinimizeWindow, Button btnMaximizeWindow, Text txtWindowBarTitle, AnchorPane windowBar) {
        this.btnCloseWindow = btnCloseWindow;
        this.btnMinimizeWindow = btnMinimizeWindow;
        this.btnMaximizeWindow = btnMaximizeWindow;
        this.txtWindowBarTitle = txtWindowBarTitle;
        this.windowBar = windowBar;
    }

    public void handleWindowBar(){
        btnMinimizeWindow.setOnAction(e -> onBtnMinimizeWindow());
        if(!(btnMaximizeWindow == null))
            btnMaximizeWindow.setOnAction(e -> onBtnMaximizeWindow());
        btnCloseWindow.setOnAction(e -> onBtnCloseWindow());
        windowDragging();
    }

    public void setWindowBarTitle(String s){
        txtWindowBarTitle.setText(s);
    }

    //window events
    private void onBtnCloseWindow(){
        Model.getInstance().getViewFactory().closeStage();
    }

    private void onBtnMinimizeWindow(){
        Model.getInstance().getViewFactory().minimizeStage();
    }

    private void onBtnMaximizeWindow(){
        Model.getInstance().getViewFactory().maximizeStage();
    }

    private void windowDragging() {
        windowBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        windowBar.setOnMouseDragged(event -> {
            windowBar.getScene().getWindow().setX(event.getScreenX() - x);
            windowBar.getScene().getWindow().setY(event.getScreenY() - y);
        });
    }

}
