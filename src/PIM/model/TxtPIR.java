package PIM.model;

class TxtPIR extends PIR
{
    // PrimaryKey, *txt

    TxtPIR()
    {
        this.type = "txt";
        this.numOfAttribute = 2;
        this.info = new String[numOfAttribute];

        StrAttrIdx = new int[]{0, 1};
        TimeAttrIdx = new int[]{};
        EssentialIdx = new int[]{1};
    }

}
