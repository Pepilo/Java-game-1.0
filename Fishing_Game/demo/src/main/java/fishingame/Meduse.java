package fishingame;

public class Meduse extends Fish{

    double randomX = (double) (Math.random() * 0.015 - 0.01);

    public Meduse(int positionX, int positionY){
        super("meduse.png", positionX, positionY, 1);
    }

    @Override
    public void swimming(){
        this.i = this.i + randomX;
        setPositionX(posDepart + (int) (Math.sin(i) * 150));
    }
}