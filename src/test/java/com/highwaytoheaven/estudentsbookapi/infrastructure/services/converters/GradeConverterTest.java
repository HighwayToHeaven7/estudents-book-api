package com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeConverterTest {

    @Test
    void convertValueToType() {
        GradeConverter conv = new GradeConverter();
        assertEquals(0.70, conv.convertValueToType("70%", GradeType.PERCENTAGE).get());
        assertEquals(0.90, conv.convertValueToType("+5", GradeType.NUMERIC).get());
        assertEquals(0.90, conv.convertValueToType("+bardzo dobry", GradeType.LETTER).get());
        assertNotEquals(0.69, conv.convertValueToType("40%", GradeType.PERCENTAGE).get());
        assertNotEquals(0.75, conv.convertValueToType("dobry", GradeType.LETTER).get());
        assertNotEquals(0.87, conv.convertValueToType("+3", GradeType.NUMERIC).get());
    }
}