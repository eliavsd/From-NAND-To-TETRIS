import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that translates Virtual Machine language commands to Assembler language commands.
 */
public class AssemblerWriter {
	private static final String SEG_NOT_LEGAL = "Given Segment not legal.";
	private static final String ARITH_CMD_NOT_LEGAL = "Given Arithmetic Command not legal.";
	private static final String END_STRING = "(END)\n@END\n0;JMP\n";
	private static final String CONSTANT = "constant";
	private static final String STATIC = "static";
	private static final String[] ARITHMIC_SIGNS = new String[] {"neg", "not", "add", "sub", "and",
																 "or", "eq", "gt", "lt"};
	private static final String[] MEM_SEGMTS = new String[] {"local", "argument", "this", "that"};
	private static final String[] REG_SEGMTS = new String[] {"pointer", "temp"};
	private static final String[] ASM_MEM_SEGMTS = new String[] {"LCL", "ARG", "THIS", "THAT"};
	private static final Map<String, String> ASM_MEM = new HashMap<String, String>() {{
			put(MEM_SEGMTS[0], ASM_MEM_SEGMTS[0]);
			put(MEM_SEGMTS[1], ASM_MEM_SEGMTS[1]);
			put(MEM_SEGMTS[2], ASM_MEM_SEGMTS[2]);
			put(MEM_SEGMTS[3], ASM_MEM_SEGMTS[3]);
	}};
	private static final Map<String, String> ASM_REG = new HashMap<String, String>() {{
		put(REG_SEGMTS[0], "3");
		put(REG_SEGMTS[1], "5");
	}};

	private enum Segment {MEMORY, REGISTER, STATIC, CONSTANT, FAILURE}

	/**
	 * Static method that sorts segments to an enum that represent that given segment.
	 * @param segment String which is a some segment (Static, Constant, Memory or Register Segment).
	 * @return Segment an enum that represent the given segment.
	 */
	private static Segment segmentSorter(String segment) {
		if (segment.equals(STATIC)) return Segment.STATIC;
		else if (segment.equals(CONSTANT)) return Segment.CONSTANT;
		else if (Arrays.asList(MEM_SEGMTS).contains(segment)) return Segment.MEMORY;
		else if (Arrays.asList(REG_SEGMTS).contains(segment)) return Segment.REGISTER;
		return Segment.FAILURE;
	}

	private enum ArithmeticCommand {NEG, NOT, ADD, SUB, AND, OR, EQ, GT, LT, FAILURE}

	/**
	 * Static method that sorts arithmetic operations to an enum that represent that operation.
	 * @param arithCmd String which is an arithmetic operation.
	 * @return ArithmeticCommand an enum that represent the given arithmetic operation.
	 */
	private static ArithmeticCommand arithCmdSorter(String arithCmd) {
		if (arithCmd.equals(ARITHMIC_SIGNS[0])) return ArithmeticCommand.NEG;
		else if (arithCmd.equals(ARITHMIC_SIGNS[1])) return ArithmeticCommand.NOT;
		else if (arithCmd.equals(ARITHMIC_SIGNS[2])) return ArithmeticCommand.ADD;
		else if (arithCmd.equals(ARITHMIC_SIGNS[3])) return ArithmeticCommand.SUB;
		else if (arithCmd.equals(ARITHMIC_SIGNS[4])) return ArithmeticCommand.AND;
		else if (arithCmd.equals(ARITHMIC_SIGNS[5])) return ArithmeticCommand.OR;
		else if (arithCmd.equals(ARITHMIC_SIGNS[6])) return ArithmeticCommand.EQ;
		else if (arithCmd.equals(ARITHMIC_SIGNS[7])) return ArithmeticCommand.GT;
		else if (arithCmd.equals(ARITHMIC_SIGNS[8])) return ArithmeticCommand.LT;
		return ArithmeticCommand.FAILURE;
	}

	private Writer myWriter = null;
	private Stack myStack = new Stack();
	private String currFileName = null;
	private String currFunc = "xxx";
	private int cmpCounter = 0;

