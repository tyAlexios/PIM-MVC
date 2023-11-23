package PIM.controller;

import PIM.model.API;
import PIM.model.CreateAPI;

public class CreateProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd)
    {
        if (cmd.length != 3)
            return 3;

        API createAPI = new CreateAPI();
        return createAPI.verify(cmd);

    }

    @Override
    public void process(String[] cmd)
    {
        OperationProcess modifyOperation = OperationLib.getOperation("modify");
        modifyOperation.process(cmd);
    }
}
