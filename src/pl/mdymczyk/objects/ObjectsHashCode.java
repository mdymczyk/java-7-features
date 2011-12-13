package pl.mdymczyk.objects;

import java.util.Objects;

public class ObjectsHashCode {

    private Object value1;
    private Object value2;

    public static void main(String[] args) {
        ObjectsHashCode objectsHashCode = new ObjectsHashCode();
        System.out.println(Objects.hashCode(objectsHashCode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }

    public int oldHashCode() {
        int result = value1 != null ? value1.hashCode() : 0;
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        return result;
    }
}
