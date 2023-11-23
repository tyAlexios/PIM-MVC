package PIM.controller;

import java.util.Objects;

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
        output(SystemView.getView(SystemView.ViewPage.Navigate));
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

}

/**
 new txt-name
 new task-name
 new event-name
 new contact-name

 del type-name

 modify type-name

 search -s 'string'
 search -t >1130
 search -s 'string' && -t>1130
 search -s 'string' && (-t <1130 || -t >1300) || ((-t == 1000 && -s 'sdfad') || )
 -s 'string'

 function search(String[] cmd)
 find '(' ')' pairs
 search( in"()" )



 print type name

 load name.pim
 store name.pim
 */
