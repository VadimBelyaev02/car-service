package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.service.RepairerService;
import com.andersen.carservice.util.UuidHelper;
import com.andersen.carservice.util.constants.RepairerUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class FireRepairer extends NamedCommand {

   private final RepairerService repairerService;

    public FireRepairer(String name, RepairerService repairerService) {
        super(name);

        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        if (!UuidHelper.isParsable(arguments.get(1))) {
            writer.println();
            return;
        }
        UUID repairerId = UUID.fromString(arguments.get(1));

        try {
            repairerService.delete(repairerId);
        } catch (NotFoundException e) {
            writer.write(e.getMessage());
        }

    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command fires one repairer. ");
        writer.println("The first and the only one argument is the repairer's id. ");
        writer.println("Format: " + name + " <repairer-id>");
        writer.println("Example: " + name + " c7365c9e-3cf5-490f-9c85-38e936f758e6");

    }
}
