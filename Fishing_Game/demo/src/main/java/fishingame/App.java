package fishingame;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
// import javafx.scene.media.Media;
// import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    // private MediaPlayer mediaPlayer;
    private Rectangle MenuPrincipal;
    private VBox buttonBox;
    private Label labelStartClick;
    private Label moneyCount;
    private ImageView moneySymbolView;
    private Player player;
    private EventHandler eventHandler;
    private StackPane stackPane;
    private double totalBackgroundHeight;
    private int banque = 0;
    private List<Fish> fishList = new ArrayList<>();
    private AnimationTimer timer;
    private boolean timerIsRunning;
    private boolean firstSpaceKeyPress = true;

    private void setupButtonInteraction(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);

        button.setOnMouseEntered(event -> {
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        button.setOnMouseExited(event -> {
            scaleTransition.setFromX(1.2);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    @Override
    public void start(Stage stage) throws IOException {
        Image backgroundImage = new Image("background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        totalBackgroundHeight = backgroundImage.getHeight();
        backgroundImageView.setViewport(new Rectangle2D(0, 0, 1024, 720 ));
        stackPane = new StackPane();
        stackPane.getChildren().add(backgroundImageView);
        scene = new Scene(stackPane, 1024, 720);

        MenuPrincipal = new Rectangle(100, 100, 640, 360);
        ImagePattern pattern = new ImagePattern(new Image("tableau.png"));
        MenuPrincipal.setFill(pattern);
        stage.setTitle("Fishing Supremacy");
        scene.setFill(Color.rgb(86,191,239));


        buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button ButtonStart = createButton("start.png");
        Button ButtonExit = createButton("Exit.png");

        setupButtonInteraction(ButtonStart);
        setupButtonInteraction(ButtonExit);

        this.player = new Player(5);
        stackPane.getChildren().add(player.sprite);
        this.eventHandler = new EventHandler(this, player);

        labelStartClick = new Label("Press SPACE to Fish !");
        labelStartClick.setStyle("-fx-font-size: 30; -fx-text-fill: Green; -fx-font-weight: bold;");
        labelStartClick.setVisible(false);

        moneyCount = new Label("Pesos: " + banque);
        moneyCount.setStyle("-fx-font-size: 30; -fx-text-fill: White; -fx-font-weight: bold;");
        moneyCount.setVisible(false);
        Image moneySymbol = new Image("sousou.png");
        ImageView moneySymbolView = new ImageView(moneySymbol);
        moneySymbolView.setVisible(false);

        StackPane.setAlignment(labelStartClick, Pos.TOP_CENTER);
        StackPane.setMargin(labelStartClick, new Insets(110, 0, 10, 10));
        StackPane.setAlignment(moneyCount, Pos.TOP_RIGHT);
        StackPane.setMargin(moneyCount, new Insets(50, 100, 10, 10));
        StackPane.setAlignment(moneySymbolView, Pos.TOP_RIGHT);
        StackPane.setMargin(moneySymbolView, new Insets(40, 40, 10, 10));

        // String musicFile = "redneck.mp3";
        // Media sound = new Media(new File(musicFile).toURI().toString());
        // mediaPlayer = new MediaPlayer(sound);
        // mediaPlayer.play();

        /*Implementation Meduses*/
        Meduse meduse1 = new Meduse(-300, 450);
        stackPane.getChildren().add(meduse1.sprite);
        fishList.add(meduse1);
        Meduse meduse2 = new Meduse(300, 450);
        stackPane.getChildren().add(meduse2.sprite);
        fishList.add(meduse2);
        Meduse meduse3 = new Meduse(0, 600);
        stackPane.getChildren().add(meduse3.sprite);
        fishList.add(meduse3);
        Meduse meduse4 = new Meduse(-300, 750);
        stackPane.getChildren().add(meduse4.sprite);
        fishList.add(meduse4);
        Meduse meduse5 = new Meduse(300, 750);
        stackPane.getChildren().add(meduse5.sprite);
        fishList.add(meduse5);
        
        /*Implementation Sardine*/
        Sardine sardine1 = new Sardine(-250, 1000);
        stackPane.getChildren().add(sardine1.sprite);
        fishList.add(sardine1);
        Sardine sardine2 = new Sardine(250, 1400);
        stackPane.getChildren().add(sardine2.sprite);
        fishList.add(sardine2);
        Sardine sardine3 = new Sardine(-250, 1700);
        stackPane.getChildren().add(sardine3.sprite);
        fishList.add(sardine3);

        /*Implementation Meduse*/
        Murene Murene1 = new Murene(400, 5000);
        stackPane.getChildren().add(Murene1.sprite);
        fishList.add(Murene1);
        Murene Murene2 = new Murene(200, 5000);
        stackPane.getChildren().add(Murene2.sprite);
        fishList.add(Murene2);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        ButtonStart.setOnAction(event -> {
            MenuPrincipal.setVisible(false);
            buttonBox.setVisible(false);
            labelStartClick.toFront();
            labelStartClick.setVisible(true);
            moneyCount.toFront();
            moneyCount.setVisible(true);
            moneySymbolView.setVisible(true);
            player.sprite.setVisible(true);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), new KeyValue(labelStartClick.visibleProperty(), false)),
                    new KeyFrame(Duration.seconds(1), new KeyValue(labelStartClick.visibleProperty(), true))
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        ButtonExit.setOnAction(event -> {
            stage.hide();
        });

        buttonBox.getChildren().addAll(ButtonStart, ButtonExit);
        stackPane.getChildren().addAll(MenuPrincipal, buttonBox, labelStartClick, moneyCount, moneySymbolView);

        stage.setScene(scene);
        stage.show();

        eventHandler.pollEvents(scene);
    }

    private Button createButton(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent;");

        return button;
    }

    

    public void handleKeyPressed(KeyCode keycode){
        switch(keycode) {
            case ESCAPE:
                MenuPrincipal.setVisible(true);
                buttonBox.setVisible(true);
                labelStartClick.toBack();
                moneyCount.setVisible(false);
                player.sprite.setVisible(false);
                moneySymbolView.setVisible(false);
                break;
            case Q:
                player.setPositionX(player.getPositionX() - player.getSpeed());
                break;
            case D:
                player.setPositionX(player.getPositionX() + player.getSpeed());
                break;
            case SPACE:

    if (firstSpaceKeyPress) {
        labelStartClick.setVisible(false);
        firstSpaceKeyPress = false;

        timer = new AnimationTimer() {
            public void handle(long now) {
                update();
                player.setPositionY(player.getPositionY() + 2);
                FollowCharacter();
            }
        };
        timer.start();
    }
    break;
    }
    }

    public static void main(String[] args) {
        launch();
    }

    public void update() {
        player.update(eventHandler);
        for (Fish fish : fishList) {
            fish.update(eventHandler);
            if (player.getBounds().intersects(fish.getBounds())) {
                handleFishCollision(fish);
            }}
    }
    

    public void FollowCharacter() {
        double playerPosY = player.getPositionY();
        double offsetFactor = playerPosY ;

        offsetFactor = Math.min(totalBackgroundHeight - 720, Math.max(0, offsetFactor));
        stackPane.setTranslateY(-offsetFactor);
        
        Rectangle2D viewport = new Rectangle2D(0, offsetFactor, 1024, 720);
        ((ImageView) stackPane.getChildren().get(0)).setViewport(viewport);
    }

    private void handleFishCollision(Fish fish) {
        banque += fish.getValue();
        moneyCount.setText("Pesos: " + banque);
    
        stackPane.getChildren().remove(fish);
    
            restartGame();
        
    }

    public void restartGame() {

        player.setPositionX(16); 
        player.setPositionY(0);
        stackPane.setTranslateY(0);
        timer.stop();
        firstSpaceKeyPress = true;
    }    
}
