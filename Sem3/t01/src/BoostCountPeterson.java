//import java.util.regex.PatternSyntaxException;

public class BoostCountPeterson extends Thread{
    private Count count;
    private Peterson pet;

    BoostCountPeterson(Count count, Peterson pet) {
        this.count = count;
        this.pet = pet;
    }

    @Override
    public void run() {
        int threadId = (int)Thread.currentThread().getId() % 14;
        for (int i = 0; i < 100000; i ++) {
            pet.enter_region(threadId);
            try {

                count.count++;
                //System.out.println("Thread-" + threadId + " " + count.count);
            } finally {
                pet.leave_region(threadId);
            }
        }
    }
}
