package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class PiCalculator{

    public String calculate(int floatingPoint)
    {
        BigDecimal pi = BigDecimal.ZERO;
        double oneMillion = Math.pow(10, 6);
        ArrayList<PiOperation> piOperations = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            piOperations.add(new PiOperation(oneMillion * i, oneMillion * (i + 1), floatingPoint));
            threads.add(new Thread(piOperations.get(i)));
            threads.get(i).start();
        }
        for (int i = 0; i < 10; i++) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < 10; i++) {
            pi.add(piOperations.get(i).getPartialPi());
        }
        return pi.toString();
    }

    public static void main(String[] args) {
        PiCalculator a = new PiCalculator();
        System.out.println(a.calculate(10));
    }
}
