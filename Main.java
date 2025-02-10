import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner(System.in);

        //1. Develop a program that takes the weight (in kilograms) and height (in meters) as input and calculates the BMI, then prints it.
        //• Input: Weight (kg) = 70, Height (m) = 1.75
        //• Expected Output: BMI = 22.86

        System.out.println("Enter Weight in KG: ");
        int weight = data.nextInt();
        System.out.println("Enter Height in Meter: ");
        float height = data.nextFloat();
        System.out.println("Your BMI is: " + (weight / (height*height)));

        //2.Write a program that takes the obtained marks and total marks as input and calculates the percentage, then prints it.
        //• Input: Obtained Marks = 85, Total Marks = 100
        //• Expected Output: Percentage = 85.0%

        System.out.println("Enter obtained marks: ");
        float obtainedMarks = data.nextFloat();
        System.out.println("Enter total marks: ");
        float totalMarks = data.nextFloat();
        float percentage = (obtainedMarks/totalMarks) * 100;
        System.out.println("Your marks percentage is: " + percentage +"%");

        //3. Create a program that takes an amount in one currency and an exchange rate as input, then converts and prints the amount in another currency.
        //• Input: Amount in USD = 100, Exchange Rate (USD to EUR) = 0.85
        //• Expected Output: Amount in EUR = 85.0

        System.out.println("Enter amount in first currency: ");
        float amountOfFirstCurrency = data.nextFloat();
        System.out.println("Enter the currency exchange: ");
        float exchangeRate = data.nextFloat();
        float amountOfNewCurrency = amountOfFirstCurrency * exchangeRate;
        System.out.println("Amount of new currency " + amountOfNewCurrency);

        //4. Create a program that takes a string as input, calculates its length, and then reverses the string using the StringBuilder class, finally printing both the length and reversed string.
        //• Input: "Hello, World!"
        //• Expected Output: Length of the string: 13 And Reversed string: "!dlroW ,olleH"

        data.nextLine();
        System.out.println("Enter a String: ");
        String sentence = data.nextLine();
        int stringLength = sentence.length();
        StringBuilder sb = new StringBuilder(sentence);
        System.out.println("Length of the string : " + stringLength);
        System.out.println("Reversed String : " + sb.reverse());

        //5. Develop a program that takes a sentence as input and extracts a substring from it, then prints the extracted substring.
        //• Input: Sentence = "The quick brown fox jumps over the lazy dog", Start Index = 10, End Index = 20
        //• Expected Output: "brown fox"

        System.out.println("Enter a sentence: ");
        String sentence_2 = data.nextLine();
        System.out.println("Enter start index: ");
        int startIndex = data.nextInt();
        System.out.println("Enter end index: ");
        int endIndex = data.nextInt();
        System.out.println("The sentence after extraction : " + sentence_2.substring(startIndex,endIndex));

        //6. Write a program that takes a sentence and a keyword as input, then check if the keyword is present in the sentence and prints the result.
        //• Input: Sentence = "The quick brown fox jumps over the lazy dog", Keyword = "jumps"
        //• Expected Output: Keyword "jumps" is present in the sentence.

        data.nextLine();
        System.out.println("Enter a sentence : ");
        String sentence_3 = data.nextLine();
        System.out.println("Enter a keyword : ");
        String keyword = data.nextLine();
        System.out.println("Is the keyword (" + keyword + ") present in the sentence? " + sentence_3.contains(keyword));

        //7. Develop a program that takes a sentence and a word to replace as input, then replace all occurrences of the word with another word and prints the modified sentence.
        //• Input: Sentence = "The quick brown fox jumps over the lazy dog", Word to Replace = "fox", Replacement Word = "cat"
        //• Expected Output: "The quick brown cat jumps over the lazy dog"

        System.out.println("Enter the sentence: ");
        String sentence_4 = data.nextLine();
        System.out.println("Enter the word to be replaced: ");
        String wordToReplace = data.nextLine();
        System.out.println("Enter the sentence: ");
        String replacementWord = data.nextLine();
        System.out.println("The sentence after replacement: " + sentence_4.replaceAll(wordToReplace,replacementWord));

        //8. Write a program that takes two strings as input and check if they are equal, ignoring the case, then prints whether they are equal or not.
        //• Input: String 1 = "Hello", String 2 = "hello"
        //• Expected Output: Strings are equal (ignoring case).

        System.out.println("Enter first string: ");
        String firstString = data.nextLine();
        System.out.println("Enter second string: ");
        String secondString = data.nextLine();
        System.out.println("Are the two string equal ignoring case : " + firstString.equalsIgnoreCase(secondString));

    }
}