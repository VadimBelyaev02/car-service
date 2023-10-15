package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.storage.RepairerStorage;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class HireRepairer extends NamedCommand {

    private final RepairerStorage repairerStorage;

    public HireRepairer(String name, RepairerStorage repairerStorage) {
        super(name);
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {

    }

    @Override
    public void printHelp(PrintWriter writer) {

    }
}
