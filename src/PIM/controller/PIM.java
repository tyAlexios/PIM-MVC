package PIM.controller;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PIM.view.*;

public class PIM
{
    public static Stream stream;

    public static String input()
    {
        return stream.in();
    }

    public static void output(String outStr)
    {
        stream.out(outStr);
    }

    private void systemStart()
    {
        output(SystemView.getView(SystemView.ViewPage.WelcomePage));
        String cmdLine = null;
        while(true)
        {
            output(SystemView.getView(SystemView.ViewPage.Navigate));
            cmdLine = input();
            if(Objects.equals(cmdLine, "quit"))
            {
                output(SystemView.getView(SystemView.ViewPage.ByePage));
                return;
            }
            int errno = runCmd(cmdLine);
            if (errno != 0)
                output(ErrorRepo.getError(errno));
        }
    }

    public int runCmd(String cmdLine)
    {
        String[] cmd = cmdLine.split(" ");
        String op = cmd[0];
        if (!OperationLib.containOperation(op))
        {
            return 1;
        }
        else
        {
            OperationProcess proc = OperationLib.getOperation(op);
            int errno = proc.verify(cmd);
            if (errno != 0)
                return errno;
            else
            {
                proc.process(cmd);
                return 0;
            }
        }
    }

    public static void main(String[] args)
    {
        stream = new ScannerStream();

        PIM pim = new PIM();
        pim.systemStart();

        stream.close();
    }

//    public static void main(String[] args) {
//        String text = "start \"part with \"escaped\" quotes and whitespace\" end";
//        Pattern pattern = Pattern.compile("(?<!\\\\)\\\"(.*?)(?<!\\\\)\\\"");
//        Matcher matcher = pattern.matcher(text);
//
//        while (matcher.find()) {
//            String matched = matcher.group(1); // Group 1 is the content inside the quotes
//            System.out.println(matched); // Output: part with \"escaped\" quotes and whitespace
//        }
//    }

}
