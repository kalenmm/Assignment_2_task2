package astanait.edu.kz;
import java.util.concurrent.ArrayBlockingQueue;

public class SharedStorage {
    private ArrayBlockingQueue<String> result;

    public SharedStorage(Integer size) {
        this.result = new ArrayBlockingQueue<>(size);
    }

    public synchronized void put(String name) throws InterruptedException {
        result.put(name);
    }

    public ArrayBlockingQueue<String> get() {
        return result;
    }
}
