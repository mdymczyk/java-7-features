package pl.mdymczyk.coin;

import java.io.*;

public class ResourceManagement {

    public static void main(String[] args) {
        ResourceManagement rm = new ResourceManagement();
        rm.oldTry();
        rm.newTry();
    }

    private void oldTry() {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream("test.txt");
            dos = new DataOutputStream(fos);
            dos.writeUTF("Java 7 isn't that great!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void newTry() {
        /*
         * Only classes implementing java.lang.AutoCloseable can be used here
         */
        try (FileOutputStream fos = new FileOutputStream("test.txt");
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeUTF("Java 7 isn't that great!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
