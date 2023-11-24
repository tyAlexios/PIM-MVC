package PIM.controller;

import PIM.model.SearchAPI;
import PIM.view.SearchView;
import PIM.view.SearchView.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchProcess implements OperationProcess
{
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
        String conditionStr = String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length));

        SearchAPI searchAPI = new SearchAPI();
        searchAPI.init(null);
        String[] tokens = Arrays.copyOfRange(cmd, 1, cmd.length);


        searchAPI.exe(tokens);


        Set<String> searchKeySet = searchAPI.getRestKeySet();
        if (searchKeySet.isEmpty())
            PIM.output(SearchView.getView(ViewPage.SearchNone));
        else
        {
            PIM.output(SearchView.getView(ViewPage.SearchTitle));
            for (String key : searchKeySet)
                PIM.output(String.format("%s\n", key));
        }
    }

}
