package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.response.RepairerResponse;
import com.andersen.carservice.service.impl.RepairerServiceImpl;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class ListRepairers extends NamedCommand {

    private final RepairerServiceImpl repairerService;

    public ListRepairers(String name, RepairerServiceImpl repairerService) {
        super(name);
        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        Comparator<RepairerResponse> comparator = Comparator
                .comparing(RepairerResponse::getId)
                .thenComparing(RepairerResponse::getName)
                .thenComparing(RepairerResponse::getStatus);

        List<RepairerResponse> repairers = repairerService.getAll().stream()
                .sorted(comparator)
                .toList();
        for (int i = 0; i < repairers.size(); i++) {
            writer.println((i + 1) + ") " + repairers.get(i));
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows all repairers. It doesn't have any arguments");
        writer.println("Example: " + name);
    }
}
