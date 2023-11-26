package fishingame;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CustomButton extends Button{
    
    /*Button constructor*/
    public CustomButton(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        setGraphic(imageView);
        setStyle("-fx-background-color: transparent;");
        setupButtonInteraction();
    }

    /*Button zoom effect*/
    private void setupButtonInteraction() {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200));

        /*Button hover effect*/
        setOnMouseEntered(event -> {
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        /*Button leaving effect*/
        setOnMouseExited(event -> {
            scaleTransition.setFromX(1.2);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }
}
