import com.iotest.IOHelperImpl;
import interfaces.IOHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertNotEquals;

public class IOHelperImplTest extends TestCase {
    IOHelper ioHelper = new IOHelperImpl();
    private String testString = "Hello, I am a test string";

    @Test
    public void testCopyStream() throws IOException {
        try (InputStream in = new StringBufferInputStream(testString);
             OutputStream out = new FileOutputStream(new File("C:/test/testout.txt"))) {
            long bytes = 0;
            bytes = ioHelper.copy(in, out);
            assertNotEquals(0, bytes);
        }
    }

    @Test
    public void testCopyFile() throws IOException {
        File testIn = File.createTempFile("C:/test/", "testIn");
        File testOut = File.createTempFile("C:/test/", "testOut");
        try (FileWriter writer = new FileWriter(testIn)) {
            writer.write(testString);
            long bytes = ioHelper.copy(testIn, testOut);
            assertEquals(0, bytes);
        }
        testIn.delete();
        testOut.delete();
    }

    @Test
    public void testReadFile() throws IOException {
        File testIn = File.createTempFile("C:/test/", "testIn");
        try (FileWriter writer = new FileWriter(testIn)) {
            writer.write(testString);
        }
        assertEquals(testString, ioHelper.readFile(testIn));
        testIn.delete();
    }

    @Test
    public void testReadFileWithEncoding() throws IOException {
        File testIn = File.createTempFile("C:/test/", "testIn");
        try (FileWriter writer = new FileWriter(testIn)) {
            writer.write(testString);
        }
        assertEquals(testString, ioHelper.readFile(testIn, "UTF-8"));
        testIn.delete();
    }

    @Test
    public void testWriteFile() throws IOException {
        File testOut = File.createTempFile("C:/test/", "testOut");
        ioHelper.writeFile(testOut, testString, "UTF-8", false);
        assertEquals(testString, ioHelper.readFile(testOut));
        testOut.delete();
    }

    @Test
    public void testCreateInputStream() throws IOException {
        InputStream in = ioHelper.createInputStream(testString);
        int c;
        String result = null;
        while ((c = in.read()) != -1) {
            result += c;
        }
        assertNotNull(result);
    }

    @Test
    public void testCreateInputStreamEncoding() throws IOException {
        InputStream in = ioHelper.createInputStream(testString, "UTF-8");
        int c;
        String result = null;
        while ((c = in.read()) != -1) {
            result += c;
        }
        assertNotNull(result);
    }
}
