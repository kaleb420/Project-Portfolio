import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @org.junit.jupiter.api.Test
    void QuestionTests() {
        ChoiceQuestion q1 =
                new ChoiceQuestion("What is the capital of North Carolina?");
        q1.addChoice("Charlotte", false);
        q1.addChoice("Raleigh", true);
        q1.addChoice("Winston Salem", false);
        q1.addChoice("Columbia", false);
        assertEquals("What is the capital of North Carolina?\n" +
                        "Charlotte\n" +
                        "Raleigh\n" +
                        "Winston Salem\n" +
                        "Columbia", q1.toString());
        assertEquals(true, q1.isCorrect("Raleigh"));
        assertEquals(false, q1.isCorrect("Charlotte"));
        ChoiceQuestion q2 =
                new TrueFalseQuestion("The square root of 2 is rational.", false);
        assertEquals("The square root of 2 is rational.\n true\n false",q2.toString());
        assertEquals(true, q2.isCorrect("true"));
        assertEquals(false,q2.isCorrect("false"));
        FillInBlankQuestion q3 = new FillInBlankQuestion("2 + 2 = _________");
        q3.addChoice("4");
        q3.addChoice("four");
        q3.addChoice("FOUR");
        q3.addChoice("4.0");
        assertEquals("2 + 2 = _________\n" +
                "4\n" +
                "four\n" +
                "FOUR\n" +
                "4.0", q3.toString());
        q3.isCorrect("4");
        q3.isCorrect("four");
        q3.isCorrect("FOUR");
        q3.isCorrect("4.0");
        q3.isCorrect("sqrt(16)");

    }
}