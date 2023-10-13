package com.andersen.carservice;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.command.impl.AssignRepairers;
import com.andersen.carservice.command.impl.CancelOrder;
import com.andersen.carservice.command.impl.CompleteOrder;

import java.util.List;
import java.util.stream.Stream;

public class CommandExecutor {

    private final List<NamedCommand> commands = Stream.of(
            new AssignRepairers("assign-repairers"),
            new CancelOrder("cancel-order"),
            new CompleteOrder("complete-order")
    ).toList();


}
