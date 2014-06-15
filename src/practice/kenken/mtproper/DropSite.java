package practice.kenken.mtproper;

/**
 * KenKen implementation, properly multi-threaded with Concurrency and Executor
 * @author derek
 */
public class DropSite<V> {
    private V value = null;
    
    public DropSite(){
        
    }
    
    public synchronized V get(){
        try{
            while (value == null) {
                wait();
            }
        }catch(InterruptedException e){
            System.err.println(Thread.currentThread().getName() + " was interrupted: could not get() from DropSite.");
        }
        return value;
    }
    
    public synchronized void set(V newVal){
        value = newVal;
        notifyAll();
    }
}
