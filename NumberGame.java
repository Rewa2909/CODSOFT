import java.util.*;


public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int numberOfAttempts = 0;
            boolean correctguess = false;
            int tries = 5;

            System.out.println("Guess a number between 1 and 100. You have " + tries + " attempts.");

            while (numberOfAttempts < tries && !correctguess) {
                System.out.print("Enter your guess: ");
                try {
                    if (scanner.hasNextInt()) {
                        int userGuess = scanner.nextInt();
                        numberOfAttempts++;

                        if (userGuess == numberToGuess) {
                            correctguess = true;
                            System.out.println("Congratulations! You guessed the correct number in " + numberOfAttempts + " attempts.");
                            totalScore += (tries - numberOfAttempts + 1); // Higher score for fewer attempts
                        } else if (userGuess < numberToGuess) {
                            System.out.println("Too low! Try again.");
                        } else {
                            System.out.println("Too high! Try again.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Clear the invalid input
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                } catch (NoSuchElementException e) {
                    System.out.println("No input available. Exiting.");
                    return; // Exit the program
                }
            }

            if (!correctguess) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Your total score is: " + totalScore);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}

