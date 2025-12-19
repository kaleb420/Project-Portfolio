import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Problem9Test {

    @Test
    void isValidMove() {
        char[][] board={
                { '-', 'B', '-'},
                { 'B', '-', 'B'},
                { '-', 'B', '-'}
        };
        assertEquals(true, Problem9.isValidMove(board, 0, 0));
        assertEquals(false, Problem9.isValidMove(board, 4, 0));
        assertEquals(true, Problem9.isValidMove(board, 1,1));
    }

    @Test
    void getValidNeighbors() {
        char[][] board={
                { '-', 'B', '-'},
                { 'B', '-', 'B'},
                { '-', 'B', '-'}
        };
        List<int[]> ls= new ArrayList<>();
        ls.add(new int[]{0, 0});
        ls.add(new int[]{0, 1});
        ls.add(new int[]{0, 2});
        ls.add(new int[]{1, 0});
        ls.add(new int[]{1, 2});
        ls.add(new int[]{2, 0});
        ls.add(new int[]{2, 1});
        ls.add(new int[]{2, 2});
        List<int[]> ls2= new ArrayList<>();
        ls2.add(new int[]{1,0});
        ls2.add(new int[]{1,1});
        ls2.add(new int[]{0,1});
        char[][] board2={
                {'-'},
        };
        assertEquals(ls, Problem9.getValidNeighbors(board, 1,1));
        assertEquals(ls2, Problem9.getValidNeighbors(board, 0,0));
        assertEquals(List.of(), Problem9.getValidNeighbors(board2, 0,0));
    }

    @Test
    void getNonMineNeighbors() {
        char[][] board={
                { '-', 'B', '-'},
                { 'B', '-', 'B'},
                { '-', 'B', '-'}
        };
        List<int[]> ls = new ArrayList<>();
        ls.add(new int[] {0,0});
        ls.add(new int[] {0,2});
        ls.add(new int[] {2,0});
        ls.add(new int[] {2,2});
        char[][] board2={
                { '-', '-', '-'},
                { '-', '-', '-'},
                { '-', '-', '-'}
        };
        List<int[]> ls2 = new ArrayList<>();
        ls2.add(new int[]{0, 0});
        ls2.add(new int[]{0, 1});
        ls2.add(new int[]{0, 2});
        ls2.add(new int[]{1, 0});
        ls2.add(new int[]{1, 2});
        ls2.add(new int[]{2, 0});
        ls2.add(new int[]{2, 1});
        ls2.add(new int[]{2, 2});
        List<int[]> ls3 = new ArrayList<>();
        ls3.add(new int[]{1,0});
        ls3.add(new int[]{1,1});
        ls3.add(new int[]{0,1});
        char[][] board3={
                {'-'},
        };
        assertEquals(ls, Problem9.getNonMineNeighbors(board, 1, 1));
        assertEquals(ls2, Problem9.getNonMineNeighbors(board2, 1, 1));
        assertEquals(ls3, Problem9.getNonMineNeighbors(board2, 0, 0));
        assertEquals(List.of(), Problem9.getNonMineNeighbors(board3, 0, 0));
    }

    @Test
    void getMineNeighbors() {
        char[][] board={
                { '-', 'B', '-'},
                { 'B', '-', 'B'},
                { '-', 'B', '-'}
        };
        List<int[]> ls = new ArrayList<>();
        ls.add(new int[] {0,1});
        ls.add(new int[] {1,0});
        ls.add(new int[] {1,2});
        ls.add(new int[] {2,1});
        char[][] board2={
                { 'B', 'B', 'B'},
                { 'B', '-', 'B'},
                { 'B', 'B', 'B'}
        };
        List<int[]> ls2 = new ArrayList<>();
        ls2.add(new int[]{0, 0});
        ls2.add(new int[]{0, 1});
        ls2.add(new int[]{0, 2});
        ls2.add(new int[]{1, 0});
        ls2.add(new int[]{1, 2});
        ls2.add(new int[]{2, 0});
        ls2.add(new int[]{2, 1});
        ls2.add(new int[]{2, 2});
        List<int[]> ls3 = new ArrayList<>();
        ls3.add(new int[]{1,0});
        ls3.add(new int[]{0,1});
        char[][] board3={
                {'-'},
        };
        assertEquals(ls, Problem9.getMineNeighbors(board, 1, 1));
        assertEquals(ls2, Problem9.getMineNeighbors(board2, 1, 1));
        assertEquals(ls3, Problem9.getMineNeighbors(board2, 0, 0));
        assertEquals(List.of(), Problem9.getMineNeighbors(board3, 0, 0));
    }

    @Test
    void countAdjacentMines() {
        char[][] board={
                { '-', 'B', '-'},
                { 'B', '-', 'B'},
                { '-', 'B', '-'}
        };
        char[][] board2={
                { '-', '-', '-'},
                { '-', '-', '-'},
                { '-', '-', '-'}
        };
        char[][] board3={
                {'-'},
        };
        assertEquals(4, Problem9.countAdjacentMines(board, 1, 1));
        assertEquals(0, Problem9.countAdjacentMines(board2, 1, 1));
        assertEquals(2, Problem9.countAdjacentMines(board, 0, 0));
        assertEquals(List.of(), Problem9.countAdjacentMines(board3, 0, 0));

    }

    @Test
    void extPath() {
    }

    @Test
    void makeBoard() {
    }

    @Test
    void play() {
        char[][] board={
                { '-', 'B', '-'},
                { '-', '-', '-'},
                { '-', '-', '-'}
        };
        char[][] boardA={
                { '-', 'B', '-'},
                { '1', '1', '1'},
                { '0', '0', '0'}
        };
        char[][] board2 ={
                {'-'},
        };
        char[][] board2A ={
                {'0'},
        };
        assertEquals(board, Problem9.play(board, 5,3));
        assertEquals(boardA, Problem9.play(board, 1,2));
        assertEquals(board, Problem9.play(board, 0,1));
        assertEquals(board2A, Problem9.play(board2, 0,0));
    }
}