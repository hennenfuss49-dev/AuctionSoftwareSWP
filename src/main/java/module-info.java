module at.auction.auction {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens at.auction.Main to javafx.fxml;
    opens at.auction.View to javafx.fxml;
    opens at.auction.Model to javafx.fxml;
    opens at.auction.Controller to javafx.fxml;
    opens at.auction.Controller.Cards to javafx.fxml;
    exports at.auction.Main;
    opens at.auction.Database to javafx.fxml;
    opens at.auction.Auth to javafx.fxml;
}