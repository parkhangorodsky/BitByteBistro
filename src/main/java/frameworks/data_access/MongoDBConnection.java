package frameworks.data_access;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBConnection {

    private final String connectionString =
            "mongodb+srv://Cluster59592:" +
            System.getenv("MONGODB_PASSWORD")
            + "@cluster59592.uf9vqsw.mongodb.net/?retryWrites=true&w=majority&appName=Cluster59592";

    private final String databaseName = System.getenv("MONGODB_NAME");

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {

        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings mongoSettings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try {
            mongoClient = MongoClients.create(mongoSettings);
            this.database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
            this.database.runCommand(new Document("ping", 1));
            System.out.println("Pinged the deployment. Successfully connected to MongoDB ✅");
        } catch (MongoException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }

}
