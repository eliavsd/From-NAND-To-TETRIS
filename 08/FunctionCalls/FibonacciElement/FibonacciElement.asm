@256
D=A
@SP
M=D
// Begin writeCall
@RET_Sys.init_0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
D=M-D
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
(RET_Sys.init_0)
// End writeCall
// Begin writeFunction
(Main.fibonacci)
// End writeFunction
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@R13
M=D
@secondArgNeg_0
D;JLT
@SP
M=M-1
A=M
D=M
@IS_LESS_0
D;JLT
@CALC_DIFF_0
0;JMP
(CALC_DIFF_0)
@R13
D=D-M
@IS_LESS_0
D;JLT
@NOT_LESS_0
D;JEQ
@NOT_LESS_0
D;JGT
(secondArgNeg_0)
@SP
M=M-1
A=M
D=M
@NOT_LESS_0
D;JGT
@CALC_DIFF_0
0;JMP
(NOT_LESS_0)
@SP
A=M
M=0
@END_COMP_0
0;JMP
(IS_LESS_0)
@SP
A=M
M=-1
@END_COMP_0
0;JMP
(END_COMP_0)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Main.fibonacci$IF_TRUE
D;JNE
@Main.fibonacci$IF_FALSE
0;JMP
(Main.fibonacci$IF_TRUE)
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// Begin writeReturn
@LCL
D=M
@FRAME
M=D
@5
D=A
@FRAME
D=M-D
A=D
D=M
@RET
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@ARG
D=M
@SP
M=D+1
@1
D=A
@FRAME
A=M-D
D=M
@THAT
M=D
@2
D=A
@FRAME
A=M-D
D=M
@THIS
M=D
@3
D=A
@FRAME
A=M-D
D=M
@ARG
M=D
@4
D=A
@FRAME
A=M-D
D=M
@LCL
M=D
@RET
A=M
0;JMP
// End write_return
(Main.fibonacci$IF_FALSE)
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@2
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D
// Begin writeCall
@RET_Main.fibonacci_1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@SP
D=M-D
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(RET_Main.fibonacci_1)
// End writeCall
@0
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D
// Begin writeCall
@RET_Main.fibonacci_2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@SP
D=M-D
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(RET_Main.fibonacci_2)
// End writeCall
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
// Begin writeReturn
@LCL
D=M
@FRAME
M=D
@5
D=A
@FRAME
D=M-D
A=D
D=M
@RET
M=D
@SP
A=M-1
D=M
@ARG
A=M
M=D
@ARG
D=M
@SP
M=D+1
@1
D=A
@FRAME
A=M-D
D=M
@THAT
M=D
@2
D=A
@FRAME
A=M-D
D=M
@THIS
M=D
@3
D=A
@FRAME
A=M-D
D=M
@ARG
M=D
@4
D=A
@FRAME
A=M-D
D=M
@LCL
M=D
@RET
A=M
0;JMP
// End write_return
// Begin writeFunction
(Sys.init)
// End writeFunction
@4
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Main.fibonacci_3
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@SP
D=M-D
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(RET_Main.fibonacci_3)
// End writeCall
(Sys.init$WHILE)
@Sys.init$WHILE
0;JMP
