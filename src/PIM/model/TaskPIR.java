package PIM.model;

public class TaskPIR extends PIR
{
    // PrimaryKey, *Deadline, Description

    TaskPIR()
    {
        this.type = "task";
        this.numOfAttribute = 3;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{2};
        TimeAttrIdx = new int[]{0, 1};
        EssentialIdx = new int[]{1};
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
        String[] tmpInfo = new String[this.numOfAttribute];
        System.arraycopy(this.info, 0, tmpInfo, 0, this.numOfAttribute);
        return tmpInfo;
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
