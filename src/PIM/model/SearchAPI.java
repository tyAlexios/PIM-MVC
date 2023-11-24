package PIM.model;

import java.util.*;

public class SearchAPI implements API
{
    private Set<String> totalKeySet;
    private Set<String> restKeySet;

    @Override
    public int verify(String[] cmd)
    {
        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        List<String[]> PIRInfos = PIRRepo.RepoImage();
        totalKeySet = new HashSet<>();
        restKeySet = new HashSet<>();
        if (para != null)
        {
            String type = para[0];
            for (String[] PIRInfo : PIRInfos)
            {
                String key = PIRInfo[0];
                if (PIRRepo.getPIR(key).getType().equals(type))
                    totalKeySet.add(key);
            }
        }
        else
        {
            for (String[] PIRInfo : PIRInfos)
                totalKeySet.add(PIRInfo[0]);
        }
        return null;
    }

    @Override
    public void exe(String[] tokens)
    {
        if (tokens.length == 0)
            restKeySet = totalKeySet;
        else
            restKeySet = filter( tokens, 0, tokens.length-1 );
    }

    public Set<String> getRestKeySet()
    {
        return new HashSet<>(restKeySet);
    }


    private Set<String> filter( String[] tokens, int start, int end)
    {
        Stack<Set<String>> keySets = new Stack<>();
        Stack<String> ops = new Stack<>();
        Set<String> ret = new HashSet<>();
        for (int i = start; i <= end; i++)
        {
            String token = tokens[i];

            Set<String> curRes = null;

            if (token.equals("("))
            {
                int j = findClosing(tokens, i);
                curRes = filter( tokens, i + 1, j - 1);
                i = j;
            } else if (isOperator(token))
            {
                ops.push(token);
                continue;
            } else
            {
                if (token.charAt(0) == '\"' && token.charAt(token.length()-1) == '\"')
                    curRes = checkStr(token.substring(1, token.length()-1));
                else
                    curRes = checkTime(token.charAt(0), token.substring(1));
            }

            while (!ops.isEmpty())
            {
                String op = ops.pop();
                if ( op.equals("!") )
                    curRes = applyOp(curRes);
                else if (op.equals("&&"))
                    curRes = applyANDOp(keySets.pop(), curRes);
                else if (op.equals("||"))
                    curRes = applyOROp(keySets.pop(), curRes);
            }
            keySets.push(curRes);
        }

        if (!keySets.isEmpty())
            ret = keySets.pop();

        return ret;
    }

    private Set<String> checkTime(char op, String targetTime)
    {
        Set<String> matchingKeys = new HashSet<>();

        for (String key : totalKeySet)
        {
            PIR curPIR = PIRRepo.getPIR(key);
            String[] PIRInfo = curPIR.getInfo();
            int[] indices = curPIR.getTimeAttrIdx();

            for (int idx : indices)
            {
                String curTime;
                if (targetTime.length() == 5)
                    curTime = PIRInfo[idx].substring(11);
                else
                    curTime = PIRInfo[idx];
                if (compareTime(op, curTime, targetTime))
                {
                    matchingKeys.add(key);
                    break;
                }
            }
        }

        return matchingKeys;
    }

    private boolean compareTime(char op, String curTime, String targetTime)
    {
        boolean flag = false;
        int ret = curTime.compareTo(targetTime);
        if (ret < 0 && op == '<')
            flag = true;
        if (ret > 0 && op == '>')
            flag = true;
        if (ret == 0 && op == '=')
            flag = true;
        return flag;
    }

    private Set<String> checkStr(String targetStr) {
        Set<String> matchingKeys = new HashSet<>();

        for (String key : totalKeySet) {
            PIR curPIR = PIRRepo.getPIR(key);
            String[] PIRInfo = curPIR.getInfo();
            int[] indices = curPIR.getStrAttrIdx();

            for (int idx : indices)
            {
                String curStr = PIRInfo[idx];
                if (curStr != null && curStr.contains(targetStr)) {
                    matchingKeys.add(key);
                    break;
                }
            }
        }

        return matchingKeys;
    }

    private int findClosing(String[] tokens, int openPos)
    {
        int closePos = openPos;
        int counter = 1;
        while (counter > 0) {
            String token = tokens[++closePos];
            if (token.equals("(")) counter++;
            if (token.equals(")")) counter--;
        }
        return closePos;
    }

    private Set<String> applyANDOp(Set<String> set1, Set<String> set2)
    {
        set1.retainAll(set2);
        return set1;
    }

    private Set<String> applyOROp(Set<String> set1, Set<String> set2)
    {
        set1.addAll(set2);
        return set1;
    }

    private Set<String> applyOp( Set<String> set)
    {
        Set<String> complement = new HashSet<>(totalKeySet);
        complement.removeAll(set);
        return complement;
    }

    private boolean isOperator(String token) {
        return token.equals("&&") || token.equals("||") || token.equals("!");
    }


}
