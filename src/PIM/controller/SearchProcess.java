package PIM.controller;

import PIM.model.SearchAPI;
import PIM.view.SearchView;
import PIM.view.SearchView.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String[] tokens = parseExpression(String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)));

        SearchAPI searchAPI = new SearchAPI();
        searchAPI.init(null);

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

    private String[] parseExpression(String expression)
    {
        List<String> components = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"\\\\]*(\\\\.[^\"\\\\]*)*)\"|\\S+");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            if (matcher.group(1) != null)
            {
                components.add("\""+matcher.group(1).replaceAll("\\\\\"", "\"")+"\"");
            }
            else
                components.add(matcher.group(0));
        }

        return components.toArray(new String[0]);
    }

}
