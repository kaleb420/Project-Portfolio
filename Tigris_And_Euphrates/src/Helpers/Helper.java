package Helpers;

import Setup.Map;
import Setup.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;

/**
 * All helper functions in this program.
 */
public class Helper {

    public Scanner scan=new Scanner(System.in);
    Map map;
    Search_Algorithms searchAlgorithms;

    public Helper(Map map){
        this.map=map;
        this.searchAlgorithms=new Search_Algorithms(map);
    }

    /**
     * Takes an input from a user and examines if it can be an integer. If not, the user will be queried again
     * @return number user selected
     */
    public int tryParseInt(){
        String input=scan.nextLine();
        try {
            return Integer.parseInt(input);
        }
        catch (Exception e) {
            System.out.println("That is not a number, type in a number.");
            return tryParseInt();
        }
    }

    /**
     * Checks to see if the location on the board is an empty space
     * @param row of the board
     * @param column of the board
     * @return true if it is an emtpy space, false otherwise
     */
    public boolean isEmpty(int row, int column){
        if (!map.board[row][column].equals(map.empty) && !map.board[row][column].equals(map.river)){
            System.out.println("That is not an empty tile on the map.");
            return false;
        }
        return true;
    }

    /**
     * Due to how map tiles have to be conveyed to players, its easiest to have a letter/number format
     * so this function translates the letter into a number and returns the appropriate tile on the map
     * @param input from user
     * @return row in ret[0], column in ret[1], correlates to a location on board
     */
    public int[] inputToLocation(String input){
        int[] ret=new int[2];
        try {
            char letter=input.charAt(0);
            char number=input.charAt(1);
            ret[1]=letter-'A';
            ret[0]=number-'0';
        }
        catch (Exception e) {
            ret[0]=-1;
            ret[1]=-1;
            System.out.println("Error not converted (inputToLocation).");
        }
        return ret;
    }

    /**
     * Determines if the user input is valid, meaning it is a valid capital letter in the first character spot
     * and a number or : in the second. This only ensures the space is on the board, while if the associated
     * error checking functions (such as if a space is not empty) will be done in the appropriate functions
     * @param input user input
     * @return true if it is valid, false otherwise
     */
    public boolean tileErrorCheck(String input){
        if (input.length()!=2)
            return false;
        if (input.charAt(0)<65 || input.charAt(0)>90){
            System.out.println("First character must be a capital letter corresponding to the board.");
            return false;
        }
        if (input.charAt(1)<48 || input.charAt(1)>58){
            System.out.println("Second character must be a number or :");
            return false;
        }
        return true;
    }

    /**
     * Asks the user to input a location on the board in the format of capital letter followed by a number
     * then sends that input into tileErrorCheck to ensure it's a valid space. Capital letter is for column
     * while number is for row.
     * @return location on the board the user chose given it's a valid input
     */
    public int[] chooseLocation(){
        System.out.println("Choose the corresponding capital letter and number for where you would like to place a tile.");
        String input=scan.nextLine();
        if (!tileErrorCheck(input)) {
            return chooseLocation();
        }
        return inputToLocation(input);
    }

    /**
     * Prints the amount of cubes a player has
     * @param player who needs to have their cubes printed
     */
    public void printCubes(Player player){
        System.out.println("These are your current cube counts: ");
        System.out.println(player.faction + " red cubes " + player.cubes.redCubes);
        System.out.println(player.faction + " green cubes " + player.cubes.greenCubes);
        System.out.println(player.faction + " blue cubes " + player.cubes.blueCubes);
        System.out.println(player.faction + " black cubes " + player.cubes.blackCubes);
        System.out.println(player.faction + " wild cubes " + player.cubes.wildCubes);
    }

    /**
     * Prints the pieces a player has
     * @param pieces that a player has
     */
    public void printPieces(ArrayList<String> pieces){
        System.out.println("These are your current tiles.");
        int i=1;
        for (String piece : pieces){
            if (piece==null) // can be null if they don't redraw to full
                continue;
            String print;
            if (piece.equals(map.temple))
                print="Temple";
            else if (piece.equals(map.market))
                print="Market";
            else if (piece.equals(map.farm))
                print="Farm";
            else
                print="Settlement";
            System.out.println(i + ": " + print);
            i++;
        }
    }

    /**
     * Translates a character to the appropriate faction
     * @param leader that needs to be translated
     * @return the full string of that faction's name
     */
    public String translateCharToLeader(char leader){
        if (leader=='B')
            return map.bulls;
        else if (leader=='L')
            return map.lions;
        else if (leader=='A')
            return map.archers;
        else
            return map.pots;
    }

    /**
     * In the case temples were removed, the adjacent spaces to the tile need to be checked to ensure
     * that a leader still has a temple adjacent to it, if it doesn't, then it is removed from the board
     * @param location of the tile being removed
     */
    public void removeLeaderCheck(int[] location){
        ArrayList<int[]> adjacentSpaces=searchAlgorithms.getAdjacent(location);
        boolean adjacentToTempleWithTreasure=false;
        for (int[] adjacentSpace : adjacentSpaces){
            int row=adjacentSpace[0];
            int column=adjacentSpace[1];
            String tile=getTile(adjacentSpace);
            if (isLeader(tile)) {
                for (int[] adj : searchAlgorithms.getAdjacent(adjacentSpace)){ // check the adjacent tiles to check if there is a temple with treasure, if there is the leader stays
                    tile=getTile(adj);
                    if (tile.equals(map.templeWithTreasure)) {
                        adjacentToTempleWithTreasure=true;
                        break;
                    }
                }
                if (!adjacentToTempleWithTreasure) { // if it isn't next to a temple with treasure the leader gets removed
                    map.board[row][column] = map.empty; // set it to empty but doesn't update leader information, may cause a bug later but realistically shouldn't
                    break;
                }
            }
        }
    }

    /**
     * Determines if the tile being analyzed is a leader, leaders have a string length of 2
     * @param tile being analyzed
     * @return true if it is a leader, false otherwise
     */
    public boolean isLeader(String tile){
        return tile.length()==2;
    }

    /**
     * Simple helper function that takes a given location on the map and returns the string value associated
     * with it
     * @param location on the board
     * @return string value of the location on the board
     */
    public String getTile(int[] location){
        int row=location[0];
        int column=location[1];
        return map.board[row][column];
    }
}