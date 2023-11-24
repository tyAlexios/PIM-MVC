package PIM.model;

public abstract class PIR
{
    String name;
    String type;
    String primaryKey;

    int numOfAttribute;
    String[] info;

    int[] StrAttrIdx;
    int[] TimeAttrIdx;
    int[] EssentialIdx;
    public static String buildKey(String type, String name)
    {
        return '['+type+']'+name;
    }

    public void initMetaData(String name)
    {
        this.name = name;
        this.primaryKey = '['+this.type+']'+this.name;
        this.info[0] = this.primaryKey;
    }

    public int getNumOfAttr()
    {
        return this.numOfAttribute;
    }

    public String getType() {
        return this.type;
    }

    public String[] getInfo()
    {
        String[] tmpInfo = new String[this.numOfAttribute];
        System.arraycopy(this.info, 0, tmpInfo, 0, this.numOfAttribute);
        return tmpInfo;
    }


    public int[] getEssentialIdx()
    {
        int[] copy = new int[this.numOfAttribute];
        System.arraycopy(this.EssentialIdx, 0, copy, 0, this.EssentialIdx.length);
        return copy;
    }

    public int[] getTimeAttrIdx() {
        int[] copy = new int[this.numOfAttribute];
        System.arraycopy(this.TimeAttrIdx, 0, copy, 0, this.TimeAttrIdx.length);
        return copy;
    }

    public int[] getStrAttrIdx() {
        int[] copy = new int[this.numOfAttribute];
        System.arraycopy(this.StrAttrIdx, 0, copy, 0, this.StrAttrIdx.length);
        return copy;
    }

    public void setInfo(String[] newInfo)
    {
        System.arraycopy(newInfo, 0, this.info, 0, newInfo.length);
    }

}
