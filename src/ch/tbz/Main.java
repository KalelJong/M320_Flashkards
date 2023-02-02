package ch.tbz;

import java.io.IOException;
import java.util.*;

public class Main {

    /**
     *  Displays the UI for creation, updating and deleting of flashcard sets
     * @param args String array of arguments
     */
    public static void main(String[] args) {
        List<FlashcardSet> flashcardSets = new ArrayList<>();
        boolean isRunning = true;
        Scanner scan = new Scanner(System.in);
        while (isRunning){
            System.out.println("WELCOME TO FLASHKARDS 😊");
            System.out.println("=======================================================");
            System.out.println("Your sets");
            for (FlashcardSet set: flashcardSets) {
                System.out.println("[" + flashcardSets.indexOf(set)+"]" + set.getFlashcardSetName()
                        + " | Created on: " + set.getCreationDate());
            }
            System.out.println("=======================================================");
            System.out.println("What would you like to do?");
            System.out.println("[1] Create new set");
            System.out.println("[2] Add cards to existent set");
            System.out.println("[3] Write learn set");
            System.out.println("[4] Flip Card learn set");
            System.out.println("[5] Delete set");
            System.out.println("[Q] Quit");

            switch (scan.nextLine().toLowerCase()){
                case "1":
                    System.out.println("Type in set name:");
                    FlashcardSet flashcardSet = new FlashcardSet(scan.nextLine(), new Date());
                    flashcardSets.add(flashcardSet);
                    break;
                case "2":
                    System.out.println("Type in set Id:");
                    try {
                        int selectedSet = Integer.parseInt(scan.nextLine());
                        if (selectedSet > flashcardSets.size() - 1 || selectedSet < 0) {
                            System.out.println("ID doesn't exist");
                            break;
                        }
                        boolean isDone = false;
                        while (!isDone){
                            System.out.println("Type in the origin term");
                            String origin = scan.nextLine();
                            System.out.println("Type in the translated term");
                            String translation = scan.nextLine();
                            flashcardSets.get(selectedSet).add(new Flashcard(origin,translation));
                            System.out.println("Added Flashcard!! [1] Add more [2] Back");
                            if (scan.nextLine().equals("2")){
                                isDone = true;
                            }
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Invalid input");
                    }
                    break;
                case "3":
                    System.out.println("Type in set Id:");
                    int writeLearnSetId = Integer.parseInt(scan.nextLine());
                    if (writeLearnSetId > flashcardSets.size() - 1 || writeLearnSetId < 0) break;
                    FlashcardSet writeLearnSet = flashcardSets.get(writeLearnSetId);
                    writeLearnSet.writeLearn(false);
                    break;
                case "4":
                    System.out.println("Type in set Id:");
                    int setId = Integer.parseInt(scan.nextLine());
                    if (setId > flashcardSets.size() - 1 || setId < 0) break;
                    FlashcardSet flipLearnSet = flashcardSets.get(setId);
                    flipLearnSet.flipCardLearn(false);
                    break;
                case "5":
                    System.out.println("Type in set Id:");
                    try {
                        int deletedSetId = Integer.parseInt(scan.nextLine());
                        if (deletedSetId > flashcardSets.size() - 1 || deletedSetId < 0) {
                            System.out.println("ID doesn't exist");
                            break;
                        }
                        flashcardSets.remove(deletedSetId);
                    }catch (NumberFormatException e){
                        System.out.println("Invalid input");
                        break;
                    }
                    break;
                case "q":
                        isRunning= false;
                    break;
                default:
                    break;
            }
        }
    }

  /*  public FlashcardSet getSet(List<FlashcardSet> flashcardSets ){
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in set Id:");
        int setId = Integer.parseInt(scan.nextLine());
        if (setId > flashcardSets.size() - 1 || setId < 0) break;
        FlashcardSet flipLearnSet = flashcardSets.get(setId);
        try {
            flipLearnSet.flipCardLearn(false);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return
    }*/
}
