package fishingame;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

    protected Image spriteSheet;
    protected ImageView sprite;
    protected int positionX;
    protected int positionY;
    protected int speed;
    protected boolean horizontal = false;

    /*Getter/Setter*/
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    public int getSpeed() {
        return speed;
    }

    public void setPositionX(int positionX){
        sprite.setTranslateX(positionX);
        this.positionX = positionX;
    }
    public void setPositionY(int positionY) {
        sprite.setTranslateY(positionY);
        this.positionY = positionY;
    }

    /*Constructor*/
    public Player(int speed) {
        spriteSheet = new Image("hamecon.png");
        sprite = new ImageView(spriteSheet);
        sprite.setVisible(false);
        sprite.setTranslateX(16);
        sprite.setTranslateY(180);
        this.speed = speed;
    }
    
    /*Collision*/
    public Bounds getBounds() {
        return sprite.getBoundsInParent();
    }

    /*Speed upgrade method*/
    public void increaseSpeed() {
        this.speed = speed +10;
    }

    public void update(EventHandler eventHandler){
    }
}
