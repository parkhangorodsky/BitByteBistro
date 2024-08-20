package frameworks.data_access.serialization;

import java.io.IOException;

/**
 * A generic interface for serializing and deserializing objects.
 * <p>
 * This interface defines methods for converting objects of type {@code Y} to a serialized format {@code X}
 * and for reconstructing objects of type {@code Y} from the serialized format {@code X}.
 * </p>
 *
 * @param <Y> The type of the object to be serialized or deserialized.
 * @param <X> The type of the serialized format.
 */
public interface Serializer<X,Y> {
    /**
     * Serializes an object of type {@code Y} to a serialized format {@code X}.
     *
     * @param deserialized The object of type {@code Y} to be serialized.
     * @return The serialized format {@code X} representing the object.
     */
    X serialize(Y deserialized);

    /**
     * Deserializes an object of type {@code X} to an object of type {@code Y}.
     *
     * @param serialized The serialized format {@code X} to be deserialized.
     * @return The object of type {@code Y} reconstructed from the serialized format.
     */
    Y deserialize(X serialized);
}
