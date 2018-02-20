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
        in.close();
        out.close();
        return amount;
    }

    @Override
    public long copy(File source, File target) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            int c;
            long amount = 0;
            if (target.canWrite()) {
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                    amount++;
                }
            } else {
                return -1;
            }
            return amount;
        }
    }

    @Override
    public String readFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()))) {
            String result = "";
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                result += currentLine;
            }
            return result;
        }
    }

    @Override
    public String readFile(File file, String encoding) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))) {
            String result = "";
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                result += currentLine;
            }
            return result;
        }

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
