package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the number of cards:");
        int leng = scanner.nextInt();
        scanner.nextLine();

        Card[] cards = new Card[leng];
        for (int i = 0; i < cards.length; i++) {
            System.out.println("The card #" + (i + 1) + "!");
            String term = scanner.nextLine();

            System.out.println("The definition of the card #" + (i + 1) + "!");
            String definition = scanner.nextLine();
            cards[i] = new Card(term,definition);
        }
        for (int i = 0; i < cards.length; i++) {
            System.out.println("Print the definition of " +'"'+cards[i].name+ '"'+"!");
            String answer = scanner.nextLine();
            if (answer.equals(cards[i].definition)) {
                System.out.println("Correct answer");
            } else {
                System.out.println("Wrong answer. The correct one is "+'"'+cards[i].definition+'"');
            }
        }
    }

}
