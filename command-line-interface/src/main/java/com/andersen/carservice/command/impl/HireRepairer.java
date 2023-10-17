package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.service.RepairerService;
import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

public class HireRepairer extends NamedCommand {

    private final RepairerService repairerService;

    public HireRepairer(String name, RepairerService repairerService) {
        super(name);
        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        final UUID id = UuidHelper.generate();
        final String name = arguments.get(1);
        final String email = arguments.get(2);

        Repairer repairer = Repairer.builder()
                .email(email)
                .name(name)
                .id(id)
                .build();

        repairerService.save(repairer);
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command hires one repairer. ");
        writer.println("The first argument is a name, the second is a status, the third is an email. ");
        writer.println("Format: " + name + " <name> <email>");
        writer.println("Example: " + name + " Vadim active vadimbelav002@gmail.com");
        writer.println("Notes: status can be written in any case. Status can be 'Active' or 'Inactive'. ");
    }
}
