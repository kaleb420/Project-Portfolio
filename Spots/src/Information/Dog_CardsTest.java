package Information;

import static org.junit.jupiter.api.Assertions.*;

class Dog_CardsTest {

    Dog_Cards dogCards=new Dog_Cards();

    @org.junit.jupiter.api.Test
    void draw() {
        String dogCard=dogCards.draw();
        assertFalse(dogCards.shuffledDeck.contains(dogCard));
    }
}