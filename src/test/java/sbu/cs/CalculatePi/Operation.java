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

    }
}
