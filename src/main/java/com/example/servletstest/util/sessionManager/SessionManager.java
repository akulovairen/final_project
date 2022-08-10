package com.example.servletstest.util.sessionManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SessionManager class that contains methods for managing session
 */
public class SessionManager implements AutoCloseable {
    private static final int TIMEOUT_IN_SECONDS = 10;
    private final DataSource dataSource;
    private Connection connection;
    private Logger log = LogManager.getLogger(SessionManager.class);

    /**
     * Creates session manager
     *
     * @param dataSource dataSource
     * @throws SessionManagerException - if datasource is null
     */
    public SessionManager(DataSource dataSource) {
        if (dataSource == null) {
            log.error("DataSource is null");
            throw new SessionManagerException("Datasource is null");
        }
        this.dataSource = dataSource;
    }

    public Connection getCurrentSession() {
        checkConnection();
        return connection;
    }

    /**
     * Opens connection
     *
     * @return Connection
     * @throws SessionManagerException - if not start session
     */
    public Connection beginSession() {
        try {
            connection = dataSource.getConnection();
            checkConnection();
            log.info("Connected to dataSource");
        } catch (SQLException e) {
            log.error("Cannot start session",e);
            throw new SessionManagerException("Cannot start session",e);
        }
        return connection;
    }

    /**
     * Commit changes in DB.
     */
    public void commitSession() {
        checkConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Cannot commit",e);
            throw new SessionManagerException("Cannot commit",e);
        }
    }

    /**
     * Cancel changes in the session
     */
    public void rollbackSession() {
        checkConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Cannot rollback", e);
            throw new SessionManagerException("Cannot rollback",e);
        }
    }

    /**
     *
     */
    public void close() {
        checkConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Cannot close connection", e);
            throw new SessionManagerException("Cannot close connection",e);
        }
    }

    private void checkConnection() {
        try {
            if (connection == null || !connection.isValid(TIMEOUT_IN_SECONDS)) {
                log.error("Connection is invalid");
                throw new SessionManagerException("Connection is invalid");
            }
        } catch (SQLException ex) {
            throw new SessionManagerException(ex);
        }
    }
}
