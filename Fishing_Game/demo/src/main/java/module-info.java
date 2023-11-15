module fishingame {
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    

    opens fishingame to javafx.fxml;
    exports fishingame;
}
