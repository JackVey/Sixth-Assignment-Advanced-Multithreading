package sbu.cs.CalculatePi;

import java.math.BigDecimal;

public class PiOperation  implements Runnable{
    private double start;
    private double end;
    private int floatingPoint;
    private BigDecimal partialPi;
    PiOperation(double start, double end, int floatingPoint){
        this.start = start;
        this.end = end;
        this.floatingPoint = floatingPoint;
        this.partialPi = BigDecimal.ZERO;
    }
    @Override
    public void run() {
        BigDecimal term;
        BigDecimal one = BigDecimal.ONE;

        for (double i = start; i < end; i++) {
            term = one.divide(new BigDecimal(2 * i + 1), floatingPoint + 5, BigDecimal.ROUND_HALF_UP);
            if (i % 2 != 0) {
                partialPi = partialPi.add(term);
            } else {
                partialPi = partialPi.subtract(term);
            }
        }
        partialPi = partialPi.multiply(BigDecimal.valueOf(4));
    }

    public BigDecimal getPartialPi() {
        return partialPi;
    }
}
