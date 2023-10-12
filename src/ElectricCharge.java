import java.util.ArrayList;

class ElectricChargesSystem {
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
    private ArrayList<Charge> charges;

    public ElectricChargesSystem(int numCharges) {
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

    public Vector3D calculateElectricField() {
        Vector3D electricField = new Vector3D(0, 0, 0);




        return electricField;
    }

    public Vector3D calculateElectricField(double px, double py, double pz, int excludeChargeIndex) {
        Vector3D electricField = new Vector3D(0, 0, 0);
        for (int i = 0; i < numCharges; i++) {
            if (i != excludeChargeIndex) {
                Charge charge = charges.get(i);
                double r = Math.sqrt(Math.pow(px - charge.x, 2) + Math.pow(py - charge.y, 2) + Math.pow(pz - charge.z, 2));
                double fieldStrength = charge.value / (4 * Math.PI * r * r);
                electricField = electricField.add(new Vector3D(px - charge.x, py - charge.y, pz - charge.z).normalize().scale(fieldStrength));
            }
        }
        return electricField;
    }
//
//    public Vector3D calculateForceOnCharge(int chargeIndex) {
//        Vector3D force = new Vector3D(0, 0, 0);
//        Charge excludedCharge = charges.get(chargeIndex);
//        for (int i = 0; i < numCharges; i++) {
//            if (i != chargeIndex) {
//                Vector3D field = calculateElectricField(excludedCharge.x, excludedCharge.y, excludedCharge.z, i);
//                force = force.add(field.scale(charges.get(i).value));
//            }
//        }
//        return force;
//    }
//
//    public void printForcesOnCharges() {
//        System.out.println("Forces acting on each charge:");
//        for (int i = 0; i < numCharges; i++) {
//            Vector3D force = calculateForceOnCharge(i);
//            System.out.println("Force on charge " + i + ": " + force.toString());
//        }
//    }
    public static void main(String[] args) {
        double time = 2.2, x0 = 257, v0 = 63;

        System.out.printf("Result: %.2f", time);
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

    public Vector3D normalize() {
        double length = Math.sqrt(x * x + y * y + z * z);
        return new Vector3D(x / length, y / length, z / length);
    }

//    @Override
//    public String toString() {
//        return "(" + x + ", " + y + ", " + z + ")";
//    }



}

