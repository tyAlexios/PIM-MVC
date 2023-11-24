package PIM.model;

public class TaskPIR extends PIR
{
    // PrimaryKey, *Deadline, Description

    TaskPIR()
    {
        this.type = "task";
        this.numOfAttribute = 3;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{0, 2};
        TimeAttrIdx = new int[]{1};
        EssentialIdx = new int[]{1};
    }

}
