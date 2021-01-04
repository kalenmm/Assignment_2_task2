package astanait.edu.kz.search;

import astanait.edu.kz.SharedStorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ArrayBlockingQueue;


public class ThirdStep implements ISearch, Runnable {
    private Integer id;
    private File file;
    private String search;
    private SharedStorage storage;

    public ThirdStep(Integer id,File file, String search, SharedStorage storage) {
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
        String str =  file.getName().replaceFirst("[.][^.]+$", "");
        String[] splits = str.split("\\s");

        for(String splits2: splits) {
            if(splits2.equals(search)){
                try {
                    storage.put(file.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
