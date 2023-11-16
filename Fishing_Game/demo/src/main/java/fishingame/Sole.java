package fishingame;

public class Sole extends Fish{

    public Sole(int positionX, int positionY){
        super("sole.png", positionX, positionY, 0);
    }

    @Override
    public void update(EventHandler eventHandler){   
        swimming();
    }

    @Override
    public void swimming() {
        this.i = this.i + 0.01;
        setPositionX(posDepart + (int) (Math.sin(i) * 1000));
    }

   
}
