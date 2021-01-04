package astanait.edu.kz.search;

import astanait.edu.kz.SharedStorage;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

public class SecondStep implements ISearch, Runnable {
    private Integer id;
    private File file;
    private String search;
    private SharedStorage storage;

    public SecondStep(Integer id,File file, String search, SharedStorage storage) {
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
        String clearSearch = search.replaceAll("[a][an][the][is][are]","");
        if (file.getName().replaceFirst("[.][^.]+$", "").equals(clearSearch)){
            try {
                storage.put(file.getName());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("SecondStep done id="+id);
    }
}
