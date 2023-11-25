package PIM.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

class PIRRepo
{
    private static final TreeMap<String, PIR> Repository = new TreeMap<>();

    static PIR getPIR(String primaryKey)
    {
        return Repository.get(primaryKey);
    }
    static void insertPIR(String primaryKey, PIR newPIR)
    {
        Repository.put(primaryKey, newPIR);
    }

    static void deletePIR(String primaryKey)
    {
        Repository.remove(primaryKey);
    }

    static List<String[]> RepoImage()
    {
        List<String[]> img = new ArrayList<>();
        for (String key : Repository.keySet())
            img.add(Repository.get(key).getInfo());
        return img;
    }
}
