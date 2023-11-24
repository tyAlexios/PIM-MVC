package PIM.model;

import java.util.*;

public class SearchAPI implements API
{
    private Set<String> totalKeySet;
    private Stack<Set<String>> keySets;
    private Set<String> restKeySet;
    Stack<String> ops = new Stack<>();

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
        for (String[] PIRInfo : PIRInfos)
            totalKeySet.add(PIRInfo[0]);
        keySets = new Stack<>();
        restKeySet = new HashSet<>();
        return null;
    }

    @Override
    public void exe(String[] tokens)
    {
        restKeySet = filter( tokens, 0, tokens.length-1 );
    }

    public Set<String> getRestKeySet()
    {
        return new HashSet<>(restKeySet);
    }


    private Set<String>  filter( String[] tokens, int start, int end)
    {
        for (int i = start; i <= end; i++)
        {
            String token = tokens[i];
            if (token.equals("("))
            {
                int j = findClosing(tokens, i);
                keySets.push(filter( tokens, i + 1, j - 1));
                i = j;
            } else if (isOperator(token)) {
                ops.push(token);
            } else
            {
                if (token.charAt(0) == '\"' && token.charAt(token.length()-1) == '\"')
                    keySets.push(checkStr(token.substring(1, token.length()-1)));
                else
                    keySets.push(checkTime(token.charAt(0), token.substring(1)));
            }

            if (ops.isEmpty() || keySets.isEmpty())
                continue;

            if (ops.peek().equals("!") && !keySets.isEmpty())
            {
                ops.pop();
                keySets.push(applyOp(keySets.pop()));
            }
            else if (keySets.size() >= 2)
            {
                keySets.push(applyOp(ops.pop(), keySets.pop(), keySets.pop()));
            }

        }
        return keySets.pop();
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
        if (op == '<' && ret < 0)
            flag = true;
        else if (op == '>' && ret > 0)
            flag = true;
        else if (op == '=')
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

    private Set<String> applyOp(String op, Set<String> set1, Set<String> set2) {
        switch (op)
        {
            case "&&" -> {
                set1.retainAll(set2);
                return set1;
            }
            case "||" -> {
                set1.addAll(set2);
                return set1;
            }
            default -> throw new IllegalArgumentException("Unknown operator: " + op);
        }
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
