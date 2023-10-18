package com.andersen.carservice;

import com.andersen.carservice.model.mapper.OrderMapper;
import com.andersen.carservice.model.mapper.RepairerMapper;
import com.andersen.carservice.service.impl.OrderServiceImpl;
import com.andersen.carservice.service.impl.RepairerServiceImpl;
import com.andersen.carservice.storage.OrderStorage;
import com.andersen.carservice.storage.RepairerStorage;
import com.andersen.carservice.storage.impl.OrderStorageImpl;
import com.andersen.carservice.storage.impl.RepairerStorageImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Application {

    private final OrderStorage orderStorage = new OrderStorageImpl();
    private final RepairerStorage repairerStorage = new RepairerStorageImpl();
    private final OrderMapper orderMapper = new OrderMapper(repairerStorage);


    private final RepairerMapper repairerMapper = new RepairerMapper(orderStorage);

    private final CommandExecutor commandExecutor = new CommandExecutor(
            new PrintWriter(System.out),
            new BufferedReader(new InputStreamReader(System.in)),
            new OrderServiceImpl(0, orderStorage, repairerStorage, orderMapper),
            new RepairerServiceImpl(0, orderStorage, repairerStorage, repairerMapper)
    );
    public static void main(String[] args) {
        System.out.println("Car Service!");
        new Application().commandExecutor.start();
    }
}
