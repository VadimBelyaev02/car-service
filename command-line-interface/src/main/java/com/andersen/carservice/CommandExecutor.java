package com.andersen.carservice;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.command.impl.*;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.ArgumentParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class CommandExecutor {

    private final PrintWriter writer;
    private final BufferedReader reader;
    private final List<NamedCommand> commands;

    public CommandExecutor(PrintWriter writer, BufferedReader reader, OrderStorage orderStorage, RepairerStorage repairerStorage) {
        this.writer = writer;
        this.reader = reader;
        this.commands = Stream.of(
                new AssignRepairers("assign-repairers", orderStorage, repairerStorage),
                new CancelOrder("cancel-order", orderService),
                new CompleteOrder("complete-order", orderStorage),
                new FireRepairer("fire-repairer", repairerService),
                new HireRepairer("hire-repairer", repairerService),
                new ListOrders("list-orders", orderService),
                new ListRepairers("list-repairers", repairerService),
                new OpenOrder("open-order", orderService),
                new ViewOrderInfo("view-order-info", orderService, repairerService)
        ).toList();
    }

    public void start() {
        while (true) {
            try {
                writer.print("> ");
                writer.flush();
                String userInput = reader.readLine();

                if (Objects.equals(userInput, "exit")) {
                    writer.println("Bye!");
                    return;
                }
                List<String> arguments = ArgumentParser.parse(userInput);
//                boolean isInvalid = true;
//                for (NamedCommand command : commands) {
//                    if (Objects.equals(command.getName(), arguments.get(0))) {
//                        isInvalid = false;
//                        command.execute(arguments, writer);
//                        break;
//                    }
//                }
//                if (isInvalid) {
//                    writer.println("Invalid command");
//                }
                commands.forEach(a -> a.execute(arguments, writer));
            } catch (IOException e) {
                writer.println(e.getMessage());
            }
        }
    }
}
