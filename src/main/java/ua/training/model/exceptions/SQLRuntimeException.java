package ua.training.model.exceptions;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class SQLRuntimeException extends RuntimeException {
    private static final Logger logger = Logger.getLogger(SQLRuntimeException.class);

    public SQLRuntimeException(SQLException cause) {
        super(cause);
        logger.error("SQLRuntimeException caused by", cause);
    }

    public SQLRuntimeException(String message) {
        super(message);
        logger.error("", this);
    }
}
