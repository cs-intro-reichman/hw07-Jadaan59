
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	// tail(a), is the word a, excluding the first character. //
	public static String tail(String str) {
		return str.substring(1);
	}

	/*
Is a string metric for measuring the difference between two strings.
	The distance between two words is the minimum number of single-character edits
	 (insertions, deletions or substitutions) needed to transform one word into the other word.
	 levenshtein(“hello”, “hell”); // 1 (one deletion)
	 levenshtein(“hello”, “hell0”); // 1 (one substitution)
 	 levenshtein(“love”, “i”); // 4 (one substitution, three deletions)
	 levenshtein(“concensus”, “consensus”); // 1
	 */
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word2.length() == 0){
			return word1.length();
		}if (word1.length() == 0){
			return word2.length();
		}if (word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		}else
			return (1 + Math.min     (levenshtein(tail(word1), word2),
						Math.min     (levenshtein(word1,tail(word2)),
								 levenshtein(tail(word1),tail(word2)))));
	}

	/*read a local file of words, and store them in an array. */
	public static String[] readDictionary(String fileName) {
				//linoy feedback: this is the same method as earlier, why don't you use one method for both of the classes? 

		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	/*
	 * receives a word, a threshold value for distance, and a dictionary as inputs.
	 * It returns the word from the dictionary that most closely resembles the given word.
	 */
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String bestMatch = word;
		int bestDistance = threshold;

		for (String dictionaryWord : dictionary) {
			int distance = levenshtein(word, dictionaryWord);
			if (distance <= bestDistance) {
				bestMatch = dictionaryWord;
				bestDistance = distance;
			}
		}

		return bestMatch;
	}

}
