package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class Crc16Test {

    @Test
    public void testBuilder() {
        Crc16.Builder builder = Crc16.builder();
        assertNotNull(builder);
    }

    @Test(expected = NullPointerException.class)
    public void testBuilderSetTableNull() {
        Crc16.builder().setTable(null);
    }
}
