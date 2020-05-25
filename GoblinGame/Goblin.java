
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This is the goblin class that use to play the goblin game. This goblin class will set up everything that goblin game will need.
 * The game will play like a guess the word game.
 * Most methods in Goblin class is implemented by the methods that were implemented in BetterArray class.
 * @author Steve Tran.
 */
class Goblin {
	/**
	 * The Scanner that will be use to opem file.
	 */
	private Scanner userIn;
	/**
	 * This is the file path address. The File path address is a String type. Direction to file.
	 */
	private String dictFileName;
	/**
	 * Number of letters is int type. Use this to filter out words in the dictionary.
	 */
	private int numLetters;
	/**
	 * Number of guesses as an int type. Indicate how many guesses the users will have.
	 */
	private int numGuesses;

	/**
	 * Use to check the game win/lose condition.
	 */
	private int win = 0;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	 * This is the Dictionary of the Goblin.
	 */
	private BetterArray<String> words = new BetterArray<>();
	/**
	 * The letters that the player has guessed.
	 */
	private BetterArray<Character> guesses = new BetterArray<>();
	/**
	 * The current word that the goblin is using.
	 */
	private BetterArray<Character> currentWorkingWord = new BetterArray<>();
	/**
	 * The lockedLetter is the correct guesses, we want to lock it in.
	 */
	private BetterArray<Character> lockedLetter = new BetterArray<>();
	/**
	 * This method only get the current/filtered words.
	 *
	 * @return a copy of current words
	 */
	public BetterArray<String> getWords() {
		/*
		Returns a _copy_ of the goblin's _current_ dictionary.
		*/

		return words.clone();
	}

	/**
	 * This will get  the number of guesses to begin with.
	 *
	 * @return copy of guesses
	 */
	public BetterArray<Character> getGuesses() {



		/*
		Returns a _copy_ of the user's current guesses.
		*/

		return guesses.clone();
	}


	/**
	 * This will get the current letters that the user locked in for the word that is being play.
	 *
	 * @return a copy of the current word
	 */
	public BetterArray<Character> getCurrentWord() {

		/*
		Returns a _copy_ of the letters the user has locked in.
		Any slots not locked in, should be set to null.
		*/

		//looping through each null value
		for (int i = 0; i < numLetters; i++) {
			// loop until letter in the current word match letter guesses and replace it what the locked in letter
			for (int j = 0; j < lockedLetter.size(); j++) {
				if (getWords().get(0).charAt(i) == lockedLetter.get(j)) {
					currentWorkingWord.replace(i, lockedLetter.get(j));
				}
			}
		}


		return currentWorkingWord.clone();
	}

	/**
	 * Get the remaining number of guesses that the user has.
	 *
	 * @return remaining number of guesses
	 */
	public int getGuessesRemaining() {
		/*
		Returns the number of wrong guesses the user has left.
		*/

		return numGuesses;
	}

	/**
	 * The init() is a method will set the game up. Init() will check for validation and it will filter the words that we are not using out of the list.
	 * It will first check to see if the letters of the words is > 2.
	 * Then it will use try and catch to check if the file the user trying to open is valid.
	 * First we filter out the words that doesnt have the length as numLetter.
	 * Then 2 BetterArray list will be created. One will hold array of duplicate words. The other will holds all the words.
	 * Nested loop is being use here to look for duplicate words. If the word is duplicated, we the will add it to one of the array.
	 * After we finish the nested loop , we then will compare the loop with all the words to the loop with the duplicate words.
	 * Another for -loop will be use to remove the duplicate words from the array of full words.
	 * We then will clone that array and store it into Words into getWords();.
	 * We also appened null value to currentWord.
	 * @return true if everything works else @return false.
	 * @throws FileNotFoundException for invalid file.
	 */
	public boolean init() {
		/*
		Setup anything you need here. Check that the number of letters
		is valid (at least 2) and read in the dictionary. The dictionary
		contains main words which are not of the proper length, and manywith
		words with duplicate letters, don't add these to your goblin's list
		of options!

		If the dictionary can't be found, print the appropriate message
		and return false. If the dictionary contains no words of the given
		length, print the appropriate message and return false. If everything
		works, return true;
		Here are some quotes:
			"Goblin can't find enough words! You win!"
			"Goblin lost his dictionary! You win!"

		Can't remember how to use a Scanner?
		See: https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
		You'll want to use the following:
			- Scanner(File) <- constructor
			- hasNextLine()
			- nextLine()
		*/
		// check for valid number of letters
		if (numLetters >= 2) {
			try {
				String dupWords = "";
				BetterArray<String> listWithDup = new BetterArray<>();
				BetterArray<String> goblinList = new BetterArray<>();
				userIn = new Scanner(new File(dictFileName));
				String currentWord = "";
				while (userIn.hasNextLine()) {
					currentWord = userIn.nextLine();
					if (currentWord.length() == numLetters) {
						//Add all the words to the array after filtering out the words with the right numLetters
						goblinList.append(currentWord);
						//nested loop to find word with duplicated letters
						for (int i = 0; i < currentWord.length(); i++) {
							for (int j = i + 1; j < currentWord.length(); j++) {
								if (currentWord.charAt(i) == currentWord.charAt(j)) {
									// check to see if the word with duplicated letters is already in the string or not
									// add the word with duplicated letters to another array (use this array to filter these words out later)
									if (!dupWords.contains(currentWord)) {
										dupWords += currentWord + " ";
										listWithDup.append(currentWord);
									}
								}
							}
						}
					}
				}
				// After while loop
				//Filtering out he duplicate words from the array
				int x = 0;
				for (int i = 0; i < goblinList.size(); i++) {
					if (goblinList.get(i).equals(listWithDup.get(x))) {
						goblinList.delete(i);
						x++;
					}
				}
				words = goblinList.clone();

				// adding null to getCurrentWords
				for (int i = 0; i < numLetters; i++) {
					currentWorkingWord.append(null);
				}
				return true;
			}
			//no file found
			catch (Exception e) {
				System.out.println("Goblin lost his dictionary! You win!");
			}
		}
		// invalid number of words
		else {
			System.out.println("Goblin can't find enough words! You win!");
		}
		return false;
	}

