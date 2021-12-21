package com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeConverterTest {

    private final GradeConverter converter;

    GradeConverterTest() {
        this.converter = new GradeConverter();
    }

    @Test
    void convertNumeric() {
        assertEquals(0.95, converter.convertValueToType("-6", GradeType.NUMERIC).get());
        assertEquals(0.55, converter.convertValueToType("3", GradeType.NUMERIC).get());
        assertNotEquals(0.0, converter.convertValueToType("+1", GradeType.NUMERIC).get());
        assertNotEquals(0.45, converter.convertValueToType("4", GradeType.NUMERIC).get());
    }

    @Test
    void convertLetter() {
        assertEquals(0.30, converter.convertValueToType("+niedostateczny", GradeType.LETTER).get());
        assertEquals(0.80, converter.convertValueToType("-bardzo dobry", GradeType.LETTER).get());
        assertNotEquals(0.0, converter.convertValueToType("dobry", GradeType.LETTER).get());
        assertNotEquals(0.80, converter.convertValueToType("+dopuszczający", GradeType.LETTER).get());
    }

    @Test
    void convertPercentage() {
        assertEquals(0.70, converter.convertValueToType("70%", GradeType.PERCENTAGE).get());
        assertEquals(1, converter.convertValueToType("100%", GradeType.PERCENTAGE).get());
        assertNotEquals(0.69, converter.convertValueToType("40%", GradeType.PERCENTAGE).get());
        assertNotEquals(0.69, converter.convertValueToType("70%", GradeType.PERCENTAGE).get());
    }

    @Test
    void exceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.convertValueToType("$", GradeType.LETTER);
        });

        String expectedMessage = "Not defined grade";

        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}