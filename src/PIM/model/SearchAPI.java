package PIM.model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
        }
        while (!ops.isEmpty())
        {
            if (ops.peek().equals("!"))
            {
                ops.pop();
                keySets.push(applyOp(keySets.pop()));
            }
            else
            {
                keySets.push(applyOp(ops.pop(), keySets.pop(), keySets.pop()));
            }
        }
        return keySets.pop();
    }

    private Set<String> checkTime(char op, String targetTime)
    {
        Set<String> matchingKeys = new HashSet<>();
        DateTimeFormatter formatter;

        if (targetTime.length() > 5)
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
        else
            formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDateTime targetDateTime = null;
        LocalTime targetLocalTime = null;

        try {
            if (formatter.toString().equals("yyyy-MM-dd-HH:mm")) {
                targetDateTime = LocalDateTime.parse(targetTime, formatter);
            } else {
                targetLocalTime = LocalTime.parse(targetTime, formatter);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format for targetTime", e);
        }

        for (String key : totalKeySet)
        {
            PIR curPIR = PIRRepo.getPIR(key);
            String[] PIRInfo = curPIR.getInfo();
            int[] indices = curPIR.getTimeAttrIdx();

            for (int idx : indices)
            {
                String curTime = PIRInfo[idx];
                boolean matches = false;

                if (targetDateTime != null) {
                    LocalDateTime curDateTime = LocalDateTime.parse(curTime, formatter);
                    matches = compareDateTime(op, curDateTime, targetDateTime);
                } else {
                    LocalTime curLocalTime = LocalTime.parse(curTime, formatter);
                    matches = compareLocalTime(op, curLocalTime, targetLocalTime);
                }

                if (matches) {
                    matchingKeys.add(key);
                    break;
                }
            }
        }

        return matchingKeys;
    }

    private boolean compareDateTime(char op, LocalDateTime curDateTime, LocalDateTime targetDateTime) {
        return switch (op) {
            case '=' -> curDateTime.equals(targetDateTime);
            case '>' -> curDateTime.isAfter(targetDateTime);
            case '<' -> curDateTime.isBefore(targetDateTime);
            default -> throw new IllegalArgumentException("Invalid operator for time comparison");
        };
    }

    private boolean compareLocalTime(char op, LocalTime curLocalTime, LocalTime targetLocalTime) {
        return switch (op) {
            case '=' -> curLocalTime.equals(targetLocalTime);
            case '>' -> curLocalTime.isAfter(targetLocalTime);
            case '<' -> curLocalTime.isBefore(targetLocalTime);
            default -> throw new IllegalArgumentException("Invalid operator for time comparison");
        };
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
