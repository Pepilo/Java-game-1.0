package fishingame;

import javafx.scene.control.Label;

public class Shop {
    private Player player;
    private Label moneyCount;
    protected int playerSpeedUpgradeCost = 75;
    protected int playerHorizontalUpgradeCost = 200;

    /*Constructor*/
    public Shop(Player player, Label moneyCount) {
        this.player = player;
        this.moneyCount = moneyCount;
    }

    /*Increase vertical speed upgrade*/
    public void upgradePlayerSpeed() {
        if (ControlPanel.banque >= playerSpeedUpgradeCost) {
            ControlPanel.banque -= playerSpeedUpgradeCost;
            moneyCount.setText("Sousous: " + ControlPanel.banque);
            player.increaseSpeed();
            playerSpeedUpgradeCost *= 2; 
        }
    }

    /*Increase horizontal speed upgrade*/
    public void upgradePlayerHorizontal() {
        if (ControlPanel.banque >= playerHorizontalUpgradeCost) {
                ControlPanel.banque -= playerHorizontalUpgradeCost;
                moneyCount.setText("Sousous: " + ControlPanel.banque);
                player.horizontal = true;
                playerSpeedUpgradeCost *= 2; 
        }
    }
}
