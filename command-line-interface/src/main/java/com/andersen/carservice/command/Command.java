package com.andersen.carservice.command;

import java.io.*;
import java.util.List;


public interface Command {

    void execute(List<String> arguments);

    void printHelp(OutputStream output);
}
