package pl.mdymczyk.objects;

import java.util.Objects;

public class ObjectsNonNull {

    private Object value1;
    private Object value2;

    public static void main(String[] args) {
        ObjectsNonNull objectsNonNull = new ObjectsNonNull();

        objectsNonNull.setValue1(null);
        objectsNonNull.setValue2(null);

    }

    private void setValue1(Object o) {
        this.value1 = Objects.requireNonNull(o);
    }

    private void setValue2(Object o) {
        this.value2 = Objects.requireNonNull(o, "You shall not pass!");
    }

}
