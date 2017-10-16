from sys import argv
import os

# Messages
USAGE_MSG = "Usage: {0} <filename.vm or directory>"
BAD_INPUT = "Invalid program. Terminating compilation"
BAD_FILE = "Problem reading from file or writing to file"

# Exit codes
CODE_SUCCESS = 0
CODE_FAILURE = -1

VM_EXTENSION = ".vm"
ASM_EXTENSION = ".asm"

# Special indicators
COMMENT_PREFIX = "//"

# Stack Commands
C_ST_PUSH = "push"
C_ST_POP = "pop"

C_ST_ADD = "add"
C_ST_SUB = "sub"
C_ST_NEG = "neg"
C_ST_AND = "and"
C_ST_OR = "or"
C_ST_NOT = "not"
C_ST_LIST = [C_ST_ADD, C_ST_SUB, C_ST_NEG, C_ST_AND, C_ST_OR, C_ST_NOT]
OP_TO_SYMBOL = {C_ST_ADD : '+', C_ST_SUB : '-', C_ST_NEG : '-',
                C_ST_AND : "&", C_ST_OR : "|", C_ST_NOT : "!"}

# Stack comparison commands
C_ST_EQ = "eq"
C_ST_GT = "gt"
C_ST_LT = "lt"
C_ST_COMP_LIST = [C_ST_EQ, C_ST_GT, C_ST_LT]
OP_COMP_DICT = {C_ST_EQ : "JEQ", C_ST_GT : "JGT", C_ST_LT : "JLT"}

# Regular segments
SEG_ARG = "argument"
SEG_LCL = "local"
SEG_THIS = "this"
SEG_THAT = "that"
SEGMENTS = {SEG_ARG : "ARG", SEG_LCL : "LCL", SEG_THIS : "THIS", SEG_THAT : "THAT"}

C_PUSH_SEGMENT_TUP = tuple(["{0} {1}".format(C_ST_PUSH, segment) for segment in SEGMENTS]) # VM syntax: "push argument/local/etc."
C_POP_SEGMENT_TUP = tuple(["{0} {1}".format(C_ST_POP, segment) for segment in SEGMENTS]) # VM syntax: "pop argument/local/etc."

# Special segments
SEG_CONSTANT = "constant"
SEG_STATIC = "static"

C_PUSH_CONSTANT = "{0} {1}".format(C_ST_PUSH, SEG_CONSTANT) # VM syntax: "push constant"
C_POP_CONSTANT = "{0} {1}".format(C_ST_POP, SEG_CONSTANT) # VM syntax: "pop constant"
C_PUSH_STATIC_SEGMENT = "{0} {1}".format(C_ST_PUSH, SEG_STATIC) # VM syntax: "push static"
C_POP_STATIC_SEGMENT = "{0} {1}".format(C_ST_POP, SEG_STATIC)  # VM syntax: "pop static"

# Temporary segments
SEG_TMP = "temp"
SEG_POINTER = "pointer"
SEGMENTS_TMP = {SEG_POINTER : "3", SEG_TMP : "5"}

C_PUSH_TMP_SEGMENT_TUP = tuple(["{0} {1}".format(C_ST_PUSH, SEG_TMP),
                                "{0} {1}".format(C_ST_PUSH, SEG_POINTER)])
                                # VM syntax: "push temp", "push pointer"
                                
C_POP_TMP_SEGMENT_TUP = tuple(["{0} {1}".format(C_ST_POP, SEG_TMP),
                               "{0} {1}".format(C_ST_POP, SEG_POINTER)])
                                # VM syntax: "pop temp", "pop pointer"

# Ex 8 Additions
C_LABEL = "label"
C_GOTO = "goto"
C_IFGOTO = "if-goto"
C_FUNC = "function"
C_CALL = "call"
C_RETURN = "return"
PROG_BOOTSTRAP_LINE = "call Sys.init 0"

# ASM code strings
PROG_INIT = """
@256
D=A
@SP
M=D
"""

PROG_INIT_DIR = """
@Sys.init
0; JMP
"""

OP_BINARY = """
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M{0}D
@SP
M=M+1
"""

OP_UNARY = """
@SP
M=M-1
A=M
M={0}M
@SP
M=M+1
"""

