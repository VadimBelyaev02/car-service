package com.andersen.carservice;

import com.andersen.carservice.storage.impl.OrderStorageImpl;
import com.andersen.carservice.storage.impl.RepairerStorageImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Application {

    private final CommandExecutor commandExecutor = new CommandExecutor(
            new PrintWriter(System.out),
            new BufferedReader(new InputStreamReader(System.in)),
            OrderStorageImpl.getInstance(),
            RepairerStorageImpl.getInstance()
    );
    public static void main(String[] args) {

    }
}
