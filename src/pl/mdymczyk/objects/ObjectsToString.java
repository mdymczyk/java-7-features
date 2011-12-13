package pl.mdymczyk.objects;

import java.util.Objects;

public class ObjectsToString {

    private Object value1;
    private Object value2;

    public static void main(String[] args) {
        ObjectsToString objectsToString = new ObjectsToString();
        System.out.println(objectsToString.oldMakeString());
        System.out.println(objectsToString.newMakeString());
    }

    public String newMakeString() {
        return "Value1: " + Objects.toString(value1) + "; value2: " + Objects.toString(value2, "was not properly set");
    }

    public String oldMakeString() {
        return "Value1: " + (value1 == null ? "null" : value1) + "; value2: " + (value1 == null ? "was not properly set" : value1);
    }

}
