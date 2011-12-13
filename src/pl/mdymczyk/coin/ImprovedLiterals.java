package pl.mdymczyk.coin;

public class ImprovedLiterals {

    public static final short[] HAPPY_FACE = {
            (short) 0b0000011111100000,
            (short) 0b0000100000010000,
            (short) 0b0001000000001000,
            (short) 0b0010000000000100,
            (short) 0b0100000000000010,
            (short) 0b1000011001100001,
            (short) 0b1000011001100001,
            (short) 0b1000000000000001,
            (short) 0b1000000000000001,
            (short) 0b1001000000001001,
            (short) 0b1000100000010001,
            (short) 0b0100011111100010,
            (short) 0b0010000000000100,
            (short) 0b0001000000001000,
            (short) 0b0000100000010000,
            (short) 0b0000011111100000
    };

    public static void main(String[] args) {
        System.out.println("With underscore");

        long number = 1234_5678_9012_3456L;
        long bytes = 0b01101101_10110110_01101101_10110110;
        long hex = 0xFFEC_DE5E;

        System.out.println(number);
        System.out.println(bytes);
        System.out.println(hex);

        System.out.println("Without underscore");

        number = 1234567890123456L;
        bytes = 0b01101101101101100110110110110110;
        hex = 0xFFEC_DE5E;

        System.out.println(number);
        System.out.println(bytes);
        System.out.println(hex);
    }

}
