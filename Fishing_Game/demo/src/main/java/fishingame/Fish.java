package fishingame;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public abstract class Fish {

    protected Image spriteSheet;
    protected ImageView sprite;
    protected String filepath;
    protected int positionX;
    protected int positionY;
    protected int speed;
    protected int value;
    protected int posDepart;
    protected double i = 0;

    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
    }
    public int getSpeed(){
        return speed;
    }
    public int getValue() {
        return value;
    }
    public void setPositionX(int positionX){
        sprite.setTranslateX(positionX);
        this.positionX = positionX;
    }
    public void setPositionY(int positionY) {
        sprite.setTranslateY(positionY);
        this.positionY = positionY;
    }

    public Fish(String filepath, int positionX, int positionY, int value) {
        spriteSheet = new Image(filepath);
        sprite = new ImageView(spriteSheet);
        sprite.setTranslateX(positionX);
        sprite.setTranslateY(positionY);
        this.posDepart = positionX;
        this.value = value;
        sprite.setUserData(this);
    }

    public Bounds getBounds() {
        return sprite.getBoundsInParent();
    }
    
    public abstract void swimming();

    public void update(EventHandler eventHandler) {
        swimming();
    }
}