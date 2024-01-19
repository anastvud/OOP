package lab14;

public class Hamming {
    public int compare(String str1, String str2) throws Exception {
        if (str1.isEmpty() || str2.isEmpty()) {
            throw new Exception("String cannot be empty");
        }

        int hammingDistance = 0;
        int minLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                hammingDistance++;
            }
        }
        hammingDistance += Math.abs(str1.length() - str2.length());
        return hammingDistance;
    }
}

class HammingCleared extends Hamming {
    private String clear(String str) {
        return str.replaceAll("[\\s_]", "");
    }

    @Override
    public int compare(String str1, String str2) throws Exception {
        String clearedStr1 = clear(str1);
        String clearedStr2 = clear(str2);

        return super.compare(clearedStr1, clearedStr2);
    }

    public static void main(String[] args) {
        HammingCleared hammingCleared = new HammingCleared();

        try {
            System.out.println("Hamming distance: " + hammingCleared.compare("speed   ", "speeding"));
            System.out.println("Hamming distance: " + hammingCleared.compare("___rock_", "sock "));
            System.out.println("Hamming distance: " + hammingCleared.compare(" medi_tation_", "medication"));
            System.out.println("Hamming distance: " + hammingCleared.compare("quiet", "loud"));
            System.out.println("Hamming distance: " + hammingCleared.compare("", "loud"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
