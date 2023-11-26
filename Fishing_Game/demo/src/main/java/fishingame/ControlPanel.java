package fishingame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.stage.Stage;

public class ControlPanel {

    private VBox buttonBox; 
    private VBox buttonBoxShop;
    private Button buttonStart;
    private Button buttonExit;
    private Button buttonShop;
    private Button speedUpgradeButton;
    private Button horizontalUpgradeButton;
    private Button buttonExitShop;
    private ImageView moneySymbolView;
    private EventHandler eventHandler;
    private List<Fish> fishList = new ArrayList<>();
    private boolean isGameRunning = false;
    private Label labelStartClick;
    private MediaPlayer mediaPlayer;
    private Rectangle menuPrincipal;
    private Label moneyCount;
    private Player player;
    private static Scene scene;
    private StackPane stackPane;
    private Stage stage;
    private AnimationTimer timer;
    protected static int banque = 0;
    private boolean firstSpaceKeyPress = true;
    private boolean isMovingRight = false;
    private boolean isMovingLeft = false;
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private MediaPlayer collisionMediaPlayer;
    private double totalBackgroundHeight;
    private Shop shop;

    public ControlPanel(Stage stage){
        this.stage = stage;
    }

    /*Setting main scene*/
    public void sceneSetting() {
        stage.setTitle("Turbo Fishing Nitro+"); 
        Image backgroundImage = new Image("background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        totalBackgroundHeight = backgroundImage.getHeight();
        backgroundImageView.setViewport(new Rectangle2D(0, 0, 1024, 720 ));

        stackPane = new StackPane();
        stackPane.getChildren().add(backgroundImageView);
        
        scene = new Scene(stackPane, 1024, 720);
        scene.setFill(Color.rgb(86, 191, 239));   
    }
    
    /*Setting main menu*/
    public void mainMenuSetting() {
        menuPrincipal = new Rectangle(100, 100, 640, 460);
        ImagePattern pattern = new ImagePattern(new Image("tableau.png"));
        menuPrincipal.setFill(pattern);
        buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);
    }

    /*Setting player*/
    public void playerSetting() {
        this.player = new Player(5);
        stackPane.getChildren().add(player.sprite);
        this.eventHandler = new EventHandler(this, player);
    }

