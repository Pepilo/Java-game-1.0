package fishingame;

public class Thon extends Fish{

    double randomX = (double) (Math.random() * 0.035 - 0.03);

    public Thon(int positionX, int positionY){
        super("thon.png", positionX, positionY, 25);
    }

    @Override
    public void swimming(){
        this.i = this.i + randomX;
         setPositionX(posDepart + (int) (Math.sin(i) * 1000));
    }
}
