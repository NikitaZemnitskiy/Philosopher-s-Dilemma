import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Filosoph implements Runnable {
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

    public Filosoph(Fork leftHandFork, Fork righrHandFork, String name, int minEatTime, int maxEatTime) {
        this.leftHandFork = leftHandFork;
        rightHandFork = righrHandFork;
        this.name = name;
        this.minEatTime = minEatTime;
        this.maxEatTime = maxEatTime;
    }

    public Filosoph(Fork leftHandFork, Fork rightHandFork, String name) {
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
            while (true) {
                if(!leftHandFork.takeFork(this)){
                    if(rightHandFork.owner==this) {
                        System.out.println(name + " don't hold a right hand fork now");
                        rightHandFork.leftFork(this);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                    continue;
                }
                if(!rightHandFork.takeFork(this)){
                    if(leftHandFork.owner == this) {
                        System.out.println(name + " don't hold a left hand fork now");
                        leftHandFork.leftFork(this);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        return;
                    }
                    continue;
                }
                Date startEating = new Date();
                    try {
                        Thread.sleep(ThreadLocalRandom.current() .nextInt(minEatTime, maxEatTime));
                        eatTimes++;
                           leftHandFork.leftFork(this);
                           rightHandFork.leftFork(this);
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

