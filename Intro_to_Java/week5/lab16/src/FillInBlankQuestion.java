import java.util.LinkedHashMap;
import java.util.Map;

public class FillInBlankQuestion extends ChoiceQuestion {

    /**
     * constructor to initialize question
     * @param prompt question
     */
    FillInBlankQuestion(String prompt) {
        super(prompt);
    }

    public void addChoice(String s){
        super.addChoice(s, true);
    }
    @Override
    boolean isCorrect(String ans){
        LinkedHashMap<String, Boolean> m= getChoices();
        return m.containsKey(ans);
    }
}
