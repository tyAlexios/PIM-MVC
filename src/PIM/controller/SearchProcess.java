package PIM.controller;

import PIM.model.SearchAPI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchProcess implements OperationProcess
{
    private Set<String> searchKeySet;
    @Override
    public int verify(String[] cmd)
    {
        // all split with only one white space
        // no priority between && and ! and ||
        return 0;
    }

    @Override
    public void process(String[] cmd)
    {
        SearchAPI searchAPI = new SearchAPI();
        searchAPI.init(null);
        searchAPI.exe(Arrays.copyOfRange(cmd, 1, cmd.length));
        searchKeySet = searchAPI.getRestKeySet();
        for (String e : searchKeySet)
        {
            System.out.println(e);
        }
    }

    public Set<String> getSearchKeySet()
    {
        return new HashSet<>(searchKeySet);
    }

}