	/**
	 * Constructor method.
	 * @param writer a Writer instance to write with.
	 */
	public AssemblerWriter(Writer writer) {
		myWriter = writer;
	}

	/**
	 * Method that writes an infinite loop at the end of the .asm file and then closes the writer
	 * instance.
	 * @throws IOException
	 */
	public void close() throws IOException {
		myWriter.write(END_STRING);
		myWriter.close();
	}

	/**
	 * Method that set the currFileName field to the current file name of the .vm file that we're
	 * translating.
	 * @param fileName String which is the current .vm file that we're translating.
	 */
	public void setFileName(String fileName) {
		currFileName = fileName;
	}

	/**
	 * Method that writes a Label to the .asm file.
	 * @param label String which is the name of the Label to write.
	 * @throws IOException
	 */
	public void writeLabel(String label) throws IOException {
		myWriter.write('(' + currFunc + ')' + '$' + label + ")\n");
	}

	/**
	 * Method that writes a Go-To command to the .asm file.
	 * @param label
	 * @throws IOException
	 */
	public void writeGoTo (String label) throws IOException {
		myWriter.write('@' + currFunc + '$' + label + "\n0;JMP\n");
	}

	/**
	 * Method that writes an Addressing Command (a-Command).
	 * @param address String which is the address to write to.
	 * @throws IOException
	 */
	private void writeAddressingCmd(String address) throws IOException {
		myWriter.write('@' + address + '\n');
	}

	/**
	 * Method that writes a Compute Command (c-Command).
	 * @param dest The destination to store the value of the computation.
	 * @param comp The mnemonic of the requested computation.
	 * @param jmp The mnemonic of the next requested instruction.
	 * @throws IOException
	 */
	private void writeComputeCmd(String dest, String comp, String jmp) throws IOException {
		if (dest != null) myWriter.write(dest + '=');
		myWriter.write(comp);
		if (jmp != null) myWriter.write(';' + jmp);
		myWriter.write("\n");
	}

	/**
	 * Method that writes a Constant variable to the .asm file.
	 * @param idx The index of the memory location to store the constant.
	 * @throws IOException
	 */
	private void writeConst(String idx) throws IOException {
		writeAddressingCmd(idx);
		myWriter.write("D=A\n");
	}

	/**
	 * Method that writes a Static variable to the .asm file.
	 * @param idx The index of the memory location to store the static variable.
	 * @throws IOException
	 */
	private void writeStatic(String idx) throws IOException {
		writeAddressingCmd(currFileName + '.' + idx);
		myWriter.write("D=M\n");
	}

	/**
	 * Method that writes a sequence of Assembler commands that loads a value from the given memory
	 * segment at the required index to the Stack. 
	 * @param segment String that specifies the required Memory Segment.
	 * @param idx String that specifies the required index in the Memory Segment.
	 * @throws IOException
	 */
	private void memoryLoader(String segment, String idx) throws IOException {
		writeAddressingCmd(idx);
		myWriter.write("D=A\n@" + ASM_MEM.get(segment) + "\nA=D+M\nD=M\n");
	}

	/**
	 * Method that writes a sequence of Assembler commands that loads a value from the given
	 * registry segment at the required index to the Stack.
	 * @param segment String that specifies the required Registry Segment.
	 * @param idx String that specifies the required index in the Registry Segment.
	 * @throws IOException
	 */
	private void registryLoader(String segment, String idx) throws IOException {
		writeAddressingCmd(idx);
		myWriter.write("D=A\n@" + ASM_REG.get(segment) + "\nA=D+A\nD=M\n");
	}

	/**
	 * Method that writes a sequence of Assembler commands that function as an if command in
	 * Virtual Machine language.
	 * @param label
	 * @throws IOException
	 */
	public void writeIf (String label) throws IOException {
		myStack.loadStackToDest();
		myStack.decStackPointer();
		myWriter.write('@' + currFunc + '$' + label + "\nD;JNE\n");
	}

