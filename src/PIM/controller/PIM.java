package PIM.controller;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PIM.view.*;

public class PIM {
    public static Stream stream;

    public static String input() {
        return stream.in();
    }

    public static void output(String outStr) {
        stream.out(outStr);
    }

    private void systemStart() {
        output(SystemView.getView(SystemView.ViewPage.WelcomePage));
        String cmdLine = null;
        while (true) {
            output(SystemView.getView(SystemView.ViewPage.Navigate));
            cmdLine = input();
            if (Objects.equals(cmdLine, "quit")) {
                output(SystemView.getView(SystemView.ViewPage.ByePage));
                return;
            }
            int errno = runCmd(cmdLine);
            if (errno != 0)
                output(ErrorRepo.getError(errno));
        }
    }

    public int runCmd(String cmdLine) {
        String[] cmd = cmdLine.split(" ");
        String op = cmd[0];
        if (!OperationLib.containOperation(op)) {
            return 1;
        } else {
            OperationProcess proc = OperationLib.getOperation(op);
            int errno = proc.verify(cmd);
            if (errno != 0)
                return errno;
            else {
                proc.process(cmd);
                return 0;
            }
        }
    }


    public static void main(String[] args) {
        stream = new ScannerStream();

        PIM pim = new PIM();
        pim.systemStart();

        stream.close();
    }

}

// search ( >11:00 || <2022-12-12-12:12 ) && ( "\" s" || ! "sss" )