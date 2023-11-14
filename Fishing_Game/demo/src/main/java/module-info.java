module fishingame {
    requires javafx.controls;
    requires javafx.fxml;

    opens fishingame to javafx.fxml;
    exports fishingame;
}
