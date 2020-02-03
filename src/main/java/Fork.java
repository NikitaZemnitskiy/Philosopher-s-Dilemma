import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    Filosoph owner = null;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public boolean takeFork(Filosoph filosoph) {
        try {
            reentrantLock.tryLock(5000, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e){
            System.out.println(owner.getName()+" can't take a fork more than 5 second");
            return false;
        }
        System.out.println(filosoph.getName()+" are taking the fork");
        if(owner != null){
            System.out.println(filosoph.getName() + " tried to take a fork, but this fork already had an owner - " + owner.getName());
            return false;
        }
        owner = filosoph;
        return true;
    }

    public void leftFork(Filosoph filosoph){

        System.out.println(filosoph.getName()+" put his fork");

        if(!owner.equals(filosoph)){
            System.out.println(filosoph.getName()+ "are trying to put the fork, dut he is'nt an owner of this fork. The owner of this fork is "+owner.getName());
            throw new IllegalStateException();
        }
        owner=null;
        reentrantLock.unlock();

    }
}