	/**
	 * this method is use to divide the goblin current list of words into different categories based on numLetters
	 * created two BetterArray of BetterArray. One for Character, one for String.
	 * The one for character, we will use it to store the letters of the individual words. We do this so we can use the method firstIndexOf in BetterArray
	 * The one for string wil lbe the partitions
	 * We then will sort the words into the correct partition.
	 * Another BetterArray of String will be make to find the biggestPartition
	 * biggestPartition then will be clone into Words in getWords()
	 *
	 * @param guess use guess letter to put the words into the right partition
	 * @return -1 for no letter found, -1 if biggestPartition is in block 0,  partition size for tie,
	 */
	public int bestPartition(char guess) {
		/*
		Divide (partition) the goblin's _current_ dictionary into X+1 lists where:
			X is the number of letter positions
			The +1 is for words where the letter doesn't occur

		Pick the biggest partition as the new dictionary and set that as the
		goblin's current dictionary. If the goblin picks a partition with the
		chosen letter (guess), return the position of that letter. If the goblin
		picks a partition without the chosen letter, return -1.

		If there are ties for partition size, choose the one with the earlier
		letter slot.
		*/
		int partitionSize = numLetters + 1;
		BetterArray<BetterArray<Character>> characterList = new BetterArray<>();
		BetterArray<BetterArray<String>> partitionList = new BetterArray<>(partitionSize);
		// System.out.println("Goblin says guess a letter: " + guess);
		//create a list of lists based on the length of the word.
		for (int i = 0; i < partitionSize; i++) {
			BetterArray<String> list = new BetterArray<>();
			partitionList.append(list);
		}

		//making a character list of lists with the length of the words in the dictionary
		for (int i = 0; i < getWords().size(); i++) {
			BetterArray<Character> characters = new BetterArray<>();
			characterList.append(characters);
		}

		//converting every words into characters and store in small lists of characters
		for (int i = 0; i < getWords().size(); i++) {
			for (int j = 0; j < getWords().get(i).length(); j++) {
				characterList.get(i).append(getWords().get(i).charAt(j));
			}
		}

		//sorting the words into the correct partition
		for (int i = 0; i < getWords().size(); i++) {
			if (characterList.get(i).firstIndexOf(guess) == -1) {
				partitionList.get(0).append(getWords().get(i));
			} else {
				partitionList.get(characterList.get(i).firstIndexOf(guess) + 1).append(getWords().get(i));
			}
		}


		BetterArray<String> temp = new BetterArray<>();
		//looking for the biggest partition
		int biggestPartition = 0;
		for (int i = 0; i < partitionList.size(); i++) {
			if (partitionList.get(i).size() > biggestPartition) {
				biggestPartition += partitionList.get(i).size();
				temp = partitionList.get(i);
			}

		}

		words = temp.clone();
		//  System.out.println(Words);
		if (temp != partitionList.get(0)) {
			return partitionList.firstIndexOf(temp) - 1;

		} else

			return -1;
	}


