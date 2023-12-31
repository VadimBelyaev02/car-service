package com.andersen.carservice.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ArgumentParser {

    public static List<String> parse(String command) {
        int i = 0;
        List<String> arguments = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        while (i < command.length()) {
            if (command.charAt(i) != ' ') {
                builder.append(command.charAt(i));
            }
            if ((command.charAt(i) == ' ' || i == command.length() - 1) && !builder.isEmpty()) {
                arguments.add(builder.toString());
                builder = new StringBuilder();
            }
            i++;
        }
        return arguments;
    }
}
