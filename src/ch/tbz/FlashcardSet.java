package ch.tbz;

import com.sun.tools.jconsole.JConsoleContext;

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
     * Prints a single flashcard
     * @param flashcard Flashcard to be printed
     * @param showOriginTerm Decides if the origin or the translation term should be shown
     */
    public void printCard(Flashcard flashcard, boolean showOriginTerm) {
        System.out.println("+---+---+---+---+---+");
        System.out.println((showOriginTerm? "(Origin) " + flashcard.getOrigin() : "(Translation) " + flashcard.getTranslation()) + " [" + (indexOf(flashcard) + 1) + "/" + size() + "]");
        System.out.println("+---+---+---+---+---+");

    }

    /**
     * Prints out a card to the console, which can be flipped to reveal the translation
     * @param isShuffled if true the flashcard set is shuffled
     */
    public void writeLearn(boolean isShuffled){
        if (isShuffled){
            Collections.shuffle(this);
        }
        Scanner scan = new Scanner(System.in);
        int rightAnswers = 0;
        for (int i = 0; i < this.size(); i++) {

            Flashcard flashcard = get(i);
            printCard(flashcard, true);
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
        System.out.println("(Press enter to continue)");
        scan.nextLine();
    }

    /**
     * Prints out a card to the console, which can be flipped to reveal the translation
     * @param isShuffled if true the flashcard set is shuffled
     */
    public void flipCardLearn(boolean isShuffled)  {
        Scanner scan = new Scanner(System.in);
        if (isShuffled){
            Collections.shuffle(this);
        }
        for (int i = 0; i < this.size(); i++) {

                Flashcard flashcard = this.get(i);
                boolean isOriginTermSide = true;
                boolean nextCard = false;
                while (!nextCard) {
                    for (int j = 0; j < 10; j++) {
                        System.out.println();
                    }
                    printCard(flashcard,isOriginTermSide);
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
        scan.nextLine();
    }

    /**
     * Prompts and gets user input to add card.
     * Loops until user is satisfied
     */
    public void addFlashCard()
    {
        boolean isDone = false;
        Scanner scan = new Scanner(System.in);
        while (!isDone){
            System.out.println("Type in the origin term");
            String origin = scan.nextLine();
            System.out.println("Type in the translated term");
            String translation = scan.nextLine();
            add(new Flashcard(origin,translation));
            while(true)
            {
                System.out.println("Added Flashcard!! [1] Add more [2] Back");
                String input = scan.nextLine();
                if (input.equals("2")){
                    isDone = true;
                    break;
                } else if (input.equals("1")){
                    break;
                }
            }

        }
    }

    /**
     *  Removes a wished flashcard from the flashcard set
     * @throws NumberFormatException If the scanner isn't able to parse the input to an int
     */
    public void removeFlashcard() throws NumberFormatException{
        Scanner scan = new Scanner(System.in);
        showAllFlashcards();
        System.out.println("Type in Flashcard Id:");
        try {
            int idFlashcard = Integer.parseInt(scan.nextLine());
            if (idFlashcard > this.size() - 1 || idFlashcard < 0)
                throw new IndexOutOfBoundsException("ID does not exist");
            remove(idFlashcard);
            System.out.println("Successfully removed");
        }
        catch (NumberFormatException e){
            throw e;
        }
    }

    /*
    Displays all flashcards in a set in the console
     */
    public void showAllFlashcards()
    {
        for (Flashcard flashcard : this) {
            System.out.println("["+indexOf(flashcard)+"] " + flashcard.getOrigin() + "   |   " + flashcard.getTranslation());
        }
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