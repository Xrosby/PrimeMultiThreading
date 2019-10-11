package client;

import java.util.ArrayList;
import java.util.Collections;

public class Primes
{

    private ArrayList<Integer> primes;
    private long runTime;


    public void calculatePrimes(int noOfThreads) throws InterruptedException
    {
        this.primes = new ArrayList<>();
        long startTime = System.currentTimeMillis();
        int max = 500000;

        ArrayList<Thread> threads = new ArrayList<>();

        int division = max / noOfThreads;




        for (int i = 1; i <= noOfThreads; i++)
        {
            int threadMin = (i * division) - (division);
            int threadMax = i * division;


            Thread thread = new Thread("" + i)
            {
                @Override
                public void run()
                {
                    //System.out.println("Starting thread " + getName());
                    //System.out.println("Working from: " + threadMin + " to " + threadMax);
                    startPrimeThread(threadMin, threadMax);
                }
            };

            thread.start();
            threads.add(thread);
        }


        for (Thread thread : threads)
        {
            thread.join();
        }

        Collections.sort(primes);
        this.runTime = (System.currentTimeMillis() - startTime);

    }


    private void startPrimeThread(int min, int max)
    {
        for (int i = min; i < max; i++)
        {
            if (this.isPrime(i))
            {
                synchronized (primes)
                {
                    this.primes.add(i);
                }
                if(primes.size() % 1000 == 0) {
                   // System.out.println(primes.size());
                }
            }

        }
    }

    public ArrayList<Integer> getPrimes()
    {
        return primes;
    }

    private synchronized boolean isPrime(int i)
    {
        boolean isPrime = true;

        if (i > 2 && (i & 1) == 0) isPrime = false;
        for (int j = 3; j < i; j++)
        {
            if (i % j == 0)
            {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public long getRunTime()
    {
        return this.runTime;
    }

    public String getTimeString() {
        int min = (int)(runTime/1000/60);
        int sec = (int)(runTime/1000%60);
        int milli = (int)(runTime%1000);
        String runTime = min + ":" + sec + ":" + milli;
        return runTime;
    }
}
