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

    public abstract int getNumOfAttr();

    public abstract String getType();

    public abstract String[] getInfo();

    public abstract int[] getEssentialIdx();

    public abstract void setInfo(String[] newInfo);

}
