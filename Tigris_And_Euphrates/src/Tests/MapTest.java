package Tests;

import Setup.Map;
import org.junit.jupiter.api.Test;

class MapTest {

    Map map=new Map();

    @Test
    void printMap() {
        map.printMap();
    }

    @Test
    void printLeadersAndMonuments() {
        map.board[5][5]="ST";
        map.board[5][6]="ST";
        map.board[6][5]="ST";
        map.board[6][6]="ST";
        map.board[0][0]="LT";
        map.printMap();
    }
}