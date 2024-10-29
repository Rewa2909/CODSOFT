import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    int rightAnswer;

    public Question(String question, String[] options, int rightAnswer) {
        this.question = question;
        this.options = options;
        this.rightAnswer = rightAnswer;
    }
}

public class QuizApplication {
    private static Question[] questions = {
        new Question("What is the capital of India?", new String[]{"1. Bombay", "2. Maharashtra", "3. Delhi", "4. Rajasthan"}, 3),
        new Question("What is 2 + 2?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 2),
        new Question("Who plays Chandler in series 'FRIENDS'?", new String[]{"1. Chris Hemsworth", "2. Matt Leblanc", "3. Mathew Perry", "4. Mark Twain"}, 3)
    };
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            answered = false;
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println(currentQuestion.question);
            for (String option : currentQuestion.options) {
                System.out.println(option);
            }

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime is up...");
                        nextQuestion();
                    }
                }
            }, 10000); 

            System.out.print("Your answer: ");
            int answer = scanner.nextInt();
            answered = true;
            timer.cancel();

            if (answer == currentQuestion.rightAnswer) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong! The correct answer was " + currentQuestion.rightAnswer + ".\n");
            }
        }

        System.out.println("Quiz over! Your score: " + score + "/" + questions.length);
        scanner.close();
    }

    private static void nextQuestion() {
        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            Question nextQuestion = questions[currentQuestionIndex];
            System.out.println(nextQuestion.question);
            for (String option : nextQuestion.options) {
                System.out.println(option);
            }
        } else {
            System.out.println("Quiz over! Your score: " + score + "/" + questions.length);
        }
    }
}

