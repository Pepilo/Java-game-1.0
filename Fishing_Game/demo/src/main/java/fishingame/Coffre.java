package fishingame;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coffre {
    
    protected Image spriteSheet;
    protected ImageView sprite;
    protected String filepath;
    protected int positionX;
    protected int positionY;
    protected int value;

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

    public Coffre() {
        spriteSheet = new Image("coffre.png");
        sprite = new ImageView(spriteSheet);
        sprite.setTranslateX(100);
        sprite.setTranslateY(5100);
        this.value = 100;
        sprite.setUserData(this);
    }

    public Bounds getBounds() {
        return sprite.getBoundsInParent();
    }

}
