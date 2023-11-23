package PIM.model;

import java.util.Objects;

public class ModifyAPI implements API
{
    private PIR curPIR;
    @Override
    public int verify(String[] para)
    {
        String type = para[1];
        String name = para[2];
        String key = PIR.buildKey(type, name);

        if (!PIRTypeLib.containPIRType(type))
            return 10;
        if(PIRRepo.getPIR(key) == null)
            return 11;

        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        String type = para[1];
        String name = para[2];
        String key = PIR.buildKey(type, name);

        curPIR = PIRRepo.getPIR(key);
        return curPIR.getInfo();
    }

    @Override
    public int exe(String[] newInfo)
    {
        curPIR.setInfo(newInfo);
        return 0;
    }

    public int finalCheck(String[] newInfo)
    {
        int[] essentialIdx = curPIR.getEssentialIdx();
        for (int idx : essentialIdx)
        {
            if (newInfo[idx] == null || Objects.equals(newInfo[idx], ""))
            {
                return -1;
            }
        }
        return 0;
    }

}
