import java.io.IOException;
import java.io.Writer;

public final class VMWriter {
	private static final String PUSH = "push";
	private static final String POP = "pop";
	private static final String LABEL = "label";
	private static final String GOTO = "goto";
	private static final String IF_GOTO = "if-goto";
	private static final String CALL = "call";
	private static final String FUNCTION = "function";
	private static final String RETURN = "return";
	private static final String EOL = "\n";
	private static final String SPACE = " ";

	private VMWriter() {}

	public static void writePush(Writer writer, String segment, String idx) throws IOException {
		writer.write(PUSH + SPACE + segment + SPACE + idx + EOL);
	}

	public static void writePop(Writer writer, String segment, String idx) throws IOException {
		writer.write(POP + SPACE + segment + SPACE + idx + EOL);
	}

	public static void writeArithmetic(Writer writer, String operation) throws IOException {
		writer.write(operation + EOL);
	}

	public static void writeLabel(Writer writer, String labelName) throws IOException {
		writer.write(LABEL + SPACE + labelName + EOL);
	}

	public static void writeGoto(Writer writer, String dest) throws IOException {
		writer.write(GOTO + SPACE + dest + EOL);
	}

	public static void writeIfGoto(Writer writer, String dest) throws IOException {
		writer.write(IF_GOTO + SPACE + dest + EOL);
	}

	public static void writeCall(Writer writer, String funcName, String argNum) throws IOException {
		writer.write(CALL + SPACE + funcName + SPACE + argNum + EOL);
	}

	public static void writeFunction(Writer writer, String funcName, String localVarsNum) throws IOException {
		writer.write(FUNCTION + SPACE + funcName + SPACE + localVarsNum + EOL);
	}

	public static void writeReturn(Writer writer) throws IOException {
		writer.write(RETURN + EOL);
	}
}
