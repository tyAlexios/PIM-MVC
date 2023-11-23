package PIM.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAPITest {
    private final String TestPIRName = "test";
    private final String PIRTextType = "txt";
    private final String PIREventType = "event";
    private final String PIRContactType = "contact";
    private final String PIRTaskType = "task";
    private final String UnknownPIRType = "unknown";
    private final String PIRTextPrimaryKey = '[' + PIRTextType + ']' + TestPIRName;
    private final String PIREventPrimaryKey = '[' + PIREventType + ']' + TestPIRName;
    private final String PIRContactPrimaryKey = '[' + PIRContactType + ']' + TestPIRName;
    private final String PIRTaskPrimaryKey = '[' + PIRTaskType + ']' + TestPIRName;

    @Test
    public void verify() {
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIRTextType, TestPIRName}));
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIREventType, TestPIRName}));
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIRContactType, TestPIRName}));
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIRTaskType, TestPIRName}));
        assertEquals(10, new CreateAPI().verify(new String[]{"create", UnknownPIRType, TestPIRName}));
        assertEquals(12, new CreateAPI().verify(new String[]{"create", PIRContactType, TestPIRName}));
    }

    @Test
    public void init() {
        assertArrayEquals(new String[]{PIRTextPrimaryKey, PIRTextType, TestPIRName}, new CreateAPI().init(new String[]{"create", PIRTextType, TestPIRName}));
        assertArrayEquals(new String[]{PIREventPrimaryKey, PIREventType, TestPIRName}, new CreateAPI().init(new String[]{"create", PIREventType, TestPIRName}));
        assertArrayEquals(new String[]{PIRContactPrimaryKey, PIRContactType, TestPIRName}, new CreateAPI().init(new String[]{"create", PIRContactType, TestPIRName}));
        assertArrayEquals(new String[]{PIRTaskPrimaryKey, PIRTaskType, TestPIRName}, new CreateAPI().init(new String[]{"create", PIRTaskType, TestPIRName}));
    }

    @Test
    public void exe() {
        
    }
}