	/**
	 * Method that writes a sequence of Assembler commands that function as a push command in
	 * Virtual Machine language.
	 * @param segment The segment that holds the variable we wish to push.
	 * @param idx The index in the segment of the variable we wish to push.
	 * @throws IOException
	 * @throws TypeNotPresentException
	 */
	public void writePush(String segment, String idx) throws IOException, TypeNotPresentException {
		switch (segmentSorter(segment)) {
			case STATIC:
				writeStatic(idx);
				break;
			case CONSTANT:
				writeConst(idx);
				break;
			case MEMORY:
				memoryLoader(segment, idx);
				break;
			case REGISTER:
				registryLoader(segment, idx);
				break;
			default:
				throw new TypeNotPresentException(segment, new Throwable(SEG_NOT_LEGAL));
		}
		myStack.loadStackPointer(); // stack[sp] = x;
		myStack.incStackPointer(); //  sp = sp + 1
	}

	/**
	 * Method that writes a sequence of Assembler commands that function as a pop command in
	 * Virtual Machine language.
	 * @param segment The segment that holds the variable we wish to pop the stack's SP value to.
	 * @param idx The index in the segment of the variable we wish to pop the stack's SP value to.
	 * @throws IOException
	 * @throws TypeNotPresentException
	 */
	public void writePop(String segment, String idx) throws IOException, TypeNotPresentException {
		switch (segmentSorter(segment)) {
			case STATIC:
				myStack.loadStackToDest();
				myStack.decStackPointer();                              // sp = sp - 1
				myWriter.write('@' + currFileName + '.' + idx + "\n" );
				writeComputeCmd("M", "D", null);
				break;
			case MEMORY:
				myStack.writeStackToMem(segment, idx);
				break;
			case REGISTER:
				myStack.writeStackToReg(segment, idx);
				break;
			default:
				throw new TypeNotPresentException(segment, new Throwable(SEG_NOT_LEGAL));
		}
	}

	/**
	 * Private Inner Class that represent a virtual, non-existent Stack in Virtual Machine syntax.
	 */
	private class Stack {
		/**
		 * Private Constructor method. Makes sure no one instances this class unnecessarily.
		 */
		private Stack() {}

		/**
		 * Method that increments the Stack Pointer.
		 * @throws IOException
		 */
		private void incStackPointer() throws IOException {
			writeAddressingCmd("SP");
			writeComputeCmd("M", "M+1", null);
		}

		/**
		 * Method that decrements the Stack Pointer.
		 * @throws IOException
		 */
		private void decStackPointer() throws IOException {
			writeAddressingCmd("SP");
			writeComputeCmd("M", "M-1", null);
		}

		/**
		 * Method that loads the value of the Stack Pointer to a Destination ("D").
		 * @throws IOException
		 */
		private void loadStackToDest() throws IOException{
			writeAddressingCmd("SP");
			writeComputeCmd("A", "M-1", null);
			writeComputeCmd("D", "M", null);
		}

		/**
		 * Method that writes a sequence of Assembler commands that loads the value of the Stack
		 * Pointer.
		 * @throws IOException
		 */
		private void loadStackPointer() throws IOException {
			writeAddressingCmd("SP");
			writeComputeCmd("A", "M", null);
			writeComputeCmd("M", "D", null);
		}

		/**
		 * Method that writes a sequence of Assembler commands that loads the value of the Stack
		 * Pointer to the request memory segment in the given index.
		 * @param segment String that specifies the requested Memory Segment.
		 * @param idx String the specifies the requested index in which to store our value/variable.
		 * @throws IOException
		 */
		private void writeStackToMem(String segment, String idx) throws IOException {
			writeAddressingCmd(idx);
			myWriter.write("D=A\n@" + ASM_MEM.get(segment) + "\nD=D+M\n@R13\nM=D\n@SP\nA=M-1" +
					       "\nD=M\n@R14\nM=D\n@SP\nM=M-1\n@R14\nD=M\n@R13\nA=M\nM=D\n");
		}

