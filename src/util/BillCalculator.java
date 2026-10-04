package util;

public class BillCalculator {

    private static final double RATE_PER_UNIT = 12.0;

    public static double calculateBill(int unitsConsumed) {
        return unitsConsumed * RATE_PER_UNIT;
    }
}