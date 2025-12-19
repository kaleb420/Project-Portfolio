public class Question {
    private final String PROMPT;
    private String answer;

    /**
     * constructor to set the instance variable to the input variables
     * @param prompt question
     * @param answer correct answer
     */
    Question(String prompt, String answer){
        this.PROMPT=prompt;
        this.answer=answer;
    }

    /**
     * constructs question class
     * @param prompt question
     */
    Question(String prompt){
        this(prompt,null);
    }

    /**
     * Determines whether a given answer is the correct answer.
     * @param ans - answer TO the question itself.
     * @return true if ans is correct, false otherwise.
     */
    boolean isCorrect(String ans) {
        return this.answer.equals(ans);
    }

    /**
     * override the string function to stringify prompt
     * @return stringified prompt
     */
    @Override
    public String toString() {
        return this.PROMPT;
    }

    String getPrompt(){
        return PROMPT;
    }
    String getAnswer(){
        return answer;
    }
    void setAnswer(String answer){
        this.answer=answer;
    }
}
