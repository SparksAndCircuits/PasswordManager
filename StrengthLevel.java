enum StrengthLevel {
    VERY_WEAK("VERY WEAK"),
    WEAK("WEAK"),
    MODERATE("Moderate"),
    STRONG("Strong"),
    VERY_STRONG("Very Strong");

    private final String description;

    StrengthLevel(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}