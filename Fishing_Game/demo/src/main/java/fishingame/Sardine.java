package fishingame;

public class Sardine extends Fish{

    double randomX = (double) (Math.random() * 0.025 - 0.02);

    public Sardine(int positionX, int positionY){
        super("sardine.png", positionX, positionY, 10);
    }

    @Override
    public void swimming(){
        this.i = this.i + randomX;
        setPositionX(posDepart + (int) (Math.sin(i) * 400));
    }
}
