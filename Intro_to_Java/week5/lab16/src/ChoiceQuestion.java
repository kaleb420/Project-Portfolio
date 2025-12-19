import java.util.LinkedHashMap;
import java.util.Map;

public class ChoiceQuestion extends Question{

    private LinkedHashMap<String, Boolean> potentialAnswers = new LinkedHashMap<>();

    /**
     * constructor for choice question class, initiates prompt and answer
     * @param prompt question
     */
    ChoiceQuestion(String prompt) {
        super(prompt);
    }

    /**
     * determines if the chosen answer is correct
     * @param choice choice added
     * @param isCorrect if it's correct
     */
    void addChoice(String choice, boolean isCorrect){
        potentialAnswers.put(choice, isCorrect);
        if (isCorrect)
            super.setAnswer(choice);
    }
    LinkedHashMap<String, Boolean> getChoices(){
        return potentialAnswers;
    }

    /**
     * converts the possible choices and the correct answer to a string
     * @return formatted string
     */
    @Override
    public String toString(){
        String x="";
        x+=super.getPrompt();
        System.out.println(super.getPrompt());
        for (String s: potentialAnswers.keySet()){
                x+="\n" + s;
        }
        return x;
    }
}
