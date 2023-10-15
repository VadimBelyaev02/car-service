package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.storage.RepairerStorage;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class ListRepairers extends NamedCommand {

    private final RepairerStorage repairerStorage;

    public ListRepairers(String name, RepairerStorage repairerStorage) {
        super(name);
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        Comparator<Repairer> comparator = Comparator
                .comparing(Repairer::getId)
                .thenComparing(Repairer::getName)
                .thenComparing(Repairer::getStatus);

        List<Repairer> repairers = repairerStorage.findAll().stream()
                .sorted(comparator)
                .toList();
        for (int i = 0; i < repairers.size(); i++) {
            System.out.println((i + 1) + ") " + repairers.get(i));
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command shows all repairers. It doesn't have any arguments");
        writer.println("Example: list-repairers");
    }
}
