package PIM.controller;


import PIM.view.HelpView;

public class ManProcess implements OperationProcess
{

    @Override
    public int verify(String[] cmd) throws Exception
    {
        if (cmd.length != 2)
            return 23;
        String op = cmd[1];
        if (!OperationLib.containOperation(op))
            return 1;
        return 0;
    }

    @Override
    public void process(String[] cmd) throws Exception
    {
        String type = cmd[1];
        PIM.output(HelpView.getView(type));
    }
}
