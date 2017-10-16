import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Compiler {
	private static final String VM_EXTENSION = ".vm";
	private static final String DOT = ".";
	private static final String UTF8 = "UTF-8";
	private static final String[] ARITHMETIC_OPERATORS_SIGNS = new String[] {"+", "-", "=", ">", "<", "&", "|"};
	private static final String[] ARITHMETIC_OPERATORS_NAMES = new String[] {"add", "sub", "eq", "gt", "lt", "and", "or"};
	private static final Map<String, String> ARITHMETICS_TRANSLATOR = new HashMap<String, String>() {{
		put(ARITHMETIC_OPERATORS_SIGNS[0], ARITHMETIC_OPERATORS_NAMES[0]);
		put(ARITHMETIC_OPERATORS_SIGNS[1], ARITHMETIC_OPERATORS_NAMES[1]);
		put(ARITHMETIC_OPERATORS_SIGNS[2], ARITHMETIC_OPERATORS_NAMES[2]);
		put(ARITHMETIC_OPERATORS_SIGNS[3], ARITHMETIC_OPERATORS_NAMES[3]);
		put(ARITHMETIC_OPERATORS_SIGNS[4], ARITHMETIC_OPERATORS_NAMES[4]);
		put(ARITHMETIC_OPERATORS_SIGNS[5], ARITHMETIC_OPERATORS_NAMES[5]);
		put(ARITHMETIC_OPERATORS_SIGNS[6], ARITHMETIC_OPERATORS_NAMES[6]);
	}};
	private ArrayList<Token> tokens = new ArrayList<>();
	private FileOutputStream myFileOutputStream = null;
	private OutputStreamWriter myOutputStreamWriter = null;
	private Writer writer = null;
	private int i = 0;
	private int ifCounter = 0;
	private int whileCounter = 0;
	private String currClass = "";
	private String currSubroutine = "";


	public Compiler(ArrayList<Token> tokens) {
		this.tokens = tokens;
	}

	public void setWriter(String filePath) throws IOException {
		String file_name = 	filePath.substring(0, filePath.lastIndexOf(DOT)) + VM_EXTENSION;
		File outputFile = new File(file_name);
		myFileOutputStream = new FileOutputStream(outputFile);
		myOutputStreamWriter = new OutputStreamWriter(myFileOutputStream, Charset.forName(UTF8));
		writer = new BufferedWriter(myOutputStreamWriter);
	}

	private void skipper(String tag, String value) throws IOException {
		if (tokens.get(i).checkEqual(tag, value)) ++i;
	}

	public void compileClass() throws IOException {
		SymbolTable.reset();
		skipper("keyword", "class");
		currClass = tokens.get(i).getTokenValue();
		++i;
		skipper("symbol", "{");
		while (tokenIsClassVarDec(tokens.get(i)))
			compileClassVarDec();
		while (tokenIsSubroutine(tokens.get(i)))
			compileSubroutine();
		skipper("symbol", "}");
		if (writer != null) writer.close();
		if (myOutputStreamWriter != null) myFileOutputStream.close();
		if (myFileOutputStream != null) myFileOutputStream.close();
	}

	private void compileClassVarDec() throws IOException {
		String varKind =  tokens.get(i).getTokenValue();
		++i;
		String varType =  tokens.get(i).getTokenValue();
		++i;
		String varName =  tokens.get(i).getTokenValue();
		++i;
		SymbolTable.registerSym(varName, varType, varKind);
		while (tokens.get(i).checkEqual("symbol", ",")) {
			++i;
			varName = tokens.get(i).getTokenValue();
			SymbolTable.registerSym(varName, varType, varKind);
			++i;
		}
		skipper("symbol", ";");
	}

	private void compileSubroutine() throws IOException {
		SymbolTable.resetSubroutine();
		ifCounter = 0; whileCounter = 0;
		String methodType = tokens.get(i).getTokenValue();
		++i; ++i;
		currSubroutine = tokens.get(i).getTokenValue();
		++i;
		if (methodType.equals("method")) {
			SymbolTable.registerSym("this", currClass, "argument");
		}
		compileArgList();
		compileBodySubroutine(methodType);
	}

	private void compileArgList() throws IOException {
		skipper("symbol", "(");
		if(!tokens.get(i).getTokenValue().contains(")")) {
			compileArg();
			while (tokens.get(i).checkEqual("symbol", ",")) {
				++i;
				compileArg();
			}
		}
		skipper("symbol", ")");
	}

	private void compileArg() {
		String varType = tokens.get(i).getTokenValue();
		++i;
		String varName = tokens.get(i).getTokenValue();
		++i;
		SymbolTable.registerSym(varName, varType, "argument");
	}

	private void compileBodySubroutine(String methodType) throws IOException {
		skipper("symbol", "{");
		while (tokens.get(i).getTokenValue().contains("var")) {
			compileVariableDec();
		}
		VMWriter.writeFunction(writer, vmFuncName(currClass, currSubroutine), SymbolTable.getSymCount("variable"));
		loadThisPtr(methodType);
		compileStatements();
		skipper("symbol", "}");
	}

	private void loadThisPtr(String methodType) throws IOException {
		switch (methodType) {
			case "method":
				VMWriter.writePush(writer, "argument", "0");
				VMWriter.writePop(writer, "pointer", "0");
				return;
			case "constructor":
				VMWriter.writePush(writer, "constant", SymbolTable.getSymCount("field"));
				VMWriter.writeCall(writer, "Memory.alloc", "1");
				VMWriter.writePop(writer, "pointer", "0");
				return;
			default:
		}
	}

	private void compileVariableDec() throws IOException {
		++i;
		String varType = tokens.get(i).getTokenValue();
		++i;
		String varName = tokens.get(i).getTokenValue();
		++i;
		SymbolTable.registerSym(varName, varType, "variable");
		while (tokens.get(i).checkEqual("symbol", ",")) {
			++i;
			varName = tokens.get(i).getTokenValue();
			SymbolTable.registerSym(varName, varType, "variable");
			++i;
		}
		skipper("symbol", ";");
	}

	private void compileStatements() throws IOException {
		while (tokenIsStatement(tokens.get(i))) {
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
	}

	private void compileLet() throws IOException {
		skipper("keyword", "let");
		String varName = tokens.get(i).getTokenValue();
		++i;
		boolean isArrPtr = tokens.get(i).getTokenValue().contains("[");
		if (isArrPtr) compileArrayPtr(varName);
		skipper("symbol", "=");
		compileExpression();
		skipper("symbol", ";");
		if (isArrPtr) popArrayElem();
		else {
			popVar(varName);
		}
	}

	private void compileArrayPtr(String varName) throws IOException {
		pushVar(varName);
		++i;
		compileExpression();
		skipper("symbol", "]");
		VMWriter.writeArithmetic(writer, "add");
	}

	private void compileIf() throws IOException {
		skipper("keyword", "if");
		String ifTrue = "IF_TRUE_" + Integer.toString(ifCounter);
		String ifFalse = "IF_FALSE_" + Integer.toString(ifCounter);
		String ifEnd = "IF_END_" + Integer.toString(ifCounter);
		++ifCounter;
		skipper("symbol", "(");
		compileExpression();
		skipper("symbol", ")");
		VMWriter.writeIfGoto(writer, ifTrue);
		VMWriter.writeGoto(writer, ifFalse);
		VMWriter.writeLabel(writer, ifTrue);
		skipper("symbol", "{");
		compileStatements();
		skipper("symbol", "}");
		if (tokens.get(i).getTokenValue().contains("else")) {
			VMWriter.writeGoto(writer, ifEnd);
			VMWriter.writeLabel(writer, ifFalse);
			++i;
			skipper("symbol", "{");
			compileStatements();
			skipper("symbol", "}");
			VMWriter.writeLabel(writer, ifEnd);
		}
		else {
			VMWriter.writeLabel(writer, ifFalse);
		}
	}

	private void compileWhile() throws IOException {
		skipper("keyword", "while");
		String whileDest = "WHILE_EXPRESSION_" + Integer.toString(whileCounter);
		String notWhileDest = "WHILE_END_" + Integer.toString(whileCounter);
		++whileCounter;
		VMWriter.writeLabel(writer, whileDest);
		skipper("symbol", "(");
		compileExpression();
		skipper("symbol", ")");
		VMWriter.writeArithmetic(writer, "not");
		VMWriter.writeIfGoto(writer, notWhileDest);
		skipper("symbol", "{");
		compileStatements();
		skipper("symbol", "}");
		VMWriter.writeGoto(writer, whileDest);
		VMWriter.writeLabel(writer, notWhileDest);
	}

	private void compileDo() throws IOException {
		skipper("keyword", "do");
		String routineName = tokens.get(i).getTokenValue();
		++i;
		compileSubroutineCall(routineName);
		VMWriter.writePop(writer, "temp", "0");
		skipper("symbol", ";");
	}

	private void compileSubroutineCall(String name) throws IOException {
		boolean flag = false;
		String[] values = SymbolTable.find(name);
		if (values == null) flag = true;
		int argsNum = 0;
		if (tokens.get(i).checkEqual("symbol", DOT)) {
			String[] arr;
			if (flag) {
				arr =  compileMethod(name, "");
			}
			else {
				arr = compileMethod(name, values[0]);
			}
			argsNum = Integer.parseInt(arr[0]);
			name = arr[1];
		}
		else {
			argsNum = 1;
			VMWriter.writePush(writer, "pointer", "0");
			name = vmFuncName(currClass, name);
		}
		skipper("symbol", "(");
		argsNum += compileExpressionList();
		skipper("symbol", ")");
		VMWriter.writeCall(writer, name, Integer.toString(argsNum));
	}

	private String[] compileMethod(String name, String returnType) throws IOException {
		int argsNum = 0;
		String objName = name;
		++i;
		name = tokens.get(i).getTokenValue();
		if (returnType.isEmpty()) {
			name = vmFuncName(objName, name);
		}
		else {
			argsNum = 1;
			pushVar(objName);
			name = vmFuncName(SymbolTable.getType(objName), name);
		}
		++i;
		String argStr = Integer.toString(argsNum);
		return new String[] {argStr, name};
	}

	private void compileReturn() throws IOException {
		skipper("keyword", "return");
		if (tokens.get(i).checkEqual("symbol", ";")) {
			VMWriter.writePush(writer, "constant", "0");
		}
		else {
			compileExpression();
		}
		skipper("symbol", ";");
		VMWriter.writeReturn(writer);
	}

	private int compileExpression() throws IOException {
		if (tokenValueIsFrom(tokens.get(i), ";", ")")) return 0;
		compileTerm();
		while (tokenValueIsFrom(tokens.get(i), "&", "|", "<", ">", "=", "+", "-", "*", "/")) {
			String op = tokens.get(i).getTokenValue();
			++i;
			compileTerm();
			if (ARITHMETICS_TRANSLATOR.containsKey(op)) {
				VMWriter.writeArithmetic(writer, ARITHMETICS_TRANSLATOR.get(op));
			}
			else if (op.equals("*")) {
				VMWriter.writeCall(writer, "Math.multiply", "2");
			}
			else if (op.equals("/")) {
				VMWriter.writeCall(writer, "Math.divide", "2");
			}
		}
		return 1;
	}

	private void compileTerm() throws IOException {
		if (isConstTerm(tokens.get(i))) {
			compileConstTerm();
		}
		else if (tokens.get(i).checkEqual("symbol", "(")) {
			++i;
			compileExpression();
			skipper("symbol", ")");
			return;
		}
		else if (tokenValueIsFrom(tokens.get(i), "-", "~")) {
			String op = tokens.get(i).getTokenValue();
			++i;
			compileTerm();
			if (op.equals("-")) op = "neg";
			else if (op.equals("~")) op = "not";
			VMWriter.writeArithmetic(writer, op);
			return;
		}
		else if (tokens.get(i).getTokenName().equals("identifier")) {
			String idnfr = tokens.get(i).getTokenValue();
			++i;
			if (tokens.get(i).checkEqual("symbol", "[")) {
				compileArrayPtrTerm(idnfr);
			}
			else if (tokens.get(i).checkEqual("symbol", "[") || tokens.get(i).checkEqual("symbol", DOT)) {
				compileSubroutineCall(idnfr);
			}
			else {
				pushVar(idnfr);
			}
			return;
		}
		++i;
	}

	private void compileConstTerm() throws IOException {
		switch (tokens.get(i).getTokenName()) {
			case "integerConstant":
				VMWriter.writePush(writer, "constant", tokens.get(i).getTokenValue());
				return;
			case "stringConstant":
				compileStrConst(tokens.get(i).getTokenValue());
				return;
			case "keyword":
				compileKeywordConst(tokens.get(i).getTokenValue());
				return;
			default:
		}
	}

	private void compileStrConst(String str) throws IOException {
		VMWriter.writePush(writer, "constant", Integer.toString(str.length()));
		VMWriter.writeCall(writer, "String.new", "1");
		for (int i = 0; i < str.length(); ++i) {
			int c = (int) str.charAt(i);
			VMWriter.writePush(writer, "constant", Integer.toString(c));
			VMWriter.writeCall(writer, "String.appendChar", "2");
		}
	}

	private void compileKeywordConst(String keyword) throws IOException {
		switch (keyword) {
			case "this":
				VMWriter.writePush(writer, "pointer","0");
				return;
			case "true":
				VMWriter.writePush(writer, "constant", "1");
				VMWriter.writeArithmetic(writer, "neg");
				return;
			default:
				VMWriter.writePush(writer, "constant", "0");
		}
	}

	private void compileArrayPtrTerm(String name) throws IOException {
		pushVar(name);
		skipper("symbol", "[");
		compileExpression();
		skipper("symbol", "]");
		VMWriter.writeArithmetic(writer, "add");
		VMWriter.writePop(writer, "pointer", "1");
		VMWriter.writePush(writer, "that", "0");
	}

	private int compileExpressionList() throws IOException {
		int argsNum = 0;
		argsNum += compileExpression();
		while (tokens.get(i).checkEqual("symbol", ",")) {
			++i;
			compileExpression();
			++argsNum;
		}
		return argsNum;
	}

	private void popArrayElem() throws IOException {
		VMWriter.writePop(writer, "temp", "1");
		VMWriter.writePop(writer, "pointer", "1");
		VMWriter.writePush(writer, "temp", "1");
		VMWriter.writePop(writer, "that", "0");
	}

	private void pushVar(String name) throws IOException {
		String[] values = SymbolTable.find(name);
		VMWriter.writePush(writer, getSegment(values[1]), values[2]);
	}

	private void popVar(String name) throws IOException {
		String[] values = SymbolTable.find(name);
		VMWriter.writePop(writer, getSegment(values[1]), values[2]);
	}

	private static String getSegment(String kind) {
		switch (kind) {
			case "static":
				 return "static";
			case "field":
				return "this";
			case "argument":
				return "argument";
			case "variable":
				return "local";
			default:
				return "lol";
		}
	}

	private static String vmFuncName(String className, String subroutineName) {
		return className + DOT + subroutineName;
	}

	private static boolean isConstTerm(Token currToken) {
		boolean intConst = currToken.getTokenName().contains("integerConstant");
		boolean other = tokenValueIsFrom(currToken, "true", "null", "this", "false");
		boolean strConst = currToken.getTokenName().contains("stringConstant");
		return (intConst || other || strConst);
	}

	private static boolean tokenIsClassVarDec(Token currToken) {
		boolean isField = currToken.getTokenValue().contains("field");
		boolean isStatic = currToken.getTokenValue().contains("static");
		return (isStatic || isField);
	}

	private static boolean tokenIsSubroutine(Token currToken) {
		boolean isFunction = currToken.getTokenValue().contains("function");
		boolean isConstructor = currToken.getTokenValue().contains("constructor");
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
		boolean isDo = currToken.getTokenValue().contains("do");
		boolean isWhile = currToken.getTokenValue().contains("while");
		boolean isReturn = currToken.getTokenValue().contains("return");
		boolean isLet = currToken.getTokenValue().contains("let");
		boolean isIf = currToken.getTokenValue().contains("if");
		return (isLet || isIf || isWhile || isDo || isReturn);
	}
}