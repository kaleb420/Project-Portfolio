package Midgame;

import Helpers.Helper;
import Setup.Player;

/**
 * A player may replace up to six tiles from their currently supply with new, randomly drawn ones. Also handles
 * redrawing at the end of each players turn.
 */
public class Replace_And_Refresh {

    Helper helper;

    public Replace_And_Refresh(Helper helper){
        this.helper=helper;
    }

    /**
     * Discards up to 6 tiles from their pieces, and draws new ones back up to 6.
     */
    public void replace(Player player){
        while (!player.pieces.isEmpty()) {
            helper.printPieces(player.pieces);
            System.out.println("Type in the number correlated to the tile you'd like to remove. If you are done removing tiles type in 0");
            int number=helper.tryParseInt();
            if (number==0)
                break;
            number-=1;
            if (number<player.pieces.size() && number>=0){
                player.pieces.remove(number);
            }
        }
        refreshTiles(player);
    }

    /**
     * At the end of each player's turn, every player draws back up to 6 tiles in their hand
     */
    public void refreshTiles(Player player){
        while (player.pieces.size()!=6){
            player.pieces.add(player.bag.draw());
        }
    }

}
