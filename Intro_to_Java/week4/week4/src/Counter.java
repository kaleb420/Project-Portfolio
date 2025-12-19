public class Counter {
    private int count;

    /**
     * incremenets count by 1
     */
    public void increaseCount(){
        this.count+=1;
    }

    /**
     * resets count to 0
     */
    public void resetCount(){
        this.count=0;
    }

    /**
     * prints the current value of count
     * @return
     */
    public int getCount(){
        return count;
    }
}
