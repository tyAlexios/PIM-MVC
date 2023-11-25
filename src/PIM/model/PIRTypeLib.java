package PIM.model;

import java.util.HashMap;
import java.util.function.Supplier;

abstract class PIRTypeLib
{
    static final HashMap<String, Supplier<PIR>> typeMap = new HashMap<>();


    static {
        typeMap.put("txt", TxtPIR::new);
        typeMap.put("task", TaskPIR::new);
        typeMap.put("event", EventPIR::new);
        typeMap.put("contact", ContactPIR::new);
    }

    static boolean containPIRType(String type)
    {
        return typeMap.containsKey(type);
    }

    static PIR createPIR(String type)
    {
        Supplier<PIR> pirSupplier = typeMap.get(type);
        if (pirSupplier != null) {
            return pirSupplier.get();
        }
        throw new IllegalArgumentException("Unknown PIR type: " + type);
    }

}

