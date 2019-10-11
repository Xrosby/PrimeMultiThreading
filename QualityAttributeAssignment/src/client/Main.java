package client;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {


        HashMap<Integer, Long> noOfThreadsAndRunTime = new HashMap<>();
	    Primes primeCalc = new Primes();


        int maxThreads = 2;
        for(int i = 1; i <= maxThreads; i++) {
            //System.out.println("\n_________________________________STARTING WITH " + i + " THREADS_______________________________________\n ");
            try
            {
                primeCalc.calculatePrimes(i);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
           // System.out.println(primeCalc.getPrimes());

           // System.out.println("PRIMES: " + primeCalc.getPrimes());
            //System.out.println("\n_____________________FINISHED IN " + primeCalc.getTimeString() + " WITH " + i + " THREADS___________________\n\n" +
              //      "########################################################################\n\n");
            noOfThreadsAndRunTime.put(i, primeCalc.getRunTime());
        }

        System.out.println("THREADS     RUNTIME");
       noOfThreadsAndRunTime.forEach((k,v) -> {
           int min = (int)(v/1000/60);
           int sec = (int)(v/1000%60);
           int milli = (int)(v%1000);
           String runTime = min + ":" + sec + ":" + milli;
           System.out.println(k + "            " + runTime);
       });








    }




}
