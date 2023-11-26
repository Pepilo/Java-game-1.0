package fishingame;

/*Imports*/
import javafx.application.Application;
import java.io.IOException;
import javafx.stage.Stage;

public class App extends Application {

    /*Attribut*/
    private ControlPanel controlPanel;   

    /*Start Method*/
    @Override
    public void start(Stage stage) throws IOException {

        this.controlPanel = new ControlPanel(stage);

        /*Main settings*/
        controlPanel.sceneSetting();
        controlPanel.mainMenuSetting();
        controlPanel.playerSetting();
        controlPanel.fishSetting();
        controlPanel.shopSetting();

        /*Buttons logic*/
        controlPanel.exitLogic();
        controlPanel.shopExitLogic();

        /*Audio and display*/
        controlPanel.tutoDisplay();
        controlPanel.moneyDisplay();
        controlPanel.mainSong();
        controlPanel.fishingAudio();

        controlPanel.startLogic();
        controlPanel.shopButtonLogic();
        controlPanel.shopSpeedLogic();
        controlPanel.shopHorizontalLogic();

        controlPanel.init();
    }

    /*Run the game*/
        public static void main(String[] args) {
            launch();
        }
}
