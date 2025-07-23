package Codsoft_Task_4;

import java.util.*;

public class QuizApp {
    static class Question {
        String question;
        String[] options;
        char correctOption;

        Question(String question, String[] options, char correctOption) {
            this.question = question;
            this.options = options;
            this.correctOption = correctOption;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("1. What is the brain of the computer?", new String[]{"A. Monitor", "B. CPU", "C. Keyboard", "D. RAM"}, 'B'));
        questions.add(new Question("2. Which part of the computer is used to store data permanently?", new String[]{"A. RAM", "B. SSD", "C. ROM", "D. Hard Disk"}, 'D'));
        questions.add(new Question("3. What does RAM stand for?", new String[]{"A. Read Access Memory", "B. Random Access Memory", "C. Ready and Manage", "D. Run Access Module"}, 'B'));
        questions.add(new Question("4. Which device is used for input?", new String[]{"A. Speaker", "B. Monitor", "C. Keyboard", "D. Printer"}, 'C'));
        questions.add(new Question("5. What is used to connect to the internet?", new String[]{"A. Modem", "B. UPS", "C. CPU", "D. USB"}, 'A'));

        int score = 0;
        int questionNumber = 1;

        System.out.println("🧠 Welcome to the Java Quiz Application!");
        System.out.println("⏱️ You have 10 seconds to answer each question.");
        System.out.println("💡 Type A, B, C, or D and press Enter to submit your answer.\n");

        for (Question q : questions) {
            System.out.println(q.question);
            for (String opt : q.options) {
                System.out.println(opt);
            }

            TimerTask task = new TimerTask() {
                public void run() {
                    System.out.println("\n⏰ Time's up!");
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 20000); // 10 seconds timer

            System.out.print("Your answer: ");
            char answer = scanner.next().toUpperCase().charAt(0);
            timer.cancel(); // cancel timer if answered

            if (answer == q.correctOption) {
                System.out.println("✅ Correct!\n");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.correctOption + "\n");
            }
            questionNumber++;
        }

        System.out.println("🎉 Quiz Finished!");
        System.out.println("📊 Your Score: " + score + "/" + questions.size());
        System.out.println("✅ Correct Answers: " + score);
        System.out.println("❌ Incorrect Answers: " + (questions.size() - score));

        scanner.close();
    }
}
