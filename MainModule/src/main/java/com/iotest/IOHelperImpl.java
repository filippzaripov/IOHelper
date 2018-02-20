package com.iotest;

import interfaces.IOHelper;

import java.io.*;
import java.nio.charset.Charset;

//TODO: implement IOHelper interface
//TODO: write tests

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
        return copy(new FileInputStream(source), new FileOutputStream(target));
    }


    @Override
    public String readFile(File file, String encoding) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
           StringBuilder result = new StringBuilder("");
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                result.append(currentLine) ;
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
        Charset localEncoding;
        OutputStreamWriter outputStream;
        if (encoding == null) {
            localEncoding = Charset.defaultCharset();
            outputStream = new OutputStreamWriter(new FileOutputStream(file), localEncoding);
        } else {
            outputStream = new OutputStreamWriter(new FileOutputStream(file), encoding);
        }
        try (Writer writer = outputStream) {
            if (append) {
                writer.append(content);
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
