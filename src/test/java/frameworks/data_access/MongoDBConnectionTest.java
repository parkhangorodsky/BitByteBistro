package frameworks.data_access;

import com.mongodb.client.MongoDatabase;
import org.junit.jupiter.api.Test;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MongoDBConnectionTest {

    private MongoClient mockMongoClient;
    private MongoDatabase mockMongoDatabase;
    private MongoDBConnection mongoDBConnection;

    @BeforeEach
    void setUp() {
        // Mock the environment variables
        mockMongoClient = Mockito.mock(MongoClient.class);
        mockMongoDatabase = Mockito.mock(MongoDatabase.class);

        // Mock the environment variables
        mockStatic(System.class);
        when(System.getenv("MONGODB_NAME")).thenReturn("testDatabase");
        when(System.getenv("MONGODB_PASSWORD")).thenReturn("testPassword");

        // Mock the MongoDB client and database
        when(mockMongoClient.getDatabase(anyString())).thenReturn(mockMongoDatabase);

        // Mock the connection settings and codec registry
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        when(mockMongoDatabase.withCodecRegistry(any(CodecRegistry.class))).thenReturn(mockMongoDatabase);

        // Create the MongoDBConnection instance with the mocked MongoClient and MongoDatabase
        mongoDBConnection = new MongoDBConnection();
    }

    @Test
    void testSuccessfulConnection() {
        // Mock the ping command
        when(mockMongoDatabase.runCommand(any(Document.class))).thenReturn(new Document("ok", 1.0));

        // Verify that the connection was established and the ping was successful
        assertDoesNotThrow(() -> {
            MongoDatabase database = mongoDBConnection.getDatabase();
            assertNotNull(database);
        });

        // Verify that the correct methods were called
        verify(mockMongoDatabase).runCommand(any(Document.class));
        verify(mockMongoClient).getDatabase("testDatabase");
    }

    @Test
    void testFailedConnection() {
        // Mock the MongoException when trying to run the ping command
        when(mockMongoDatabase.runCommand(any(Document.class))).thenThrow(new MongoException("Ping failed"));

        // Verify that the exception is handled correctly
        assertDoesNotThrow(() -> {
            MongoDatabase database = mongoDBConnection.getDatabase();
            assertNull(database);
        });

        // Verify that the correct methods were called
        verify(mockMongoDatabase).runCommand(any(Document.class));
        verify(mockMongoClient).getDatabase("testDatabase");
    }

    @Test
    void testCloseConnection() {
        // Test the close method
        mongoDBConnection.close();

        // Verify that the MongoClient's close method was called
        verify(mockMongoClient).close();
    }
}
