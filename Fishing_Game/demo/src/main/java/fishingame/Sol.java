package fishingame;

public class Sol extends Fish{

    public Sol(int positionX, int positionY){
        super("Sol.png", positionX, positionY, 100);
    }

    @Override
    public void update(EventHandler eventHandler){   
        swimming();
    }

    @Override
    public void swimming() {
        this.i = this.i + 0.00;
        setPositionX(posDepart + (int) (Math.sin(i) * 150));
    }

   
}
