package io.github.starwishsama.StarTool.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils
{
    public static void init() {}

    public static Field getField(Class<?> clazz, String name) {
        do {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equals(name)) {
                    field.setAccessible(true);
                    return field;
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);
        throw new RuntimeException("Can't find field " + name);
    }

    public static Method getMethod(Class<?> clazz, String name, int paramlength) {
        do {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals(name) && method.getParameterTypes().length == paramlength) {
                    method.setAccessible(true);
                    return method;
                }
            }
        } while ((clazz = clazz.getSuperclass()) != null);
        throw new RuntimeException("Can't find method " + name + " with params length " + paramlength);
    }


    public static void throwException(Throwable exception) throws Throwable { throwException0(exception); }




    private static <T extends Throwable> void throwException0(Throwable exception) throws Throwable {
            throw exception;
    }
}
