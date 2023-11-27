package fishingame;

public class Sardine extends Fish{

    /*Speed*/
    double randomX = (double) (Math.random() * 0.025 - 0.02);

    /*Constructor*/
    public Sardine(int positionX, int positionY){
        super("sardine.png", positionX, positionY, 10);
    }

    /*Specific swimming method*/
    @Override
    public void swimming(){
        this.i = this.i + randomX;
        setPositionX(posDepart + (int) (Math.sin(i) * 400));
    }
}
