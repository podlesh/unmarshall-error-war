package eu.lmc.experiment;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Special class that handles its own serialization/deserialization and <b>FAILS</b>.
 * Used to test how is deserialization error handled.
 */
public class FailingDeserializationClass implements Serializable {

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        //do nothing
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("always failing!");
    }

    private void readObjectNoData()
            throws ObjectStreamException {
        throw new UnsupportedOperationException("always failing!");
    }

}
