package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.response.RepairerResponse;
import com.andersen.carservice.service.impl.RepairerServiceImpl;

import java.io.PrintWriter;
import java.util.List;

public class HireRepairer extends NamedCommand {

    private final RepairerServiceImpl repairerService;

    public HireRepairer(String name, RepairerServiceImpl repairerService) {
        super(name);
        this.repairerService = repairerService;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        if (arguments.size() < 2) {
            writer.println();
            return;
        }
        final String name = arguments.get(1);
        final String email = arguments.get(2);
        if (!email.matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$\n")) {
            writer.println();
            return;
        }

        Repairer repairer = Repairer.builder()
                .email(email)
                .name(name)
                .build();

        RepairerResponse response = repairerService.save(repairer);
        writer.println("Repairer saved: " + response);
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
