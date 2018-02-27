package unitTests;

import com.fujitsu.fs.iohelper.impl.IOHelperImpl;
import com.fujitsu.fs.iohelper.IOHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class IOHelperImplTest extends Assert {
    IOHelper ioHelper = new IOHelperImpl();
    private String testString = "A"+System.lineSeparator()+"test string";

    @Test
    public void testCopyStream() throws IOException {
        File testOut = File.createTempFile("testOut", null, new File("C:/test/"));
        long bytes = 0;
        OutputStream outputStream = new FileOutputStream(testOut);
        bytes = ioHelper.copy(ioHelper.createInputStream(testString), outputStream);
        outputStream.close();
        testOut.delete();
        assertNotEquals(0, bytes);
    }

    @Test
    public void testCopyFile() throws IOException {
        long bytes = 0;
        File testIn = File.createTempFile("testIn", null, new File("C:/test/"));
        File testOut = File.createTempFile("testOut", null, new File("C:/test/"));
        ioHelper.writeFile(testIn, testString, null, false);
        bytes = ioHelper.copy(testIn, testOut);
        testIn.delete();
        testOut.delete();
        assertNotEquals(0, bytes);
    }

    @Test
    public void testReadFile() throws IOException {
        File testIn = File.createTempFile("testIn", null, new File("C:/test/"));
        ioHelper.writeFile(testIn, testString, null, false);
        String result = ioHelper.readFile(testIn).trim();
        testIn.delete();
        assertEquals(testString, result);
    }

    @Test
    public void testReadFileWithEncoding() throws IOException {
        File testIn = File.createTempFile("C:/test/", "testIn");
        ioHelper.writeFile(testIn, testString, null, false);
        assertEquals(testString, ioHelper.readFile(testIn, "UTF-8").trim());
        testIn.delete();
    }

    @Test
    public void testWriteFile() throws IOException {
        File testOut = File.createTempFile("C:/test/", "testOut");
        ioHelper.writeFile(testOut, testString, "UTF-8", false);
        assertEquals(testString, ioHelper.readFile(testOut).trim());
        testOut.delete();
    }

    @Test
    public void testWriteFileAppend() throws IOException {
        File testOut = File.createTempFile("C:/test/", "testOut");
        ioHelper.writeFile(testOut, testString, "UTF-8", false);
        ioHelper.writeFile(testOut, testString, "UTF-8", true);
        StringBuilder finalString = new StringBuilder("");
        finalString.insert(0, ioHelper.readFile(testOut).trim());
        testOut.delete();
        assertEquals(testString + testString, finalString.toString());
    }

    @Test
    public void testCreateInputStream() throws IOException {
        try (InputStream in = ioHelper.createInputStream(testString)) {
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
