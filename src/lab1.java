import java.lang.Math;

public class lab1 {
    final static double A = -9.81;

    public static double position(double time, double x0, double v0) {
        return 0.5 * A * Math.pow(time, 2) + v0 * time + x0;
    }

    public static double kmh_to_ms(double km) {
        return km * (5.0 / 18);
    }

    public static void main(String[] args) {
        double time = 2.2, x0 = 257, v0 = 63;
        double result = position(time, x0, kmh_to_ms(v0));

        System.out.printf("Result: %.2f", result);
    }
}