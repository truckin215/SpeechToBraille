package speech;

public class BrailleChar {
	int[][] value;

	BrailleChar(char c) {
		Alphabet alphabet = new Alphabet();
		switch (c) {
			case 'a':
				this.value = alphabet.Abraille();
				break;
			case 'b':
				this.value = alphabet.Bbraille();
				break;
			case 'c':
				this.value = alphabet.Cbraille();
				break;
			case 'd':
				this.value = alphabet.Dbraille();
				break;
			case 'e':
				this.value = alphabet.Ebraille();
				break;
			case 'f':
				this.value = alphabet.Fbraille();
				break;
			case 'g':
				this.value = alphabet.Gbraille();
				break;
			case 'h':
				this.value = alphabet.Hbraille();
				break;
			case 'i':
				this.value = alphabet.Ibraille();
				break;
			case 'j':
				this.value = alphabet.Jbraille();
				break;
			case 'k':
				this.value = alphabet.Kbraille();
				break;
			case 'l':
				this.value = alphabet.Lbraille();
				break;
			case 'm':
				this.value = alphabet.Mbraille();
				break;
			case 'n':
				this.value = alphabet.Nbraille();
				break;
			case 'o':
				this.value = alphabet.Obraille();
				break;
			case 'p':
				this.value = alphabet.Pbraille();
				break;
			case 'q':
				this.value = alphabet.Qbraille();
				break;
			case 'r':
				this.value = alphabet.Rbraille();
				break;
			case 's':
				this.value = alphabet.Sbraille();
				break;
			case 't':
				this.value = alphabet.Tbraille();
				break;
			case 'u':
				this.value = alphabet.Ubraille();
				break;
			case 'v':
				this.value = alphabet.Vbraille();
				break;
			case 'w':
				this.value = alphabet.Wbraille();
				break;
			case 'x':
				this.value = alphabet.Xbraille();
				break;
			case 'y':
				this.value = alphabet.Ybraille();
				break;
			case 'z':
				this.value = alphabet.Zbraille();
				break;
			case '.':
				this.value = alphabet.Periodbraille();
				break;
			case ',':
				this.value = alphabet.Commabraille();
				break;
			case '!':
				this.value = alphabet.Exclamationbraille();
				break;
			case ' ':
				this.value = alphabet.Spacebraille();
				break;
		}

	}

	//todo get value function
	public int[][] getValue() {
		return value;
	}

	public String toUnicode() {
		int code = 0x2800;

		if (value[0][0] == 1) code += 0x01;
		if (value[1][0] == 1) code += 0x02;
		if (value[2][0] == 1) code += 0x04;
		if (value[0][1] == 1) code += 0x08;
		if (value[1][1] == 1) code += 0x10;
		if (value[2][1] == 1) code += 0x20;

		return Character.toString((char) code);
	}
}