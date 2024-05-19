package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator{
    //MathContest to set our divide and multiply scale to 1100 for more accuracy
    static MathContext mc = new MathContext(1100, RoundingMode.HALF_UP);
    //BigDecimal pi to add terms
    static BigDecimal pi = BigDecimal.ZERO;
    //here we make one, two and four BigDecimal numbers
    static BigDecimal one = BigDecimal.ONE;
    static BigDecimal four = new BigDecimal(4);
    static BigDecimal two = new BigDecimal(2);
    public static class PiOperation implements Runnable{
        //i is for i'th term
        double i;
        PiOperation(double i){
            this.i = i;
        }
        @Override
        public void run() {
            //here we make BBP algorithm parts
            BigDecimal a = four.divide(BigDecimal.valueOf(8L * i + 1), mc);
            BigDecimal b = two.divide(BigDecimal.valueOf(8L * i + 4), mc);
            BigDecimal c = one.divide(BigDecimal.valueOf(8L * i + 5), mc);
            BigDecimal d = one.divide(BigDecimal.valueOf(8L * i + 6), mc);
            BigDecimal sixteenPowK = BigDecimal.valueOf(16).pow((int) i);
            //here we subtract others from a
            BigDecimal sum = a.subtract(b).subtract(c).subtract(d);
            //here we divide sum to sixteenPowK
            BigDecimal term = sum.divide(sixteenPowK, mc);
            //here we add our terms to pi using addToPi
            addToPi(term);
        }

    }
    public String calculate(int floatingPoint)
    {
        //we set number of repetition we want to sum the terms
        double repetition = Math.pow(10, 4);
        //we set number of threads to calculate terms in thread pool
        int numberOfThreads = 10;
        ExecutorService threadPool = Executors.newFixedThreadPool(numberOfThreads);
        //execute threads in thread pool to the number of repetitions
        for (int i = 0; i < repetition; i++) {
            PiOperation po = new PiOperation(i);
            threadPool.execute(po);
        }
        //we shut down thread pool
        threadPool.shutdown();

        try {
            threadPool.awaitTermination(100, TimeUnit.MILLISECONDS);
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
