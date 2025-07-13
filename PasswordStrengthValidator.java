public class PasswordStrengthValidator {
    public static PasswordStrength validateStrength(String password) {
        int score = 0;
        StringBuilder feedback = new StringBuilder();

        if (password.length() >= 8) {
            score += 1;
        } else {
            System.out.println("Password should be at least 8 characters long.");
        }

        if (password.length() >= 12) {
            score += 1;
        }

        if (password.matches(".*[a-z].*")) {
            score += 1;
        } else {
            System.out.println(" The password must include an lowercase letter.");
        }

        if (password.matches(".*[A-Z].*")) {
            score += 1;
        } else {
            System.out.println(" The password must include an uppercase letter.");
        }

        if (password.matches(".*[0-9].*")) {
            score += 1;
        } else {
            System.out.println(" The password must include a numbers letter.");
        }

        if (password.matches(".*[!@#$%^&*()_+-={}|[]:;'<>?,./]")) {
            score += 1;
        } else {
            System.out.println("The password must include special characters.");
        }

        StrengthLevel level;
        if (score >= 7) {
            level = StrengthLevel.VERY_STRONG;
        } else if (score >= 5) {
            level = StrengthLevel.STRONG;
        } else if (score >= 3) {
            level = StrengthLevel.MODERATE;
        } else if (score >= 1) {
            level = StrengthLevel.WEAK;
        } else {
            level = StrengthLevel.VERY_WEAK;
        }

        return new PasswordStrength(level, score, feedback.toString().trim());
    }

    public static boolean hasRepeatingChars(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
                return true;
            }
        }

        return false;
    }
}
