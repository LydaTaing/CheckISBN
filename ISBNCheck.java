

import java.util.Scanner;

public class ISBNCheck {

	public static void main(String[] args) {
		// calling the information of the program.
		info();
		Scanner console = new Scanner(System.in);
		System.out.print("Enter ISBN number(Enter Q to exit): ");
		String isbn = console.next();
		//Beside "q" or "Q", the program will run.
		while (!isbn.equalsIgnoreCase("q")) {
			
			//calling each method to list all the reasons if isbn number is invalid.
			countDash(isbn);
			dashPosition(isbn);
			sequentialDash(isbn);
			countdigit(isbn);
			checkletter(isbn);
			if (!countDash(isbn) && !dashPosition(isbn) && !sequentialDash(isbn) && !countdigit(isbn)
					&& !checkletter(isbn)) {
				if(checksum(isbn)) {
					System.out.println(isbn + " is valid.");
				
				}else {
					System.out.println(isbn + " is invalid; checksum is wrong.");
				}
			}
			System.out.print("Enter ISBN number(Enter Q to exit): ");
			isbn = console.next();
		}
		console.close();
		//My personal reflection will be shown after the user stop using the program.
		reflections();
	}

	//This method is the last part of checking ISBN number if user input the right form of the
	//number, but it check whether it follow the rule of checksum. 
	//The  checksum  is determined  from  the  first  9  digits;  it  is  computed  by  taking 
	//modulo  11  (the remainder  after  dividing  by  11)  of  the  sum  of  each  digit  
	//multiplied  by  its position in the ISBN.
	private static boolean checksum(String isbn) {
		char[] list = createArray(isbn);
		int last = Character.getNumericValue(isbn.charAt(isbn.length() - 1));
		if (isbn.charAt(isbn.length() - 1) == 'x' || isbn.charAt(isbn.length() - 1) == 'X') {
			last = 10;
		}
		int sum = 0;
		for (int i = 0; i < list.length-1; i++) {
			int value = Character.getNumericValue(list[i]);
			sum += value * (i + 1);	
		}		
		int check = sum % 11;
		
		//while it equal to last, isbn has to meet all the requirements to be valid.
			if (check == last) {
				//System.out.println(isbn + " is valid.");
				return true;
				}
			else {
				//System.out.println(isbn + " is invalid; checksum is wrong.");
				return false;
			}		
	}
	
	//Create a array of isbn digit numbers.
	private static char[] createArray(String isbn) {
		int dash = findDash(isbn);
		int index = 0;
		int count = 0;
		for (int i = 0; i < isbn.length() ; i++) {
			if (Character.isDigit(isbn.charAt(i)) || isbn.charAt(i)=='x' || isbn.charAt(i)=='X') {
				count++;
			}
		}
		//in order to create a list, I find all digit number in isbn to avoid the out of range error.
		char[] data = new char[count];
		
		if (dash==3) {
			for (int i = 0; i <= count; i++) {
				if (Character.isDigit(isbn.charAt(i))) {
					data[index] = isbn.charAt(i);
					index++;
				}
			}
			
		}else if(dash==0) {
			for (int i = 0; i < count; i++) {
				if (Character.isDigit(isbn.charAt(i))) {
					data[index] = isbn.charAt(i);
					index++;
				}
			}
		}	
		//data is the array of the isbn digit.
		return data;
	}

	private static boolean countdigit(String isbn) {
		int count = 0;
		for (int i = 0; i < isbn.length(); i++) {
			if (Character.isDigit(isbn.charAt(i)) || isbn.charAt(i) == 'x' || isbn.charAt(i) == 'X') {
				count++;
			}
		}
		// count the digit and set the condition.
		if (count > 10) {
			System.out.println(isbn + " is invalid; too many digits.");
			return true;
		} else if (count < 10 && count > 0) {
			System.out.println(isbn + " is invalid; too few digits.");
			return true;
		}
		return false;
	}

	private static boolean checkletter(String isbn) {

		for (int i = 0; i < isbn.length() - 1; i++) {
			if (Character.isLetter(isbn.charAt(i)) && isbn.charAt(i) != 'X') {
				System.out.println(isbn + " is invalid; bad digit.");
				return true;
			}
		}
		return false;
	}

	private static boolean sequentialDash(String isbn) {
		for (int i = 0; i < isbn.length() - 1; i++) {
			if (isbn.charAt(i) == '-' && isbn.charAt(i + 1) == '-') {
				System.out.println(isbn + " is invalid; sequentail dashes.");
				return true;
			}
		}
		return false;
	}

	private static boolean dashPosition(String isbn) {
		if (isbn.charAt(0) == '-' || isbn.charAt(isbn.length() - 1) == '-') {
			System.out.println(isbn + " is invalid; begining or ending with dash.");
			return true;
		}
		return false;
	}

	private static boolean countDash(String isbn) {

		//calling the number of dush in the isbn
		int dash = findDash(isbn);
		if (dash > 3) {
			System.out.println(isbn + " is invalid; too many dashes.");
			return true;
		} else if (dash < 3 && dash > 0) {
			System.out.println(isbn + " is invalid; not enough dashes.");
			return true;
		}
		return false;
	}

	private static int findDash(String isbn) {
		int dash = 0;
		for (int i = 0; i < isbn.length(); i++) {
			if (isbn.charAt(i) == '-') {
				dash++;
			}
		}
		return dash;
	}

	private static void info() {
		System.out.println("Lyda Taing \nCSC 142 \nNovember 29, 2017 \nAssignment #3 \nISBNCheck.java"
				+ "\nThis program will validate ISBNs and state ISBNs if it is valid or invalid."
				+ "\nThe ISBNs number are entered by user and the program will run as long as "
				+ "\nthe user enters ISBN numbers to validate."
				+ "\nThe program will exit itself when the user enter \"q\" or \"Q\". ");
	}

	private static void reflections() {
		System.out.println("In this assignment, it is hard, but not a hundered percent hard because this program "
				+ "\nrequres to understand the invalidate condition and analyze the array. "
				+ "\nIt is interesting that the character can convert into the number where"
				+ "\nI found it on the internet. I improve a lot with boolean and return statement"
				+ "\nand control the array item and lenght. During coding this program, I think my"
				+ "\nbest source is textbook, but discuss with classmate and eardrop to classmate "
				+ "\ndiscussion about this pragram help me a lot on some bugs and issuse in my codes."
				+ "\nThis assisngment took me 4 days, but I fully focused to working on it on two days."
				+ "\nI startes it early, so I went around to talk to my classmate and drafting the strucutre "
				+ "\nbefore type it in the computer. The best part of this assignment is my professor extand"
				+ "\nthe deadline, so everyone has enough time to discuss and impliment thier codes. "
				+ "\nIf talking about the code, I personally like the condition of the valid value because"
				+ "\nit made me to think about any possible condition would recieve from the users. ");

	}

}
