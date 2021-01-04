package astanait.edu.kz.search;

import astanait.edu.kz.SharedStorage;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

public class FirstStep implements ISearch, Runnable {
    private Integer id;
    private File file;
    private String search;
    private SharedStorage storage;

    public FirstStep(Integer id,File file, String search, SharedStorage storage) {
        this.id = id;
        this.file = file;
        this.search = search;
        this.storage = storage;
    }


    @Override
    public ArrayBlockingQueue<String> get() {
        return storage.get();
    }

    @Override
    public void run() {
        if (file.getName().replaceFirst("[.][^.]+$", "").equals(search)){
            try {
                storage.put(file.getName());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("FirstStep done id="+id);
    }
}
