import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SimpleQueue {
    public static Queue<Long> times = new LinkedList<>();
    public static TimerTask producer() {
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                times.add(System.currentTimeMillis() / 1000);
            }
        };
        return tt;
    }
    public static TimerTask customer(){
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println(times.poll());
            }
        };
        return tt;
    }

    public static void run(){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        //producer
        scheduler.scheduleAtFixedRate(producer(), 1, 1L , SECONDS);

        //customer
        scheduler.scheduleAtFixedRate(customer(), 2, 2L , SECONDS);

    }


    public static void main(String[] args) throws InterruptedException {
        run();
    }



}
