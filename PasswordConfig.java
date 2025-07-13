public class PasswordConfig {
    private int length = 12;
    private boolean includeUpperCase = true;
    private boolean includeLowerCase = true;
    private boolean includeDigits = true;
    private boolean includeSpecialChars = true;
    private boolean excludeAmbiguous = false;
    private int minLength = 6;
    private int maxLength = 32;

    public PasswordConfig() {

    }

    public PasswordConfig(int length) {
        setLength(length);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < minLength || length > maxLength) {
            throw new IllegalArgumentException("Length must be between " + minLength + " and " + maxLength);
        }

        this.length = length;
    }

    public boolean isIncludeUpperCase() {
        return includeUpperCase;
    }

    public void setIncludeUppercase(boolean includeUpperCase) {
        this.includeUpperCase = includeUpperCase;
    }

    public boolean isIncludeLowerCase() {
        return includeLowerCase;
    }

    public void setIncludeLowercase(boolean includeLowerCase) {
        this.includeLowerCase = includeLowerCase;
    }

    public boolean isIncludeDigits() {
        return includeDigits;
    }

    public void setIncludeDigits(boolean includeDigits) {
        this.includeDigits = includeDigits;
    }

    public boolean isIncludeSpecialChars() {
        return includeSpecialChars;
    }

    public void setIncludeSpecialChars(boolean includeSpecialChars) {
        this.includeSpecialChars = includeSpecialChars;
    }

    public boolean isExcludeAmbiguous() {
        return excludeAmbiguous;
    }

    public void setExcludeAmbiguous(boolean excludeAmbiguous) {
        this.excludeAmbiguous = excludeAmbiguous;
    }

    public PasswordConfig length(int length) {
        setLength(length);
        return this;
    }

    public PasswordConfig uppercase(boolean include) {
        setIncludeUppercase(include);
        return this;
    }

    public PasswordConfig lowercase(boolean include) {
        setIncludeLowercase(include);
        return this;
    }

    public PasswordConfig digits(boolean include) {
        setIncludeDigits(include);
        return this;
    }

    public PasswordConfig specialConfig(boolean include) {
        setIncludeSpecialChars(include);
        return this;
    }

    public PasswordConfig excludeAmbiguous(boolean exclude) {
        setExcludeAmbiguous(exclude);
        return this;
    }
}
