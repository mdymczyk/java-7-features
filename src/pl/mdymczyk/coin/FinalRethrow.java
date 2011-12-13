package pl.mdymczyk.coin;

public class FinalRethrow {

    static class A extends Exception {
    }

    static class B extends Exception {
    }

    private void testFinalRethrow(boolean test, boolean test2) throws B {
        try {
            if (test) {
                throw new A();
            } else if (test2) {
                throw new B();
            }

        } catch (A e) {

        } catch (final Exception e) {
            throw e;
        }
    }

}
