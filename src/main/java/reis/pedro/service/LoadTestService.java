package reis.pedro.service;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;

public class LoadTestService {

    private LoadTestService() {
    }

    public static void memoryEater() {
        Vector v = new Vector();
        while (true) {
            byte b[] = new byte[1048576];
            v.add(b);
            Runtime rt = Runtime.getRuntime();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            Date date = new Date();
            System.out.println(date.toString() + " " + "free memory: " + rt.freeMemory());
            System.out.println(date);
            System.out.println(" free memory is: ");
            System.out.println(rt.freeMemory());
        }
    }

    public static void cpuEater(int threadsQtd) {
        try {
            LongAdder counter = new LongAdder();

            int numThreads = threadsQtd;

            List<CalculationThread> runningCalcs = new ArrayList<>();
            List<Thread> runningThreads = new ArrayList<>();

            System.out.printf("Starting %d threads\n", numThreads);

            for (int i = 0; i < numThreads; i++) {
                CalculationThread r = new CalculationThread(counter);
                Thread t = new Thread(r);
                runningCalcs.add(r);
                runningThreads.add(t);
                t.start();
            }

            for (int i = 0; i < 15; i++) {
                counter.reset();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                System.out.printf("[%d] Calculations per second: %d (%.2f per thread)\n",
                        i,
                        counter.longValue(),
                        (double) (counter.longValue()) / numThreads
                );
            }

            for (int i = 0; i < runningCalcs.size(); i++) {
                runningCalcs.get(i).stop();
                runningThreads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class CalculationThread implements Runnable {
        private final Random rng;
        private final LongAdder calculationsPerformed;
        private boolean stopped;
        private double store;

        public CalculationThread(LongAdder calculationsPerformed) {
            this.calculationsPerformed = calculationsPerformed;
            this.stopped = false;
            this.rng = new Random();
            this.store = 1;
        }

        public void stop() {
            this.stopped = true;
        }

        @Override
        public void run() {
            while (!this.stopped) {
                double r = this.rng.nextFloat();
                double v = Math.sin(Math.cos(Math.sin(Math.cos(r))));
                this.store *= v;
                this.calculationsPerformed.add(1);
            }
        }
    }
}
