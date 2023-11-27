package fishingame;

public class Meduse extends Fish{

    /*Speed*/
    double randomX = (double) (Math.random() * 0.015 - 0.01);

    /*Constructor*/
    public Meduse(int positionX, int positionY){
        super("meduse.png", positionX, positionY, 1);
    }

    /*Specific swimming method*/
    @Override
    public void swimming(){
        this.i = this.i + randomX;
        setPositionX(posDepart + (int) (Math.sin(i) * 150));
    }
}