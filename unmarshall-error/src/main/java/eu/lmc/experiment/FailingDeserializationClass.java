package eu.lmc.experiment;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.UUID;

/**
 * Special class that handles its own serialization/deserialization and <b>FAILS</b>.
 * Used to test how is deserialization error handled.
 */
public class FailingDeserializationClass implements Serializable {

    private UUID uuid = UUID.randomUUID();

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public String toString() {
        return String.valueOf(uuid);
    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        System.out.println(">>>> Serializing: " + uuid);
        out.writeUTF(uuid.toString());
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        final String str = in.readUTF();
        System.out.println(">>>> Deserializing: " + str);
        uuid = UUID.fromString(str);
        throw new InvalidClassException("always failing!");
    }

    private void readObjectNoData()
            throws ObjectStreamException {
        throw new UnsupportedOperationException("always failing!");
    }

}
