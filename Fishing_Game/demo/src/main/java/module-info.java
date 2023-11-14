module fishingame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens fishingame to javafx.fxml;
    exports fishingame;
}