OP_DICT = {C_ST_ADD : OP_BINARY.format(OP_TO_SYMBOL[C_ST_ADD]),
           C_ST_SUB : OP_BINARY.format(OP_TO_SYMBOL[C_ST_SUB]),
           C_ST_AND : OP_BINARY.format(OP_TO_SYMBOL[C_ST_AND]),
           C_ST_OR : OP_BINARY.format(OP_TO_SYMBOL[C_ST_OR]),
           C_ST_NEG : OP_UNARY.format(OP_TO_SYMBOL[C_ST_NEG]),
           C_ST_NOT : OP_UNARY.format(OP_TO_SYMBOL[C_ST_NOT]),
           }

OP_COMP = """
@SP
M=M-1
A=M
D=M
@R13
M=D
@SP
M=M-1
A=M
D=M
@R14
M=D
@R13
D=M
@REG.{0}.{1}
D;JEQ
@R14
D=M
@REG.{0}.{1}
D;JEQ
@MGZ.{0}.{1}
D;JGT
@MLZ.{0}.{1}
D;JLT
(MGZ.{0}.{1})
@R13
D=M
@SPECIAL.{0}.{1}
D;JLT
@REG.{0}.{1}
0;JMP
(MLZ.{0}.{1})
@R13
D=M
@SPECIAL.{0}.{1}
D;JGT
@REG.{0}.{1}
0;JMP
(SPECIAL.{0}.{1})
@R14
D=M
@TRUE.{0}.{1}
D;{2}
@FALSE.{0}.{1}
0;JMP
(REG.{0}.{1})
@R13
D=M
@R14
D=M-D
@TRUE.{0}.{1}
D;{2}
(FALSE.{0}.{1})
@SP
A=M
M=0
@CONT.{0}.{1}
0;JMP
(TRUE.{0}.{1})
@SP
A=M
M=-1
(CONT.{0}.{1})
@SP
M=M+1
"""
             
OP_PUSH_CONST_HEADER = """
@{0}
D=A
"""

OP_PUSH_SEGMENT_HEADER = """
@{0}
D=A
@{1}
A=M+D
D=M
"""

OP_PUSH_SEGMENT_FOOTER = """
@SP
A=M
M=D
@SP
M=M+1
"""

OP_POP_SEGMENT_HEADER = """
@{0}
D=A
@{1}
D=M+D
@13
M=D
"""

OP_POP_SEGMENT_FOOTER = """
@SP
A=M-1
D=M
@SP
M=M-1
@R13
A=M
M=D
"""

OP_POP_CONST = """
@SP
M=M-1
"""

OP_PUSH_TMP_SEGMENT_HEADER = """
@{0}
D=A
@{1}
A=A+D
D=M
"""

OP_POP_TMP_SEGMENT_HEADER = """
@{0}
D=A
@{1}
D=A+D
@13
M=D
"""

OP_PUSH_STATIC_SEGMENT_HEADER = """
@{0}.{1}
D=M
"""

OP_POP_STATIC_SEGMENT = """
@SP
A=M-1
D=M
@SP
M=M-1
@{0}.{1}
M=D
"""

OP_LABEL = """
({0})
"""

LABEL_FORMAT = "{0}.{1}.{2}"

FUNC_FORMAT = "{0}.{1}"

#OP_LABEL_ = """
#({0})
#""".format(LABEL_FORMAT)

#OP_FUNC = """
#({0}.{1})
#"""

OP_GOTO = """
@{0}
0;JMP
"""

OP_IFGOTO = """
@SP
A=M-1
D=M
@SP
M=M-1
@{0}
D;JNE
"""

OP_CALL_PUSH = """
@{0} 
D=A
@ARG
A=M+D
D=M
@ARG
A=M
M=D
"""

