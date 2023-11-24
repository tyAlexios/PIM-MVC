package PIM.controller;

import PIM.model.*;
import PIM.view.LoadView;
import PIM.view.SystemView;
import PIM.view.VisualPIRView;

import java.util.List;

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

        LoadAPI loadAPI = new LoadAPI();
        loadAPI.init(cmd);
        List<String[]> conflictPIRs = loadAPI.getConflictPIRs();
        List<String[]> originalPIRs = loadAPI.getOriginalPIRs();
        if (conflictPIRs != null)
        {
            for (int i = 0; i< conflictPIRs.size(); ++i)
            {
                String[] conflictPIRInfo = conflictPIRs.get(i);
                String[] originalPIRInfo = originalPIRs.get(i);

                String key = conflictPIRInfo[0];
                String type = key.substring(1, key.indexOf(']'));

                PIM.output(LoadView.getView(LoadView.ViewPage.ConflictDecision, VisualPIRView.getView(type, originalPIRInfo),VisualPIRView.getView(type, conflictPIRInfo)) );

                String optionStr;
                while (true)
                {
                    PIM.output(SystemView.getView(SystemView.ViewPage.IdSelectionPrompt));
                    optionStr = PIM.input();
                    if (inputVerify.optionVerify(optionStr, 1))
                        PIM.output(ErrorRepo.getError(2));
                    else
                        break;
                }

                int option = optionStr.charAt(0) - '0';
                if (option == 0)
                {
                    PIM.output(LoadView.getView(LoadView.ViewPage.NotOverwrite));
                    loadAPI.setOverwriteKeys(key);
                }
                else
                {
                    PIM.output(LoadView.getView(LoadView.ViewPage.YesOverwrite));
                }
            }
        }
        loadAPI.exe(null);
        PIM.output(String.format(SystemView.getView(SystemView.ViewPage.Success), "load"));

    }
}
