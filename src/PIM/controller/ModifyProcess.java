package PIM.controller;

import PIM.model.*;
import PIM.view.*;

public class ModifyProcess implements OperationProcess
{

    @Override
    public int verify(String[] cmd)
    {
        if (cmd.length != 3)
            return 4;
        API modifyAPI = new ModifyAPI();
        return modifyAPI.verify(cmd);
    }

    @Override
    public void process(String[] cmd)
    {
        String op = cmd[0];
        String type = cmd[1];

        API updateAPI;
        if (op.equals("new"))
            updateAPI = new CreateAPI();
        else
            updateAPI = new ModifyAPI();
        String[] PIRInfo = updateAPI.init(cmd);


        PIM.output(UpdateView.getView(op, type));


        while (true)
        {
            PIM.output(SystemView.getView(SystemView.ViewPage.IdSelectionPrompt));
            String optionStr = PIM.input();
            if (!inputVerify.optionVerify(optionStr, PIRInfo.length))
            {
                PIM.output(ErrorRepo.getError(2));
                continue;
            }


            int option = optionStr.charAt(0) - '0';
            if (option == 1)
            {
                PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), "cancel"));
                return;
            }

            if (option == 0)
            {
                PIM.output(UpdateView.getView(UpdateView.ViewPage.ModifyConfirm, VisualPIRView.getView(type, PIRInfo)));

                PIM.output(SystemView.getView(SystemView.ViewPage.IdSelectionPrompt));
                optionStr = PIM.input();
                if (!inputVerify.optionVerify(optionStr, 1))
                {
                    PIM.output(ErrorRepo.getError(2));
                    continue;
                }

                int confirmOption = optionStr.charAt(0) - '0';
                if (confirmOption == 0)
                    continue;

                if ( updateAPI.exe(PIRInfo) == -1)
                {
                    PIM.output( UpdateView.getView(UpdateView.ViewPage.FillEssential) );
                    continue;
                }

                PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), op));
                return;
            }

            int attrIdx = option - 1;
            if (PIRInfo[attrIdx] != null)
                PIM.output( UpdateView.getView(UpdateView.ViewPage.PreVersion) );

            PIM.output(SystemView.getView(SystemView.ViewPage.InputPrompt));
            PIRInfo[attrIdx] = PIM.input();
        }
    }
}
