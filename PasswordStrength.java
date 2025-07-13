public class PasswordStrength {
    private final StrengthLevel level;
    private final int score;
    private final String feedback;

    public PasswordStrength(StrengthLevel level, int score, String feedback) {
        this.level = level;
        this.score = score;
        this.feedback = feedback;
    }

    public StrengthLevel getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return String.format("Strength: %s (Score: %d/8)%s", level, score, feedback.isEmpty() ? "" : " - " + feedback);
    }
}
