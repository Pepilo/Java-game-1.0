package fishingame;

import javafx.scene.input.KeyCode;
import javafx.scene.Scene;

public class EventHandler {

    protected ControlPanel controlPanel;
    protected Player player;

    /*Constructor*/
    public EventHandler(ControlPanel controlPanel, Player player){
        this.controlPanel = controlPanel;
        this.player = player;
    }

    /*Set event trigger*/
    public void pollEvents(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode keycode = event.getCode();
            controlPanel.handleKeyPressed(keycode);
        });
        scene.setOnKeyReleased(event -> {
            KeyCode keycode = event.getCode();
            controlPanel.handleKeyReleased(keycode);
        });
    }
}
