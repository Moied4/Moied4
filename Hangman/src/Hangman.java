		// In this game player 1 chooses word player 2 must guess

import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Word to guess input is converted to lower case for simplicity of case insensitivity 
        System.out.print("Player 1, enter the word to guess (letters only, max 10 characters): ");
        String wordToGuess = scanner.nextLine().toLowerCase();

        // while loop for the input if guess does not match 
        while (!wordToGuess.matches("[a-zA-Z]+") || wordToGuess.length() > 10) {
            System.out.print("Invalid input. Enter letters only (max 10 characters): ");
            wordToGuess = scanner.nextLine().toLowerCase();
        }

        //  Clear screen by printing 20 blank lines to hide guessword from player 2 after player 1 inputed on console
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }

        //  Initialize game conditions so tried letters will be removed from available letters 
        String guessedWord = "*".repeat(wordToGuess.length());
        String availableLetters = "abcdefghijklmnopqrstuvwxyz";
        String triedLetters = "";
        int guessesLeft = 10;

        // While guesses are left and letter is in guessword loop through word and reveal matching letters
        while (guessesLeft > 0 && guessedWord.contains("*")) {
            System.out.println("\nWord to guess: " + guessedWord);
            System.out.println("Guesses left: " + guessesLeft);
            System.out.println("Available letters: " + availableLetters);
            System.out.print("Guess a letter or the full word: ");

            String guess = scanner.nextLine().toLowerCase();

            // if condition defines the loop in case letter correct or whole word is guessed it breaks loop 
           //else wrong letter deincrements guessesLeft int
            if (guess.length() == wordToGuess.length() && guess.matches("[a-zA-Z]+")) {
                if (guess.equals(wordToGuess)) {
                    guessedWord = wordToGuess;
                    break;
                } else {
                    guessesLeft--;
                    System.out.println("Incorrect word guess.");
                    continue;
                }
            }

            // For dealing with invalid input neither a word nor 1 letter guess since letter guess must be char at some string index
            if (guess.length() != 1 || !guess.matches("[a-zA-Z]")) {
                System.out.println("Invalid input. Enter a single letter or guess the full word.");
                continue;
            }

            char letter = guess.charAt(0);

            // if condition if letter already tried on one of the indices from 0 system outputs message
            if (triedLetters.indexOf(letter) >= 0) {
                System.out.println("Letter already tried!");
                continue;
            }

            // Record letter as tried and update available letters by removing that letter
            triedLetters += letter;
            availableLetters = availableLetters.replace(letter, '.');

            // if condition for when guessed letter is in the word
            if (wordToGuess.indexOf(letter) >= 0) {
                String updatedGuessedWord = "";

                
                for (int i = 0; i < wordToGuess.length(); i++) {
                    updatedGuessedWord += (wordToGuess.charAt(i) == letter)
                        ? letter
                        : guessedWord.charAt(i);
                }

                guessedWord = updatedGuessedWord;
                System.out.println("Good guess!");
            } else {
                guessesLeft--;
                System.out.println("Sorry, letter not in word.");
            }
        }

        //  End of game displays message for wether player guessed word or not
        System.out.println("\n  Game Over  ");
        System.out.println(
            guessedWord.equals(wordToGuess)
                ? "Congratulations! You guessed the word: " + wordToGuess
                : "Out of guesses... The word was: " + wordToGuess
        );

        scanner.close();
    }
}
