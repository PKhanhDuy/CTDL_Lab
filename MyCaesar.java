package ctdl;

public class MyCaesar {
	public static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private int n; // shift steps (right shift)

	public MyCaesar(int shiftSteps) {
		this.n = shiftSteps;
	}

	public char encryptChar(char c) {
		int x = -1;
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				x = i;
			}
		}

		if (x + n > 25) {
			return ALPHABET[n - (24 - x) - 1];
		} else
			return ALPHABET[(x + n) % 26];

	}

	public char decryptChar(char c) {
		int x = -1;
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				x = i;
			}
		}
		if (x - n < 0) {
			return ALPHABET[(24 - n) + x + 1];
		} else
			return ALPHABET[(x - n) % 26];

	}

// phương thức mã hóa chuỗi dùng mảng ALPHABET
	public String encrypt(String input) {
		String res = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			res += encryptChar(c);
		}
		return res;
	}
//phương thức dùng để giải mã chuỗi dùng mảng ALPHABET
	public String decrypt(String input) {
		String res = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			res += decryptChar(c);
		}
		return res;
	}

//Task3
	
//phương thức bổ trợ cho phương thức mã hóa chuỗi
	//start và end là giới hạn mã theo thệ 10 của các kí tự như số, chữ cái thường, chữ cái in hoa.
	public char encrypt(char c, int steps, int start, int end) {
		if (c >= start && c <= end) {
			if (c + steps > end) {
				return (char) (start + (steps - (end - c)) - 1);
			}
			return (char) (c + steps);
		}
		return ' ';
	}
//phương thức bổ trợ cho phương thức giải mã chuỗi
	//start và end là giới hạn mã theo hệ 10 của các kí tự như số, chữ thường hay chữ in hoa.
	public char decrypt(char c, int steps, int start, int end) {
		if (c >= start && c <= end) {
			if (c - steps < start) {
				return (char) (end - (steps - (c - start)) + 1);
			}
			return (char) (c - steps);
		}
		return ' ';
	}
	
//phương thức mã hóa chuỗi nhiều ký tự
	public String encryptString(String input) {
		String encryptedString = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == ' ')
				encryptedString += ' ';
			if (c >= 48 && c <= 57) {
				encryptedString += encrypt(c, n, 48, 57);
			} else if (c >= 65 && c <= 90) {
				encryptedString += encrypt(c, n, 65, 90);
			} else if (c >= 97 && c <= 122) {
				encryptedString += encrypt(c, n, 97, 122);
			}else encryptedString +='*';
		}
		return encryptedString;
	}

// phương thức giải mã chuỗi nhiều ký tự
	public String decryptString(String input) {
		String decryptedString = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == ' ')
				decryptedString += ' ';
			if (c >= 48 && c <= 57) {
				decryptedString += decrypt(c, n, 48, 57);
			} else if (c >= 65 && c <= 90) {
				decryptedString += decrypt(c, n, 65, 90);
			} else if (c >= 97 && c <= 122) {
				decryptedString += decrypt(c, n, 97, 122);
			}else decryptedString +='*';
		}
		return decryptedString;
	}

	// Hệ thập phân(hệ 10) của các chữ số.
	/*
	 * số: 0->9 hệ 10: 48->57
	 *
	 * Chữ cái thường: a-> z hệ 10: 97->122
	 *
	 * Chữ in hoa: A -> Z hệ 10: 65->90
	 *
	 */
	public static void main(String[] args) {
		MyCaesar myCaesar = new MyCaesar(3);
//		System.out.println(myCaesar.encryptChar('H'));
//		System.out.println(myCaesar.decryptChar('B'));
//		System.out.println(myCaesar.decrypt(myCaesar.encrypt("KHANHDUY")));
		System.out.println(myCaesar.encryptString("toi sap chet voi deadline roi"));
		System.out.println(myCaesar.decryptString("wrl vds fkhw yrl ghdgolqh url"
				+ ""));

	}
}
