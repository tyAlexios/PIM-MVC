package PIM.controller;

import PIM.model.*;

import java.util.List;

public class PrintProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd) throws Exception
    {
        API printAPI = new PrintAPI();
        if (cmd.length != 2 && cmd.length !=3)
            return 5;
        if (cmd.length == 2 && !cmd[1].equals("-a"))
                return 5;
        return printAPI.verify(cmd);
    }

    @Override
    public void process(String[] cmd)
    {
        PrintAPI printAPI = new PrintAPI();

        if (cmd[1].equals("-a"))
        {
            List<String[]> RepoImg = printAPI.getRepoImg();
            for (String[] PIRInfo : RepoImg)
            {
                printAPI.exe(PIRInfo);
                PIM.output(printAPI.flushPrintCache());
            }
            return;
        }

        String[] PIRInfo = printAPI.init(cmd);
        printAPI.exe(PIRInfo);

        PIM.output(printAPI.flushPrintCache());
    }
}
