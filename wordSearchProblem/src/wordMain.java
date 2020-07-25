import java.util.ArrayList;
import java.util.Scanner;

public class wordMain {

        public static void main(String[] args) {

            wordSearch controller = new wordSearch();
            Scanner input = new Scanner(System.in);
            String word = "1";
            ArrayList<String> Board = controller.ReadBoardFromFile();
            System.out.println("*** Welcome to word search problem ***\n"
                    + "Please enter your board first in wordBoard.txt file;\n"
                    + "Now enter the word you want to search....");
            while(word != "0") {
                word = input.nextLine();
                wordNode root = new wordNode();
                controller.insert(root, word.toUpperCase());

                boolean check = controller.findWords(Board, root);
                System.out.println(check);
            }


        }

}
