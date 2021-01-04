package astanait.edu.kz;

import astanait.edu.kz.search.*;
/*
import com.company.task2.search.FirstSearch;
import com.company.task2.search.Sample;
import com.company.task2.search.SecondSearch;
import com.company.task2.storage.IStorage;
import com.company.task2.storage.Storage;
*/
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        File folder = new File("search");
        if (!folder.exists())
            folder.mkdir();
        List<File> files = Arrays.asList(folder.listFiles().clone());
        int j = 0;
        String search = "";

        Scanner scanner = new Scanner(System.in);
        search = scanner.nextLine();

        ExecutorService es = Executors.newCachedThreadPool();
        Collector collector = new Collector();

        List<List<ISearch>> lists = new ArrayList<>();

        int id = 0;
        while (j < files.size()){
            SharedStorage storage = new SharedStorage(files.size());
            List<ISearch> searches = new ArrayList<>();

            FirstStep firstStep = new FirstStep(id++,files.get(j), search, storage);
            SecondStep secondSearch = new SecondStep(id++,files.get(j), search, storage);
            ThirdStep thirdStep = new ThirdStep(id++,files.get(j), search, storage);
            FourthStep fourthStep = new FourthStep(id++, files.get(j), search, storage);

            es.execute(firstStep);
            es.execute(secondSearch);
            es.execute(thirdStep);
            es.execute(fourthStep);

            searches.add(firstStep);
            searches.add(secondSearch);
            searches.add(thirdStep);
            searches.add(fourthStep);

            lists.add(searches);
            j++;
        }
        es.shutdown();
        es.awaitTermination(2, TimeUnit.SECONDS);
        List<String> strings = collector.collect(lists);

        System.out.println(Arrays.toString(strings.toArray()));
    }
}
