public class wordNode {

        wordNode[] Child = new wordNode[26];
        boolean leaf;

        public wordNode() {
            leaf = false;
            for (int i = 0 ; i < 26 ; i++)
                Child[i] = null;
        }

}
