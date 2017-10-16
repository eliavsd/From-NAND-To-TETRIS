__author__ = "Or Keren"

class Writer:

    """
    The writer class, this class writes the actual asm lines
    """
    def __init__(self, output_path):
        """
        The constructor of the Writer, it initializes the internal members to their initial states
        :param output_path: The output path the writer should write the file to
        :return: N/A
        """
        self._output_file = open(output_path, "w")
        self._file_name = ""
        self._current_func = ""
        self._counter = 0
        self._a_counter = 0
        self.write_bootstrap()

    def __del__(self):
        """
        This is the destructor, closes the file
        :return:
        """
        self._output_file.close()

    def set_file_name(self, file_name):
        """
        Sets the current file name
        :param file_name: The file name
        :return: N/A
        """
        self._file_name = file_name

    def write_arithmetic(self, command):
        """
        Deals with the writing of an arithmetic command, such as add, sub etc.
        calls sub-methods depending on what command it is
        :param command: The current command to translate
        :return: N/A
        """
        if command in ["add", "sub"]:
            self.arithmetic_shared()
            self._output_file.write("M=M" + ("-" if command == "sub" else "+") + "D\n"
                                    "D=A+1\n")

            self.write_a_command("SP")
            self.write_c_command("M", "D")

        elif command in ["neg","not"]:
            self._output_file.write("@SP\n"
                                    "A=M-1\n"
                                    "M=" + ("-" if command == "neg" else "!") + "M\n")
        elif command in ["and", "or"]:
            self.arithmetic_shared()
            self._output_file.write("M=D" + ("&" if command == "and" else "|") + "M\n")
            self.decrease_stack_pointer()
        elif command == "eq":
            self.arithmetic_shared()
            self._output_file.write("D=D-M\n"
                                    "@SP\n"
                                    "M=M-1\n"
                                    "@EQUAL_" + str(self._a_counter) + "\n"
                                    "D;JEQ\n"
                                    "@NOT_EQUAL_" + str(self._a_counter) + "\n"
                                    "D;JNE\n"
                                    "(EQUAL_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=-1\n"
                                    "@END_EQ_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(NOT_EQUAL_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=0\n"
                                    "@END_EQ_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(END_EQ_" + str(self._a_counter) + ")\n")
        elif command == "gt":
            self.arithmetic_shared()
            self._output_file.write("D=M-D\n"
                                    "@SP\n"
                                    "M=M-1\n"
                                    "@GREATER_" + str(self._a_counter) + "\n"
                                    "D;JGT\n"
                                    "@LESS_EQUAL_" + str(self._a_counter) + "\n"
                                    "D;JLE\n"
                                    "(GREATER_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=-1\n"
                                    "@END_GT_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(LESS_EQUAL_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=0\n"
                                    "@END_GT_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(END_GT_" + str(self._a_counter) + ")\n")
        elif command == "lt":
            self.arithmetic_shared()
            self._output_file.write("D=M-D\n"
                                    "@SP\n"
                                    "M=M-1\n"
                                    "@LESS_" + str(self._a_counter) + "\n"
                                    "D;JLT\n"
                                    "@GE_" + str(self._a_counter) + "\n"
                                    "D;JGE\n"
                                    "(LESS_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=-1\n"
                                    "@END_LT_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(GE_" + str(self._a_counter) + ")\n"
                                    "@SP\n"
                                    "A=M-1\n"
                                    "M=0\n"
                                    "@END_LT_" + str(self._a_counter) + "\n"
                                    "0;JMP\n"
                                    "(END_LT_" + str(self._a_counter) + ")\n")
        self._a_counter += 1

    def write_label(self, label):
        """
        Writes a label command
        :param label: The label to write into HACK label
        :return: N/A
        """
        self._output_file.write("(" + self._current_func + "$" + label + ")\n")


    def write_if(self, label):
        """
        Writes an IF block in HACK language
        :param label:
        :return:
        """
        self.load_stack_to_dest("D")
        self.decrease_stack_pointer()

        self._output_file.write("@" + self._current_func + "$" + label + "\n"
                                "D;JNE\n")


    def write_goto(self, label):
        """
        Writes a GOTO block in HACK language
        :param label: The label to convert to HACK
        :return: N/A
        """

        self._output_file.write("@" + self._current_func + "$" + label + "\n"
                                "0;JMP\n")


    def write_function(self, function_name, variables):
        """
        :param function_name: The name of the function
        :param variables: The number of variables to zero
        :return: N/A
        """

        self._output_file.write("// Begin write_function\n")

        self._output_file.write("(" + function_name + ")\n")
        for i in range(variables):
            self.write_push("constant", 0)

        self._current_func = function_name # TODO added

        self._output_file.write("// End write_function\n")

    def write_return(self):

        self._output_file.write("// Begin write_return\n")

        # frame = LCL
        self._output_file.write("@LCL\n"
                                "D=M\n"
                                "@FRAME\n"
                                "M=D\n")

        # RET = *(FRAME-5)
        self._output_file.write("@5\n"
                                "D=A\n"
                                "@FRAME\n"
                                "D=M-D\n"
                                "A=D\n"
                                "D=M\n"
                                "@RET\n"
                                "M=D\n")
        # *ARG = pop()
        self._output_file.write("@SP\n"
                                "A=M-1\n"
                                "D=M\n"
                                "@ARG\n"
                                "A=M\n"
                                "M=D\n")

        # SP = ARG+1
        self._output_file.write("@ARG\n"
                                "D=M\n"
                                "@SP\n"
                                "M=D+1\n")

        # THAT = *(FRAME-1)
        self._output_file.write("@1\n"
                                "D=A\n"
                                "@FRAME\n"
                                "A=M-D\n"
                                "D=M\n"
                                "@THAT\n"
                                "M=D\n")
        # THIS = *(FRAME-2)
        self._output_file.write("@2\n"
                                "D=A\n"
                                "@FRAME\n"
                                "A=M-D\n"
                                "D=M\n"
                                "@THIS\n"
                                "M=D\n")
        # ARG = *(FRAME-3)
        self._output_file.write("@3\n"
                                "D=A\n"
                                "@FRAME\n"
                                "A=M-D\n"
                                "D=M\n"
                                "@ARG\n"
                                "M=D\n")
        # LCL = *(FRAME-4)
        self._output_file.write("@4\n"
                                "D=A\n"
                                "@FRAME\n"
                                "A=M-D\n"
                                "D=M\n"
                                "@LCL\n"
                                "M=D\n")
        # goto RET
        self._output_file.write("@RET\n"
                                "A=M\n"
                                "0;JMP\n")


        self._output_file.write("// End write_return\n")


    def write_call(self, function_name, args):

        self._output_file.write("// Begin write_call\n")

        # PUSH return label
        self.write_push("constant", "RET_" + function_name + "_" + str(self._counter)) # push return address

        # PUSH LCL ARG THIS THAT
        self._output_file.write("@LCL\n"  # load LCL to A
                                "D=M\n"
                                "@SP\n"   # Save LCL to SP location
                                "A=M\n"
                                "M=D\n"
                                "@SP\n"    # Increase SP by 1
                                "M=M+1\n")

        self._output_file.write("@ARG\n"  # load ARG to A
                                "D=M\n"
                                "@SP\n"   # Save ARG to SP location
                                "A=M\n"
                                "M=D\n"
                                "@SP\n"    # Increase SP by 1
                                "M=M+1\n")

        self._output_file.write("@THIS\n"  # load THIS to A
                                "D=M\n"
                                "@SP\n"   # Save THIS to SP location
                                "A=M\n"
                                "M=D\n"
                                "@SP\n"    # Increase SP by 1
                                "M=M+1\n")

        self._output_file.write("@THAT\n"  # load THAT to A
                                "D=M\n"
                                "@SP\n"   # Save THAT to SP location
                                "A=M\n"
                                "M=D\n"
                                "@SP\n"    # Increase SP by 1
                                "M=M+1\n")

        # ARG = SP-n-5
        self._output_file.write("@" + str(args) + "\n")
        self._output_file.write("D=A\n"
                                "@SP\n"
                                "D=M-D\n"
                                "@5\n"
                                "D=D-A\n"
                                "@ARG\n"
                                "M=D\n")

        # LCL = SP
        self._output_file.write("@SP\n"
                                "D=M\n"
                                "@LCL\n"
                                "M=D\n")

        # goto f
        self._output_file.write("@" + function_name + "\n")
        self._output_file.write("0;JMP\n")

        # return address
        self._output_file.write("(RET_" + function_name + "_" + str(self._counter) + ")\n")
        self._output_file.write("// End write_call\n")
        self._counter += 1

    def write_bootstrap(self):
        self._output_file.write("@256\n"
                                "D=A\n"
                                "@SP\n"
                                "M=D\n")
        self.write_call("Sys.init", 0)

    def write_push(self, mem_segment, index):
        """
        Handles the writing of all push commands, delegates to subroutines according to the type of command
        :param mem_segment: The memory segment - first parameter of the push command
        :param index: The index - second parameter of the push command
        :return: N/A
        """
        if mem_segment == "constant":
            self.write_const(index)

        if self.is_mem_segment(mem_segment):
            self.write_mem_seg(mem_segment, index)

        if self.is_reg_segment(mem_segment):
            self.write_reg_seg(mem_segment, index)

        if mem_segment == "static":
            self.write_static(index)

        self.load_stack_pointer()
        self.increase_stack_pointer()

    def write_pop(self, mem_segment, index):
        """
        Handles the writing of all pop commands, delegates to subroutines according to the type of command
        :param mem_segment: The memory segment - first parameter of the pop command
        :param index: The index - second parameter of the pop command
        :return: N/A
        """
        if self.is_mem_segment(mem_segment):
            self.write_stack_to_mem(mem_segment, index)

        if self.is_reg_segment(mem_segment):
            self.write_stack_to_reg(mem_segment, index)

        if mem_segment == "static":
            self.load_stack_to_dest("D")
            self.decrease_stack_pointer()
            self._output_file.write("@" + self._file_name + "." + str(index) + "\n")
            self.write_c_command("M", "D")


    def is_mem_segment(self, mem_segment):
        """
        Checks if a segment is a memory segment, i.e local, argument, this or that
        :param mem_segment: The mem segment for the pop \ push command
        :return: true if it is pointer or temp
        """
        return mem_segment in ["local", "argument", "this", "that"]

    def is_reg_segment(self, mem_segment):
        """
        Checks if a segment is a register segment, i.e local, argument, this or that
        :param mem_segment: The mem segment for the pop \ push command
        :return: true if it is pointer or temp
        """
        return mem_segment in ["pointer", "temp"]

    def get_mem_token(self, mem_segment):
        """
        Tokenizes a memory segment name into the corresponding HACK language token
        :param mem_segment: The memory segment to tokenize
        :return: The HACK token
        """
        if mem_segment == "local":
            return "LCL"
        if mem_segment == "argument":
            return "ARG"
        if mem_segment == "this":
            return "THIS"
        if mem_segment == "that":
            return "THAT"

    def get_reg_token(self, reg):
        """
        Tokenizes a memory segment name into the corresponding HACK language token
        :param mem_segment: The memory segment to tokenize
        :return: The HACK token
        """
        if reg == "pointer":
            return "3"
        elif reg == "temp":
            return "5"

    # ===============Writing================

    def write_mem_seg(self, mem_segment, index):
        """
        Writes a block of HACK language that loads from memory segment block to the stack
        :param mem_segment: the memory segment
        :param index: The index to load
        :return:N/A
        """
        self.write_a_command(str(index))
        token = self.get_mem_token(mem_segment)
        self._output_file.write("D=A\n"
                                "@" + token + "\n"
                                "A=D+M\n"
                                "D=M\n")

    def write_reg_seg(self, mem_segment, index):
        """
        Writes a block of HACK language that loads from register segment block to the stack
        :param mem_segment: the reg segment
        :param index: The index to load
        :return:N/A
        """
        self.write_a_command(str(index))
        token = self.get_reg_token(mem_segment)
        self._output_file.write("D=A\n"
                                "@" + token + "\n"
                                "A=D+A\n"
                                "D=M\n")

    def write_const(self, index):
        """
        Writes a block of HACK language that loads a constant into the stack
        :param index: The constant to load
        :return:N/A
        """
        self.write_a_command(str(index))
        self._output_file.write("D=A\n")

    def write_static(self, index):
        """
        Writes a block of HACK language that loads from a static to the stack
        :param index: The index to load
        :return:N/A
        """
        self.write_a_command(self._file_name + "." + str(index))
        self._output_file.write("D=M\n")

    def write_stack_to_mem(self, mem_segment, index):
        """
        Writes a block of HACK language that loads from the stack into a memory segment
        :param mem_segment: The memory segment to load to
        :param index: The index
        :return: N/A
        """

        self.write_a_command(str(index))
        token = self.get_mem_token(mem_segment)
        self._output_file.write("D=A\n"
                                "@" + token + "\n"
                                "D=D+M\n"
                                "@R13\n"
                                "M=D\n"
                                "@SP\n"
                                "A=M-1\n"
                                "D=M\n"
                                "@R14\n"
                                "M=D\n"
                                "@SP\n"
                                "M=M-1\n"
                                "@R14\n"
                                "D=M\n"
                                "@R13\n"
                                "A=M\n"
                                "M=D\n")

    def write_stack_to_reg(self, mem_segment, index):
        """
        Writes a block of HACK language that loads from the stack into a register segment
        :param mem_segment: The memory segment to load to
        :param index: The index
        :return: N/A
        """

        self.write_a_command(str(index))
        token = self.get_reg_token(mem_segment)
        self._output_file.write("D=A\n"
                                "@" + token + "\n"
                                "D=D+A\n"
                                "@R13\n"
                                "M=D\n"
                                "@SP\n"
                                "A=M-1\n"
                                "D=M\n"
                                "@R14\n"
                                "M=D\n"
                                "@SP\n"
                                "M=M-1\n"
                                "@R14\n"
                                "D=M\n"
                                "@R13\n"
                                "A=M\n"
                                "M=D\n")

    def write_a_command(self, val):
        """
        Writes the HACK block of code that loads an index into A register
        :param val: The value to load
        :return: N/A
        """
        self._output_file.write("@" + val + "\n")

    def write_c_command(self, destination, comp, jmp=None):
        """
        Writes a HACK block "c" command, i.e an insertion of one value into another
        :param destination: The destination register
        :param comp: The register to insert (right side of the =)
        :param jmp: Will be used when jmp occurs
        :return:
        """
        if destination is not None:
            self._output_file.write(destination + "=")
        self._output_file.write(comp)
        if jmp is not None:
            self._output_file.write(";"+jmp)
        self._output_file.write("\n")

    def load_stack_to_dest(self, destination):
        """
        Writes the HACK block that loads the Stack Pointer into the destination
        :param destination: The destination to load the stack pointer to
        :return: N/A
        """
        self.load_stack_pointer_pop()
        self.write_c_command(destination, "M")  # destination = *SP

    def load_stack_pointer_pop(self):
        """
        Writes the HACK block that loads the Stack pointer as shown in class for pop
        :return: N/A
        """
        self.write_a_command("SP")
        self.write_c_command("A", "M-1")

    def load_stack_pointer(self):
        """
        Writes the HACK block that loads the Stack pointer
        :return: N/A
        """
        self.write_a_command("SP")      # A = &SP
        self.write_c_command("A", "M")  # A = SP
        self.write_c_command("M", "D")

    def increase_stack_pointer(self):
        """
        Writes the HACK block that increases the Stack pointer
        :return: N/A
        """
        self.write_a_command("SP")       # A = &SP
        self.write_c_command("M", "M+1") # SP += 1

    def decrease_stack_pointer(self):
        """
        Writes the HACK block that decreases the stack pointer
        :return: N/A
        """
        self.write_a_command("SP")        # A = &SP
        self.write_c_command("M", "M-1")  # SP -= 1

    def arithmetic_shared(self):
        """
        Writes a joint block of HACK code that is used with every arithmetic operation
        :return:
        """
        self._output_file.write("@SP\n"
                                "A=M-1\n"
                                "D=M\n"
                                "A=A-1\n")

