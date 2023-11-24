package PIM.model;

public class EventPIR extends PIR
{
    // PrimaryKey, *StartingTime, *AlarmTime, Description

    EventPIR()
    {
        this.type = "event";
        this.numOfAttribute = 4;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{0, 3};
        TimeAttrIdx = new int[]{1, 2};
        EssentialIdx = new int[]{1, 2};
    }

}
