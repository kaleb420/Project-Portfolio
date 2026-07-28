package Midgame;

import Helpers.Helper;
import Setup.Map;
import Setup.Player;

import java.util.*;

public class Make_Move {

    Map map;
    Helper helper;
    HashMap<String, Player> players;
    Place_Tile placeTile;
    Leader leader;
    Catastrophe catastrophe;
    Replace_And_Refresh replaceAndRefresh;
    boolean gameEnd=false;
    Monument monument;
    ExternalConflicts externalConflicts;
    InternalConflicts internalConflicts;
    List<String> factions;
    Adjust_Map adjustMap;
    Collect_Treasure collectTreasure;

    public Make_Move(Map map, HashMap<String, Player> players, Helper helper){
        this.map=map;
        this.players=players;
        this.helper=helper;
        this.placeTile=new Place_Tile(map, helper, players);
        this.leader=new Leader(map, helper, players);
        this.catastrophe=new Catastrophe(map, helper);
        this.replaceAndRefresh=new Replace_And_Refresh(helper);
        this.monument=new Monument(map, players, helper);
        this.adjustMap=new Adjust_Map(map, players, helper);
        externalConflicts=new ExternalConflicts(map, adjustMap, players, helper);
        internalConflicts=new InternalConflicts(map, adjustMap, players, helper);
        this.factions=new LinkedList<>(List.of(map.lions, map.archers, map.pots, map.bulls));
        this.collectTreasure=new Collect_Treasure(players, map, helper);
    }

    /**
     * After a player places a tile, there is a check to see if there is an external conflict, if there is
     * no cube gets awarded for the placed tile, if there is not, then the appropriate cube function is
     * called based on what tile was placed
     * @param player who placed the tile
     * @param location the tile was placed
     */
    public void cubesAndExternalConflictCheck(Player player, int[] location){
        String tile= helper.getTile(location);
        if (!externalConflicts.externalConflictCheck(location)) {
            if (tile.equals(map.temple))
                player.cubes.getRedCube(map, location);
            else if (tile.equals(map.market))
                player.cubes.getGreenCube(map, location);
            else if (tile.equals(map.farm))
                player.cubes.getBlueCube(map, location);
            else if (tile.equals(map.settlement))
                player.cubes.getBlackCube(map, location);
        }
    }

    /**
     * Works as a starting point for each player, they choose the associated number to the action they
     * would like to take, then the appropriate function is called
     */
    public void makeMove(Player player){
        System.out.println("Type in the appropriate number to take the correlated action.");
        System.out.println("1: Place one of your drawn tiles.");
        System.out.println("2: Place, move, or withdraw one of your leaders.");
        System.out.println("3: Place a catastrophe tile.");
        System.out.println("4: Replace up to six of your tiles.");
        helper.printPieces(player.pieces);
        helper.printCubes(player);
        map.printMap();
        String input=helper.scan.nextLine();
        int[] location;
        switch (input) {
            case "1":
                location=placeTile.placeTile(player);
                cubesAndExternalConflictCheck(player, location);
                monument.monumentCheck(location);

                break;
            case "2":
                location=leader.leader(player);
                internalConflicts.internalConflictCheck(location);
                break;
            case "3":
                catastrophe.placeCatastrophe(player);
                break;
            case "4":
                replaceAndRefresh.replace(player);
                break;
            default:
                System.out.println("Please choose a number between 1-4.");
                makeMove(player);
                break;
        }
    }

    /**
     * Checks to see if the game has ended, the game ends when a player needs to draw tiles and there is
     * none in the bag, or if there are only two or one treasures on the map. But this function will
     * only check to see if there is enough treasures on the map, the draw function will take care of
     * the other condition.
     */
    public void gameEnd(){
        gameEnd=map.totalTreasures == 2 || map.totalTreasures == 1;
    }

    public void gameEngine(int playerCount){
    int turn=0;
        while (!gameEnd) {
            Player currPlayer = players.get(factions.get(turn));
            System.out.println("It is now the " + currPlayer.faction + " turn");
            for (int i = 0; i < 2; i++) { // two actions in a turn
                makeMove(currPlayer);
            }
            monument.endOfTurn();
            for (String faction : players.keySet()) { //redraw back up to 6 tiles at the end of each player's turn
                Player redraw = players.get(faction);
                replaceAndRefresh.refreshTiles(players.get(faction));
                if (redraw.pieces.size() != 6)
                    gameEnd = true;
            }
            gameEnd();
            turn++;
            turn = turn % playerCount;
        }
    }
}
