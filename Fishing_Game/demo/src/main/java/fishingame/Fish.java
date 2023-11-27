package fishingame;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fish {

    protected Image spriteSheet;
    protected ImageView sprite;
    protected String filepath;
    protected int positionX;
    protected int positionY;
    protected int value;
    protected int posDepart;
    protected double i = 0;

    /*Getter/Setter*/
    public int getPositionX(){
        return positionX;
    }
    public int getPositionY(){
        return positionY;
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

    /*Constructor*/
    public Fish(String filepath, int positionX, int positionY, int value) {
        spriteSheet = new Image(filepath);
        sprite = new ImageView(spriteSheet);
        sprite.setTranslateX(positionX);
        sprite.setTranslateY(positionY);
        this.posDepart = positionX;
        this.value = value;
        sprite.setUserData(this);
    }

    /*Collision*/
    public Bounds getBounds() {
        return sprite.getBoundsInParent();
    }
    
    /*Default swimming method */
    public abstract void swimming();

    /*Updated each frame*/
    public void update(EventHandler eventHandler) {
        swimming();
    }
}