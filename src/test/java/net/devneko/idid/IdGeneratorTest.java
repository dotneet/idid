package net.devneko.idid;

import junit.framework.TestCase;
import org.junit.Test;

public class IdGeneratorTest extends TestCase {

    public void testGenerate() {
        IdGenerator generator = new IdGenerator();
        long id1 = generator.generate();

        assertEquals(1L, id1 & 0x0FFF);
        assertEquals(1L, (id1 & 0x3FF000) >> 12);
    }
}
