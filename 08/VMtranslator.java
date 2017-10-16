import java.io.*;
import java.nio.charset.Charset;
import java.util.Vector;

/**
 * Class that manages the translation from several or one .vm file to a single .asm file.
 */
public final class VMtranslator {
	private static final int FAILURE = -1;
	private static final String USAGE_MSG = "Usage: VMtranslator <file or folder path>";
	private static final String VM_EXTENSION = ".vm";
	private static final String ASM_EXTENSION = ".asm";
	private static final String UTF8 = "UTF-8";
	private static final char DOT = '.';
	private static final String SLASH = "\\"; // TODO: need to make sure this works on linux

	/**
	 * Class constructor. A private method since this is a kind-of 'static' class that doesn't
	 * require any instantiations.
	 */
	private VMtranslator() {}

	/**
	 * The Main method. Creates VMparser instance for each .vm file it receives as input, and a
	 * single AssemblerWriter instance to translate all .vm file/s into. 
	 * @param args String Array with a single element which is the path of a .vm file or a path to
	 *             a directory which have .vm files.
	 *             If it doesn't have any .vm files then an empty .asm file is created.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(USAGE_MSG);
			System.exit(FAILURE);
		}
		String path = args[0];
		try {
			File file = new File(path);
			String asmFilePath = null;
			Vector<VMparser> parsers = new Vector<>();
			if (file.isFile()) {
				parsers.add(new VMparser(file));
				asmFilePath = file.toString().substring(0, file.toString().lastIndexOf(DOT)) +
						ASM_EXTENSION;
			}
			else if (file.isDirectory()) {
				asmFilePath = file.toString() + SLASH + file.getName() + ASM_EXTENSION;
				for (File fileEntry : file.listFiles()) {
					if (fileEntry.getName().endsWith(VM_EXTENSION)) { // TODO: Or didn't append Sys.vm file
						parsers.add(new VMparser(fileEntry));
					}
				}
				// TODO: Or appended the Sys.vm file last, in this place right here.
			}
			File asmCodeFile = new File(asmFilePath);
			FileOutputStream codeFileOutStream = new FileOutputStream(asmCodeFile);
			Writer writer = new BufferedWriter(new OutputStreamWriter(codeFileOutStream,
											   Charset.forName(UTF8)));
			AssemblerWriter asmWriter = new AssemblerWriter(writer);
			for (VMparser VMparser : parsers) {
				VMparser.removedExcessLines();
				asmWriter.setFileName(VMparser.getFileName());
				// TODO: At first they called bootstrap here, then called in the ctor of writer. we don't do that in java?
				while (!VMparser.hasFinished()) {
					switch (VMparser.getCmdType()) {
						case C_ARITHMETIC:
							asmWriter.writeArithmetic(VMparser.getCurrentCommand()); // TODO: Or changed it to VMparser.getFirstArg() because of whitespace problem
							break;
						case C_PUSH:
							asmWriter.writePush(VMparser.getFirstArg(), VMparser.getSecondArg());
							break;
						case C_POP:
							asmWriter.writePop(VMparser.getFirstArg(), VMparser.getSecondArg());
							break;
						case C_LABEL:
							asmWriter.writeLabel(VMparser.getFirstArg());
							break;
						case C_IF_GOTO:
							asmWriter.writeIf(VMparser.getFirstArg());
							break;
						case C_GOTO:
							asmWriter.writeGoTo(VMparser.getFirstArg());
							break;
						case C_FUNCTION:
							asmWriter.writeFunction();
							break;
						case C_RETURN:
							asmWriter.writeReturn();
							break;
						case C_CALL:
							asmWriter.writeCall();
							break;
						default:
							System.exit(FAILURE);
					}
					VMparser.advance();
				}
			}
			asmWriter.close();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(FAILURE);
		}
	}
}