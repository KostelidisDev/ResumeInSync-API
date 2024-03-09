package gr.ihu.ict.resumeinsync.common.util;

public abstract class ClassUtils {
    public static Boolean isChildClass(final Class<?> parent,
            final Class<?> child) {
        return parent.isAssignableFrom(child);
    }
}
