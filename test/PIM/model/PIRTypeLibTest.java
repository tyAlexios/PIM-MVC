package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PIRTypeLibTest {

    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String UnknownPIRType = "unknown";

    @Test
    public void containPIRType() {
        assertTrue(PIRTypeLib.containPIRType(PIRTextType));
        assertTrue(PIRTypeLib.containPIRType(PIREventType));
        assertTrue(PIRTypeLib.containPIRType(PIRContactType));
        assertTrue(PIRTypeLib.containPIRType(PIRTaskType));
    }

    @Test
    public void createPIR() {
        PIR txt = PIRTypeLib.createPIR(PIRTextType);
        assertEquals(PIRTextType, txt.type);

        PIR event = PIRTypeLib.createPIR(PIREventType);
        assertEquals(PIREventType, event.type);

        PIR contact = PIRTypeLib.createPIR(PIRContactType);
        assertEquals(PIRContactType, contact.type);

        PIR task = PIRTypeLib.createPIR(PIRTaskType);
        assertEquals(PIRTaskType, task.type);

        try {
            PIRTypeLib.createPIR(UnknownPIRType);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Unknown PIR type: " + UnknownPIRType, e.getMessage());
        }
    }
}