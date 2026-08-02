package Midgame;

import Helpers.Helper;
import Helpers.Search_Algorithms;
import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Superclass that deals with external and internal conflicts. The injection point for this class is within
 * external and internal conflict respectively, then getSameColorLeaders determines if there are two leaders
 * of the same color in the same kingdom. If so, the conflictManager function is called, gathering all the
 * global variables. Then the player is asked if they would like to add any pieces to the conflict. Afterward,
 * determine who won the conflict (who has a higher strength value), and call the endConflict function. All
 * other details not mentioned are handled by internal conflicts and external conflicts respectively.
 */
public abstract class Conflicts {

    Map map;
    HashMap<String, Player> players;
    Search_Algorithms searchAlgorithms;
    Helper helper;
    Adjust_Map adjustMap;
    public HashMap<String, int[]> leadersInKingdom=new HashMap<>(); // what leaders are in a kingdom, key is leader and value is location
    public HashMap<String, int[]> leadersFighting=new HashMap<>(); // what leaders are in a conflict with each other, key is leader and value is location
    public HashMap<String, int[]> sameColorLeadersInSameKingdom=new HashMap<>(); // what leaders have the same color and are in the same kingdom
    Player[] playerArray=new Player[2]; // these variables are correspondant to attacker in player[0] and defender in player[1]
    int[] originalStrength=new int[2]; // same as above
    int[] strength=new int[2]; // same as above
    int[][] leaderLocations=new int[2][2]; // same as above

