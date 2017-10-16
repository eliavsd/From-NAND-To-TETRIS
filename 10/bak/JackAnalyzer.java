import java.io.File;
import java.util.ArrayList;


public final class JackAnalyzer {
	private static final int FAILURE = -1;
	private static final String USAGE_MSG = "Usage: JackAnalyzer <file or folder path>";
	private static final String JACK_EXTENSION = ".jack";
	private static final String XML_EXTENSION = ".xml";
	private static final String SLASH = "/";
	private static final char DOT = '.';

	private JackAnalyzer() {}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(USAGE_MSG);
			System.exit(FAILURE);
		}
		String path = args[0];
		try {
			File file = new File(path);
			String xmlFilePath = null; // TODO: maybe not need this at all. kept just in case we do
			ArrayList<JackTokenizer> tokenizers = new ArrayList<>();
			if (file.isFile()) {
				tokenizers.add(new JackTokenizer(file));
				xmlFilePath = file.toString().substring(0, file.toString()
						.lastIndexOf(DOT)) +
						XML_EXTENSION;
			}
			else if (file.isDirectory()) {
				xmlFilePath = file.toString() + SLASH + file.getName() +
						XML_EXTENSION;
				for (File fileEntry : file.listFiles()) {
					if ((fileEntry.getName().endsWith(JACK_EXTENSION))) {
						tokenizers.add(new JackTokenizer(fileEntry));
					}
				}
			}
			for (JackTokenizer tokenizer : tokenizers) {
			CompilationEngine compiler = new CompilationEngine(tokenizer);
			if(compiler.isValid())
			    compiler.compileClass();
			else
			    throw new Exception("I/O Error");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(FAILURE);
		}
	}
}