class Parser(object):
    """
    This class is built in order to perform the whole assembly process.
    Contains functions that handle the different textual aspects of the asm code,
    and converts them into the correct Hack bit codes.
    """
    
    def __init__(self):
        self.jump_counter = 0        
        self.func_call_counter = 0
        self.current_func = ""        
    
    def parse(self, file_list):
        """
        Main function of the parser.
        Receives list of tuples (<filename, file content>), and returns its asm translation.
        """        
        # Init program string        
        result = PROG_INIT
        
        result += self.parse_line(PROG_BOOTSTRAP_LINE, "")
        
        for i in range(len(file_list)):
            # Preliminary handling of input lines
            filename, text = file_list[i]
            lines = text.splitlines()
            lines = self.clean_lines(lines)

            # Parse lines
            for line in lines:
                print ("parsing line: " + line)
                result += self.parse_line(line, filename)
        
        # Return the ready program code
        return result       
       
    def clean_lines(self, lines):
        """
        Removes empty lines, comments and spaces.
        Returns a new list of lines, which are the actual program lines.
        """
        new_lines = []
        for line in lines:
            # Remove comments
            split_line = line.split(COMMENT_PREFIX)
            # Remove spaces
            clean_line = split_line[0].strip()
            if clean_line:
                # Add line only if it is an actual program line.
                new_lines.append(clean_line)
        return new_lines
                        
    def parse_line(self, line, filename):
        """
        Translates a single VM code line to an ASM code segment
        """        
        if line.startswith(C_PUSH_CONSTANT):
            # push constant x
            return OP_PUSH_CONST_HEADER.format(line.split()[-1]) + "\n" + OP_PUSH_SEGMENT_FOOTER

        elif line.startswith(C_POP_CONSTANT):
            # pop constant x (i.e just pop stack top)
            return OP_POP_CONST
        
        elif line in C_ST_LIST:
            # Arithmetic operation (add/sub/neg/not/and/or)
            return OP_DICT[line]
        
        elif line.startswith(C_PUSH_SEGMENT_TUP):
            # push <segment> <index> (argument/local/this/that)
            segment, index = SEGMENTS[line.split()[1]], line.split()[2]
            return OP_PUSH_SEGMENT_HEADER.format(index, segment) + "\n" + OP_PUSH_SEGMENT_FOOTER
        
        elif line.startswith(C_POP_SEGMENT_TUP):
            # pop <segment> <index> (argument/local/this/that)
            segment, index = SEGMENTS[line.split()[1]], line.split()[2]
            return OP_POP_SEGMENT_HEADER.format(index, segment) + "\n" + OP_POP_SEGMENT_FOOTER
        
        elif line.startswith(C_PUSH_TMP_SEGMENT_TUP):
            # push <tmp_segment> <index> (temp/pointer)
            segment, index = SEGMENTS_TMP[line.split()[1]], line.split()[2]
            return OP_PUSH_TMP_SEGMENT_HEADER.format(index, segment) + "\n" + OP_PUSH_SEGMENT_FOOTER
        
        elif line.startswith(C_POP_TMP_SEGMENT_TUP):
            # pop <tmp_segment> <index> (temp/pointer)
            segment, index = SEGMENTS_TMP[line.split()[1]], line.split()[2]
            return OP_POP_TMP_SEGMENT_HEADER.format(index, segment) + "\n" + OP_POP_SEGMENT_FOOTER

        elif line.startswith(C_PUSH_STATIC_SEGMENT):
            # push static x
            index = line.split()[2]
            return OP_PUSH_STATIC_SEGMENT_HEADER.format(filename, index) + "\n" + OP_PUSH_SEGMENT_FOOTER
        
        elif line.startswith(C_POP_STATIC_SEGMENT):
            # pop static x
            index = line.split()[2]
            return OP_POP_STATIC_SEGMENT.format(filename, index)

        elif line in C_ST_COMP_LIST:
            # Comparison (eq/gt/lt)
            self.jump_counter += 1
            return OP_COMP.format(filename, self.jump_counter, OP_COMP_DICT[line])

        elif line.startswith(C_LABEL):
            label = line.split()[-1]
            return OP_LABEL.format(LABEL_FORMAT.format(filename, self.current_func, label))
        
        elif line.startswith(C_GOTO):
            label = line.split()[-1]    
            return OP_GOTO.format(LABEL_FORMAT.format(filename, self.current_func, label))
            
        elif line.startswith(C_IFGOTO):
            label = line.split()[-1]    
            return OP_IFGOTO.format(LABEL_FORMAT.format(filename, self.current_func, label))
        
        elif line.startswith(C_FUNC):
            name = line.split()[1]
            nargs = int(line.split()[2])
            self.current_func = name
            s = ""
            s += OP_LABEL.format(name)

            # Function declaration - push <nargs> zeros
            s += '\n'.join([OP_PUSH_CONST_HEADER.format(0) + OP_PUSH_SEGMENT_FOOTER for i in range(nargs)])
            return s
            
        elif line.startswith(C_CALL):
            name = line.split()[1]
            nargs = int(line.split()[2])
            ret_label = "{0}.return.{1}".format(name, self.func_call_counter)
            self.func_call_counter += 1
            
            # lines 1-5
            s=""
            
            to_push = [ret_label, SEGMENTS[SEG_LCL], SEGMENTS[SEG_ARG], SEGMENTS[SEG_THIS], SEGMENTS[SEG_THAT]]            
            s += OP_PUSH_CONST_HEADER.format(ret_label) + OP_PUSH_SEGMENT_FOOTER # push return-label
            
            t = """
            @{0}
            D=M            
            """
            
            s += '\n'.join([t.format(to_push[i+1]) + OP_PUSH_SEGMENT_FOOTER for i in range(4)]) # push return-label
                       
            t = """
            @{0}
            D=A
            @ARG
            D=D+M
            @tmpvar
            M=D
            @{1}
            D={2}
            @tmpvar
            A=M
            M=D            
            """ 
                
            # line 6
            s += """
            @SP
            D=M
            @{0}
            D=D-A
            @ARG
            M=D
            """.format(nargs+5)
                
            # line 7                
            s+="""
            @SP
            D=M
            @LCL
            M=D
            """
            
            s+=OP_GOTO.format(name)
            
            s+=OP_LABEL.format(ret_label)
            return s

        elif line.startswith(C_RETURN):               
            
            s = ""
            
            # line 1
            s += """
            @LCL
            D=M
            @FRAME
            M=D
            """
            
            # line 2
            s += """
            @5
            D=D-A
            A=D
            D=M
            @RET
            M=D                                           
            """
            
            # line 3
            s += OP_POP_SEGMENT_HEADER.format(0, SEGMENTS[SEG_ARG]) + OP_POP_SEGMENT_FOOTER
            
            # line 4
            s += """
            @ARG
            D=M+1
            @SP
            M=D
            """
            
            # line 5-8
            t = """
            @FRAME
            D=M
            @{0}
            D=D-A
            A=D
            D=M
            @{1}
            M=D                                           
            """

            segs = [SEGMENTS[SEG_THAT], SEGMENTS[SEG_THIS], SEGMENTS[SEG_ARG], SEGMENTS[SEG_LCL]]
            s += '\n'.join([t.format(i+1, segs[i]) for i in range(4)])
            
            # line 9    
            s += """
            @RET
            A=M
            0;JMP
            """                    
            return s

                                            
