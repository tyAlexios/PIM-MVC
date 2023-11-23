package PIM.controller;

import PIM.model.SearchAPI;

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
        SearchAPI searchAPI = new SearchAPI();
        searchAPI.init(null);
        return;
    }
}
