public class Fork {
    boolean isOwned;


    public synchronized boolean takeFork(){
        if(isOwned){
            return false;
        }
        else {
            isOwned = true;
            return true;
        }
    }
    public synchronized void letFork(){
        isOwned = false;
    }
}
