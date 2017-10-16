import java.io.File;
import java.util.ArrayList;


public final class JackCompiler {
	private static final int FAILURE = -1;
	private static final String USAGE_MSG = "Usage: JackCompiler <file or folder path>";
	private static final String JACK_EXTENSION = ".jack";

	private JackCompiler() {}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(USAGE_MSG);
			System.exit(FAILURE);
		}
		String path = args[0];
		File file;
		try {
			if (path.equals("")) file = new File("./");
			else file = new File(path);
			ArrayList<Tokenizer> tokenizers = new ArrayList<>();
			if (file.isFile()) {
				tokenizers.add(new Tokenizer(file));
			}
			else if (file.isDirectory()) {
				for (File fileEntry : file.listFiles()) {
					if ((fileEntry.getName().endsWith(JACK_EXTENSION))) {
						tokenizers.add(new Tokenizer(fileEntry));
					}
				}
			}
			for (Tokenizer tokenizer : tokenizers) {
				tokenizer.parse();
				Compiler compiler = new Compiler(tokenizer.getTokens());
				compiler.setWriter(tokenizer.getFilePath());
				compiler.compileClass();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(FAILURE);
		}
	}
}

