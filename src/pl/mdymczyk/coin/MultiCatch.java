package pl.mdymczyk.coin;

public class MultiCatch {

    public static void main(String[] args) {
        MultiCatch mc = new MultiCatch();
        mc.oldMultiCatch();
        mc.newMultiCatch();
    }

    private void oldMultiCatch() {
        try {
            Class<?> string = Class.forName("java.lang.String");
            string.getMethod("toString");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void newMultiCatch() {
        try {
            Class<?> string = Class.forName("java.lang.String");
            string.getMethod("toString");
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

}