    /*Setting Fish*/
    public void fishSetting() {
        /*Implementation Meduses*/
        for (int i = 0; i < 20; i++) {
            int randomX = (int) (Math.random() * 800 - 400);
            int randomY = (int) (Math.random() * 4500 + 450); 
            Meduse meduse = new Meduse(randomX, randomY);
            stackPane.getChildren().add(meduse.sprite);
            fishList.add(meduse);
        }
        /*Implementation Sardine*/
        for (int i = 0; i < 6; i++) {
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
        /*Implementation coffre et sol */
        Coffre coffre = new Coffre();
        stackPane.getChildren().add(coffre.sprite);

        Sol solFinal = new Sol(0, 5200);
        stackPane.getChildren().add(solFinal.sprite);
        fishList.add(solFinal);
    }

    /*Setting shop menu*/
    public void shopSetting() {
        this.buttonBoxShop = new VBox(20);
        buttonBoxShop.setAlignment(Pos.CENTER);
        buttonBoxShop.setVisible(false);
        // StackPane.setAlignment(buttonShop, Pos.TOP_CENTER);
        // StackPane.setMargin(buttonShop, new Insets(110, 0, 10, 10));
    }

    /*Start button Logic*/
    public void startLogic() {
        buttonStart = new CustomButton("start.png");
            buttonStart.setOnAction(event -> {
                menuPrincipal.setVisible(false);
                buttonBox.setVisible(false);
                labelStartClick.toFront();
                labelStartClick.setVisible(true);
                moneyCount.toFront();
                moneyCount.setVisible(true);
                player.sprite.setVisible(true);
                mediaPlayer.play();
                isGameRunning = true;

                /*Press key flashing*/
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(0.5), new KeyValue(labelStartClick.visibleProperty(), false)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(labelStartClick.visibleProperty(), true))
                );
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            });
    }

    /*Main exit button logic*/
    public void exitLogic() {
        buttonExit = new CustomButton("exit.png");
        buttonExit.setOnAction(event -> {
            stage.hide();
        });
    }

    /*Shop button logic*/
    public void shopButtonLogic() {
        buttonShop = new CustomButton("shop.png");
            buttonShop.setOnAction(event -> {
                moneyCount.setVisible(true);
                buttonBox.setVisible(false);
                buttonBoxShop.setVisible(true);
                
            });
    }

    /*Speed upgrade logic*/
    public void shopSpeedLogic() {
        speedUpgradeButton = new Button("Upgrade Speed (Cost: 75 sousous)");
            speedUpgradeButton.setOnAction(event -> {
                shop.upgradePlayerSpeed();
            });
    }

    /*Horizontal upgrade logic*/
    public void shopHorizontalLogic() {
        horizontalUpgradeButton = new Button("Horizontal upgrade (Cost: 200 sousous)");
            horizontalUpgradeButton.setOnAction(event -> {
                shop.upgradePlayerHorizontal();
            });
    }

    /*Shop exit button logic */
    public void shopExitLogic() {
        buttonExitShop = new CustomButton("exit.png");
            buttonExitShop.setOnAction(event -> {
                buttonBox.setVisible(true);
                buttonBoxShop.setVisible(false);
            });
    }

    /*Tutorial display*/
    public void tutoDisplay() {
        labelStartClick = new Label("Press SPACE to Fish !");
        labelStartClick.setStyle("-fx-font-size: 30; -fx-text-fill: Green; -fx-font-weight: bold;");
        labelStartClick.setVisible(false);
        StackPane.setAlignment(labelStartClick, Pos.TOP_CENTER);
        StackPane.setMargin(labelStartClick, new Insets(110, 0, 10, 10));
    }

    /*Money display*/
    public void moneyDisplay(){
        moneyCount = new Label("Sousous: " + ControlPanel.banque);
        moneyCount.setStyle("-fx-font-size: 30; -fx-text-fill: White; -fx-font-weight: bold;");
        moneyCount.setVisible(false);
        StackPane.setAlignment(moneyCount, Pos.TOP_RIGHT);
        StackPane.setMargin(moneyCount, new Insets(50, 100, 10, 10));
        Image moneySymbol = new Image("sousou.png");
        this.moneySymbolView = new ImageView(moneySymbol);
        StackPane.setAlignment(moneySymbolView, Pos.TOP_RIGHT);
        StackPane.setMargin(moneySymbolView, new Insets(40, 40, 10, 10));
        this.shop = new Shop(player, moneyCount);
    }

    /*Main song*/
    public void mainSong() {
        String musicFile = "Fishing_Game/demo/src/main/resources/redneck_paradise.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
    }

    /*Fishing audio effect*/
    public void fishingAudio() {
        String collisionSoundFile = "Fishing_Game/demo/src/main/resources/Poisson_attrape.mp3";
        Media collisionSound = new Media(new File(collisionSoundFile).toURI().toString());
        collisionMediaPlayer = new MediaPlayer(collisionSound);
    }

    /*Scene init*/
    public void init() {
        buttonBox.getChildren().addAll(buttonStart, buttonShop, buttonExit);
        buttonBoxShop.getChildren().addAll(speedUpgradeButton, buttonExitShop, horizontalUpgradeButton);
        stackPane.getChildren().addAll(menuPrincipal, buttonBox, buttonBoxShop, labelStartClick, moneyCount, moneySymbolView);
        eventHandler.pollEvents(scene);
        stage.setScene(scene);
        stage.show();
    }
    
    /*KeyPress handler*/
    public void handleKeyPressed(KeyCode keycode) {
        switch (keycode) {
            case ESCAPE:
            if (isGameRunning) {
                break;
            }
                menuPrincipal.setVisible(true);
                buttonBox.setVisible(true);
                labelStartClick.toBack();
                mediaPlayer.stop();
                player.sprite.setVisible(false);

                break;
            case Q:
                movePlayerLeft();
                break;
            case D:
                movePlayerRight();
                break;
            case Z:
                movePlayerUp();
                break;
            case S:
                movePlayerDown();
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

    /*KeyRelease Handler*/
    public void handleKeyReleased(KeyCode keycode) {
        switch (keycode) {
            case D:
                isMovingRight = false;
                break;
            case Q:
                isMovingLeft = false;
                break;
            case Z:
                isMovingUp = false;
                break;
            case S:
                isMovingDown = false;
                break;
        }
        if (!isMovingRight && !isMovingLeft) {
            moveTimer.stop();
        }
        if (!isMovingUp && !isMovingDown) {
            moveTimer.stop();
        }
    }

    /*Moving methods*/
    private void movePlayerRight() {
        isMovingRight = true;
        moveTimer.start();
    }
    private void movePlayerLeft() {
        isMovingLeft = true;
        moveTimer.start();
    }
    private void movePlayerUp() {
        if (player.horizontal){
        isMovingUp = true;
        moveTimer.start();
        }
    }   
    private void movePlayerDown() {
        if (player.horizontal){
        isMovingDown = true;
        moveTimer.start();
        }
    }

    /*Player movement boudaries*/
    private AnimationTimer moveTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (isMovingRight && player.getPositionX() < 495) {
                player.setPositionX(player.getPositionX() + player.getSpeed());
            }
            if (isMovingLeft && player.getPositionX() > -495) {
                player.setPositionX(player.getPositionX() - player.getSpeed());
            }
            if (isMovingUp && player.getPositionY() > 0) {
                player.setPositionY(player.getPositionY() - player.getSpeed());
            }
            if (isMovingDown && player.getPositionY() < 5200) {
                player.setPositionY(player.getPositionY() + player.getSpeed());
            }
        }
    };

    /*Frame update*/
    public void update() {
        player.update(eventHandler);
        for (Fish fish : fishList) {
            fish.update(eventHandler);
            if (player.getBounds().intersects(fish.getBounds())) {
                handleFishCollision(fish);
            }
        }
    }

    /*Camera logic*/
    public void FollowCharacter() {
        double playerPosY = player.getPositionY();
        double offsetFactor = playerPosY;

        offsetFactor = Math.min(totalBackgroundHeight - 720, Math.max(0, offsetFactor));
        stackPane.setTranslateY(-offsetFactor);

        Rectangle2D viewport = new Rectangle2D(0, offsetFactor, 1024, 720);
        ((ImageView) stackPane.getChildren().get(0)).setViewport(viewport);
    }

    /*Fishing logic*/
    private void handleFishCollision(Fish fish) {
        if (collisionMediaPlayer != null) {
            collisionMediaPlayer.stop();
        }
        collisionMediaPlayer.play();
        banque += fish.getValue();
        moneyCount.setText("Sousous: " + banque);
        stackPane.getChildren().remove(fish);
        restartGame();
        isGameRunning = false;
    }
    
    /*New run logic*/
    public void restartGame() {
        player.setPositionX(16);
        player.setPositionY(0);
        stackPane.setTranslateY(0);
        timer.stop();
        firstSpaceKeyPress = true;
    }
}
