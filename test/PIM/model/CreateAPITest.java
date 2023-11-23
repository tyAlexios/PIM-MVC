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

    @Test
    public void verify() {
        assertEquals(0, new CreateAPI().verify(new String[]{"create", PIRContactType, TestPIRName}));
        assertEquals(10, new CreateAPI().verify(new String[]{"create", UnknownPIRType, TestPIRName}));
        assertEquals(12, new CreateAPI().verify(new String[]{"create", PIRContactType, TestPIRName}));
    }

    @Test
    public void init() {
    }

    @Test
    public void exe() {
    }
}