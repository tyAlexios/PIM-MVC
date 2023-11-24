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


}
