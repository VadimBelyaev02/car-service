package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.entity.enums.RepairerStatus;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.UuidHelper;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.UUID;

public class HireRepairer extends NamedCommand {

    private final RepairerStorage repairerStorage;

    public HireRepairer(String name, RepairerStorage repairerStorage) {
        super(name);
        this.repairerStorage = repairerStorage;
    }

    @Override
    protected void runCommand(List<String> arguments, PrintWriter writer) {
        int i = 1;
        final UUID id = UuidHelper.generate();
        final String name = arguments.get(i++);
        final RepairerStatus status = RepairerStatus.valueOf(arguments.get(i++).toUpperCase());
        final String email = arguments.get(i);

        Repairer repairer = Repairer.builder()
                .status(status)
                .email(email)
                .name(name)
                .id(id)
                .build();

        Repairer savedRepairer = repairerStorage.save(repairer);
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command hires one repairer. ");
        writer.println("The first argument is a name, the second is a status, the third is an email. ");
        writer.println("Format: " + name + " <name> <status> <email>");
        writer.println("Example: " + name + " Vadim active vadimbelav002@gmail.com");
        writer.println("Notes: status can be written in any case. Status can be 'Active' or 'Inactive'. ");
    }
}
