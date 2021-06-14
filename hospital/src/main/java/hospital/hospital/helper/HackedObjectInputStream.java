package hospital.hospital.helper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class HackedObjectInputStream extends ObjectInputStream {

    public HackedObjectInputStream(InputStream in) throws IOException {
        super(in);
        enableResolveObject(true);
    }

    @Override
    protected ObjectStreamClass readClassDescriptor() throws IOException, ClassNotFoundException {
        ObjectStreamClass read = super.readClassDescriptor();
        System.out.println("OVO MU JE NAME " + read.getName());

        if (read.getName().startsWith("admin.admin.model.")) {

            return ObjectStreamClass.lookup(Class.forName(read.getName().replace("admin.admin.model.", "hospital.hospital.model.")));
        }
        return read;
    }
}