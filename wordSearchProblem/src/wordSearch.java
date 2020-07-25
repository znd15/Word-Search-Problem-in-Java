import java.io.BufferedReader;
import java.lang.instrument.IllegalClassFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class wordSearch {

    /** Reads the board from wordBoard.txt     */
    public  ArrayList<String> ReadBoardFromFile() {
        String fileName = "wordBoard.txt";
        String line = null;
        ArrayList<String> Board = new ArrayList<>();
        int lengthCheck = 0;
        try {

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                Board.add(line.replaceAll(" ", "").toUpperCase());
                if(lengthCheck == 0)
                    lengthCheck = line.length();
                else if(lengthCheck != line.length()) {
                    throw new IllegalClassFormatException();
                }

            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println( "Unable to open file '" + fileName + "'");

        }
        catch(IOException ex) {
            System.out.println( "Error reading file '" + fileName + "'");

        } catch (IllegalClassFormatException ex) {
            System.out.println(" Board format problem");

        }
        return Board;
    }

    /** Insert the word to be found in Node */
    public void insert(wordNode root, String Key)
    {
        int n = Key.length();
        wordNode tempNode = root;
        for (int i=0; i<n; i++)
        {
            int index = Key.charAt(i) - 'A';

            if (tempNode.Child[index] == null)
                tempNode.Child[index] = new wordNode();

            tempNode = tempNode.Child[index];
        }
        tempNode.leaf = true;
    }


    boolean CheckValid(int i, int j, boolean checked[][], int M, int N)
    {
        return (i >=0 && i < M && j >=0 && j < N && !checked[i][j]);
    }

    /** search for the word */
    boolean searchWord(wordNode root, ArrayList<String> board, int i, int j, boolean checked[][], String str)
    {
        int M = board.size(), N =board.get(0).length();
        boolean check = false;
        if (root.leaf == true)
            check = true;

        if (CheckValid(i, j, checked, M, N))
        {
            checked[i][j] = true;
            for (int K =0; K < 26; K++)
            {
                if (root.Child[K] != null)
                {
                    char ch = (char) (K + 'A') ;

                    if (CheckValid(i, j+1,checked, M, N) && board.get(i).charAt(j+1) == ch)
                        check = searchWord(root.Child[K],board,i, j+1, checked,str+ch);
                    if (CheckValid(i+1,j, checked, M, N) && board.get(i+1).charAt(j) == ch)
                        check = searchWord(root.Child[K],board,i+1, j, checked,str+ch);
                    if (CheckValid(i, j-1,checked, M, N) && board.get(i).charAt(j-1) == ch)
                        check = searchWord(root.Child[K],board,i ,j-1, checked,str+ch);
                    if (CheckValid(i-1, j,checked, M, N) && board.get(i-1).charAt(j) == ch)
                        check = searchWord(root.Child[K],board,i-1, j, checked,str+ch);
                }
            }
            checked[i][j] = false;
        }
        return check;
    }

    /** find words in the board by looping   */
    public boolean findWords(ArrayList<String> board, wordNode root)
    {
        int M = board.size(), N =board.get(0).length();
        boolean[][] checked = new boolean[M][N];
        wordNode tempNode = root ;
        boolean check = false;
        String str = "";
        for (int i = 0 ; i < M; i++)
        {
            for (int j = 0 ; j < N ; j++)
            {
                if (tempNode.Child[(board.get(i).charAt(j)) - 'A'] != null)
                {
                    str = str+board.get(i).charAt(j);
                    check = searchWord(tempNode.Child[(board.get(i).charAt(j)) - 'A'],board, i, j, checked, str);
                    str = "";
                }
            }
        }
        return check;
    }
}
