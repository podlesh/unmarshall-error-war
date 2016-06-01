package eu.lmc.experiment;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Kamil Podlesak &lt;kamil.podlesak@lmc.eu&gt;
 */
public class FailingDeserializationClassTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testDeserialization() throws Exception {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject("?");
        oos.writeObject(new FailingDeserializationClass());
        oos.close();

        final byte[] bytes = bos.toByteArray();

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Assert.assertEquals("?", ois.readObject());
        final Object o = ois.readObject();
        Assert.fail("object successfuly read! " + o);

    }
}