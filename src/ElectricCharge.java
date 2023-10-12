import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class ElectricChargeSystem {
    private class Charge {
        double x, y, z, value;

        public Charge(double value, double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.value = value;
        }
    }

    private int numCharges;
    private static double K = 8.98e+9;
    private ArrayList<Charge> charges;

    public ElectricChargeSystem(int numCharges) {
        this.numCharges = numCharges;
        charges = new ArrayList<>(numCharges);
    }

    public void addCharge(double value, double x, double y, double z) {
        charges.add(new Charge(value, x, y, z));
    }

    public void printChargesAndTotalCharge() {
        double totalCharge = 0.0;
        System.out.println("Charges in the system:");
        for (Charge charge : charges) {
            System.out.println("Charge at (" + charge.x + ", " + charge.y + ", " + charge.z + "): " + charge.value);
            totalCharge += charge.value;
        }
        System.out.println("Total charge in the system: " + totalCharge);
    }


    public Vector3D calculateElectricField(Charge q, int excludeChargeIndex) {
        Vector3D electricField = new Vector3D(0, 0, 0);
        for (int i = 0; i < numCharges; i++) {
            if (i != excludeChargeIndex) {
                Charge charge = charges.get(i);
                double x = q.x - charge.x;
                double y = q.y - charge.y;
                double z = q.z - charge.z;
                double coef = K * Math.abs(q.value) / (x*x + y*y + z*z);
                electricField = electricField.add(new Vector3D(x * coef, y * coef, z * coef));
            }
        }
//        System.out.println(electricField);
        return electricField;
    }

    public Vector3D calculateForceOnCharge(int chargeIndex) {
        Vector3D force = new Vector3D(0, 0, 0);
        Charge excludedCharge = charges.get(chargeIndex);
        for (int i = 0; i < numCharges; i++) {
            if (i != chargeIndex) {
                Vector3D field = calculateElectricField(excludedCharge, i);
                force = force.add(field.scale(charges.get(i).value));
            }
        }
        return force;
    }

    public void printForcesOnCharges() {
        System.out.println("Forces acting on each charge:");
        for (int i = 0; i < numCharges; i++) {
            Vector3D force = calculateForceOnCharge(i);
            System.out.println("Force on charge " + i + ": " + force.toString());
        }
    }
    public void createChargeDistributionImage(String filePath) {
        int width = 800;  // Image width
        int height = 600; // Image height

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Clear the background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Find the maximum absolute charge value to normalize charge sizes
        double maxChargeValue = 0;
        for (Charge charge : charges) {
            double absValue = Math.abs(charge.value);
            if (absValue > maxChargeValue) {
                maxChargeValue = absValue;
            }
        }

        // Draw charges as circles on the image
        for (Charge charge : charges) {
            int x = (int) (charge.x * 100 + width / 2); // Scale and translate to image coordinates
            int y = (int) (-charge.y * 100 + height / 2); // Scale and translate to image coordinates

            double normalizedValue = Math.abs(charge.value) / maxChargeValue;
            Color chargeColor = (charge.value < 0) ? Color.BLUE : Color.RED;

            int circleSize = (int) (normalizedValue * 20); // Adjust the scale factor as needed

            g2d.setColor(chargeColor);
            g2d.fillOval(x - circleSize / 2, y - circleSize / 2, circleSize, circleSize);
        }

        g2d.dispose();

        try {
            File outputFile = new File(filePath);
            ImageIO.write(image, "PNG", outputFile);
        } catch (IOException e) {
            System.err.println("Error saving the image: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        ElectricChargeSystem chargeSystem = new ElectricChargeSystem(4);

        // Set up the system with charges
        chargeSystem.addCharge(2e-6, 0, -1, 0);
        chargeSystem.addCharge(-2e-6,0, 2, 0);
        chargeSystem.addCharge(1e-6,1, -1, 0);
        chargeSystem.addCharge(-1e-6, 1, 1, 0);

        // Print the values of charges and the total charge in the system
        chargeSystem.printChargesAndTotalCharge();

        // Print the forces acting on each charge
        chargeSystem.printForcesOnCharges();

        // Create an image showing the charges
        chargeSystem.createChargeDistributionImage("charge_distribution.png");
    }
}

class Vector3D {
    double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3D scale(double scalar) {
        return new Vector3D(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

