package lab13;

import java.util.Arrays;

public class BubbleSorter<T extends Comparable<T>> {

    public void bubbleSort(T[] array) {
        int n = array.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (compare(array[i - 1], array[i]) > 0) {
                    T temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    private int compare(T a, T b) {
        if (a instanceof String) {
            return compareStrings((String) a, (String) b);
        } else if (a instanceof Integer) {
            return compareIntegers((Integer) a, (Integer) b);
        } else if (a instanceof Double) {
            return compareDoubles((Double) a, (Double) b);
        } else {
            return a.compareTo(b);
        }
    }

    private int compareStrings(String a, String b) {
        return Integer.compare(a.length(), b.length());
    }

    private int compareIntegers(Integer a, Integer b) {
        return Integer.compare(countNonZeroDigits(a), countNonZeroDigits(b));
    }

    private int compareDoubles(Double a, Double b) {
        return Double.compare(getSignificand(a), getSignificand(b));
    }

    private int countNonZeroDigits(int num) {
        String strNum = Integer.toString(num);
        int count = 0;
        for (char digit : strNum.toCharArray()) {
            if (digit == '0') {
                count++;
            }
        }
        return count;
    }

    private double getSignificand(double num) {
        String strNum = Double.toString(num);
        int dotIndex = strNum.indexOf('.');
        if (dotIndex != -1) {
            return strNum.substring(dotIndex).length();
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {700, 223, 120, 8, 8000};
        BubbleSorter<Integer> intBubbleSorter = new BubbleSorter<>();
        intBubbleSorter.bubbleSort(intArray);
        System.out.println("Sorted Integer Array: " + Arrays.toString(intArray));

        Double[] doubleArray = {3.245, 1.0, 4.56, 2.1, 0.5689};
        BubbleSorter<Double> doubleBubbleSorter = new BubbleSorter<>();
        doubleBubbleSorter.bubbleSort(doubleArray);
        System.out.println("Sorted Double Array: " + Arrays.toString(doubleArray));

        String[] stringArray = {"banana", "apple", "orange", "grape", "kiwi", "pineapple"};
        BubbleSorter<String> stringBubbleSorter = new BubbleSorter<>();
        stringBubbleSorter.bubbleSort(stringArray);
        System.out.println("Sorted String Array: " + Arrays.toString(stringArray));
    }
}
