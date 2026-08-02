package Helpers;

import Setup.Map;

import java.util.*;

/**
 * Search algorithms used to determine if a tile is adjacent to something, or to get the pieces in the kingdom.
 * BFS is used, and calls adjacency until there are no more adjacent tiles, then returns all the tiles
 * added to the ArrayList. After being ran, clearVisited is called to remove all old information.
 */
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
}