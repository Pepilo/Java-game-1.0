package fishingame;

/*Imports*/
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    /*Attributs*/
    private static Scene scene;
    private MediaPlayer mediaPlayer;
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
    private boolean firstSpaceKeyPress = true;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;

    private AnimationTimer moveTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (isMovingRight && player.getPositionX() < 495) {
                player.setPositionX(player.getPositionX() + player.getSpeed());
            }
            if (isMovingLeft && player.getPositionX() > -495) {
                player.setPositionX(player.getPositionX() - player.getSpeed());
            }
        }
    };

    /*Button zoom effect*/
    private void setupButtonInteraction(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);

        /*Button hover effect*/
        button.setOnMouseEntered(event -> {
            scaleTransition.setFromX(1);
            scaleTransition.setFromY(1);
            scaleTransition.setToX(1.2);
            scaleTransition.setToY(1.2);
            scaleTransition.play();
        });

        /*Button leaving effect*/
        button.setOnMouseExited(event -> {
            scaleTransition.setFromX(1.2);
            scaleTransition.setFromY(1.2);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();
        });
    }

    /*Start Method*/
    @Override
    public void start(Stage stage) throws IOException {
        /*Setting main scene*/
        Image backgroundImage = new Image("background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        totalBackgroundHeight = backgroundImage.getHeight();
        backgroundImageView.setViewport(new Rectangle2D(0, 0, 1024, 720 ));
        stackPane = new StackPane();
        stackPane.getChildren().add(backgroundImageView);
        scene = new Scene(stackPane, 1024, 720);
        scene.setFill(Color.rgb(86, 191, 239));
        stage.setTitle("Turbo Fishing Nitro+");

        /*Setting main menu*/
        MenuPrincipal = new Rectangle(100, 100, 640, 360);
        ImagePattern pattern = new ImagePattern(new Image("tableau.png"));
        MenuPrincipal.setFill(pattern);
        buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button ButtonStart = createButton("start.png");
        Button ButtonExit = createButton("Exit.png");
        setupButtonInteraction(ButtonStart);
        setupButtonInteraction(ButtonExit);

        /*Setting player*/
        this.player = new Player(5);
        stackPane.getChildren().add(player.sprite);
        this.eventHandler = new EventHandler(this, player);

        /*Tutorial display*/
        labelStartClick = new Label("Press SPACE to Fish !");
        labelStartClick.setStyle("-fx-font-size: 30; -fx-text-fill: Green; -fx-font-weight: bold;");
        labelStartClick.setVisible(false);
        StackPane.setAlignment(labelStartClick, Pos.TOP_CENTER);
        StackPane.setMargin(labelStartClick, new Insets(110, 0, 10, 10));

        /*Money display*/
        moneyCount = new Label("Pesos: " + banque);
        moneyCount.setStyle("-fx-font-size: 30; -fx-text-fill: White; -fx-font-weight: bold;");
        moneyCount.setVisible(false);
        Image moneySymbol = new Image("sousou.png");
        ImageView moneySymbolView = new ImageView(moneySymbol);
        moneySymbolView.setVisible(false);        
        StackPane.setAlignment(moneyCount, Pos.TOP_RIGHT);
        StackPane.setMargin(moneyCount, new Insets(50, 100, 10, 10));
        StackPane.setAlignment(moneySymbolView, Pos.TOP_RIGHT);
        StackPane.setMargin(moneySymbolView, new Insets(40, 40, 10, 10));

        /*Main menu song */
        String musicFile = "Fishing_Game/demo/src/main/resources/redneck.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        /*Implementation Meduses*/
        for (int i = 0; i < 15; i++) {
            int randomX = (int) (Math.random() * 800 - 400);
            int randomY = (int) (Math.random() * 4500 + 450); 
            Meduse meduse = new Meduse(randomX, randomY);
            stackPane.getChildren().add(meduse.sprite);
            fishList.add(meduse);
        }

        /*Implementation Sardine*/
        for (int i = 0; i < 5; i++) {
            int randomX = (int) (Math.random() * 800 - 400);
            int randomY = (int) (Math.random() * 2500 + 450);
            Sardine sardine = new Sardine(randomX, randomY);
            stackPane.getChildren().add(sardine.sprite);
            fishList.add(sardine);
        }

        /*Implementation Thon*/
        for (int i = 0; i < 3; i++) {
            int randomX = (int) (Math.random() * 800 - 400);
            int randomY = (int) (Math.random() * 3500 + 1000);
            Thon thon = new Thon(randomX, randomY);
            stackPane.getChildren().add(thon.sprite);
            fishList.add(thon);
        }

        Sol SolFinal = new Sol(0, 5200);
        stackPane.getChildren().add(SolFinal.sprite);
        fishList.add(SolFinal);

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
            mediaPlayer.play();

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

    public void handleKeyPressed(KeyCode keycode) {
        switch (keycode) {
            case ESCAPE:
                MenuPrincipal.setVisible(true);
                buttonBox.setVisible(true);
                labelStartClick.toBack();
                mediaPlayer.stop();
                moneyCount.setVisible(false);
                player.sprite.setVisible(false);
                moneySymbolView.setVisible(false);
                break;
            case Q:
                movePlayerLeft();
                break;
            case D:
                movePlayerRight();
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

    public void handleKeyReleased(KeyCode keycode) {
        switch (keycode) {
            case D:
                isMovingRight = false;
                break;
            case Q:
                isMovingLeft = false;
                break;
        }
        // Arrêtez le mouvement uniquement si les deux touches sont relâchées
        if (!isMovingRight && !isMovingLeft) {
            moveTimer.stop();
        }
    }

    private void movePlayerRight() {
        isMovingRight = true;
        moveTimer.start();
    }

    private void movePlayerLeft() {
        isMovingLeft = true;
        moveTimer.start();
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
            }
        }
    }

    public void FollowCharacter() {
        double playerPosY = player.getPositionY();
        double offsetFactor = playerPosY;

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