def asm_filename_from_filename(vm_filename):
    """
    Gets an input filename and returns the output ASM filename.
    """
    return os.path.splitext(vm_filename)[0] + ASM_EXTENSION

def asm_filename_from_dirname(dirname):
    """
    Gets an input directory name and returns the output ASM filename.
    """
    abspath = os.path.abspath(dirname)
    fn = os.path.basename(abspath) + ASM_EXTENSION
    return "{0}/{1}".format(abspath, fn)
    
def get_file_clean_name(fn):
    return os.path.splitext(os.path.basename(fn))[0]
      
def main(path):
    """
    Main program - compiles the given file,
    and saves the output to a Hack file.
    """    
    parser = Parser()
    
    if os.path.isfile(path):
        # Handle single file
        file_list = [(get_file_clean_name(path), open(path).read())]
        asm_filename = asm_filename_from_filename(path)
    else:
        # Handle directory
        try:            
            file_list = [(get_file_clean_name(fn), open("{0}/{1}".format(os.path.abspath(path), fn)).read()) 
                          for fn in os.listdir(path) if fn.endswith(VM_EXTENSION)]
        except OSError:
            raise
        asm_filename = asm_filename_from_dirname(path)
    
    # Compile file content and handle compilation errors
    try:
        asm_program = parser.parse(file_list)        
    except:
        raise Exception(BAD_INPUT)        
    
    # Write to Hack file and handle file errors.
    try:
        open(asm_filename, 'w').write(asm_program)
    except OSError:
        raise
        
def usage():
    """
    Prints a usage message to the user.
    """
    print(USAGE_MSG.format(argv[0]))

if __name__=="__main__":

    if len(argv) != 2:
        # Invalid command line arguments
        usage()
        exit(CODE_SUCCESS)
        
    # Run program, exit(-1) on failure
    try:
        main(argv[1])
    except OSError as e:
        print("HERE")
        print(BAD_FILE)
        exit(CODE_FAILURE)
    except Exception as e:
        raise
        exit(CODE_FAILURE)
    
    # exit(0) on success
    exit(CODE_SUCCESS)