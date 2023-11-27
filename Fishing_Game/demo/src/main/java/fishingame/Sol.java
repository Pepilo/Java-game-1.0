package fishingame;

public class Sol extends Fish{

    /*Constructor*/
    public Sol(int positionX, int positionY){
        super("Sol.png", positionX, positionY, 100);
    }

    /*Specific swimming method*/
    @Override
    public void swimming() {
        this.i = this.i + 0.00;
        setPositionX(posDepart + (int) (Math.sin(i) * 150));
    }
}
