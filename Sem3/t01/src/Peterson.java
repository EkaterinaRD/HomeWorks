public class Peterson {
    static int FALSE = 0;
    static int N = 2;

    int turn[] = new int[N];
    int stage[] = new int[N + 1];

    void enter_region(int process) {
        for (int i = 1; i < N; i++) {
            stage[process] = i;
            turn[i] = process;
            for (int j = 1; j <= N; j++) {
                if (j == process){
                    continue;
                }
                while (stage[j] >= i && turn[i] == process) {}
            }
        }
    }

    void leave_region(int process) {
        stage[process] = FALSE;
    }
}
