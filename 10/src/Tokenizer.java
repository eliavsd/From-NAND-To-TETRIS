import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Tokenizer {
	private static final String UTF8 = "UTF-8";
	private static final String KEYWORD = "keyword";
	private static final String SYMBOL = "symbol";
	private static final String INT_CONST = "integerConstant";
	private static final String STR_CONST = "stringConstant";
	private static final String IDENTIFIER = "identifier";
	private static final String LESS_THAN = "&lt;";
	private static final String GREATER_THAN = "&gt;";
	private static final String AMP = "&amp;";
	private static final char SINGLE_QUOTATION = '"';
	private static final String SINGLE_QUOTATION_STR = "\"";
	private static final String OPEN_MULTI_LINE_COMMENT = "/*";
	private static final String CLOSE_MULTI_LINE_COMMENT = "*/";
	private static final String SLASH = "//";

	private static final ArrayList<String> KEY_WORDS = new ArrayList<String>() {{
		add("class"); add("constructor"); add("function"); add("method"); add("field");
		add("static"); add("var"); add("int"); add("char"); add("boolean"); add("void");
		add("true"); add("false"); add("null"); add("this"); add("let"); add("do"); add("if");
		add("else"); add("while"); add("return");
	}};
	private static final ArrayList<String> SYMBS = new ArrayList<String>() {{
		add("{"); add("}"); add("("); add(")"); add("["); add("]"); add("."); add(","); add(";");
		add("+"); add("-"); add("*"); add("/"); add("&"); add("|"); add("<"); add(">"); add("=");
		add("~");
	}};
	private static final ArrayList<String> SPACES = new ArrayList<String>() {{
		add(" "); add("\n"); add("\t");
	}};

	private static String fixChars (String str) {
		if (str.equals(SYMBS.get(15))) return LESS_THAN;
		else if (str.equals(SYMBS.get(16))) return GREATER_THAN;
		else if (str.equals(SYMBS.get(13))) return AMP;
		else {
			return str;
		}
	}

	private static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	private File myFile = null;
	private ArrayList<Token> myTokens = new ArrayList<>();

	public Tokenizer(File file) { this.myFile = file; }

	public String getFilePath() { return myFile.toString(); }

	public ArrayList<Token> getTokens() { return myTokens; }

	public void parse() throws IOException {
		Charset charSet = Charset.forName(UTF8);
		FileInputStream inputStream = new FileInputStream(myFile);
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charSet);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		ArrayList<Character> codeChars = new ArrayList<>();
		int val = 0;
		while ((val = reader.read()) != -1) {
			char c = (char) val;
			if (c == '\r') continue;
			codeChars.add(c);
		}
		boolean inString = false;
		boolean inComment = false;
		boolean commentBlock = false;
		boolean doubleSlash = false;
		String currStr = "";
		char c1;
		char c2 = '\u0000';
		for (int i = 0; i < codeChars.size() - 1; ++i) {
			c1 = codeChars.get(i);
			c2 = codeChars.get(i+1);
			char[] charArr = {c1, c2};
			String c1c2 = new String(charArr);
			char[] c1Arr = {c1};
			String strc1 = new String(c1Arr);
			if (!inComment) {
				if (c1 == SINGLE_QUOTATION) {
					if (inString) {
						tokenize(currStr + SINGLE_QUOTATION);
						currStr = "";
					}
					inString = !inString;
				}
			}
			if (!inString) {
				if ((c1c2).equals(OPEN_MULTI_LINE_COMMENT) && !doubleSlash) {
					commentBlock = true;
					if (!currStr.isEmpty()) {
						tokenize(currStr);
						currStr = "";
					}
				}
				else if ((c1c2).equals(CLOSE_MULTI_LINE_COMMENT) && !doubleSlash) {
					commentBlock = false;
					codeChars.set(i, '\n');
					codeChars.set(i+1, '\n');
				}
				else if ((c1c2).equals(SLASH) && !commentBlock) {
					doubleSlash = true;
					if (!currStr.isEmpty()) {
						tokenize(currStr);
						currStr = "";
					}
				}
				else if (c1 == '\n' && doubleSlash) {
					doubleSlash = false;
				}
				inComment = doubleSlash || commentBlock;
			}
			else {
				currStr += fixChars(strc1);
			}
			if (!inString && !inComment && !strc1.equals(SINGLE_QUOTATION_STR) &&
					!(c1c2).equals(CLOSE_MULTI_LINE_COMMENT)) {
				if (SYMBS.contains(strc1)) {
					if (!currStr.isEmpty()) {
						tokenize(currStr);
					}
					tokenize(strc1);
					currStr = "";
				}
				else if (SPACES.contains(strc1)) {
					if (!currStr.isEmpty()) {
						tokenize(currStr);
						currStr = "";
					}
				}
				else {
					currStr += c1;
				}
			}
		}
		if (!inString && !inComment) {
			if (!currStr.isEmpty()) {
				char[] c2Arr = {c2};
				String strc2 = new String(c2Arr);
				String currStrC2 = currStr + strc2;
				tokenize(currStrC2);
			}
			else {
				char[] c2Arr = {c2};
				tokenize(new String(c2Arr));
			}
		}
		if (reader != null) reader.close();
		if (inputStreamReader != null) inputStreamReader.close();
		if (inputStream != null) inputStream.close();
	}

	private void tokenize(String str) {
		if (KEY_WORDS.contains(str)) myTokens.add(new Token(KEYWORD, str));
		else if (SYMBS.contains(str)) myTokens.add(new Token(SYMBOL, fixChars(str)));
		else if (isNumeric(str)) myTokens.add(new Token(INT_CONST, str));
		else if (str.charAt(0) == '"') myTokens.add(new Token(STR_CONST, str.substring(1, str.length()-1)));
		else {
			myTokens.add(new Token(IDENTIFIER, str));
		}
	}
}