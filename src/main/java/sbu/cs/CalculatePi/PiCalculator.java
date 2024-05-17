package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class PiCalculator{

    public String calculate(int floatingPoint)
    {
        BigDecimal pi = BigDecimal.ZERO;
        double repetion = Math.pow(10, 9);
        int numberOfThreads = 10;
        ArrayList<PiOperation> piOperations = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            piOperations.add(new PiOperation(repetion * i, repetion * (i + 1), floatingPoint));
            threads.add(new Thread(piOperations.get(i)));
            threads.get(i).start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < numberOfThreads; i++) {
            pi = pi.add(piOperations.get(i).getPartialPi());
        }
        return pi.toString().substring(0, floatingPoint + 2);
    }

    public static void main(String[] args) {
        PiCalculator a = new PiCalculator();
        System.out.println(a.calculate(5));
    }
}
