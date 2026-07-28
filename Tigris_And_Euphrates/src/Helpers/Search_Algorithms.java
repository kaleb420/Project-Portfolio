package Helpers;

import Setup.Map;

import java.util.*;

public class Search_Algorithms {

    public HashSet<String> visited=new HashSet<>();
    public Queue<int[]> toVisit=new LinkedList<>();
    Map map;

    public Search_Algorithms(Map map){
        this.map=map;
    }

    /**
     * Function that clears the current search information, clears visited and toVisit
     */
    public void clearVisited(){
        visited.clear();
        toVisit.clear();
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
     * Get the tile up from the given location, as long as it is not empty, a river, conflict, or catastrophe
     * @param location that is being searched
     * @return the coordinates above the location given
     */
    public int[] getUp(int[] location){
        int[] up=new int[]{location[0]+1, location[1]};
        if (!inBounds(up))
            return null;
        String tile=map.board[up[0]][up[1]];
        if (tile.equals(map.empty) || tile.equals(map.river) || tile.equals(map.conflict) || tile.equals(map.catastrophe))
            return null;
        return up;
    }

    /**
     * Get the tile below the given location, as long as it is not empty, a river, conflict, or catastrophe
     * @param location that is being searched
     * @return the coordinates below the location given
     */
    public int[] getDown(int[] location){
        int[] down=new int[]{location[0]-1, location[1]};
        if (!inBounds(down))
            return null;
        String tile=map.board[down[0]][down[1]];
        if (tile.equals(map.empty) || tile.equals(map.river) || tile.equals(map.conflict) || tile.equals(map.catastrophe))
            return null;
        return down;
    }

    /**
     * Get the tile right from the given location, as long as it is not empty, a river, conflict, or catastrophe
     * @param location that is being searched
     * @return the coordinates right of the location given
     */
    public int[] getRight(int[] location){
        int[] right=new int[]{location[0], location[1]+1};
        if (!inBounds(right))
            return null;
        String tile=map.board[right[0]][right[1]];
        if (tile.equals(map.empty) || tile.equals(map.river) || tile.equals(map.conflict) || tile.equals(map.catastrophe))
            return null;
        return right;
    }

    /**
     * Get the tile left from the given location, as long as it is not empty, a river, conflict, or catastrophe
     * @param location that is being searched
     * @return the coordinates left of the location given
     */
    public int[] getLeft(int[] location){
        int[] left=new int[]{location[0], location[1]-1};
        if (!inBounds(left))
            return null;
        String tile=map.board[left[0]][left[1]];
        if (tile.equals(map.empty) || tile.equals(map.river) || tile.equals(map.conflict) || tile.equals(map.catastrophe))
            return null;
        return left;
    }

    /**
     * Based on a given space, get the spaces surrounding it that has not been visited, not empty, not a river,
     * not a conflict, and not a catastrophe
     * @param location of the tile being analyzed
     * @return an arrayList of int[] that contains every adjacent space to the given tile
     */
    public ArrayList<int[]> getAdjacent(int[] location){
        ArrayList<int[]> adjacent=new ArrayList<>();
        int[] up=getUp(location);
        if (up!=null)
            adjacent.add(up);
        int[] down=getDown(location);
        if (down!=null)
            adjacent.add(down);
        int[] right=getRight(location);
        if (right!=null)
            adjacent.add(right);
        int[] left=getLeft(location);
        if (left!=null)
            adjacent.add(left);
        return adjacent;
    }

    /**
     * Uses a BFS search to get every tile in a kingdom.
     * @param location of the starting tile
     * @return an arrayList of int[] that contains every location associated with the tile placed in that kingdom
     */
    public ArrayList<int[]> BFSSearchKingdom(int[] location){
        ArrayList<int[]> ret=new ArrayList<>();
        ret.add(location);
        visited.add(location[0] + " " + location[1]);
        toVisit.addAll(getAdjacent(location));
        while (!toVisit.isEmpty()){
            int[] currVisit=toVisit.poll();
            String stringLocation=currVisit[0] + " " + currVisit[1];
            if (!visited.contains(stringLocation)){
                ret.add(currVisit);
                visited.add(stringLocation);
                toVisit.addAll(getAdjacent(currVisit));
            }
        }
        clearVisited();
        return ret;
    }

    /**
     * Given a location on the board, gives the adjacent tiles as long as it's a part of the kingdom
     * (not empty, river, catastrophe, or conflict)
     * @param location on the board
     * @return a 2d array of the adjacent tiles
     */
    /*public ArrayList<int[]> adjacency(int[] location){
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
    } */

    /**
     * This function searches the kingdom to examine if the piece placed (either tile or leader) has an
     * equivalent leader in the same kingdom
     * @param location of the tile placed
     * @param color of the tile placed
     * @return the faction that is connected to the piece if there is one, else null
     */
    /*public Optional<Character> searchKingdomLeaders(Map map, int[] location, char color){
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
    }  */
}
