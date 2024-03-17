package com.panem.panem_backend.service;

import org.slf4j.Logger;

public class DatabaseConnection {
    private final Logger logger;

    public DatabaseConnection(Logger logger) {
        this.logger = logger;
    }

    public void connectToDatabase() {
        // Настройка подключения к базе данных...
        logger.info("Успешно подключились к базе данных");
    }

}
