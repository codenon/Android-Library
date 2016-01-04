
package cn.conon.android.library.utils;

/**
 * Utility methods for Objects
 *
 * @author codenon
 * @date: Dec 9, 2013 11:37:43 PM
 */
public final class ObjectUtils {

    private ObjectUtils() {
        // No public constructor
    }

    /**
     * Perform a safe equals between 2 objects.
     * <p/>
     * It manages the case where the first object is null and it would have
     * resulted in a {@link NullPointerException} if <code>o1.equals(o2)</code>
     * was used.
     *
     * @param o1 First object to check.
     * @param o2 Second object to check.
     * @return <code>true</code> if both objects are equal. <code>false</code>
     * otherwise
     * @see Object#equals(Object)
     */
    public static boolean safeEquals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        } else {
            return o1.equals(o2);
        }
    }
}
