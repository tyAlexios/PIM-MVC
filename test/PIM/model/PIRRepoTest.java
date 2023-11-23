package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PIRRepoTest {
    private final String PIRNameForTesting = "test";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + PIRNameForTesting;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + PIRNameForTesting;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + PIRNameForTesting;
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + PIRNameForTesting;

    @Test
    public void getPIR() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTextPrimaryKey, txt);
        assertEquals(txt, PIRRepo.getPIR(PIRTextPrimaryKey));

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIREventPrimaryKey, event);
        assertEquals(event, PIRRepo.getPIR(PIREventPrimaryKey));

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRContactPrimaryKey, contact);
        assertEquals(contact, PIRRepo.getPIR(PIRContactPrimaryKey));

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTaskPrimaryKey, task);
        assertEquals(task, PIRRepo.getPIR(PIRTaskPrimaryKey));
    }

    @Test
    public void insertPIR() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTextPrimaryKey, txt);
        assertEquals(txt, PIRRepo.getPIR(PIRTextPrimaryKey));

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIREventPrimaryKey, event);
        assertEquals(event, PIRRepo.getPIR(PIREventPrimaryKey));

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRContactPrimaryKey, contact);
        assertEquals(contact, PIRRepo.getPIR(PIRContactPrimaryKey));

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTaskPrimaryKey, task);
        assertEquals(task, PIRRepo.getPIR(PIRTaskPrimaryKey));
    }

    @Test
    public void deletePIR() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTextPrimaryKey, txt);
        assertEquals(txt, PIRRepo.getPIR(PIRTextPrimaryKey));
        PIRRepo.deletePIR(PIRTextPrimaryKey);
        assertNull(PIRRepo.getPIR(PIRTextPrimaryKey));

        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIREventPrimaryKey, event);
        assertEquals(event, PIRRepo.getPIR(PIREventPrimaryKey));
        PIRRepo.deletePIR(PIREventPrimaryKey);
        assertNull(PIRRepo.getPIR(PIREventPrimaryKey));

        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRContactPrimaryKey, contact);
        assertEquals(contact, PIRRepo.getPIR(PIRContactPrimaryKey));
        PIRRepo.deletePIR(PIRContactPrimaryKey);
        assertNull(PIRRepo.getPIR(PIRContactPrimaryKey));

        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTaskPrimaryKey, task);
        assertEquals(task, PIRRepo.getPIR(PIRTaskPrimaryKey));
        PIRRepo.deletePIR(PIRTaskPrimaryKey);
        assertNull(PIRRepo.getPIR(PIRTaskPrimaryKey));
    }

    @Test
    public void repoImage() {
        PIR txt = new TxtPIR();
        txt.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTextPrimaryKey, txt);
        PIR event = new EventPIR();
        event.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIREventPrimaryKey, event);
        PIR contact = new ContactPIR();
        contact.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRContactPrimaryKey, contact);
        PIR task = new TaskPIR();
        task.initMetaData(PIRNameForTesting);
        PIRRepo.insertPIR(PIRTaskPrimaryKey, task);
        assertEquals(4, PIRRepo.RepoImage().size());
    }
}