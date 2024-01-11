package lab13;

public class Adder<T extends Number> {
    private T num1;
    private T num2;

    public Adder(T num1, T num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public T add() {
        if (num1 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
        } else if (num1 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
        } else if (num1 instanceof Long) {
            return (T) Long.valueOf(num1.longValue() + num2.longValue());
        } else {
            throw new IllegalArgumentException("Unsupported data type");
        }
    }

    public static void main(String[] args) {
        Adder<Integer> integerAdder = new Adder<>(5, 10);
        System.out.println("Integer Sum: " + integerAdder.add());

        Adder<Double> doubleAdder = new Adder<>(3.5, 7.2);
        System.out.println("Double Sum: " + doubleAdder.add());

        Adder<Long> longAdder = new Adder<>(10L, 200L);
        System.out.println("Long Sum: " + longAdder.add());
    }
}
