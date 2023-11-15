package fishingame;

public class Sardine extends Fish{

    public Sardine(int positionX, int positionY){
        super("sardine.png", positionX, positionY, 10);
    }

    @Override
    public void swimming(){
        this.i = this.i + 0.02;
        setPositionX(posDepart + (int) (Math.sin(i) * 400));
    }
}
