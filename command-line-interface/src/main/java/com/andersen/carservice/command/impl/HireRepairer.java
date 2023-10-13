package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.Command;
import com.andersen.carservice.storage.RepairerStorage;
import lombok.AllArgsConstructor;

import java.io.OutputStream;
import java.util.List;

@AllArgsConstructor
public class HireRepairer implements Command {

    private final RepairerStorage repairerStorage;

    @Override
    public void execute(List<String> arguments) {

    }

    @Override
    public void printHelp(OutputStream output) {

    }
}
