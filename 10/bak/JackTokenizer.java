import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JackTokenizer {
	private static final String UTF8 = "UTF-8";
	private static final String KEYWORD = "keyword";
	private static final String SYMBOL = "symbol";
	private static final String INT_CONST = "integerConstant";
	private static final String STR_CONST = "stringConstant";
	private static final String IDENTIFIER = "Identifier";
	private static final String LESS_THAN = "&lt;";
	private static final String GREATER_THAN = "&gt;";
	private static final String AMP = "&amp;";
	private static final String SINGLE_QUOTATION = "\"";
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
	private ArrayList<Tuple> myTokens = new ArrayList<>();

	public JackTokenizer(File file) { this.myFile = file; }

	public String getFileName() { return myFile.toString(); }

	public ArrayList<Tuple> getTokens() { return myTokens; }


	public void parse() throws IOException {
		// String str = "this \"is\" a string";
		Charset charSet = Charset.forName(UTF8);
		List<String> lines = Files.readAllLines(myFile.toPath(), charSet);
		String jackCodeStr = "";
		for (String line : lines) {
			line = line.replace('\r', '\0');
			jackCodeStr += line;
		}
		boolean inString = false;
		boolean inComment = false;
		boolean commentBlock = false;
		boolean doubleSlash = false;
		String currStr = "";
		String c1 = "";
		String c2 = "";
		String[] jackCodeArr = jackCodeStr.split("WHITESPACE");
		for (int i=0; i < jackCodeArr.length - 1; ++i) {
			c1 = jackCodeArr[i];
			c2 = jackCodeArr[i+1];
			if (!inComment) {
				if (c1.equals(SINGLE_QUOTATION)) {
					if (inString) {
						tokenize(currStr + SINGLE_QUOTATION);
						currStr = "";
					}
					inString = !inString;
				}
			}
			if (!inString) {
				if ((c1 + c2).equals(OPEN_MULTI_LINE_COMMENT) && !doubleSlash) {
					commentBlock = true;
					if (!currStr.isEmpty()) {
						tokenize(currStr);
						currStr = "";
					}
				}
				else if ((c1 + c2).equals(CLOSE_MULTI_LINE_COMMENT) && !doubleSlash) {
					commentBlock = false;
					jackCodeArr[i] = "\n";
					jackCodeArr[i+1] = "\n";
				}
				else if ((c1 + c2).equals(SLASH) && !commentBlock) {
					doubleSlash = true;
					if (!currStr.isEmpty()) {
						tokenize(currStr);
						currStr = "";
					}
				}
				else if (c1.equals("\n") && doubleSlash) {
					doubleSlash = false;
				}
				inComment = doubleSlash || commentBlock;
			}
			else {
				currStr += fixChars(c1);
			}
			if (!inString && !inComment && !c1.equals(SINGLE_QUOTATION) &&
					!(c1+c2).equals(CLOSE_MULTI_LINE_COMMENT)) {
				if (SYMBS.contains(c1)) {
					if (!currStr.isEmpty()) {
						tokenize(currStr);
					}
					tokenize(c1);
					currStr = "";
				}
				else if (SPACES.contains(c1)) {
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
				tokenize(currStr + c2);
			}
			else {
				tokenize(c2);
			}
		}
	}


	private void tokenize(String str) {
		if (KEY_WORDS.contains(str)) myTokens.add(new Tuple(KEYWORD, str));
		else if (SYMBS.contains(str)) myTokens.add(new Tuple(SYMBOL, fixChars(str)));
		else if (isNumeric(str)) myTokens.add(new Tuple(INT_CONST, str));
		else if (str.charAt(0) == '"') myTokens.add(new Tuple(STR_CONST, str.substring(1, str.length()-1)));
		else {
			myTokens.add(new Tuple(IDENTIFIER, str));
		}
	}
}
