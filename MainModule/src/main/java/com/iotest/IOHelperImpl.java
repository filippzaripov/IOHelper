package com.iotest;

import interfaces.IOHelper;

import java.io.*;
import java.nio.charset.Charset;

public class IOHelperImpl implements IOHelper {

    @Override
    public long copy(InputStream in, OutputStream out) throws IOException {
        long amount = 0;
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
            amount++;
        }
        return amount;
    }

    @Override
    public long copy(File source, File target) throws IOException {
        try(InputStream inputStream = new FileInputStream(source);
        OutputStream outputStream = new FileOutputStream(target)){
            return copy(inputStream, outputStream);
        }
    }


    @Override
    public String readFile(File file, String encoding) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            StringBuilder result = new StringBuilder("");
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                result.append(currentLine);
            }
            return result.toString();
        }

    }

    @Override
    public String readFile(File file) throws IOException {
        return readFile(file, Charset.defaultCharset().toString());
    }

    @Override
    public void writeFile(File file, String content, String encoding, boolean append) throws IOException {
        OutputStreamWriter outputStreamWriter;
        if (encoding == null) {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, append), Charset.defaultCharset());
        } else {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, append), encoding);
        }
        try (Writer writer = outputStreamWriter) {
            if (append) {
                writer.write(content);
            } else {
                writer.write(content);
            }
        }
    }

    @Override
    public InputStream createInputStream(String source) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(source.getBytes());
        return inputStream;
    }

    @Override
    public InputStream createInputStream(String source, String encoding) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(source.getBytes(encoding));
        return inputStream;
    }
}
