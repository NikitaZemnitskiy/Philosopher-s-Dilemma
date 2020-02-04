import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Fork {
    private final static int TIME_OUT = 2;
    Philosopher owner = null;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public boolean takeFork(Philosopher philosopher) throws InterruptedException {

            if (!reentrantLock.tryLock(TIME_OUT, TimeUnit.SECONDS)){
                log.info(owner.getName()+" can't take a fork more than "+TIME_OUT+" seconds");
                return false;
            }

        log.info(philosopher.getName()+" is taking the fork");
        if(owner != null){
           throw new IllegalStateException(philosopher.getName() + " tried to take a fork, but this fork already had an owner - " + owner.getName());
        }
        owner = philosopher;
        return true;
    }

    public void leftFork(Philosopher philosopher){

        log.info(philosopher.getName()+" put his fork");

        if(owner != philosopher){
            throw new IllegalStateException(philosopher.getName()+
                    "are trying to put the fork, dut he is'nt an owner of this fork. " +
                    "The owner of this fork is "+ owner.getName());
        }
        owner=null;
        reentrantLock.unlock();
    }
}
