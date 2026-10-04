package util;

public class Validation {

    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}