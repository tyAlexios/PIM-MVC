package PIM.model;

import PIM.controller.PIM;
import PIM.view.VisualPIRView;

import java.util.List;

public class PrintAPI implements API
{

    @Override
    public int verify(String[] cmd)
    {
        if (cmd[1].equals("-a"))
        {
            List<String[]> RepoImg = PIRRepo.RepoImage();
            if (RepoImg.isEmpty())
                return 13;
        }
        else
        {
            String type = cmd[1];
            String name = cmd[2];
            String key = PIR.buildKey(type, name);
            if (PIRRepo.getPIR(key) == null)
                return 11;
        }
        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        String type = para[1];
        String name = para[2];
        String key = PIR.buildKey(type, name);

        return PIRRepo.getPIR(key).getInfo();
    }

    @Override
    public void exe(String[] PIRInfo)
    {
        String key = PIRInfo[0];
        String type = key.substring(1, key.indexOf(']'));
        PIM.output(VisualPIRView.getView(type, PIRInfo));
    }
}
