package fishingame;

public class Thon extends Fish{

    public Thon(int positionX, int positionY){
        super("thon.png", positionX, positionY, 25);
    }

    @Override
    public void swimming(){
        this.i = this.i + 0.03;
         setPositionX(posDepart + (int) (Math.sin(i) * 1000));
    }
}
