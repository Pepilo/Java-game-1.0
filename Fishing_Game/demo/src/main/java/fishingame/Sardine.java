package fishingame;

public class Sardine extends Fish{

    public Sardine(int positionX, int positionY){
        super("sardine.png", positionX, positionY, 10);
    }

    @Override
    public void swimming(){
        this.i = this.i + 0.025;
        setPositionX(posDepart + (int) (Math.sin(i) * 500));
    }
    @Override
    public void update(EventHandler eventHandler){
        swimming();
    }
}