	/**
	 * This is where the Goblin will the game with the users.
	 * the Goblin will get the guess letter from the user then it will look at the current word to see if the letter is contain in the word
	 * users cannot enter more than 1 letter at a time, and the Goblin will also check to see if the guessed word is already in the its Guesses
	 * users will lose when they dont have anymore number of guesses
	 * @return true if the game still going @return false for game over
	 */
	public boolean step() {
		int count = 0;
		BetterArray<Character> guessLetter = new BetterArray<>();
		String guessString = "";
		Scanner scan = new Scanner(System.in);

		while(numGuesses > 0) {
			//Prompt and get a guess from the user.
			System.out.print("Goblin says, \"guess a letter: ");
			guessString = scan.next();
			//check for valid letter
			while(guessString.length()>1){
				System.out.print( "Goblin says \"One letter! Guess a single letter\": ");
				guessString = "";
				guessString = scan.next();
			}
			//check if the letter guessed yet or not
			while(getGuesses().firstIndexOf(guessString.charAt(0)) != -1){
				System.out.println("Goblin says \"You already guessed: " + getGuesses().toString() + "\nGuess a new letter\": ");
				guessString= scan.next();
			}
			char c = guessString.charAt(0);
			guesses.append(c);

			int x = guesses.size() - 1;
			guessLetter.append(guesses.get(x));
			// check to see if the guess letter is in the word or no
			int position = bestPartition(guessLetter.get(guessLetter.size() - 1));
			//if the letter wrong
			if (position == -1) {
				//  Guesses.append(guessLetter.get(0));
				numGuesses--;
				System.out.println("Goblin says \"No dice! " + getGuessesRemaining() + " wrong guesses left...\"");
			} else {
				//adding locked in letters to lockedLetter array
				//Guesses.append(guessLetter.get(0));
				count++;
				lockedLetter.append(guessLetter.get(guessLetter.size() - 1));
				System.out.println("Goblin says \"Yeah, yeah, it's like this: " + getCurrentWord().toString().replaceAll("null", "-").replaceAll("[ ,]", "") + "\"");
			}


			//game over when the user guessed all the letter
			if (count==getCurrentWord().size()) {
				win++;
				return false;

			}

			//game end when no more number of guesses left
			if (numGuesses == 0) {
				win--;
				return false;
			}
		}
		//return true for game still going on, false for game over
		return false;
	}

	/**
	 * The finish method will print out the method for win/lose condition based on the result form step().
	 */
	public void finish() {
		if(win ==1){
			System.out.println("Goblin says \"You win... here's your stuff. But I'll get you next time!\"");
		}
		else
		{
			System.out.println("Goblin says \"I win! I was thinking of the word '"+getWords().get(0)+"'");
			System.out.println("Your stuff is all mine... I'll come back for more soon!\"");
		}

		/*
		This will be called after step() returns false. Print the appropriate
		win/lose message here.


		Here are some quotes:
			"Goblin says \"You win... here's your stuff. But I'll get you next time!\""
			"Goblin says \"I win! I was thinking of the word '"+getWords().get(0)+"'"
			"Your stuff is all mine... I'll come back for more soon!\""
		*/



	}

	/**
	 * The goblin constructor take in the file path, number of letters that the word should be, and the number of guesses that the users will have.
	 * @param dictFileName this is the file path
	 * @param numLetters    this is the number of letters the words inside the dictionary should be narrow down to
	 * @param numGuesses    number of guesses that the users will have to begin with
	 */
	// --------------------------------------------------------
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add JavaDocs)
	// --------------------------------------------------------
	public Goblin(String dictFileName, int numLetters, int numGuesses) {
		this.userIn = new Scanner(System.in);
		this.dictFileName = dictFileName;
		this.numLetters = numLetters;
		this.numGuesses = numGuesses;
	}


	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	/**
	 * This is main to test for cases.
	 * @param args argument array String
	 */
	public static void main(String[] args) {

		//if you don't have the mini dictionary one folder above your
		//user folder, this won't work!

		/*
		Sample successful run with output:
			> java Goblin
			Goblin can't find enough words! You win!
			Goblin lost his dictionary! You win!
			Yay 1
			Yay 2
			Yay 3
			Yay 4
			Yay 5
		*/

		Goblin g1 = new Goblin("C:\\Users\\duong\\Desktop\\CS310\\project1r1\\dictionary-mini.txt", 3, 10);
		Goblin g2 = new Goblin("C:\\Users\\duong\\Desktop\\CS310\\project1r1\\dictionary-mini.txt", 6, 6);
		Goblin g3 = new Goblin("C:\\Users\\duong\\Desktop\\CS310\\project1r1\\dictionary.txt", 1, 6);
		Goblin g4 = new Goblin("banana.txt", 3, 3);

		if (g1.init() && g2.init() && !g3.init() && !g4.init()) {
			System.out.println("Yay 1");
		}




		if (g1.getGuessesRemaining() == 10 && g1.getWords().size() == 1
				&& g2.getGuessesRemaining() == 6 && g2.getWords().size() == 5
				&& g1.getGuesses().size() == 0 && g2.getCurrentWord().size() == 6) {
			System.out.println("Yay 2");
		}


		BetterArray<Character> g1word = g1.getCurrentWord();
		if (g1word.get(0) == null && g1word.get(1) == null && g1word.get(2) == null) {
			System.out.println("Yay 3");
		}

		//remember what == does on objects... not the same as .equals()


		if (g1.getWords() != g1.getWords() && g1.getGuesses() != g1.getGuesses()
				&& g1.getCurrentWord() != g1.getCurrentWord()) {
			System.out.println("Yay 4");
		}


		if (g2.bestPartition('l') == -1 && g2.getWords().size() == 3
				&& g2.bestPartition('p') == 0 && g2.getWords().size() == 2
				&& g2.bestPartition('n') == -1 && g2.getWords().size() == 1) {
			System.out.println("Yay 5");
		}

		System.out.println("--------------------------------");
		g2.step();
		g2.finish();

		//can't test step() or finish() this way... requires user input!
		//maybe you need to test another way...
	}
}
