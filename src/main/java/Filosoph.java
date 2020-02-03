import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Filosoph implements Runnable {
    private int eatTimes = 0;
    private final Fork LeftHandFork;
    private final Fork RighrHandFork;
    private final String name;
    private int minEatTime = 0;
    private int maxEatTime = 1000;
    private long eatTime = 0;

    public int getEatTimes() {
        return eatTimes;
    }

    public Filosoph(Fork leftHandFork, Fork righrHandFork, String name, int minEatTime, int maxEatTime) {
        LeftHandFork = leftHandFork;
        RighrHandFork = righrHandFork;
        this.name = name;
        this.minEatTime = minEatTime;
        this.maxEatTime = maxEatTime;
    }

    public Filosoph(Fork leftHandFork, Fork rightHandFork, String name) {
        LeftHandFork = leftHandFork;
        RighrHandFork = rightHandFork;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getEatTime() {
        return eatTime;
    }

    public void run() {
            while (true) {
                LeftHandFork.takeFork(this);
                RighrHandFork.takeFork(this);
                Date startEating = new Date();
                    try {
                        Thread.sleep(ThreadLocalRandom.current() .nextInt(minEatTime, maxEatTime));
                        eatTimes++;
                           LeftHandFork.leftFork(this);
                           RighrHandFork.leftFork(this);
                           Date endEating = new Date();
                           eatTime = (eatTime+(endEating.getTime()-startEating.getTime()));
                        System.out.println(name +" stoped dinner and he is thinking now");
                           Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

