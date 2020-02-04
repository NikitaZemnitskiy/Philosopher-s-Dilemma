import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
@Slf4j
public class Philosopher implements Runnable {
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
                log.info(getName() + " was interupt");
                return;
            }
        }
    }

    public void eat() throws InterruptedException {
            if (!leftHandFork.takeFork(this)) {
                if (rightHandFork.owner == this) {
                    log.info(name + " don't hold a right hand fork now");
                    rightHandFork.leftFork(this);
                }
                return;
            }
            if (!rightHandFork.takeFork(this)) {
                if (leftHandFork.owner == this) {
                    log.info(name + " don't hold a left hand fork now");
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
            log.info(name + " stoped dinner and he is thinking now");
    }
}

