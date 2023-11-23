package PIM.model;

public class EventPIR extends PIR
{
    // PrimaryKey, *StartingTime, *AlarmTime, Description

    EventPIR()
    {
        this.type = "event";
        this.numOfAttribute = 4;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{3};
        TimeAttrIdx = new int[]{0, 1, 2};
        EssentialIdx = new int[]{1, 2};
    }

    @Override
    public int getNumOfAttr()
    {
        return this.numOfAttribute;
    }
    @Override
    public String getType() {
        return this.type;
    }
    @Override
    public String[] getInfo()
    {
        String[] copy = new String[this.numOfAttribute];
        System.arraycopy(this.info, 0, copy, 0, this.info.length);
        return copy;
    }
    @Override
    public int[] getEssentialIdx()
    {
        int[] copy = new int[this.numOfAttribute];
        System.arraycopy(this.EssentialIdx, 0, copy, 0, this.EssentialIdx.length);
        return copy;
    }
    @Override
    public void setInfo(String[] newInfo)
    {
        System.arraycopy(newInfo, 0, this.info, 0, newInfo.length);
    }
}
