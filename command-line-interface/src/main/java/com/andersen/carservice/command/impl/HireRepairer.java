package com.andersen.carservice.command.impl;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.entity.Repairer;
import com.andersen.carservice.exception.AlreadyExistsException;
import com.andersen.carservice.exception.NotFoundException;
import com.andersen.carservice.request.RepairerRequest;
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
        if (arguments.size() < 3) {
            writer.println("Not enough parameters");
            return;
        }
        final String name = arguments.get(1);
        final String email = arguments.get(2).trim();
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            writer.println("Incorrect email: \\'" + email + "\\'");
            return;
        }

        RepairerRequest repairer = RepairerRequest.builder()
                .email(email)
                .name(name)
                .build();

        RepairerResponse response = null;
        try {
            response = repairerService.save(repairer);
            writer.println("Repairer saved: " + response);
        } catch (NotFoundException | AlreadyExistsException e) {
            writer.println(e.getMessage());
        }
    }

    @Override
    public void printHelp(PrintWriter writer) {
        writer.println("The command hires one repairer. ");
        writer.println("The first argument is a name, the second is a status, the third is an email. ");
        writer.println("Format: " + name + " <name> <email>");
        writer.println("Example: " + name + " Vadim vadimbelav002@gmail.com");
        writer.println("Notes: status can be written in any case. Status can be 'Active' or 'Inactive'. ");
    }
}
