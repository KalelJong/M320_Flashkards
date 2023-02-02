package ch.tbz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A Flashcard set that contains multiple Flashcards and
 * which has learning functions
 */
public class FlashcardSet extends ArrayList<Flashcard> {

    private String flashcardSetName;
    private Date creationDate;

    public FlashcardSet(String flashcardSetName, Date creationDate) {

        this.creationDate = creationDate;
        this.flashcardSetName = flashcardSetName;
    }

    /**
     * Prints out a card to the console, which can be flipped to reveal the translation
     * @param isShuffled if true the flashcard set is shuffled
     */
    public void writeLearn(boolean isShuffled){
        Scanner scan = new Scanner(System.in);
        int rightAnswers = 0;
        for (int i = 0; i < this.size(); i++) {

            Flashcard flashcard = this.get(isShuffled? new Random().nextInt(this.size()) + 1 : i ) ;
            System.out.println(flashcard.getOrigin() + " [" + i + "/" + this.size() + " ]");
            System.out.println("Type in translation:");
            String guess = scan.nextLine();
            if (guess.equals(flashcard.getTranslation())) {
                System.out.println( "Correct ✅");
                rightAnswers++;
            }
            else {
                System.out.println("Incorrect ❌");
                System.out.println("Translation is: " + flashcard.getTranslation());
            }
        }
        System.out.println("Completed set! You got " + rightAnswers + " right answers out of " + this.size());
        System.out.println("That's " + (rightAnswers*100)/this.size() + "%!" );
    }

    /**
     * Prints out a card to the console, which can be flipped to reveal the translation
     * @param isShuffled if true the flashcard set is shuffled
     */
    public void flipCardLearn(boolean isShuffled)  {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < this.size(); i++) {

                Flashcard flashcard = this.get(isShuffled ? new Random().nextInt(this.size()) + 1 : i);
                boolean isOriginTermSide = true;
                boolean nextCard = false;
                while (!nextCard) {
                    for (int j = 0; j < 10; j++) {
                        System.out.println();
                    }
                    System.out.println("+---+---+---+---+---+");
                    System.out.println((isOriginTermSide ? flashcard.getOrigin() : flashcard.getTranslation())+ " [" + i + "/" + this.size() + "]");
                    System.out.println("+---+---+---+---+---+");
                    System.out.println("Press enter to flip card or type 1 to go to the next");
                    String userInput = scan.nextLine();
                    if (userInput.equals("")) {
                        isOriginTermSide = !isOriginTermSide;
                    }
                    else if (userInput.equals("1")){
                        nextCard = true;
                    }
                }
        }
        System.out.println("Set was completed!");
    }


    public String getFlashcardSetName() {
        return flashcardSetName;
    }

    public void setFlashcardSetName(String flashcardSetName) {
        this.flashcardSetName = flashcardSetName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}