import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Philosopher implements Runnable {
    private static AtomicLong totalEatTime = new AtomicLong();

    public static AtomicLong getTotalEatTime() {
        return totalEatTime;
    }

    private int eatTimes = 0;
    private final Fork leftHandFork;
    private final Fork rightHandFork;
    private final String name;
    private int minEatTime = 0;
    private int maxEatTime = 1000;
    private long eatTime = 0;

    public int getEatTimes() {
        return eatTimes;
    }

    public Philosopher(Fork leftHandFork, Fork righrHandFork, String name, int minEatTime, int maxEatTime) {
        this.leftHandFork = leftHandFork;
        rightHandFork = righrHandFork;
        this.name = name;
        this.minEatTime = minEatTime;
        this.maxEatTime = maxEatTime;
    }

    public Philosopher(Fork leftHandFork, Fork rightHandFork, String name) {
        this.leftHandFork = leftHandFork;
        this.rightHandFork = rightHandFork;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getEatTime() {
        return eatTime;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                    eat();
                   Thread.sleep(10);

            } catch (InterruptedException e) {
                log.debug("{} was interupt", getName());
                return;
            }
        }
    }

    public void eat() throws InterruptedException {
            if (!leftHandFork.takeFork(this)) {
                if (rightHandFork.owner == this) {
                    log.debug("{} don't hold a right hand fork now", getName());
                    rightHandFork.leftFork(this);
                }
                return;
            }
            if (!rightHandFork.takeFork(this)) {
                if (leftHandFork.owner == this) {
                    log.debug("{} don't hold a left hand fork now", getName());
                    leftHandFork.leftFork(this);
                }
                return;
            }
            Instant startEating = Instant.now();
            Thread.sleep(ThreadLocalRandom.current().nextInt(minEatTime, maxEatTime));
            eatTimes++;
            leftHandFork.leftFork(this);
            rightHandFork.leftFork(this);

            Instant endEating = Instant.now();
            eatTime = (eatTime + (endEating.toEpochMilli() - startEating.toEpochMilli()));
            log.debug("{} stoped dinner and he is thinking now", getName());
    }

    public void getStat (){
        log.info("--------"+getName()+"----------");
        log.info("{} ate {} times",getName(), getEatTimes());
        log.info("{} ate {} ms",getName(), getEatTime());
        totalEatTime.addAndGet(getEatTime());
    }
}

