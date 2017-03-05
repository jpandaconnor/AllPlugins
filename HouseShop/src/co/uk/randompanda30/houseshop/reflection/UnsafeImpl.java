package co.uk.randompanda30.houseshop.reflection;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by panda on 27/05/16.
 */
public class UnsafeImpl {

    public static Unsafe unsafe;
    static Object anchor;
    static long offset;


    static /**<cinit>*/ {
        try {
            Field f = Class.forName("sun.misc.Unsafe").getDeclaredFields()[0];
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            offset = unsafe.staticFieldOffset(fetchField(UnsafeImpl.class, "anchor"));
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(42);
        }
    }

    static public long Object2ID(Object O) {
        anchor = O;
        return unsafe.getLong(UnsafeImpl.class, offset);
    }

    static public Object ID2Object(long ID) {
        unsafe.putLong(UnsafeImpl.class, offset, ID);
        return anchor;
    }

    static public String Object2Trace(Object O) {
        return Object2Trace(O, 64);
    }

    static public String Object2Trace(Object O, int L) {
        StringBuilder sb = new StringBuilder();
        sb.append("Trace of " + Long.toHexString(Object2ID(O)));
        sb.append("\n");
        for (long i = 0; i < L; i++) {
            sb.append(String.format("%02X ", unsafe.getByte(O, i)));
            if (i % 4 == 3 && i != L - 1)
                sb.append("\n");
        }
        return sb.toString();
    }

    static private Field fetchField(Class Source, String... Names) {
        for (; Source != Object.class; Source = Source.getSuperclass()) {
            for (Field f : Source.getDeclaredFields())
                for (String s : Names)
                    if (f.getName().equals(s))
                        return f;
        }
        return null;
    }

    static public void putObject(Class Owner, Object Value, String... Names) {
        unsafe.putObject(Owner, unsafe.staticFieldOffset(fetchField(Owner, Names)), Value);
    }

    static public Object getObject(Class Owner, String... Names) {
        return unsafe.getObject(Owner, unsafe.staticFieldOffset(fetchField(Owner, Names)));
    }

    static public void putObject(Object Owner, Object Value, String... Names) {
        unsafe.putObject(Owner, unsafe.objectFieldOffset(fetchField(Owner.getClass(), Names)), Value);
    }

    static public Object getObject(Object Owner, String... Names) {
        return unsafe.getObject(Owner, unsafe.objectFieldOffset(fetchField(Owner.getClass(), Names)));
    }

    static public void putInt(Object Owner, int Value, String... Names) {
        unsafe.putInt(Owner, unsafe.objectFieldOffset(fetchField(Owner.getClass(), Names)), Value);
    }

    static public int getInt(Object Owner, String... Names) {
        return unsafe.getInt(Owner, unsafe.objectFieldOffset(fetchField(Owner.getClass(), Names)));
    }

    static public int getInt(Class Owner, String... Names) {
        return unsafe.getInt(Owner, unsafe.staticFieldOffset(fetchField(Owner, Names)));
    }

    static public void putInt(Class Owner, int Value, String... Names) {
        unsafe.putInt(Owner, unsafe.staticFieldOffset(fetchField(Owner, Names)), Value);
    }
}