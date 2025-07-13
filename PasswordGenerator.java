import java.security.SecureRandom;
import java.util.*;

public class PasswordGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+=-{}[]:;<>,./?|`";
    private static final String AMBIGUOUS_CHARS = "0o1lI";

    private static final String[] CONSONANTS = {
            "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"
    };

    private static final String[] VOWELS = { "a", "e", "i", "o", "u" };

    private final PasswordConfig config;

    public PasswordGenerator(PasswordConfig config) {
        this.config = config;
    }

    public PasswordGenerator() {
        this.config = new PasswordConfig();
    }

    public String generateRandomPassword() {
        String characterSet = buildCharacterSet();

        if (characterSet.isEmpty()) {
            throw new IllegalStateException("No character sets enabled in configuration");
        }

        StringBuilder password = new StringBuilder();

        if (config.isIncludeUpperCase()) {
            password.append(getRandomChar(UPPERCASE));
        }

        if (config.isIncludeLowerCase()) {
            password.append(getRandomChar(LOWERCASE));
        }

        if (config.isIncludeDigits()) {
            password.append(getRandomChar(DIGITS));
        }

        if (config.isIncludeSpecialChars()) {
            password.append(getRandomChar(SPECIAL_CHARS));
        }

        while (password.length() < config.getLength()) {
            password.append(getRandomChar(characterSet));
        }

        return shuffleString(password.toString());
    }

    public String generatePronounceablePassword() {
        StringBuilder password = new StringBuilder();
        boolean isConsonant = SECURE_RANDOM.nextBoolean();

        while (password.length() < config.getLength()) {
            if (isConsonant) {
                password.append(getRandomChar(String.join("", CONSONANTS)));
            } else {
                password.append(getRandomChar(String.join(" ", VOWELS)));
            }

            isConsonant = !isConsonant;
        }

        String result = password.toString();

        if (config.isIncludeDigits() || config.isIncludeSpecialChars()) {
            result = enhanceWithRequiredChars(result);
        }

        return result.length() > config.getLength() ? result.substring(0, config.getLength()) : result;
    }

    public String generatePatternPassword() {
        List<String> words = generateWordList();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < Math.min(words.size(), 3); i++) {
            if (i > 0) {
                password.append(config.isIncludeSpecialChars() ? getRandomChar("-_") : "");
            }

            password.append(words.get(i));
        }

        if (config.isIncludeDigits()) {
            password.append(SECURE_RANDOM.nextInt(100));
        }

        if (config.isIncludeSpecialChars() && password.length() < config.getLength()) {
            password.append(getRandomChar("!@#$%"));
        }

        String result = password.toString();
        return result.length() > config.getLength() ? result.substring(0, config.getLength()) : result;
    }

    private String buildCharacterSet() {
        StringBuilder charSet = new StringBuilder();

        if (config.isIncludeUpperCase()) {
            charSet.append(UPPERCASE);
        }
        if (config.isIncludeLowerCase()) {
            charSet.append(LOWERCASE);
        }
        if (config.isIncludeDigits()) {
            charSet.append(DIGITS);
        }
        if (config.isIncludeSpecialChars()) {
            charSet.append(SPECIAL_CHARS);
        }

        String result = charSet.toString();

        if (config.isExcludeAmbiguous()) {
            for (char c : AMBIGUOUS_CHARS.toCharArray()) {
                result = result.replace(String.valueOf(c), "");
            }
        }

        return result;
    }

    private char getRandomChar(String charSet) {
        return charSet.charAt(SECURE_RANDOM.nextInt(charSet.length()));
    }

    private String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();

        for (char c : input.toCharArray()) {
            characters.add(c);
        }

        Collections.shuffle(characters, SECURE_RANDOM);

        StringBuilder result = new StringBuilder();

        for (char c : characters) {
            result.append(c);
        }

        return result.toString();
    }

    private String enhanceWithRequiredChars(String base) {
        StringBuilder enhanced = new StringBuilder(base);

        if (config.isIncludeDigits()) {
            enhanced.append(SECURE_RANDOM.nextInt(10));
        }

        if (config.isIncludeSpecialChars()) {
            enhanced.append(getRandomChar("!@#$"));
        }

        return enhanced.toString();
    }

    private List<String> generateWordList() {
        String[] commonWords = {
                "Apple", "Brave", "Cloud", "Dream", "Eagle"
        };
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            words.add(commonWords[SECURE_RANDOM.nextInt(commonWords.length)]);
        }

        return words;
    }
}