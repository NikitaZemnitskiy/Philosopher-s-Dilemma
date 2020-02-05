import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException {
        List<Fork> forkList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            forkList.add(new Fork());
        }
        List<Philosopher> philosophers = new ArrayList<>();
        philosophers.add(new Philosopher(forkList.get(0), forkList.get(1), "nikita", 100, 200));
        philosophers.add(new Philosopher(forkList.get(1), forkList.get(2), "aristotel", 150, 250));
        philosophers.add(new Philosopher(forkList.get(2), forkList.get(3), "platon", 200, 270));
        philosophers.add(new Philosopher(forkList.get(3), forkList.get(4), "shopenhauer", 50, 220));
        philosophers.add(new Philosopher(forkList.get(4), forkList.get(0), "nitsche", 100, 200));

        ExecutorService philosophersThreadPool = Executors.newFixedThreadPool(philosophers.size());
        for (Philosopher p : philosophers) {
            philosophersThreadPool.submit(p);
        }
        Thread.sleep(12000);
        if (!philosophersThreadPool.shutdownNow().isEmpty()) {
            throw new IllegalStateException("Failed threads shutdown");
        }

        philosophers.forEach(Philosopher::getStat);
        log.info("All philosophies spend {} ms on the dinner today", philosophers.get(0).getTotalEatTime());

    }
}
