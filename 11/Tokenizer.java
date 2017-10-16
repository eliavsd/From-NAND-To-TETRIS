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
		boolean parsingMultiComment = false;
		boolean parsingSingleLineComment = false;
		boolean parsingString = false;
		boolean parsingComment = false;
		String currStr = "";
		char firstChar;
		char secondChar = '\u0000';
		for (int i = 0; i < codeChars.size() - 1; ++i) {
			firstChar = codeChars.get(i);
			secondChar = codeChars.get(i+1);
			char[] charArr = {firstChar, secondChar};
			String c1c2 = new String(charArr);
			char[] firstCharArr = {firstChar};
			String strfirstChar = new String(firstCharArr);
			if (!parsingComment) {
				if (firstChar == SINGLE_QUOTATION) {
					if (parsingString) {
						addToken(currStr + SINGLE_QUOTATION);
						currStr = "";
					}
					parsingString = !parsingString;
				}
			}
			if (!parsingString) {
				if ((c1c2).equals(OPEN_MULTI_LINE_COMMENT) && !parsingSingleLineComment) {
					parsingMultiComment = true;
					if (!currStr.isEmpty()) {
						addToken(currStr);
						currStr = "";
					}
				}
				else if ((c1c2).equals(CLOSE_MULTI_LINE_COMMENT) && !parsingSingleLineComment) {
					parsingMultiComment = false;
					codeChars.set(i, '\n');
					codeChars.set(i+1, '\n');
				}
				else if ((c1c2).equals(SLASH) && !parsingMultiComment) {
					parsingSingleLineComment = true;
					if (!currStr.isEmpty()) {
						addToken(currStr);
						currStr = "";
					}
				}
				else if (firstChar == '\n' && parsingSingleLineComment) {
					parsingSingleLineComment = false;
				}
				parsingComment = parsingSingleLineComment || parsingMultiComment;
			}
			else {
				currStr += strfirstChar;
			}
			if (!parsingString && !parsingComment && !strfirstChar.equals(SINGLE_QUOTATION_STR) &&
					!(c1c2).equals(CLOSE_MULTI_LINE_COMMENT)) {
				if (SYMBS.contains(strfirstChar)) {
					if (!currStr.isEmpty()) {
						addToken(currStr);
					}
					addToken(strfirstChar);
					currStr = "";
				}
				else if (SPACES.contains(strfirstChar)) {
					if (!currStr.isEmpty()) {
						addToken(currStr);
						currStr = "";
					}
				}
				else {
					currStr += firstChar;
				}
			}
		}
		if (!parsingString && !parsingComment) {
			if (!currStr.isEmpty()) {
				char[] secondCharArr = {secondChar};
				String strsecondChar = new String(secondCharArr);
				String currStrsecondChar = currStr + strsecondChar;
				addToken(currStrsecondChar);
			}
			else {
				char[] secondCharArr = {secondChar};
				addToken(new String(secondCharArr));
			}
		}
		if (reader != null) reader.close();
		if (inputStreamReader != null) inputStreamReader.close();
		if (inputStream != null) inputStream.close();
	}

	private void addToken(String str) {
		if (KEY_WORDS.contains(str)) myTokens.add(new Token(KEYWORD, str));
		else if (SYMBS.contains(str)) myTokens.add(new Token(SYMBOL, str));
		else if (isNumeric(str)) myTokens.add(new Token(INT_CONST, str));
		else if (str.charAt(0) == '"') myTokens.add(new Token(STR_CONST, str.substring(1, str.length()-1)));
		else {
			myTokens.add(new Token(IDENTIFIER, str));
		}
	}
}