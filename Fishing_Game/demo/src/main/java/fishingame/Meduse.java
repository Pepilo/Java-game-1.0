package fishingame;

public class Meduse extends Fish{

    public Meduse(int positionX, int positionY){
        super("meduse.png", positionX, positionY, 1);
    }

    @Override
    public void swimming(){
        this.i = this.i + 0.01;
        setPositionX(posDepart + (int) (Math.sin(i) * 150));
    }
}