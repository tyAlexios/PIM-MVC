package PIM.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PIRRepo
{
    private static final TreeMap<String, PIR> Repository = new TreeMap<>();

    public static PIR getPIR(String primaryKey)
    {
        return Repository.get(primaryKey);
    }
    public static void insertPIR(String primaryKey, PIR newPIR)
    {
        Repository.put(primaryKey, newPIR);
    }

    public static void deletePIR(String primaryKey)
    {
        Repository.remove(primaryKey);
    }

    public static List<String[]> RepoImage()
    {
        List<String[]> img = new ArrayList<>();
        for (String key : Repository.keySet())
            img.add(Repository.get(key).getInfo());
        return img;
    }
}
