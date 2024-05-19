package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator{
    static MathContext mc = new MathContext(1100, RoundingMode.HALF_UP);
    static BigDecimal pi = BigDecimal.ZERO;
    static BigDecimal one = BigDecimal.ONE;
    static BigDecimal four = new BigDecimal(4);
    static BigDecimal two = new BigDecimal(2);
    public static class PiOperation implements Runnable{
        private BigDecimal partialPi;
        double i;
        PiOperation(double i){
            this.partialPi = BigDecimal.ZERO;
            this.i = i;
        }
        @Override
        public void run() {
            BigDecimal a = four.divide(BigDecimal.valueOf(8L * i + 1), mc);
            BigDecimal b = two.divide(BigDecimal.valueOf(8L * i + 4), mc);
            BigDecimal c = one.divide(BigDecimal.valueOf(8L * i + 5), mc);
            BigDecimal d = one.divide(BigDecimal.valueOf(8L * i + 6), mc);
            BigDecimal sum = a.subtract(b).subtract(c).subtract(d);

            BigDecimal sixteenPowK = BigDecimal.valueOf(16).pow((int) i);
            BigDecimal term = sum.divide(sixteenPowK, mc);

            addToPi(term);
        }

    }
    public String calculate(int floatingPoint)
    {

        double repetition = Math.pow(10, 4);
        int numberOfThreads = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < repetition; i++) {
            PiOperation po = new PiOperation(i);
            threadPool.execute(po);
        }

        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return pi.toString().substring(0, floatingPoint + 2);
    }
    public static synchronized void addToPi(BigDecimal value){
        pi = pi.add(value);
    }
    public static void main(String[] args) {
        PiCalculator a = new PiCalculator();
        System.out.println(a.calculate(7));
    }
}
