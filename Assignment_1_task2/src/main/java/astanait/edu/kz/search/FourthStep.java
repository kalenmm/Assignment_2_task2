package astanait.edu.kz.search;

import astanait.edu.kz.SharedStorage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;

public class FourthStep implements ISearch, Runnable{
    private Integer id;
    private File file;
    private String search;
    private SharedStorage storage;

    public FourthStep(Integer id,File file, String search, SharedStorage storage) {
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
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String title = bf.readLine();
            if (title.equals(clearSearch))
                storage.put(file.getName());
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ThirdStep done id="+id);
    }
}

