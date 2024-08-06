package app.config;

import frameworks.data_access.MongoDBConnection;
import frameworks.data_access.MongoUserDAO;
import frameworks.data_access.UserDataAccessInterface;

class DataAccessConfig {

    static final MongoDBConnection mongoDBConnection = new MongoDBConnection();
    static final UserDataAccessInterface userDAO = new MongoUserDAO(mongoDBConnection.getDatabase());

}
