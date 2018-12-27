import java.util.concurrent.locks.ReentrantLock;

public class MainClass {

    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        ReentrantLock locker = new ReentrantLock();
        Peterson peter = new Peterson();

        //without Lock
        /*for (int i = 0; i < 2; i++) {
            BoostCountOne ct = new BoostCountOne(count);
            ct.start();
        }*/

        //with Lock
        /*for (int i = 0; i < 2; i++) {
            BoostCountWithLock ct = new BoostCountWithLock(count, locker);
            ct.start();
        }*/

        //Paterson
        for (int i = 0; i < 2; i++) {
            BoostCountPeterson ct = new BoostCountPeterson(count, peter);
            ct.start();
        }

        Thread.sleep(1000);
        System.out.println("Counter: " + count.count);
    }
}
