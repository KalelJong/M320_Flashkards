package ch.tbz;

/**
 * A single flashcard, that cointains an origin and translated term
 */
public class Flashcard {
    private String origin;
    private String translation;


    public Flashcard(String origin, String translation) {
        this.origin = origin;
        this.translation = translation;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
