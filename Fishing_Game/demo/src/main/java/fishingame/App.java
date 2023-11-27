package fishingame;

/*Imports*/
import javafx.application.Application;
import java.io.IOException;
import javafx.stage.Stage;

public class App extends Application {

    private ControlPanel controlPanel;   

    @Override
    public void start(Stage stage) throws IOException {

        this.controlPanel = new ControlPanel(stage);

        /*Main settings*/
        controlPanel.sceneSetting();
        controlPanel.mainMenuSetting();
        controlPanel.playerSetting();
        controlPanel.fishSetting();
        controlPanel.shopSetting();

        /*Exit buttons logic*/
        controlPanel.exitLogic();
        controlPanel.shopExitLogic();

        /*Audio and display*/
        controlPanel.tutoDisplay();
        controlPanel.moneyDisplay();
        controlPanel.mainSong();
        controlPanel.fishingAudio();

        /*Other buttons logic*/
        controlPanel.startLogic();
        controlPanel.shopButtonLogic();
        controlPanel.shopSpeedLogic();
        controlPanel.shopHorizontalLogic();

        /*Init logic*/
        controlPanel.init();
    }

    /*Run the game*/
        public static void main(String[] args) {
            launch();
        }
}
