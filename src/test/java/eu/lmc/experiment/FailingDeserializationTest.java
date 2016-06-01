package eu.lmc.experiment;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author Kamil Podlesak &lt;kamil.podlesak@lmc.eu&gt;
 */
public class FailingDeserializationTest {

    @Test(expected = InvalidClassException.class)
    public void testDeserialization1() throws Exception {
        testFailing(new FailingDeserialization1());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeserialization2() throws Exception {
        testFailing(new FailingDeserialization2());
    }

    private void testFailing(Serializable obj) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("?");
        oos.writeObject(obj);
        oos.close();

        final byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Assert.assertEquals("?", ois.readObject());
        final Object o = ois.readObject();
        Assert.fail("object successfuly read! " + o);
    }
}