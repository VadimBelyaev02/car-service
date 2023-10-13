package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.Command;
import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.storage.RepairerStorage;
import lombok.AllArgsConstructor;

import java.io.OutputStream;
import java.util.List;

public class HireRepairer extends NamedCommand {

    public HireRepairer(String name) {
        super(name);
    }

    @Override
    public void execute(List<String> arguments) {

    }

    @Override
    public void printHelp(OutputStream output) {

    }
}
