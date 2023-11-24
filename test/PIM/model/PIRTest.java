package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PIRTest {
    private final String PIRNameForTesting = "test";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + PIRNameForTesting;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + PIRNameForTesting;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + PIRNameForTesting;
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + PIRNameForTesting;
    private final int PIRTextNumOfAttr = 2;
    private final int PIREventNumOfAttr = 4;
    private final int PIRContactNumOfAttr = 4;
    private final int PIRTaskNumOfAttr = 3;
    private final String PIRTestDescription = "Test.";
    private final String PIRTestStartingTime = "2023-11-23-09:00";
    private final String PIRTestAlarmTime = "2023-11-23-10:00";
    private final String PIRTestName = "Test User";
    private final String PIRTestAddress = "Test Address";
    private final String PIRTestMobileNumber = "12345678";
    private final String PIRTestDeadline = "2023-11-23-11:00";
    private final int[] txtEssentialIdx = new int[]{1};
    private final int[] eventEssentialIdx = new int[]{1, 2};
    private final int[] contactEssentialIdx = new int[]{1, 2, 3};
    private final int[] taskEssentialIdx = new int[]{1};

    @Test
    public void buildKey() {
        assertEquals(PIRTextPrimaryKey, PIR.buildKey(PIRTextType, PIRNameForTesting));
        assertEquals(PIREventPrimaryKey, PIR.buildKey(PIREventType, PIRNameForTesting));
        assertEquals(PIRContactPrimaryKey, PIR.buildKey(PIRContactType, PIRNameForTesting));
        assertEquals(PIRTaskPrimaryKey, PIR.buildKey(PIRTaskType, PIRNameForTesting));
    }

    @Test
    public void initMetaData() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        assertEquals(PIRTextPrimaryKey, txt.primaryKey);
        assertEquals(PIRTextType, txt.type);
        assertEquals(PIRNameForTesting, txt.name);
        assertEquals(PIRTextPrimaryKey, txt.info[0]);

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        assertEquals(PIREventPrimaryKey, event.primaryKey);
        assertEquals(PIREventType, event.type);
        assertEquals(PIRNameForTesting, event.name);
        assertEquals(PIREventPrimaryKey, event.info[0]);

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        assertEquals(PIRContactPrimaryKey, contact.primaryKey);
        assertEquals(PIRContactType, contact.type);
        assertEquals(PIRNameForTesting, contact.name);
        assertEquals(PIRContactPrimaryKey, contact.info[0]);

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        assertEquals(PIRTaskPrimaryKey, task.primaryKey);
        assertEquals(PIRTaskType, task.type);
        assertEquals(PIRNameForTesting, task.name);
        assertEquals(PIRTaskPrimaryKey, task.info[0]);
    }

    @Test
    public void getNumOfAttr() {
        PIR txt = new TxtPIR();
        assertEquals(PIRTextNumOfAttr, txt.getNumOfAttr());

        PIR event = new EventPIR();
        assertEquals(PIREventNumOfAttr, event.getNumOfAttr());

        PIR contact = new ContactPIR();
        assertEquals(PIRContactNumOfAttr, contact.getNumOfAttr());

        PIR task = new TaskPIR();
        assertEquals(PIRTaskNumOfAttr, task.getNumOfAttr());
    }

    @Test
    public void getInfo() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        txt.setInfo(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(PIRTextPrimaryKey, txt.getInfo()[0]);
        assertEquals(PIRTestDescription, txt.getInfo()[1]);

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        event.setInfo(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(PIREventPrimaryKey, event.getInfo()[0]);
        assertEquals(PIRTestStartingTime, event.getInfo()[1]);
        assertEquals(PIRTestAlarmTime, event.getInfo()[2]);
        assertEquals(PIRTestDescription, event.getInfo()[3]);

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        contact.setInfo(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(PIRContactPrimaryKey, contact.getInfo()[0]);
        assertEquals(PIRTestName, contact.getInfo()[1]);
        assertEquals(PIRTestAddress, contact.getInfo()[2]);
        assertEquals(PIRTestMobileNumber, contact.getInfo()[3]);

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        task.setInfo(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(PIRTaskPrimaryKey, task.getInfo()[0]);
        assertEquals(PIRTestDeadline, task.getInfo()[1]);
        assertEquals(PIRTestDescription, task.getInfo()[2]);
    }

    @Test
    public void getEssentialIdx() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        txt.setInfo(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(txtEssentialIdx[0], txt.getEssentialIdx()[0]);

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        event.setInfo(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(eventEssentialIdx[0], event.getEssentialIdx()[0]);
        assertEquals(eventEssentialIdx[1], event.getEssentialIdx()[1]);

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        contact.setInfo(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(contactEssentialIdx[0], contact.getEssentialIdx()[0]);
        assertEquals(contactEssentialIdx[1], contact.getEssentialIdx()[1]);
        assertEquals(contactEssentialIdx[2], contact.getEssentialIdx()[2]);

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        task.setInfo(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(taskEssentialIdx[0], task.getEssentialIdx()[0]);
    }

    @Test
    public void setInfo() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        txt.setInfo(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertEquals(PIRTextPrimaryKey, txt.getInfo()[0]);
        assertEquals(PIRTestDescription, txt.getInfo()[1]);

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        event.setInfo(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertEquals(PIREventPrimaryKey, event.getInfo()[0]);
        assertEquals(PIRTestStartingTime, event.getInfo()[1]);
        assertEquals(PIRTestAlarmTime, event.getInfo()[2]);
        assertEquals(PIRTestDescription, event.getInfo()[3]);

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        contact.setInfo(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertEquals(PIRContactPrimaryKey, contact.getInfo()[0]);
        assertEquals(PIRTestName, contact.getInfo()[1]);
        assertEquals(PIRTestAddress, contact.getInfo()[2]);
        assertEquals(PIRTestMobileNumber, contact.getInfo()[3]);

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        task.setInfo(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertEquals(PIRTaskPrimaryKey, task.getInfo()[0]);
        assertEquals(PIRTestDeadline, task.getInfo()[1]);
        assertEquals(PIRTestDescription, task.getInfo()[2]);
    }

    @Test
    public void getType() {
        PIR txt = new TxtPIR();
        assertEquals(PIRTextType, txt.getType());

        PIR event = new EventPIR();
        assertEquals(PIREventType, event.getType());

        PIR contact = new ContactPIR();
        assertEquals(PIRContactType, contact.getType());

        PIR task = new TaskPIR();
        assertEquals(PIRTaskType, task.getType());
    }

    @Test
    public void getTimeAttrIdx() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        txt.setInfo(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new int[]{}, txt.getTimeAttrIdx());

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        event.setInfo(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new int[]{1, 2}, event.getTimeAttrIdx());

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        contact.setInfo(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new int[]{}, contact.getTimeAttrIdx());

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        task.setInfo(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new int[]{1}, task.getTimeAttrIdx());
    }

    @Test
    public void getStrAttrIdx() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        txt.setInfo(new String[]{PIRTextPrimaryKey, PIRTestDescription});
        assertArrayEquals(new int[]{0,1}, txt.getStrAttrIdx());

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        event.setInfo(new String[]{PIREventPrimaryKey, PIRTestStartingTime, PIRTestAlarmTime, PIRTestDescription});
        assertArrayEquals(new int[]{0,3}, event.getStrAttrIdx());

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        contact.setInfo(new String[]{PIRContactPrimaryKey, PIRTestName, PIRTestAddress, PIRTestMobileNumber});
        assertArrayEquals(new int[]{0, 1, 2, 3}, contact.getStrAttrIdx());

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        task.setInfo(new String[]{PIRTaskPrimaryKey, PIRTestDeadline, PIRTestDescription});
        assertArrayEquals(new int[]{0, 2}, task.getStrAttrIdx());
    }
}