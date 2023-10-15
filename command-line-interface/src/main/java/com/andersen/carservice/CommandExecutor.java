package com.andersen.carservice;

import com.andersen.carservice.command.NamedCommand;
import com.andersen.carservice.command.impl.*;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.util.ArgumentParser;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@AllArgsConstructor
public class CommandExecutor {

    private final PrintWriter writer;
    private final BufferedReader reader;
    private final OrderStorage orderStorage;
    private final RepairerStorage repairerStorage;
    // Reader Writer

    private final List<NamedCommand> commands = Stream.of(
            new AssignRepairers("assign-repairers", orderStorage, repairerStorage),
            new CancelOrder("cancel-order", orderStorage, repairerStorage),
            new CompleteOrder("complete-order", orderStorage),
            new FireRepairer("fire-repairer", repairerStorage, orderStorage),
            new HireRepairer("hire-repairer", repairerStorage),
            new ListOrders("list-orders", orderStorage),
            new ListRepairers("list-repairers", repairerStorage),
            new OpenOrder("open-order", orderStorage),
            new ViewOrderInfo("view-order-info", orderStorage)
    ).toList();

    public void start() {
        while (true) {
            try {
                String command = reader.readLine();

                if (Objects.equals(command, "exit")) {
                    return;
                }
                List<String> arguments = ArgumentParser.parse(command);
                commands.forEach(a -> a.execute(arguments, writer));
            } catch (IOException e) {
                writer.write(e.getMessage());
            }
        }
    }
}
