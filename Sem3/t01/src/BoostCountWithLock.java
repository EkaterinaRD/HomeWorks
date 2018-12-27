import java.util.concurrent.locks.ReentrantLock;

public class BoostCountWithLock extends Thread {
    private Count res;
    private ReentrantLock locker;

    BoostCountWithLock(Count res, ReentrantLock lock) {
        this.res = res;
        locker = lock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            locker.lock();
            try {
                res.count++;
                //System.out.println(threadName + " " + res.count);
            } finally {
                locker.unlock();
            }
        }
    }
}
