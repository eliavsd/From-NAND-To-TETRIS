import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that manages parsing of a .vm file.
 */
public class VMparser {
	private static final String[] ARITHMIC_SIGNS = new String[] {"add", "sub", "neg", "eq", "gt",
															     "lt", "and", "or", "not"};
	private static final String POP = "pop";
	private static final String PUSH = "push";
	private static final String LABEL = "label";
	private static final String GOTO = "goto";
	private static final String IF_GOTO = "if-goto";
	private static final String FUNCTION = "function";
	private static final String CALL = "call";
	private static final String RETURN = "return";
	private static final String UTF8 = "UTF-8";
	private static final String COMMENT_PREFIX = "//";
	private static final String WHITESPACE = "\\s";
	private static final char DOT = '.';

	public enum CommandType {C_ARITHMETIC, C_PUSH, C_POP, C_LABEL, C_GOTO, C_IF_GOTO, C_FUNCTION,
							 C_RETURN, C_CALL, FAILURE}

	private int currIdx = 0;
	private File myFile = null;
	private ArrayList<String> myLines = new ArrayList<>();

	/**
	 * Class Constructor.
	 * @param file A file object that encapsulate a .vm file.
	 */
	public VMparser(File file) {
		this.myFile = file;
	}

	/**
	 * Returns the .vm file's name.
	 * @return String which is this file's name.
	 */
	public String getFileName() {
		return this.myFile.getName().substring(0, myFile.getName().lastIndexOf(DOT));
	}

	/**
	 * Method which returns a boolean which is an indicator whether this parser has finished parsing
	 * the .vm file or not.
	 * @return true iff the parser finished parsing the file.
	 */
	public boolean hasFinished() {
		return this.currIdx > this.myLines.size()-1;
	}

	/**
	 * Method that returns the current command the parser holds.
	 * @return String which is the current line/command this parser holds.
	 */
	public String getCurrentCommand() {
		return myLines.get(currIdx);
	}

	/**
	 * Method that advances this parser's current line index.
	 */
	public void advance() {
		++this.currIdx;
	}

	/**
	 * Method that removes all comment or blank lines, as well as comments in lines who have
	 * comments at their ends.
	 * @throws IOException
	 */
	public void removedExcessLines() throws IOException {
		Charset charSet = Charset.forName(UTF8);
		List<String> lines = Files.readAllLines(myFile.toPath(), charSet);
		for (String line : lines) {
			String[] noCommentsLine = line.split(COMMENT_PREFIX);
			String noSpacesLine = noCommentsLine[0].trim();
			if (noSpacesLine.isEmpty()) continue;
			this.myLines.add(noSpacesLine);
		}
	}

	/**
	 * Method that returns the current command-line's type, which can be one of the types in the
	 * CommandType enum class.
	 * @return CommandType an enum class that holds possible command types.
	 */
	public CommandType getCmdType() {
		for (String arithmicSign : ARITHMIC_SIGNS) {
			if (myLines.get(currIdx).contains(arithmicSign)) return CommandType.C_ARITHMETIC;
		}
		if (myLines.get(currIdx).contains(POP)) return CommandType.C_POP;
		else if (myLines.get(currIdx).contains(PUSH)) return CommandType.C_PUSH;
		else if (myLines.get(currIdx).contains(LABEL)) return CommandType.C_LABEL;
		else if (myLines.get(currIdx).contains(GOTO)) return CommandType.C_GOTO;
		else if (myLines.get(currIdx).contains(IF_GOTO)) return CommandType.C_IF_GOTO;
		else if (myLines.get(currIdx).contains(FUNCTION)) return CommandType.C_FUNCTION;
		else if (myLines.get(currIdx).contains(CALL)) return CommandType.C_CALL;
		else if (myLines.get(currIdx).contains(RETURN)) return CommandType.C_RETURN;
		else {
			return CommandType.FAILURE;
		}
	}

	/**
	 * Method that returns the first argument of non-arithmetic command-lines.
	 * @return String that represent the first argument of a non-arithmetic command-line.
	 */
	public String getFirstArg() {
		String[] lineWords = myLines.get(currIdx).split(WHITESPACE);
		CommandType currCmd = this.getCmdType();
		if (currCmd == CommandType.C_ARITHMETIC) return lineWords[0];
		return lineWords[1];
	}

	/**
	 * Method that returns the second argument of non-arithmetic command-lines.
	 * @return String that represent the second argument of a non-arithmetic command-line.
	 */
	public String getSecondArg() {
		String[] lineWords = myLines.get(currIdx).split(WHITESPACE);
		return lineWords[2];
	}

}