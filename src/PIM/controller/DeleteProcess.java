package PIM.controller;

import PIM.model.*;
import PIM.view.*;
public class DeleteProcess implements OperationProcess
{
    @Override
    public int verify(String[] cmd)
    {

        if (cmd.length != 3)
            return 6;

        API deleteAPI = new DeleteAPI();
        return deleteAPI.verify(cmd);

    }

    @Override
    public void process(String[] cmd)
    {
        String type = cmd[1];

        API deleteAPI = new DeleteAPI();
        String[] PIRInfo = deleteAPI.init(cmd);

        PIM.output(DeleteView.getView(DeleteView.ViewPage.DeleteConfirm, VisualPIRView.getView(type, PIRInfo)));

        String optionStr;
        while (true)
        {
            PIM.output(SystemView.getView(SystemView.ViewPage.IdSelectionPrompt));
            optionStr = PIM.input();
            if (!inputVerify.optionVerify(optionStr, 1))
                PIM.output(ErrorRepo.getError(2));
            else
                break;
        }

        int option = optionStr.charAt(0) - '0';
        if (option == 0)
        {
            PIM.output(DeleteView.getView(DeleteView.ViewPage.DeleteCancel));
            return;
        }

        deleteAPI.exe(PIRInfo);
        PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), "delete"));

    }
}
