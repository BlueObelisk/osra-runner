package uk.ac.cam.ch.wwmm.osra;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author sea36
 */
public class OsraRunnerTest {

    @Test
    public void testOsra() throws Exception {
        OsraRunner osra = Osra.getOsraRunner();
        File input = new File("target/test-classes/scheme.gif");
        List<String> output = osra.run(input, "-f", "sdf");
        assertEquals(" 18 20  0  0  0  0  0  0  0  0999 V2000", output.get(3));
    }


}
