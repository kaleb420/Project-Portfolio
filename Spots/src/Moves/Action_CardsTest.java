package Moves;

import Information.Dog_Cards;
import Player_Classes.Player;
import Print.Print;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Action_CardsTest {

    Dog_Cards dogCards=new Dog_Cards();
    Action_Cards actionCards=new Action_Cards();
    Make_Move makeMove=new Make_Move(actionCards);
    ArrayList<Integer> dice=new ArrayList<>();
    Player player=null;

    void initializePlayer(){
        String name="Tom\n";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        this.player=new Player(dogCards, makeMove);
    }

    @Test
    void useTreat(){
        initializePlayer();
        ArrayList<Integer> dice=new ArrayList<>(List.of(1, 3));
        String input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.useTreat(player, dice, 0, 1);
        assertEquals(1, player.treats);
        assertTrue(dice.contains(3));
        input="y\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.useTreat(player, dice, 0, dice.size());
        assertEquals(0, player.treats);
    }

        @Test
    void rollMoreDice() {
    }

    @Test
    void howl() {
        initializePlayer();
        String input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        assertEquals(3, player.dogsUnflipped);
        assertEquals(1, dice.size());
        input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        input="n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.howl(player, dice);
        assertEquals(6, player.dogsUnflipped);
    }

    @Test
    void rollOver() {
        initializePlayer();
        player.yard.spots.add(1);
        String input="n\n" // don't use treat
                + "y\n" // roll another die
                + "n\n"; // don't use treat on the most recent die
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.rollOver(player, dice); // test with 2 dice in yard and player rolls another
        assertEquals(3, dice.size());
        assertEquals(0, player.yard.spots.size());
        input="n\n" // don't use treat
                + "n\n"; // don't roll another
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.rollOver(player, dice); // test with 3 dice in yard and player does not roll another
        assertEquals(3, dice.size());
        player.yard.spots.clear();
        input="n\n" // don't use treat
                + "n\n"; // don't roll another
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.rollOver(player, dice); // test with 0 dice in yard and player does not roll another
        assertEquals(0, player.yard.spots.size());
    }

    @Test
    void dig() { // try to decrease own yard past 0
        initializePlayer();
        player.unfilledSpaces.put("Doog",  new ArrayList<>(List.of(2, 3, 5 ,6)));
        player.getPaws();
        player.yard.spots.clear();
        player.yard.spots.add(1);
        String input="y\n" // change a yard?
                + player.players.getFirst().name // player name?
                + "2\n" // adjust by how much?
                + "d\n" // increase or decrease?
                + "n\n"; // use treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.dig(player, new ArrayList<>());
        assertEquals(0, player.yard.sum(player));
        assertTrue(player.yard.spots.isEmpty());
    }

    @Test
    void dig1() { // try increase own yard past 6
        initializePlayer();
        player.unfilledSpaces.put("Doog",  new ArrayList<>(List.of(2, 3, 5 ,6)));
        player.getPaws();
        player.yard.spots.clear();
        player.yard.spots.add(5);
        String input="y\n" // change a yard?
                + "Tom\n" // player number?
                + "2\n" // adjust by how much?
                + "i\n" // increase or decrease?
                + "n\n"; // use treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.dig(player, new ArrayList<>());
        assertEquals(6, player.yard.sum(player));
        assertFalse(player.yard.spots.isEmpty());
    }

    @Test
    void dig2() { // error test
        initializePlayer();
        player.unfilledSpaces.put("Doog",  new ArrayList<>(List.of(2, 3, 5 ,6)));
        player.getPaws();
        player.yard.spots.clear();
        player.yard.spots.add(5);
        String input="y\n" // change a yard?
                + "bob\n" // player name?
                + "y\n" // change a yard?
                + "Tom\n" // player name?
                + "2\n" // adjust by how much?
                + "i\n" // increase or decrease?
                + "n\n"; // use treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.dig(player, new ArrayList<>());
        assertEquals(6, player.yard.sum(player));
        assertFalse(player.yard.spots.isEmpty());
    }

    @Test
    void dig3() { // multiple dice in yard, increase past bust limit
        initializePlayer();
        player.unfilledSpaces.put("Doog",  new ArrayList<>(List.of(2, 3, 5 ,6)));
        player.getPaws();
        player.yard.spots.clear();
        player.yard.spots.add(5);
        player.yard.spots.add(2);
        String input="y\n" // change a yard?
                + "Tom\n" // player name?
                + "2\n" // adjust by how much?
                + "i\n" // increase or decrease?
                + "n\n"; // use treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.dig(player, new ArrayList<>());
        assertEquals(0, player.yard.sum(player));
        assertTrue(player.yard.spots.isEmpty());
    }

    @Test
    void dig4() { // multiple dice in yard, decrease but not to 0
        initializePlayer();
        player.unfilledSpaces.put("Doog",  new ArrayList<>(List.of(2, 3, 5 ,6)));
        player.getPaws();
        player.yard.spots.clear();
        player.yard.spots.add(5);
        player.yard.spots.add(2);
        String input="y\n" // change a yard?
                + "Tom\n" // player name?
                + "2\n" // adjust by how much?
                + "d\n" // increase or decrease?
                + "n\n"; // use treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.dig(player, new ArrayList<>());
        assertEquals(5, player.yard.sum(player));
        assertFalse(player.yard.spots.isEmpty());
    }

    /*@Test
    void retrieve() { // currently doesn't work because relies on a different scan function`
        initialization();
        player.dogCards.shuffledDeck.add("Doog");
        while (!player.dogCards.nextDog(player).equals("Doog")){
            player.dogCards.shuffledDeck.poll();
        }
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>());
        String input="6\n" // what die from the top card would you like to use?
                + "Bitsy\n" // what dog card would you like to add that die to?
                + "6\n" // what spot would you like to place on the dog?
                + "d\n" // done placing spots
                + "n\n"; // do not use any treats
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.retrieve(player, dice);
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    } */

    @Test
    void trot() { // tests if it works normally and not moving a die on a dog card works
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 6)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4)));
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>(List.of()));
        String input="y\n" // want to move a die?
                + "Doog\n" // what dog do you want to remove a die from?
                + "4\n" // what die do you want to remove?
                + "Bitsy\n" // what dog do you want to add that die to?
                + "4\n" // what number do you want to set that die to?
                + "n\n"; // do not reroll
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice); // tests if it works normally
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
        input="n\n" // want to move a die?
                + "n\n"; // want to use a treat?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice); // doesn't move die, just rolls
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    }

    @Test
    void trot1(){ // should fail the check if the removing dog card exists
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 6)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4)));
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>(List.of()));
        String input="y\n" // want to move a die?
                + "agpohie\n" // fails dog card check
                + "y\n" // want to move a die?
                + "Doog\n" // what dog do you want to remove a die from?
                + "4\n" // what die do you want to remove?
                + "Bitsy\n" // what dog do you want to add that die to?
                + "4\n" // what number do you want to set that die to?
                + "n\n"; // do not reroll
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice);
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    }

    @Test
    void trot2(){ // checks to see if it fails the dog not having the selected number
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 6)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4)));
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>(List.of()));
        String input= "y\n" // want to move a die?
                + "Bitsy\n" // passes dog card check
                + "4\n" // doesn't have spot, so fails
                + "y\n" // want to move a die?
                + "Doog\n" // what dog do you want to remove a die from?
                + "4\n" // what die do you want to remove?
                + "Bitsy\n" // what dog do you want to add that die to?
                + "4\n" // what number do you want to set that die to?
                + "n\n"; // do not reroll
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice);
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    }

    @Test
    void trot3(){ // tests if the receiving dog exists
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 6)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4)));
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>(List.of()));
        String input="y\n" // want to move a die?
                + "Doog\n" // what dog card do you want to remove a die from?
                + "4\n" // what die do you want to move?
                + "apfoeiw\n" // fails receiving dog card check
                + "y\n" // want to move a die?
                + "Doog\n" // what dog do you want to remove a die from?
                + "4\n" // what die do you want to remove?
                + "Bitsy\n" // what dog do you want to add a die to?
                + "4\n" // what number do you want to set that die to?
                + "n\n"; // do not reroll
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice);
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    }

    @Test
    void trot4(){ // tests if the receiving spot on the receiving dog exists
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 6)));
        player.filledSpaces.put("Doog", new ArrayList<>(List.of(4)));
        player.unfilledSpaces.put("Bitsy", new ArrayList<>(List.of(4, 6)));
        player.filledSpaces.put("Bitsy", new ArrayList<>(List.of()));
        String input="y\n" // want to move a die?
                + "Doog\n" // what dog card do you want to remove a die from?
                + "4\n" // what die do you want to move?
                + "Bitsy\n" // what dog do you want to add a die to?
                + "1\n" // die doesn't exist in unfilled spaces
                + "y\n" // want to move a die?
                + "Doog\n" // what dog do you want to remove a die from?
                + "4\n" // what die do you want to remove?
                + "Bitsy\n" // what dog do you want to add a die to?
                + "4\n" // what number do you want to set that die to?
                + "n\n"; // do not reroll
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.trot(player, dice);
        assertTrue(player.unfilledSpaces.get("Doog").contains(4));
        assertFalse(player.filledSpaces.get("Doog").contains(4));
        assertTrue(player.filledSpaces.get("Bitsy").contains(4));
        assertFalse(player.unfilledSpaces.get("Bitsy").contains(4));
    }

    @Test
    void walk() {
        initializePlayer();
        String input="n\n" // use treat
                + "n\n"; // roll 1 more die
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.walk(player, dice);
        assertEquals(2, dice.size());
    }

    @Test
    void track() {
    }

    @Test
    void chase() {
        initializePlayer();
        String input="n\n"
                + "y\n"
                + "n\n"
                + "y\n"
                + "n\n"
                + "y\n"
                + "n\n"
                + "y\n"
                + "n\n"
                + "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.chase(player, dice);
        assertEquals(15, dice.size());
    }

    @Test
    void run() {
        initializePlayer();
        String input="n\n" + "y\n" + "n\n" + "y\n" + "n\n" +"n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.run(player, dice); // tests rolling 5 dice
        assertEquals(5, dice.size());
        dice.clear();
        input="n\n" + "n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.run(player, dice); // tests rolling 3 dice
        assertEquals(3, dice.size());
    }

    @Test
    void bolt() {
    }

    @Test
    void dogpile() {
    }

    @Test
    void sprint() {
    }

    @Test
    void chew() {
        initializePlayer();
        actionCards.chew(player, dice);
        assertEquals(3, player.treats);
        actionCards.chew(player, dice);
        assertEquals(7, player.treats);
    }

    @Test
    void playDead() {
        initializePlayer();
        player.yard.spots.clear();
        String input="n\n" // use treats?
                + "p\n"; // place or bury?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.playDead(player, dice); // place the dice so nothing happens
        assertEquals(1, player.treats);
        input="n\n" // use treats?
                + "b\n"; // place or bury?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.playDead(player, new ArrayList<>(List.of(6))); // bury the die without busting so it should give 6 treats
        assertEquals(1, dice.size());
        assertEquals(7, player.treats);
        assertTrue(player.yard.spots.contains(6));
        input="n\n" // use treats?
                + "b\n"; // place or bury?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.playDead(player, new ArrayList<>(List.of(3))); // bury the die but you bust, so no treats
        assertEquals(7, player.treats);
    }

    @Test
    void beg() {
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(2, 3, 5, 6)));
        player.unfilledSpaces.put("Cabbage", new ArrayList<>(List.of(1, 4, 5)));
        player.getPaws();
        actionCards.beg(player, dice); // player should have at least 3 paws
        assertTrue(player.treats>=4);
    }

    @Test
    void stay() { // may be wrong but not sure
        initializePlayer();
        actionCards.stay(player, dice); // 0 face down cards
        assertEquals(0, player.treats);
        player.makeMove.usedCards.add("a");
        player.makeMove.usedCards.add("b");
        player.makeMove.usedCards.add("c");
        player.makeMove.usedCards.add("d");
        actionCards.stay(player, dice); // 4 face down cards
        assertEquals(3, player.treats);
    }

    @Test
    void gobble() {
        initializePlayer();
        player.unfilledSpaces.put("Doog", new ArrayList<>(List.of(6)));
        actionCards.gobble(player, dice);
        assertEquals(2, player.treats);
    }

    @Test
    void guard() {
        initializePlayer();
        String input="2\n" // guard against
                + "n\n"; // no treats
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.guard(player, dice);
        assertFalse(dice.contains(3) || dice.contains(4) || dice.contains(5) || dice.contains(6));
    }

    @Test
    void scavenge() { // may have some inconsistencies because it still rolls dice upon calling the function
        initializePlayer();
        player.yard.spots.clear();
        player.yard.spots.add(1);
        player.yard.spots.add(6); // 7 dice in yard
        ArrayList<Integer> dice=new ArrayList<>(List.of(6, 5, 1, 1));
        String input="n\n" // want to use a treat?
                + "y\n" // want to discard any dice?
                + "5\n" // what number die would you like to discard?
                + "y\n" // want to discard any dice?
                + "1\n" // what number die would you like to discard?
                + "y\n" // want to discard any dice?
                + "1\n" // shouldn't work because discard would be at 7, can only go to 6
                + "n\n"; // want to discard any dice?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.scavenge(player, dice);
        assertTrue(dice.contains(1));
        assertTrue(dice.contains(6));
        assertFalse(dice.contains(5));
    }

    @Test
    void hunt() { // cannot test due to the inherent randomness of this function, will have to manually test
    }

    @Test
    void fetch() {
        initializePlayer();
        ArrayList<Integer> dice=new ArrayList<>(List.of(1, 1, 2, 2, 4, 5, 5, 6));
        String input="n\n" // do not use treat
                + "3\n" // number doesn't exist so call function again
                + "n\n" // do not use treat
                + "4\n"; // what number do you want to pick?
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.fetch(player, dice);
        assertTrue(dice.contains(4));
        assertFalse(dice.contains(2));
    }

    @Test
    void searchRecursionHelper(){
        ArrayList<Integer> dice=new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 2, 2));
        HashSet<ArrayList<Integer>> possibleSums=new HashSet<>();
        actionCards.searchRecursionHelper(dice, 8, 0, possibleSums, new ArrayList<>());
        int total=0;
        for (ArrayList<Integer> sum : possibleSums){
            for (int i = 0; i < sum.size(); i++) {
                total+=sum.get(i);
            }
            assertEquals(8, total);
            total=0;
        }
    }

    @Test
    void search() {
        initializePlayer();
        ArrayList<Integer> dice=new ArrayList<>();
        String input="n\n" // do not use treat
                + "1\n"; // what set would you like to select
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        actionCards.scan=new Scanner(System.in);
        actionCards.search(player, dice);
        assertNotEquals(8, dice.size());
    }
}