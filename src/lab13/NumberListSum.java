package lab13;

import java.util.List;

public class NumberListSum {

    public static double sumList(List<? extends Number> numberList) {
        double sum = 0.0;
        for (Number num : numberList) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4);
        System.out.println("Sum of Integer List: " + sumList(intList));

        List<Double> doubleList = List.of(1.5, 2.5, 3.5, 4.5);
        System.out.println("Sum of Double List: " + sumList(doubleList));

        List<Long> longList = List.of(100L, 200L, 300L, 400L);
        System.out.println("Sum of Long List: " + sumList(longList));
    }
}
