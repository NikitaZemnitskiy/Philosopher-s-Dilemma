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
        philosophers.add(new Philosopher(forkList.get(0), forkList.get(1), "nikita", 10, 20));
        philosophers.add(new Philosopher(forkList.get(1), forkList.get(2), "aristotel", 15, 25));
        philosophers.add(new Philosopher(forkList.get(2), forkList.get(3), "platon", 20, 27));
        philosophers.add(new Philosopher(forkList.get(3), forkList.get(4), "shopenhauer", 5, 22));
        philosophers.add(new Philosopher(forkList.get(4), forkList.get(0), "nitsche", 10, 20));

        ExecutorService philosophersThreadPool = Executors.newFixedThreadPool(5);
        for (Philosopher p : philosophers) {
            philosophersThreadPool.submit(p);
        }
        Thread.sleep(12000);
        if (philosophersThreadPool.shutdownNow().size() != 0) {
            throw new IllegalStateException("Failed threads shutdown");
        }
        AtomicLong totalEatTime = new AtomicLong();
        philosophers.stream().forEach(
                p -> {
                    log.info("--------"+p.getName()+"----------");
                    log.info(p.getName() + " ate " + p.getEatTimes() + " times");
                    log.info(p.getName() + " ate " + p.getEatTime() + " ms");
                    totalEatTime.addAndGet(p.getEatTime());
                });
        log.info("All philosophies spend " + totalEatTime + " ms on the dinner today");

    }
}