    public Conflicts(Map map, Adjust_Map adjustMap, HashMap<String, Player> players, Helper helper){
        this.map=map;
        this.players=players;
        this.helper=helper;
        this.adjustMap=adjustMap;
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    /**
     * From leadersFighting.keySet(), get the character at index 1.
     * @return the character at index 1, which is the color of the leaders fighting
     */
    public char getColorsFighting(){
        for (String leader : leadersFighting.keySet()){
            return leader.charAt(1);
        }
        return 'a';
    }

    /**
     * Override function that each conflict will use to determine a player's strength in a conflict
     * @param player whose strength is being calculated
     * @param location dependent on internal or external conflict
     * @param color of the leaders fighting, irrelevant for internal conflicts
     * @return strength a player has at the start of a conflict
     */
    public abstract int getStrength(Player player, int[] location, char color);

    /**
     * Override function that only external conflict uses, removes all tiles from the kingdom
     * @param loser player who lost
     */
    public abstract void removeTilesFromKingdom(Player loser);

    public abstract void endConflict(Player winner, Player loser, char tile, int points);

    /**
     * Gathers all relevant conflict information and puts them into the appropriate global variables,
     * calls the appropriate function to get the value in the global variable
     */
    public void conflictInformation(){
        char color=getColorsFighting();
        int i=0;
        for (String leader : leadersFighting.keySet()){
            playerArray[i]=players.get(helper.translateCharToLeader(leader.charAt(0)));
            leaderLocations[i]=leadersFighting.get(leader);
            searchAlgorithms.clearVisited();
            originalStrength[i]=getStrength(playerArray[i], leadersFighting.get(leader), leader.charAt(1));
            strength[i]=originalStrength[i];
            i++;
        }
        System.out.println(playerArray[0].faction + " has started this conflict, you have " + strength[0] + " strength.");
        System.out.println(playerArray[1].faction + " is defending, you have " + strength[1] + " strength.");
        addPieces(playerArray[0], 0, originalStrength[0], color); // add pieces to their strength
        addPieces(playerArray[1], 1, originalStrength[1], color);
    }

    /**
     * Gets the colors fighting, then collects all relevant conflict information by calling conflictInformation()
     * depending on who wins and loses calls endConflict with appropriate parameters, and removes the leader
     * from the map
     */
    public void conflictManager() {
        char color=getColorsFighting();
        conflictInformation();
        if (strength[0]>strength[1]) { // if attacker wins
            System.out.println(playerArray[0].faction + " has won the conflict and gained " + originalStrength[0] + " points.");
            endConflict(playerArray[0], playerArray[1], color, originalStrength[1]);
            adjustMap.removeLeader(playerArray[1], color);
        }
        else { // if defender wins
            System.out.println(playerArray[1].faction + " has won the conflict and gained " + originalStrength[0] + " points.");
            endConflict(playerArray[1], playerArray[0], color, originalStrength[0]);
            adjustMap.removeLeader(playerArray[0], color);
        }
    }

    /**
     * Function that examines all leaders in a kingdom and adds them to a global variable along with their
     * location, this global variable is used to check if any of them have the same color, the leader string
     * is the key and the value is the location of the leader
     * @param location of the tile placed (may be a leader or piece)
     */
    public void getLeadersInKingdom(int[] location){
        ArrayList<int[]> kingdom=searchAlgorithms.BFSSearchKingdom(location);
        for (int[] space : kingdom){
            String tile=helper.getTile(space);
            if (helper.isLeader(tile))
                leadersInKingdom.put(tile, space);
        }
    }

    /**
     * Prompts the player if they would like to add any pieces to the conflict
     * @param player given the ability to add pieces
     * @param playerIndex the index of the player so the strength variable can be updated accurately
     * @param currStrength their current strength
     * @param tile being used
     */
    public void addPieces(Player player, int playerIndex, int currStrength, char tile){
        int availableTiles=piecesAvailable(player, tile);
        System.out.println(player.faction + " you currently have " + currStrength + " strength. How many tiles would you like to add? (0-" + availableTiles + ")");
        int added=helper.tryParseInt();
        if (exceedsLimitCheck(added, availableTiles) || added<0){
            System.out.println("You cannot add more tiles than you have or less than 0.");
            addPieces(player, playerIndex, currStrength, tile);
        }
        removeTiles(player, added, tile);
        strength[playerIndex]+=added;
    }

    /**
     * If there is only two leaders involved in the conflict the leaders fighting are chosen automatically,
     * otherwise, the player who started the conflict will be able to choose what leaders fight in what order
     * @return a hashmap of the leaders chosen to be involved in the conflict as a key, with the value
     * being the location of the leader
     */
    public HashMap<String, int[]> chooseLeadersFighting(){
        if (sameColorLeadersInSameKingdom.size()==2)
            return sameColorLeadersInSameKingdom;
        map.printMap();
        System.out.println("Pick two leaders who are eligible to fight each other, they must be the same color.");
        System.out.print("What is the first leader you would like to pick: ");
        String input=helper.scan.nextLine();
        System.out.print("What is the second leader you would like to pick: ");
        String input2=helper.scan.nextLine();
        HashMap<String, int[]> ret=new HashMap<>();
        try {
            if (input.charAt(1)!=input2.charAt(1)) {
                System.out.println("That is not a valid input, type in the leader name exactly as written.");
                return chooseLeadersFighting();
            }
            ret.put(input, leadersFighting.get(input));
            ret.put(input2, leadersFighting.get(input2));
        }
        catch (Exception e) {
            System.out.println("That is not a valid input, type in the leader name exactly as written.");
            return chooseLeadersFighting();
        }
        return ret;
    }

    /**
     * Iterates through the list of leaders to see if any leaders have the same color in the second character
     * location in the key value of leadersInKingdom, if so, the conflict manager function is called
     * and a conflict is inevitable
     * @param location the tile was placed
     * @return true if it starts a conflict, false otherwise
     */
    public boolean getSameColorLeaders(int[] location){
        getLeadersInKingdom(location);
        for (String leader : leadersInKingdom.keySet()){
            for (String leader2 : leadersInKingdom.keySet()){
                char firstColorIndex=leader.charAt(1);
                char secondColorIndex=leader2.charAt(1);
                if (!leader.equals(leader2) && firstColorIndex==secondColorIndex) {
                    sameColorLeadersInSameKingdom.put(leader, leadersInKingdom.get(leader));
                    sameColorLeadersInSameKingdom.put(leader2, leadersInKingdom.get(leader2));
                }
            }
        }
        if (sameColorLeadersInSameKingdom.size()>=2)
            leadersFighting=chooseLeadersFighting();
        return sameColorLeadersInSameKingdom.size()>=2;
    }

    /**
     * Counts how many of a certain tile is available for a player to add during a conflict
     * @param player with the pieces
     * @param tile desired tile to be added to the conflict
     * @return amount of tiles available
     */
    public int piecesAvailable(Player player, char tile) {
        int count=0;
        for (String piece : player.pieces){
            if (piece.charAt(0)==(tile))
                count++;
        }
        return count;
    }

    /**
     * Removes the amount of tiles they added to the conflict of the same tile type
     * @param player who used the tiles
     * @param tilesUsed how many tiles they used
     * @param tile type of tile they used
     */
    public void removeTiles(Player player, int tilesUsed, char tile){
        for (int i = player.pieces.size()-1; i>=0 && tilesUsed>0; i--) {
            if (player.pieces.get(i).charAt(0)==tile) {
                player.pieces.remove(i);
                tilesUsed--;
            }
        }
    }

    /**
     * Error check function to determine if the inputted value by the player is greater than the amount of
     * temples they actually have
     * @param added user input
     * @param tilesAvailable self-explanatory
     * @return true if it is less than or equal to the amount available, false otherwise
     */
    public boolean exceedsLimitCheck(int added, int tilesAvailable){
        return added > tilesAvailable;
    }

    /**
     * Clears all information from the global variables
     */
    public void clearConflict(){
        leadersInKingdom.clear();
        leadersFighting.clear();
    }
}
