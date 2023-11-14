package fishingame;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class EventHandler {

    protected App app;
    protected Player player;

    public EventHandler(App app, Player player){
        this.app = app;
        this.player = player;
    }

    public void pollEvents(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode keycode = event.getCode();
            app.handleKeyPressed(keycode);
        });
    }
}
