import java.util.LinkedHashMap;
import java.util.Map;

public class TrueFalseQuestion extends ChoiceQuestion{

    /**
     * constructor to initiate instance variables
     * @param prompt question
     */
    TrueFalseQuestion(String prompt) {
        super(prompt);
    }

    /**
     * constructor to initiate instance variables
     * @param prompt question
     * @param x correct answer
     */
    TrueFalseQuestion(String prompt, boolean x) {
        super(prompt);
        if (x) {
            this.addChoice(true + "", true);
            this.addChoice(false + "", false);
        }
        else {
            this.addChoice(true + "", false);
            this.addChoice(false + "", true);
        }
    }

    @Override
    public String toString(){
        return super.toString();
    }
}