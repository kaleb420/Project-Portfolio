package Helpers;

import Setup.Map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

public class Search_Algorithms {

    public HashSet<String> visited=new HashSet<>();
    Map map;

    public Search_Algorithms(Map map){
        this.map=map;
    }

    public void clearVisited(){
        visited.clear();
    }

    /**
     * Checks to see if an input is within the bounds of the board
     * @param location on the board to be evaluated
     * @return true if is within the bounds on the board, false otherwise
     */
    public boolean inBounds(int[] location){
        if (location[0]<0 || location[0]>=map.board.length)
            return false;
        else
            return location[1] >= 0 && location[1] < map.board[0].length;
    }

    /**
     * Given a location on the board, gives the adjacent tiles as long as it's a part of the kingdom
     * (not empty, river, catastrophe, or conflict)
     * @param location on the board
     * @return a 2d array of the adjacent tiles
     */
    public ArrayList<int[]> adjacency(int[] location){
        String currLocation=location[0] + ", " + location[1];
        visited.add(currLocation);
        ArrayList<int[]> ret=new ArrayList<>();
        int adjustment=location[0]+1;
        int[] up=new int[]{adjustment, location[1]};
        String newTile=up[0] + ", " + up[1];
        String tile;
        if (!visited.contains(newTile) && inBounds(up)) {
            tile = map.board[up[0]][up[1]];
            visited.add(newTile);
            if (!tile.equals(map.empty) && !tile.equals(map.river) && !tile.equals(map.catastrophe) && !tile.equals(map.conflict)) // check to ensure that the tile is not empty, not a river, not a catastrophe, and not a conflict
                ret.add(up);
        }
        adjustment=location[0]-1;
        int[] down=new int[]{adjustment, location[1]};
        newTile=down[0] + ", " + down[1];
        if (!visited.contains(newTile) && inBounds(down)) {
            tile = map.board[down[0]][down[1]];
            visited.add(newTile);
            if (!tile.equals(map.empty) && !tile.equals(map.river) && !tile.equals(map.catastrophe) && !tile.equals(map.conflict))
                ret.add(down);
        }
        adjustment=location[1]+1;
        int[] right=new int[]{location[0], adjustment};
        newTile=right[0] + ", " + right[1];
        if (!visited.contains(newTile) && inBounds(right)) {
            tile = map.board[right[0]][right[1]];
            visited.add(newTile);
            if (!tile.equals(map.empty) && !tile.equals(map.river) && !tile.equals(map.catastrophe) && !tile.equals(map.conflict))
                ret.add(right);
        }
        adjustment=location[1]-1;
        int[] left=new int[]{location[0], adjustment};
        newTile=left[0] + ", " + left[1];
        if (!visited.contains(newTile) && inBounds(left)) {
            tile = map.board[left[0]][left[1]];
            visited.add(newTile);
            if (!tile.equals(map.empty) && !tile.equals(map.river) && !tile.equals(map.catastrophe) && !tile.equals(map.conflict))
                ret.add(left);
        }
        return ret;
    }

    /**
     * This function searches the kingdom to examine if the piece placed (either tile or leader) has an
     * equivalent leader in the same kingdom
     * @param location of the tile placed
     * @param color of the tile placed
     * @return the faction that is connected to the piece if there is one, else null
     */
    public Optional<Character> searchKingdomLeaders(Map map, int[] location, char color){
        Optional<Character> ret;
        int row=location[0];
        int column=location[1];
        String piece=map.board[row][column];
        String bullGoal=String.valueOf(map.bulls.charAt(0)) + color;
        String lionGoal=String.valueOf(map.lions.charAt(0)) + color;
        String archerGoal=String.valueOf(map.archers.charAt(0)) + color;
        String potGoal=String.valueOf(map.pots.charAt(0)) + color;
        if (piece.equals(bullGoal))
            return Optional.of('B');
        else if (piece.equals(lionGoal))
            return Optional.of('L');
        else if (piece.equals(archerGoal))
            return Optional.of('A');
        else if (piece.equals(potGoal))
            return Optional.of('P');
        ArrayList<int[]> adjacentSpaces=adjacency(location);
        for (int[] adjacentSpace : adjacentSpaces){
            ret=searchKingdomLeaders(map, adjacentSpace, color);
            if (ret.isPresent()) {
                visited.clear();
                return ret;
            }
        }
        visited.clear();
        return Optional.empty();
    }


}
