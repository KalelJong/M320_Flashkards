package ch.tbz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 */
public class Main {

    /**
     *  Displays the UI for creation, updating and deleting of flashcard sets
     * @param args String array of arguments
     */
    public static void main(String[] args) {

        List<FlashcardSet> flashcardSetList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        FlashcardSet initFlashcardSet = new FlashcardSet("Sus",new Date());
        initFlashcardSet.add(new Flashcard("hello", "hallo"));
        flashcardSetList.add(initFlashcardSet);
        SimpleDateFormat fomatter = new SimpleDateFormat("dd/MM/yyyy");
        do {
            System.out.println("WELCOME TO FLASHKARDS 😊");
            System.out.println("=======================================================");
            System.out.println("Your sets");
            for (FlashcardSet set: flashcardSetList) {
                System.out.println("[" + flashcardSetList.indexOf(set)+"] " + set.getFlashcardSetName()
                        + " | " + set.size() + " Term(s)" + " | Created on: " + fomatter.format(set.getCreationDate()));
            }
            System.out.println("""
            =======================================================
            What would you like to do?
            [1] Create new set
            [2] Choose a set
            [Q] Quit
            """);
            switch (scan.nextLine().toLowerCase()){
                case "1":
                    System.out.println("Type in set name:");
                    FlashcardSet newFlashcardSet = new FlashcardSet(scan.nextLine(), new Date());
                    flashcardSetList.add(newFlashcardSet);
                    break;
                case "2":
                    try {
                       FlashcardSet flashcardSet = getSet(flashcardSetList);
                       boolean isEditting= true;
                       do {
                           System.out.println(""" 
                                   *=====================================*
                                   """ + flashcardSet.getFlashcardSetName()+ " - " + flashcardSet.size() + " Term(s)" + """
                                   
                                   """ + "Created On: " + fomatter.format(flashcardSet.getCreationDate())+"""
                                   
                                   *=====================================*
                                   [1] Add cards to existent set
                                   [2] Delete cards from existent set
                                   [3] Show all cards
                                   [4] Write learn set
                                   [5] Flip Card learn set
                                   [6] Delete set
                                   [B] Go back""");
                           switch (scan.nextLine().toLowerCase()) {
                               case "1":
                                   flashcardSet.addFlashCard();
                                   break;
                               case "2":
                                   flashcardSet.removeFlashcard();
                                   break;
                               case "3":
                                   flashcardSet.showAllFlashcards();
                                   break;
                               case "4":
                                   flashcardSet.writeLearn(false);
                                   break;
                               case "5":
                                   flashcardSet.flipCardLearn(false);
                                   break;
                               case "6":
                                   flashcardSetList.remove(flashcardSet);
                                   break;
                               case "b":
                                   isEditting = false;
                                   break;
                               default:
                                   break;
                           }
                       }while (isEditting);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid input");
                    }
                    catch (IndexOutOfBoundsException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "q":
                    System.exit(0);
                break;
                default:
                    break;
            }

        }while (true);
    }

    /**
     * Prompts for user input and validates id of flashcard set
     * @param flashcardSets List of flashcard sets to whom the wished flashcard set belongs
     * @return Returns a Flashcard Set
     */
    public static FlashcardSet getSet(List<FlashcardSet> flashcardSets ){
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in set Id:");
        try {
            int setId = Integer.parseInt(scan.nextLine());
            if (setId > flashcardSets.size() - 1 || setId < 0)
                throw new IndexOutOfBoundsException("ID does not exist");
            return flashcardSets.get(setId);
        }
        catch (NumberFormatException e){
            throw e;
        }
    }
}
