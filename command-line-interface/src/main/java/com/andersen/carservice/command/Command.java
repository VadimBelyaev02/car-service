package com.andersen.carservice.command;

import java.io.*;
import java.util.List;


public interface Command {

    void execute(List<String> arguments);

    void printHelp(OutputStream output);



//    public static void main(String[] args) {
//        try {
//            overflow();
//        } finally {
//            System.out.println("finally");
//        }
//    }
//
//    public static void overflow() {
//        throw new StackOverflowError();
//    }
}
