package lab15;

import java.util.Scanner;

public class SharedResource {
    private int[] array;
    private boolean isFilled;

    public SharedResource(int size) {
        array = new int[size];
        isFilled = false;
    }

    public synchronized void fillArray() {
        if (isFilled) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter " + array.length + " numbers:");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        isFilled = true;
        notify();
    }

    public synchronized void calculateSumAndReset() {
        if (!isFilled) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sum = 0;
        for (int num : array) {
            sum += num;
        }

        System.out.println("Sum: " + sum);

        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
        isFilled = false;
        notify();
    }
}

class GetNumbers extends Thread {
    private SharedResource sharedResource;

    public GetNumbers(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.fillArray();
        }
    }
}

class GetSum extends Thread {
    private SharedResource sharedResource;

    public GetSum(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.calculateSumAndReset();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(5);

        GetNumbers getNumbersThread = new GetNumbers(sharedResource);
        GetSum getSumThread = new GetSum(sharedResource);

        getNumbersThread.start();
        getSumThread.start();
    }
}
