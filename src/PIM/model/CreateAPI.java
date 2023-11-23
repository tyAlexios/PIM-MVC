package PIM.model;

import java.util.Objects;

public class CreateAPI implements API
{
    private PIR newPIR;

    @Override
    public int verify(String[] para)
    {
        String type = para[1];
        String name = para[2];
        String key = PIR.buildKey(type, name);

        if (!PIRTypeLib.containPIRType(type))
            return 10;
        if(PIRRepo.getPIR(key) != null)
            return 12;

        return 0;
    }

    @Override
    public String[] init(String[] para)
    {
        String type = para[1];
        String name = para[2];
        newPIR= PIRTypeLib.createPIR(type);
        newPIR.initMetaData(name);
        return newPIR.getInfo();
    }

    @Override
    public int exe(String[] newInfo)
    {
        int[] essentialIdx = newPIR.getEssentialIdx();
        for (int idx : essentialIdx)
        {
            if (newInfo[idx] == null || Objects.equals(newInfo[idx], ""))
            {
                return -1;
            }
        }
        newPIR.setInfo(newInfo);
        PIRRepo.insertPIR(newInfo[0], newPIR);
        return 0;
    }

}
