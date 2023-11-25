package PIM.controller;

import PIM.model.*;
import PIM.view.SystemView;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StoreProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd) throws Exception
    {
        if (cmd.length != 2 && cmd.length != 3)
            return 9;

        String name = cmd[1];
        if (!name.endsWith(".pim"))
            return 18;

        if (cmd.length == 3)
        {
            Path saveDir = Paths.get(cmd[2]);
            if ( ! (Files.exists(saveDir) && Files.isDirectory(saveDir)) )
                return 19;
        }

        API storeAPI = new StoreAPI();
        return storeAPI.verify(cmd);

    }

    @Override
    public void process(String[] cmd) throws Exception
    {
        API storeAPI = new StoreAPI();
        String savePath = storeAPI.init(cmd)[0];
        storeAPI.exe(null);
        PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), String.format("store PIM to %s", savePath)));
    }
}
