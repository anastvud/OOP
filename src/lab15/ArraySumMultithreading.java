package lab15;
import java.util.Random;

class PartialSum implements Runnable  {
    private int[] array;
    private int start;
    private int end;
    private long partialSum;

    public PartialSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.partialSum = 0;
    }

    public long getPartialSum() {
        return partialSum;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            partialSum += array[i];
        }
    }

    public static void Sum(int N, int[] array) {
        long startTime, endTime;
        long totalSumRunnable = 0;
        startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            totalSumRunnable += array[i];
        }
        endTime = System.currentTimeMillis();
        System.out.println("Using normal algorithm: ");
        System.out.println("Total sum: " + totalSumRunnable);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds\n");
    }

    public static void SumRunnableInterface(int N, int K, int[] array) throws InterruptedException {
        long startTime, endTime;

        PartialSum[] partialSums = new PartialSum[K];
        Thread[] threads = new Thread[K];

        int m = N / K;
        startTime = System.currentTimeMillis();

        for (int i = 0; i < K; i++) {
            partialSums[i] = new PartialSum(array, i * m, (i + 1) * m);
            threads[i] = new Thread(partialSums[i]);
            threads[i].start();
        }

        for (int i = 0; i < K; i++) {
            threads[i].join();
        }

        long totalSumRunnable = 0;
        for (int i = 0; i < K; i++) {
            totalSumRunnable += partialSums[i].getPartialSum();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Using Runnable interface: ");
        System.out.println("Total sum: " + totalSumRunnable);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds\n");
    }

    public static void SumThreadClass(int N, int K, int[] array) throws InterruptedException {
        long startTime, endTime;
        int m = N / K;

        PartialSum[] partialSumsThread = new PartialSum[K];
        CustomThread[] customThreads = new CustomThread[K];

        startTime = System.currentTimeMillis();

        for (int i = 0; i < K; i++) {
            partialSumsThread[i] = new PartialSum(array, i * m, (i + 1) * m);
            customThreads[i] = new CustomThread(partialSumsThread[i]);
            customThreads[i].start();
        }

        for (int i = 0; i < K; i++) {
            customThreads[i].join();
        }

        long totalSumThread = 0;
        for (int i = 0; i < K; i++) {
            totalSumThread += partialSumsThread[i].getPartialSum();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Using Thread class: ");
        System.out.println("Total sum: " + totalSumThread);
        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds\n");
    }
    private static void fillArrayWithRandomNumbers(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        int N = 100000;
        int K = 4;

        int[] array = new int[N];
        fillArrayWithRandomNumbers(array);
        SumRunnableInterface(N, K, array);
        SumThreadClass(N, K, array);
        Sum(N, array);
    }
}

class CustomThread extends Thread {
    private PartialSum partialSum;

    public CustomThread(PartialSum partialSum) {
        this.partialSum = partialSum;
    }

    @Override
    public void run() {
        partialSum.run();
    }
}