		/**
		 * Method that writes a sequence of Assembler commands that loads the value of the Stack
		 * Pointer to the request registry segment in the given index.
		 * @param segment String that specifies the requested Registry Segment.
		 * @param idx String the specifies the requested index in which to store our value/variable.
		 * @throws IOException
		 */
		private void writeStackToReg(String segment, String idx) throws IOException {
			writeAddressingCmd(idx);
			myWriter.write("D=A\n@" + ASM_REG.get(segment) + "\nD=D+A\n@R13\nM=D\n@SP\nA=M-1" +
					       "\nD=M\n@R14\nM=D\n@SP\nM=M-1\n@R14\nD=M\n@R13\nA=M\nM=D\n");
		}
	}

	/**
	 * Method that writes a sequence of Assembler commands that are used to prepare for an
	 * Arithmetic-type Command.
	 * @throws IOException
	 */
	private void prepArithmetic() throws IOException {
		myWriter.write("@SP\nA=M-1\nD=M\nA=A-1\n");
	}

	/**
	 * Method that writes a sequence of Assembler commands that executes an Arithmetic operation,
	 * e.g: "neg", "not", "add", "sub", "and", "or", "eq", "gt" or "lt".
	 * @param arithCommand
	 * @throws IOException
	 * @throws TypeNotPresentException
	 */
	public void writeArithmetic(String arithCommand) throws IOException, TypeNotPresentException {
		String count = Integer.toString(cmpCounter);
		switch (arithCmdSorter(arithCommand)) {
			case NEG:
				myWriter.write("@SP\nA=M-1\nM=-M\n");
				break;
			case NOT:
				myWriter.write("@SP\nA=M-1\nM=!M\n");
				break;
			case AND:
				prepArithmetic();
				myWriter.write("M=M&D\n");
				myStack.decStackPointer();
				break;
			case OR:
				prepArithmetic();
				myWriter.write("M=M|D\n");
				myStack.decStackPointer();
				break;
			case ADD:
				prepArithmetic();
				myWriter.write("M=M+D\nD=A+1\n");
				writeAddressingCmd("SP");
				writeComputeCmd("M", "D", null);
				break;
			case SUB:
				prepArithmetic();
				myWriter.write("M=M-D\nD=A+1\n");
				writeAddressingCmd("SP");
				writeComputeCmd("M", "D", null);
				break;
			case EQ:
				prepArithmetic();
				myWriter.write("D=D-M\n@SP\nM=M-1\n@EQUAL_" + count + "\nD;JEQ\n@NOT_EQUAL_" +
							   count + "\nD;JNE\n(EQUAL_" + count + ")\n@SP\nA=M-1\nM=-1\n" +
							   "@END_EQ_" + count + "\n0;JMP\n(NOT_EQUAL_" + count + ")\n@SP\n" +
						      "A=M-1\nM=0\n@END_EQ_" + count + "\n0;JMP\n(END_EQ_" + count + ")\n");
				break;
			case GT:
				prepArithmetic();
				myWriter.write("D=M-D\n@SP\nM=M-1\n@GREATER_" + count + "\nD;JGT\n@LESS_EQUAL_" +
							   count + "\nD;JLE\n(GREATER_" + count + ")\n@SP\nA=M-1\nM=-1\n" +
							   "@END_GT_" + count + "\n0;JMP\n(LESS_EQUAL_" + count + ")\n" +
							   "@SP\nA=M-1\nM=0\n@END_GT_" + count + "\n0;JMP\n" +
							   "(END_GT_" + count + ")\n");
				break;
			case LT:
				prepArithmetic();
				myWriter.write("D=M-D\n@SP\nM=M-1\n@LESS_" + count + "\nD;JLT\n@GE_" + count + "\n"
							    + "D;JGE\n(LESS_" + count + ")\n@SP\nA=M-1\nM=-1\n" +
							   "@END_LT_" + count + "\n0;JMP\n(GE_" + count + ")\n@SP\nA=M-1\n" +
							   "M=0\n@END_LT_" + count + "\n0;JMP\n(END_LT_" + count + ")\n");
				break;
			default:
				throw new TypeNotPresentException(arithCommand, new Throwable(ARITH_CMD_NOT_LEGAL));
		}
		++cmpCounter;
	}

	public void writeFunction() {}
	public void writeReturn() {}
	public void writeCall() {}
}
