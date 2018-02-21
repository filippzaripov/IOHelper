import com.iotest.IOHelperImpl;
import interfaces.IOHelper;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertNotEquals;

public class IOHelperImplTest extends TestCase {
    IOHelper ioHelper = new IOHelperImpl();
    private String testString = "A";

    @Test
    public void testCopyStream() throws IOException {
        File testOut = File.createTempFile("testOut", null, new File("C:/test/"));
        try (InputStream in = ioHelper.createInputStream(testString);
             OutputStream out = new FileOutputStream(testOut)) {
            long bytes = 0;
            bytes = ioHelper.copy(in, out);
            testOut.delete();
            assertNotEquals(0, bytes);
        }

    }

    @Test
    public void testCopyFile() throws IOException {
        File testIn = File.createTempFile("testIn", null, new File("C:/test/"));
        File testOut = File.createTempFile("testOut", null, new File("C:/test/"));
        ioHelper.writeFile(testIn, testString, null, false);
        long bytes = ioHelper.copy(testIn, testOut);
        assertNotEquals(0, bytes);
        testIn.delete();
        testOut.delete();
    }

    @Test
    public void testReadFile() throws IOException {
        File testIn = File.createTempFile("testIn", null, new File("C:/test/"));
        ioHelper.writeFile(testIn, testString, null, false);
        assertEquals(testString, ioHelper.readFile(testIn));
        testIn.delete();
    }

    @Test
    public void testReadFileWithEncoding() throws IOException {
        File testIn = File.createTempFile("C:/test/", "testIn");
        ioHelper.writeFile(testIn, testString, null, false);
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
    public void testWriteFileAppend() throws IOException {
        File testOut = File.createTempFile("C:/test/", "testOut");
        ioHelper.writeFile(testOut, testString, "UTF-8", false);
        ioHelper.writeFile(testOut, testString, "UTF-8", true);
        StringBuilder finalString = new StringBuilder("");
        finalString.insert(0, ioHelper.readFile(testOut));
        testOut.delete();
        assertEquals(testString+testString, finalString.toString());
    }

    @Test
    public void testCreateInputStream() throws IOException {
       try(InputStream in = ioHelper.createInputStream(testString)){
           int c;
           String result = null;
           while ((c = in.read()) != -1) {
               result += c;
           }
           assertNotNull(result);
       }
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
