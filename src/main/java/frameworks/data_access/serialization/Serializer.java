package frameworks.data_access.serialization;

import java.io.IOException;

public interface Serializer<X,Y> {
    X serialize(Y deserialized);
    Y deserialize(X serialized);
}
