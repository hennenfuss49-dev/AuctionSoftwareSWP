package at.auction.View;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {

    Stage mainStage;

    // method for switching Stages / between FXML documents
    public void switchStage(String fileName, int width, int height, boolean isResizable){
        boolean firstTime = mainStage == null;
        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/" + fileName + ".fxml"))));
            // gets rid of white background
            scene.setFill(Color.TRANSPARENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(mainStage == null)
            mainStage = new Stage();

        mainStage.setScene(scene);
        mainStage.setWidth(width);
        mainStage.setHeight(height);
        mainStage.setResizable(isResizable);
        mainStage.setFullScreenExitHint("");

        // removes default window bar
        if(firstTime)
            mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.setMinWidth(scene.getRoot().minWidth(-1));
        mainStage.setMinHeight(scene.getRoot().minHeight(-1));
        if(firstTime)
            mainStage.show();
    }

    public void closeStage(){
        mainStage.close();
        Platform.exit();
    }

    public void maximizeStage(){
        mainStage.setFullScreen(!mainStage.isFullScreen());
    }

    public void minimizeStage(){
        mainStage.setIconified(true);
    }

}
