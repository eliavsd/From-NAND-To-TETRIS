import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Compiler {
	private static final String XML_EXTENSION = ".xml";
	private static final String DOT = ".";
	private static final String UTF8 = "UTF-8";
	private ArrayList<Token> tokens = new ArrayList<>();
	private FileOutputStream myFileOutputStream = null;
	private Writer outputWriter = null;
	private int indentCount = 0;
	private int i = 0;

	public Compiler(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	public void setWriter(String filePath) throws IOException {
		String file_name = 	filePath.substring(0, filePath.lastIndexOf(DOT)) + XML_EXTENSION;
		File outputFile = new File(file_name);
		myFileOutputStream = new FileOutputStream(outputFile);
		outputWriter = new BufferedWriter(new OutputStreamWriter(myFileOutputStream, Charset.forName(UTF8)));
	}


	private String indent() { return new String(new char[indentCount]).replace('\u0000', '\t'); }
	
	private void openTag(String tag) throws IOException {
		outputWriter.write(indent() + "<" + tag + ">" + "\n");
		++indentCount;
	}


	private void closeTag(String tag) throws IOException {
		--indentCount;
		outputWriter.write(indent() + "</" + tag + ">" + "\n");
	}

	private void writeTag(String tag, String value) throws IOException {
		outputWriter.write(indent() + "<" + tag + "> " + value + " </" + tag + ">" + "\n");
	}

	private void skip(String tag, String value) throws Exception {
		if (tokens.get(i).checkEqual(tag, value)) ++i;
		writeTag(tag, value);
	}

	private void writeToken() throws Exception {
		String tokenName = tokens.get(i).getTokenName();
		String tokenValue = tokens.get(i).getTokenValue();
		++i;
		writeTag(tokenName, tokenValue);
	}

	public void compileClass() throws Exception {
		openTag("class");
		skip("keyword", "class");
		writeToken();           
		skip("symbol", "{");

		while(tokenIsClassVarDec(tokens.get(i))) 
			compileClassVarDec();

		while(tokenIsSubroutine(tokens.get(i))) 
			compileSubroutine();

		skip("symbol", "}");
		closeTag("class");
		if (outputWriter != null) outputWriter.close();
		if (myFileOutputStream != null) myFileOutputStream.close();
	}

	private void compileClassVarDec() throws Exception {
		openTag("classVarDec");
		writeToken(); 
		writeToken(); 
		writeToken(); 

		while(tokens.get(i).checkEqual("symbol", ",")) {
			writeToken(); 
			writeToken(); 
		}

		skip("symbol", ";");
		closeTag("classVarDec");
	}

	private void compileSubroutine() throws Exception {
		openTag("subroutineDec");
		writeToken(); 
		writeToken(); 
		writeToken(); 
		compileParamList();
		compileSubroutineBody();
		closeTag("subroutineDec");
	}

	private void compileParamList() throws Exception{
		skip("symbol", "(");
		openTag("parameterList");

		
		if(!tokens.get(i).getTokenValue().contains(")")) {
			writeToken(); 
			writeToken(); 
		}

		while(tokens.get(i).checkEqual("symbol", ",")) {
			writeToken(); 
			writeToken(); 
			writeToken(); 
		}

		closeTag("parameterList");
		skip("symbol", ")");
	}

	private void compileSubroutineBody() throws Exception {
		openTag("subroutineBody");
		skip("symbol", "{");

		while(tokens.get(i).getTokenValue().contains("var"))
			compileVarDec();

		compileStatements();

		skip("symbol", "}");
		closeTag("subroutineBody");
	}

	private void compileVarDec() throws Exception {
		openTag("varDec");
		writeToken(); 
		writeToken(); 
		writeToken(); 

		while(tokens.get(i).checkEqual("symbol", ",")) {
			writeToken();
			writeToken();
		}

		skip("symbol", ";");
		closeTag("varDec");
	}

	private void compileStatements() throws Exception {
		openTag("statements");

		while(tokenIsStatement(tokens.get(i))) {
			switch (tokens.get(i).getTokenValue()) {
				case "let":
					compileLet();
					break;
				case "if":
					compileIf();
					break;
				case "while":
					compileWhile();
					break;
				case "do":
					compileDo();
					break;
				case "return":
					compileReturn();
					break;
			}
		}

		closeTag("statements");
	}

	private void compileLet() throws Exception {
		openTag("letStatement");
		skip("keyword", "let");
		writeToken(); 

		if (tokens.get(i).getTokenValue().contains("[")) {
			writeToken();  
			compileExpression();
			writeToken();
		}
		skip("symbol", "=");
		compileExpression();
		skip("symbol", ";");

		closeTag("letStatement");
	}

	private void compileIf() throws Exception {
		openTag("ifStatement");
		skip("keyword", "if");

		
		skip("symbol", "(");
		compileExpression();
		skip("symbol", ")");

		
		skip("symbol", "{");
		compileStatements();
		skip("symbol", "}");

		if (tokens.get(i).getTokenValue().contains("else")) {
			writeToken();
			skip("symbol", "{");
			compileStatements();
			skip("symbol", "}");
		}
		closeTag("ifStatement");
	}

	private void compileWhile() throws Exception {
		openTag("whileStatement");
		skip("keyword", "while");

		
		skip("symbol", "(");
		compileExpression();
		skip("symbol", ")");

		
		skip("symbol", "{");
		compileStatements();
		skip("symbol", "}");

		closeTag("whileStatement");
	}

	private void compileDo() throws Exception {
		openTag("doStatement");
		skip("keyword", "do");
		writeToken(); 

		if (tokens.get(i).getTokenValue().contains(".")) {
			writeToken();
			writeToken();
		}

		skip("symbol", "(");
		compileExpressionList();
		skip("symbol", ")");
		skip("symbol", ";");

		closeTag("doStatement");
	}

	private void compileReturn() throws Exception {
		openTag("returnStatement");
		skip("keyword", "return");

		compileExpression();
		skip("symbol", ";");

		closeTag("returnStatement");
	}

	private void compileExpression() throws Exception {
		if (tokenValueIsFrom(tokens.get(i), ")", ";")) return;
		openTag("expression");
		compileTerm();
		
		while (tokenValueIsFrom(tokens.get(i), "+", "-", "*", "/", "&amp;", "|", "&lt;", "&gt;", "=")) {
			writeToken();
			compileTerm();
		}
		closeTag("expression");
	}

	private void compileTerm() throws Exception {
		openTag("term");
		if (tokens.get(i).getTokenName().equals("identifier")) {
			writeToken();
			
			if (tokens.get(i).checkEqual("symbol", "[")) {
				writeToken();
				compileExpression();
				skip("symbol", "]");
			}
			else if (tokens.get(i).checkEqual("symbol", "(")) {
				writeToken();
				compileExpressionList();
				skip("symbol", ")");
			}
			else if (tokens.get(i).checkEqual("symbol", ".")) {
				writeToken(); 
				writeToken();
				skip("symbol", "(");
				compileExpressionList();
				skip("symbol", ")");
			}
		}
		else if(tokens.get(i).getTokenName().equals("integerConstant")) {
			writeToken();
		}
		else if (tokens.get(i).getTokenName().equals("stringConstant")) {
			writeToken();
		} 
		else if (tokenValueIsFrom(tokens.get(i), "true", "false", "null", "this")) {
			writeToken();
		}
		else if (tokens.get(i).checkEqual("symbol", "(")) {
			writeToken();
			compileExpression();
			skip("symbol", ")");
		} 
		else if (tokenValueIsFrom(tokens.get(i), "-", "~")){
			writeToken();
			compileTerm();
		}
		closeTag("term");
	}

	private void compileExpressionList() throws Exception {
		openTag("expressionList");
		compileExpression();
		while(tokens.get(i).checkEqual("symbol", ",")) {
			writeToken();
			compileExpression();
		}
		closeTag("expressionList");
	}

	private static boolean tokenIsClassVarDec(Token currToken) { 
		boolean isStatic = currToken.getTokenValue().contains("static");
		boolean isField = currToken.getTokenValue().contains("field");
		return (isStatic || isField);
	}

	private static boolean tokenIsSubroutine(Token currToken) { 
		boolean isConstructor = currToken.getTokenValue().contains("constructor");
		boolean isFunction = currToken.getTokenValue().contains("function");
		boolean isMethod = currToken.getTokenValue().contains("method");
		return (isConstructor || isFunction || isMethod);
	}

	private static boolean tokenValueIsFrom(Token currToken, String... values) {
		for (String value : values) {
			if (currToken.getTokenValue().equals(value)) return true;
		}
		return false;
	}

	private static boolean tokenIsStatement(Token currToken) {
		boolean isLet = currToken.getTokenValue().contains("let");
		boolean isIf = currToken.getTokenValue().contains("if");
		boolean isWhile = currToken.getTokenValue().contains("while");
		boolean isDo = currToken.getTokenValue().contains("do");
		boolean isReturn = currToken.getTokenValue().contains("return");
		return (isLet || isIf || isWhile || isDo || isReturn);
	}
}
