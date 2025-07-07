package dasturlash.uz.util;

public class UzbPhoneValidator {

    private static final String REGEX = "^(?:\\+998|998|0)[\\s-]?\\(?((9[0-9]|88))\\)?[\\s-]?\\d{3}[\\s-]?\\d{2}[\\s-]?\\d{2}$";

    public static boolean validatePhoneNumber(String input) {
        return input.matches(REGEX);
    }
}
