package PIM.model;

abstract class PIR
{
    String name;
    String type;
    String primaryKey;

    int numOfAttribute;
    String[] info;

    int[] StrAttrIdx;
    int[] TimeAttrIdx;
    int[] EssentialIdx;
    static String buildKey(String type, String name)
    {
        return '['+type+']'+name;
    }

    void initMetaData(String name)
    {
        this.name = name;
        this.primaryKey = '['+this.type+']'+this.name;
        this.info[0] = this.primaryKey;
    }

    int getNumOfAttr()
    {
        return this.numOfAttribute;
    }

    String getType() {
        return this.type;
    }

    String[] getInfo()
    {
        String[] tmpInfo = new String[this.numOfAttribute];
        System.arraycopy(this.info, 0, tmpInfo, 0, this.numOfAttribute);
        return tmpInfo;
    }


    int[] getEssentialIdx()
    {
        int[] copy = new int[this.EssentialIdx.length];
        System.arraycopy(this.EssentialIdx, 0, copy, 0, this.EssentialIdx.length);
        return copy;
    }

    int[] getTimeAttrIdx() {
        int[] copy = new int[this.TimeAttrIdx.length];
        System.arraycopy(this.TimeAttrIdx, 0, copy, 0, this.TimeAttrIdx.length);
        return copy;
    }

    int[] getStrAttrIdx() {
        int[] copy = new int[this.StrAttrIdx.length];
        System.arraycopy(this.StrAttrIdx, 0, copy, 0, this.StrAttrIdx.length);
        return copy;
    }

    void setInfo(String[] newInfo)
    {
        System.arraycopy(newInfo, 0, this.info, 0, newInfo.length);
    }

}
