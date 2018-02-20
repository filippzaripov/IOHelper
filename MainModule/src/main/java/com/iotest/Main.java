package com.iotest;

import interfaces.IOHelper;

import java.io.*;
import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IOHelper ioHelper = new IOHelperImpl();

    }

}
