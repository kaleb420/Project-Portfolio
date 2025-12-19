public class CounterDemo {
    public static void main(String[] args) {
        Counter concertCounter= new Counter();
        Counter movieCounter= new Counter();
        concertCounter.increaseCount();
        concertCounter.increaseCount();
        concertCounter.increaseCount();
        System.out.println("concert counter = " + concertCounter.getCount());
        concertCounter.resetCount();
        System.out.println("concert counter = " + concertCounter.getCount());
    }
}
