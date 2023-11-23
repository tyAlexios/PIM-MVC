package PIM.model;

public class DeleteAPI implements API
{
    PIR curPIR;
    @Override
    public int verify(String[] cmd)
    {
        String type = cmd[1];
        String name = cmd[2];
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
    public void exe(String[] PIRInfo)
    {
        PIRRepo.deletePIR(PIRInfo[0]);
    }
}
