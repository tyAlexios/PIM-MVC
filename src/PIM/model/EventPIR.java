package PIM.model;

public class EventPIR extends PIR
{

    @Override
    public int getNumOfAttr() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String[] getInfo() {
        return new String[0];
    }

    @Override
    public int[] getEssentialIdx() {
        return new int[0];
    }

    @Override
    public void setInfo(String[] newInfo) {

    }
}
