public class BoostCountOne extends Thread{
    private Count res;
    BoostCountOne(Count res) {
        this.res = res;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100000; i++) {
            res.count++;
        }
    }
}
