package PIM.controller;

import PIM.model.*;
import PIM.view.SystemView;

public class LoadProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd) throws Exception
    {
        if (cmd.length != 2)
            return 22;

        String loadPath = cmd[1];
        if (!loadPath.endsWith(".pim"))
            return 19;

        API loadAPI = new LoadAPI();
        return loadAPI.verify(cmd);

    }

    @Override
    public void process(String[] cmd) throws Exception {

        API loadAPI = new LoadAPI();
        String[] para = loadAPI.init(cmd);
        loadAPI.exe(para);
        PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), "load"));

    }
}
