package PIM.controller;

import PIM.model.SearchAPI;
import PIM.view.SearchView;
import PIM.view.SearchView.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd)
    {
        if (cmd.length < 2)
            return 16;

        String[] tokens;

        if (cmd[1].charAt(0) == '-')
        {
            tokens = parseExpression(String.join(" ", Arrays.copyOfRange(cmd, 2, cmd.length)));

            String type = cmd[1].substring(1);
            String[] para = new String[1];
            para[0] = type;
            SearchAPI searchAPI = new SearchAPI();
            return searchAPI.verify(para);
        }
        else
            tokens = parseExpression(String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)));

        for (String token : tokens)
            System.out.println(token);

        for (int i=0; i<tokens.length; ++i)
        {
            String token = tokens[i];
            char st = token.charAt(0);
            char end = token.charAt(token.length()-1);

            if (token.length() == 1)
            {
                if( !token.equals("(") && !token.equals(")") && !token.equals("!"))
                    return 16;
                else
                    continue;
            }

            if (st == '\"' && end !='\"')
                return 16;
            if (end == '\"' && st !='\"')
                return 16;

            if (st == '\"')
            {
                if ( i == 0 && i == tokens.length-1 )
                    continue;

                if (i == 0)
                {
                    String nxtToken = tokens[i+1];
                    if ( !nxtToken.equals("&&") && !nxtToken.equals("||") && !nxtToken.equals(")"))
                        return 16;
                    continue;
                }

                if (i == tokens.length-1)
                {
                    String preToken = tokens[i-1];
                    if ( !preToken.equals("&&") && !preToken.equals("||") && !preToken.equals("!") && !preToken.equals("("))
                        return 16;
                    continue;
                }

                String preToken = tokens[i-1];
                if ( !preToken.equals("&&") && !preToken.equals("||") && !preToken.equals("!") && !preToken.equals("("))
                    return 16;

                String nxtToken = tokens[i+1];
                if ( !nxtToken.equals("&&") && !nxtToken.equals("||") && !nxtToken.equals(")"))
                    return 16;

                continue;
            }

            if (st == '=' || st == '>' || st == '<')
            {
                String time = token.substring(1);
                if (time.length() == 5)
                {
                    try {
                        LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
                        continue;
                    }
                    catch (DateTimeParseException e)
                    {
                        return 16;
                    }
                }
                else
                {
                    try {
                        LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"));
                        continue;
                    }
                    catch (DateTimeParseException e)
                    {
                        return 16;
                    }
                }
            }


            if (token.equals("&&") || token.equals("||"))
            {
                if ( i == 0 || i == tokens.length-1 )
                    return 16;
                String preToken = tokens[i-1];
                String nxtToken = tokens[i+1];

                if (    (preToken.charAt(0) != '\"' && preToken.charAt(preToken.length()-1) != '\"')
                        && (preToken.charAt(0) != '>' && preToken.charAt(0) != '=' && preToken.charAt(0) != '<')
                        && (nxtToken.charAt(0) != '\"' && nxtToken.charAt(nxtToken.length()-1) != '\"')
                        && (nxtToken.charAt(0) != '>' && nxtToken.charAt(0) != '=' && nxtToken.charAt(0) != '<') )
                    return 16;

                continue;
            }

            return 16;
        }
        return 0;
    }

    @Override
    public void process(String[] cmd)
    {
        String[] tokens;
        SearchAPI searchAPI = new SearchAPI();

        if (cmd[1].charAt(0) == '-')
        {
            String type = cmd[1].substring(1);
            String[] para = new String[1];
            para[0] = type;
            searchAPI.init(para);
            tokens = parseExpression(String.join(" ", Arrays.copyOfRange(cmd, 2, cmd.length)));
        }
        else
        {
            searchAPI.init(null);
            tokens = parseExpression(String.join(" ", Arrays.copyOfRange(cmd, 1, cmd.length)));
        }

        searchAPI.exe(tokens);

        Set<String> searchKeySet = searchAPI.getRestKeySet();
        if (searchKeySet == null || searchKeySet.isEmpty())
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
