package sbu.cs.CalculatePi;

import java.math.BigDecimal;

public class Operation  implements Runnable{
    private double start;
    private double end;
    private int floatingPoint;
    private BigDecimal partialPi;
    Operation(double start, double end, int floatingPoint){
        this.start = start;
        this.end = end;
        this.floatingPoint = floatingPoint;
        this.partialPi = BigDecimal.ZERO;
    }
    @Override
    public void run() {
        BigDecimal term;
        BigDecimal one = BigDecimal.ONE;
        boolean add = true;

        for (double i = start; i < end; i++) {
            term = one.divide(new BigDecimal(2 * i + 1), floatingPoint + 5, BigDecimal.ROUND_HALF_UP);
            if (add) {
                partialPi = partialPi.add(term);
            } else {
                partialPi = partialPi.subtract(term);
            }
            add = !add;
        }
    }
}
