import java.util.Locale;

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	
	public static String[] readDictionary(String fileName) {
		//linoy feedback: who said you have 3000 lines? why not use a while loop?
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	/**
	 * Take a string as an input and determine its presence in the dictionary.
	 * It returns true if the word is found within the dictionary array,
	 * and false if it is not.
	 * @param word
	 * @param dictionary
	 */
	public static boolean existInDictionary(String word, String []dictionary) {
		boolean isExist = false;
		for (int i = 0; i < dictionary.length; i++){
			if (word.equals(dictionary[i])){
				isExist = true;
				//linoy feedback: if isExist is true, why do you continue checking? You check return true and stop the loop.
			}
			}
		return isExist;
		}



	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.

		//linoy feedback: this checking is great ! but in thee 47 line you are doing manipulation about hashtag that will cause a problem if it is empty. 
		//linoy feedback: change the order of the fucntion: 
		// if (hashtag.isEmpty()) {
            			//	return;
        			//	}
		//hashtag = hashtag.toLowerCase();
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtag.substring(0,i), dictionary)){
				System.out.println(hashtag.substring(0,i));
				breakHashTag(hashtag.substring(i), dictionary);
				return;
			}
        }
    }
}
