
package Codsoft_Task_110;

import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        final int MAX_TRIES = 7;
        int totalScore = 0;
        boolean keepPlaying;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            int numberToGuess = rand.nextInt(100) + 1; 
            int attempts = 0;
            boolean isGuessed = false;

            System.out.println("\nI've picked a number between 1 and 100.");
            System.out.println("You have " + MAX_TRIES + " chances to guess it!");

            while (attempts < MAX_TRIES) {
                System.out.print("Enter your guess: ");
                int userGuess = input.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Nice! You got it in " + attempts + " tries.");
                    totalScore++;
                    isGuessed = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low, try again.");
                } else {
                    System.out.println("Too high, try again.");
                }
            }

            if (!isGuessed) {
                System.out.println("Oops! The correct number was: " + numberToGuess);
            }

            System.out.print("Play again? (yes/no): ");
            String userChoice = input.next();
            keepPlaying = userChoice.equalsIgnoreCase("yes");

        } while (keepPlaying);

        System.out.println("\nGame over. Your score: " + totalScore);
        System.out.println("Thanks for playing!");
        input.close();
    }
}

