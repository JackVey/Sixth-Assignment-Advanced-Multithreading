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
        boolean add = true;
        for (double i = start; i < end; i++) {
            term = one.divide(new BigDecimal(2 * i + 1), floatingPoint * 10, BigDecimal.ROUND_UP);
            if (add) {
                partialPi = partialPi.add(term);
            } else {
                partialPi = partialPi.subtract(term);
            }
            add = !add;
        }
        partialPi = partialPi.multiply(BigDecimal.valueOf(4));
    }

    public BigDecimal getPartialPi() {
        return partialPi;
    }
}
