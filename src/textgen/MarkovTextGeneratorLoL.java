package textgen;

import java.util.*;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
    private List<ListNode> wordList;

    // The starting "word"
    private String starter;

    // The random number generator
    private Random rnGenerator;

    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<ListNode>();
        starter = "";
        rnGenerator = generator;
    }


    /**
     * Train the generator by adding the sourceText
     */
    @Override
    public void train(String sourceText) {
        String[] textWords = sourceText.split(" ");
        List<String> words = new ArrayList<>();
        for (String s : textWords) {
            if (s != null && s.length() > 0) {
                words.add(s);
            }
        }
        if (words.size() > 0) {
            starter = words.get(0);
            String prevWord = starter;
            for (int i = 1; i < words.size(); i++) {
                int index = exists(prevWord);
                if (index != -1) {
                    ListNode node = wordList.get(index);
                    node.addNextWord(words.get(i));
                } else {
                    ListNode node = new ListNode(prevWord);
                    wordList.add(node);
                    node.addNextWord(words.get(i));
                }
                prevWord = words.get(i);
            }
            // Checking last word
            String lastWord = words.get(words.size() - 1);
            int index = exists(lastWord);
            if (index != -1) {
                ListNode node = wordList.get(index);
                node.addNextWord(starter);
            } else {
                ListNode node = new ListNode(lastWord);
                wordList.add(node);
                node.addNextWord(starter);
            }
        }
    }

    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        // TODO: Implement this method
        String outputText = "";
        if (numWords > 0) {
            String currentWord = starter;
            outputText += currentWord;
            for (int i = 1; i < numWords; i++) {
                int index = exists(currentWord);
                if (index != -1) {
                    ListNode node = wordList.get(index);
                    String word = node.getRandomNextWord(rnGenerator);
                    outputText += " " + word;
                    currentWord = word;
                }
            }
        }
        return outputText;
    }


    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /**
     * Retrain the generator from scratch on the source text
     */
    @Override
    public void retrain(String sourceText) {
        // TODO: Implement this method.
        wordList.clear();
        starter = "";
        String[] textWords = sourceText.split(" ");
        List<String> words = new ArrayList<>();
        for (String s : textWords) {
            if (s != null && s.length() > 0) {
                words.add(s);
            }
        }
        if (words.size() > 0) {
            starter = words.get(0);
            String prevWord = starter;
            for (int i = 1; i < words.size(); i++) {
                int index = exists(prevWord);
                if (index != -1) {
                    ListNode node = wordList.get(index);
                    node.addNextWord(words.get(i));
                } else {
                    ListNode node = new ListNode(prevWord);
                    wordList.add(node);
                    node.addNextWord(words.get(i));
                }
                prevWord = words.get(i);
            }
            // Checking last word
            String lastWord = words.get(words.size() - 1);
            int index = exists(lastWord);
            if (index != -1) {
                ListNode node = wordList.get(index);
                node.addNextWord(starter);
            } else {
                ListNode node = new ListNode(lastWord);
                wordList.add(node);
                node.addNextWord(starter);
            }
        }
    }

    // TODO: Add any private helper methods you need here.
    private int exists(String word) {
        for (int i = 0; i < wordList.size(); i++) {
            ListNode node = wordList.get(i);
            if (node.getWord().equals(word)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This is a minimal set of tests.  Note that it can be difficult
     * to test methods/classes with randomized behavior.
     *
     * @param args
     */
    public static void main(String[] args) {
        // feed the generator a fixed random value for repeatable behavior
        MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
        String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        System.out.println(textString);
        gen.train(textString);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
        String textString2 = "You say yes, I say no, " +
                "You say stop, and I say go, go, go, " +
                "Oh no. You say goodbye and I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "I say high, you say low, " +
                "You say why, and I say I don't know. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "Why, why, why, why, why, why, " +
                "Do you say goodbye. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "You say yes, I say no, " +
                "You say stop and I say go, go, go. " +
                "Oh, oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello,";
        System.out.println(textString2);
        gen.retrain(textString2);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
    }

}

/**
 * Links a word to the next words in the list
 * You should use this class in your implementation.
 */
class ListNode {
    // The word that is linking to the next words
    private String word;

    // The next words that could follow it
    private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<String>();
    }

    public String getWord() {
        return word;
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        // TODO: Implement this method
        // The random number generator should be passed from
        // the MarkovTextGeneratorLoL class
        int index = generator.nextInt(nextWords.size());
        if (nextWords.size() > 0) {
            return nextWords.get(index);
        } else {
            return null;
        }

    }

    public String toString() {
        String toReturn = word + ": ";
        for (String s : nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }

}


