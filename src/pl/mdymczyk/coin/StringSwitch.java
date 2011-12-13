package pl.mdymczyk.coin;

public class StringSwitch {

    public static void main(String[] args) {
        StringSwitch stringSwitch = new StringSwitch();
        stringSwitch.oldMain("one");
        stringSwitch.newMain("two");
    }

    private void newMain(String parameter) {
        switch (parameter) {
            case "one":
                System.out.println("one");
                break; // do something
            case "two":
                System.out.println("two");
                break; // do something
            case "three":
                System.out.println("three");
                break; // do something
            default:
                System.out.println("default");
                ; // do something
        }
    }

    private void oldMain(String parameter) {
        if ("one".equals(parameter)) {
            System.out.println("one");
        } else if ("two".equals(parameter)) {
            System.out.println("two");
        } else if ("three".equals(parameter)) {
            System.out.println("three");
        } else {
            System.out.println("default");
        }
    }

}
