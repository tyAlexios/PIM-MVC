package PIM.model;

public class ContactPIR extends PIR
{
    // PrimaryKey, *Name, *Address, *MobileNumber

    ContactPIR()
    {
        this.type = "contact";
        this.numOfAttribute = 4;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{0, 1, 2, 3};
        TimeAttrIdx = new int[]{};
        EssentialIdx = new int[]{1, 2, 3};
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
