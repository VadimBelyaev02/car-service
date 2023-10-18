package com.andersen.carservice.command.impl;

import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.service.impl.RepairerServiceImpl;
import com.andersen.carservice.util.UuidUtil;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import static com.andersen.carservice.util.constants.GeneralConstants.UUID_IS_NOT_PARSABLE;

public class FireRepairer extends NamedCommand {

   private final RepairerServiceImpl repairerService;

    public FireRepairer(String name, RepairerServiceImpl repairerService) {
        super(name);

        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        if (UuidUtil.isNotParsable(arguments.get(1))) {
            writer.println(UUID_IS_NOT_PARSABLE);
            return;
        }
        UUID repairerId = UUID.fromString(arguments.get(1));

        try {
            repairerService.deleteById(repairerId);
            writer.println("Repairer with id = " + repairerId + " was fired");
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
