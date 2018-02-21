package com.iotest;

import interfaces.IOHelper;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IOHelper ioHelper = new IOHelperImpl();
        StringBuilder testString = new StringBuilder("");
        testString.append("Hello, my friend\n" +
                "It's just a test string\n" +
                "with perenos");
        ioHelper.writeFile(new File("C:/test/testOut.txt"), testString.toString(), null, false );
        ioHelper.writeFile(new File("C:/test/testOut.txt"), testString.toString(), null, true );


    }

}
