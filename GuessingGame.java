package guessinggame;
/*
* Java game "Guess a Number" that allows user to guess a random number that has been generated (between 1-10)
* Version: 0.0.1
* Author: Brandon Grunes
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGame {
    private JPanel mainPanel;
    private JTextField tfBestScore;
    private JTextField tfGuesses;
    private JTextField tfUserField;
    private JLabel lbResponse;
    private JButton btnGuess;
    private JButton btnPlayAgain;
    private JButton btnGiveUp;

    static JFrame frame = new JFrame("Guessing Game");
    static ImageIcon icon;
    // Create instance of Random class
    static Random rng = new Random();

    // Generate random number between 1 and 10
    static int number = rng.nextInt(10) + 1;

    // User defined variable
    int userAnswer = 0;

    int guesses = 0;
    int bestScore = 0;

    public GuessingGame() {
        btnGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userAnswer= Integer.parseInt(tfUserField.getText());
                if(userAnswer == 0 || userAnswer > 10) {
                    System.err.println("User entered " + userAnswer + ". Not viable response!");
                    lbResponse.setText(userAnswer + " is not between 1-10!");
                }
                else if (userAnswer == number) {
                    lbResponse.setText("Congrats! You guessed correctly!");
                    System.out.println("User guessed " + number + ". Correct!");

                    // If the best score is higher than the number of guesses, user achieved new best score
                    if (bestScore > guesses || bestScore == 0) {
                        bestScore = guesses;
                        tfBestScore.setText(String.valueOf(bestScore));
                    }
                    btnPlayAgain.doClick();
                }
                else if (userAnswer != number) {
                    lbResponse.setText("Wrong Number! Try guessing again!");
                    System.err.println("User guessed " + userAnswer + ". Incorrect!");
                    guesses++;
                    tfGuesses.setText(String.valueOf(guesses));
                }
                else if (tfUserField.getText() == "") {
                    lbResponse.setText("You did not enter your guess into the text box!"
                                        + "Please try again!");
                    System.err.println("User did not use text box!");
                }
            }
        });
        btnPlayAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Do you want to play again?",
                        "Play Again", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(result == JOptionPane.YES_OPTION) {
                    reset();
                    System.out.println("Number is " + number);
                }

            }
        });
        btnGiveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame, "Give Up?",
                        "Are you sure you want to Give Up?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if(result == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "The Correct Number was " + number, "Correct Answer", JOptionPane.PLAIN_MESSAGE);

                }
            }
        });
    }

    public static void main(String[] args) {
        // Create instance of JFrame
        displayJFrame();

        System.out.println("Number is " + number);
    }

    static void displayJFrame() {
        frame.setContentPane(new GuessingGame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        icon = new ImageIcon("imgs/question_mark_icon.jfif");
        frame.setIconImage(icon.getImage());
        frame.setVisible(true);
        frame.pack();
    }

    void clearTextFields() {
        lbResponse.setText("");
        tfUserField.setText("");
        tfGuesses.setText("");
    }

    void reset() {
        clearTextFields();
        guesses = 0;
        number = rng.nextInt(10) + 1;
    }
}
