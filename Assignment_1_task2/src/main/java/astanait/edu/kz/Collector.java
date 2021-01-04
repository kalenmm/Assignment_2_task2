package astanait.edu.kz;

import astanait.edu.kz.search.ISearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Collector {
    public List<String> collect(List<List<ISearch>> searches){

        HashMap<String,Integer> result = new HashMap<>();

        for (int i=0;i<searches.size();i++){
            List<ISearch> searches1 = searches.get(i);
            for (int j=0; j<searches1.size();j++){
                ArrayBlockingQueue<String> queue = searches1.get(i).get();
                for (String s : queue) {
                    if (!result.containsKey(s))
                        result.put(s,1);
                }
            }
        }
        List<String> res = new ArrayList<>();
        result.forEach((k,v)->{
            res.add(k);
        });
        Collections.reverse(res);
        return res;
    }
}
