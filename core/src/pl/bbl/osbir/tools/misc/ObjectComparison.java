package pl.bbl.osbir.tools.misc;

public class ObjectComparison {
    public static boolean doesObjectQualifyForChange(Object firstObject, Object secondObject){
        return !isObjectEqualOrNull(firstObject, secondObject);
    }

    private static boolean isObjectEqualOrNull(Object firstObject, Object secondObject) {
        return firstObject != null && firstObject.equals(secondObject);
    }
}
