package fishingame;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class EventHandler {

    protected ControlPanel controlPanel;
    protected Player player;

    public EventHandler(ControlPanel controlPanel, Player player){
        this.controlPanel = controlPanel;
        this.player = player;
    }

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
