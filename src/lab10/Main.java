package lab10;

import java.util.Scanner;

public class Main {
    public enum MonthOfYear {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);


        private final int numDays;

        MonthOfYear(int num) {
            this.numDays = num;
        }

        public int getNumDays() {
            return numDays;
        }

    }

    public static boolean isValidMonth(String month) {
        for (MonthOfYear m : MonthOfYear.values()) {
            if (m.name().equalsIgnoreCase(month)) {
                return true;
            }
        }
        System.out.println("Invalid month. Try again");
        return false;
    }

    private static int numberOfDay(String month, int day) {
        int dayOfYear = 0;
        for (MonthOfYear m : MonthOfYear.values()) {
            if (m.name().equalsIgnoreCase(month)) {
                break;
            }
            dayOfYear += m.getNumDays();
        }
        return dayOfYear + day;
    }

    private static String[] dayAndMonthFromNumber(int dayOfYear) {
        for (MonthOfYear m : MonthOfYear.values()) {
            if (dayOfYear <= m.getNumDays()) {
                return new String[]{m.name(), String.valueOf(dayOfYear)};
            }
            dayOfYear -= m.getNumDays();
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month;
        do {
            System.out.print("Enter month: ");
            month = scanner.nextLine();
        } while (!isValidMonth(month));

        int day;
        do {
            System.out.print("Enter day: ");
            day = scanner.nextInt();
        } while (day < 1 || day > MonthOfYear.valueOf(month.toUpperCase()).getNumDays());


        int dayOfYear = numberOfDay(month, day);
        System.out.println("The day of the year is: " + dayOfYear);

        System.out.print("Enter the day of the year: ");
        int dayOfYearInput = scanner.nextInt();
        String[] monthAndDay = dayAndMonthFromNumber(dayOfYearInput);
        System.out.println("Month: " + monthAndDay[0] + ", Day: " + monthAndDay[1]);

        scanner.close();
    }
}
