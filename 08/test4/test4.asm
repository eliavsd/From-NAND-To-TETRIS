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
(Main.main)
// End writeFunction
// Begin writeCall
@RET_Stata.init_1
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
@Stata.init
0;JMP
(RET_Stata.init_1)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
// Begin writeCall
@RET_Statb.init_2
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
@Statb.init
0;JMP
(RET_Statb.init_2)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@5
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Main.f_3
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
@Main.f
0;JMP
(RET_Main.f_3)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
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
// Begin writeFunction
(Main.f)
// End writeFunction
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Main.dump_4
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
@Main.dump
0;JMP
(RET_Main.dump_4)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
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
// Begin writeCall
@RET_Stata.setVal_5
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
@Stata.setVal
0;JMP
(RET_Stata.setVal_5)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
// Begin writeCall
@RET_Statb.setVal_6
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
@Statb.setVal
0;JMP
(RET_Statb.setVal_6)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@10
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Main.dump_7
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
@Main.dump
0;JMP
(RET_Main.dump_7)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
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
// Begin writeFunction
(Main.dump)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// End writeFunction
// Begin writeCall
@RET_Stata.getVal_8
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
@Stata.getVal
0;JMP
(RET_Stata.getVal_8)
// End writeCall
@0
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
// Begin writeCall
@RET_Statb.getVal_9
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
@Statb.getVal
0;JMP
(RET_Statb.getVal_9)
// End writeCall
@1
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Memory.poke_10
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
@2
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
@Memory.poke
0;JMP
(RET_Memory.poke_10)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Memory.poke_11
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
@2
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
@Memory.poke
0;JMP
(RET_Memory.poke_11)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
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
// Begin writeFunction
(Memory.init)
// End writeFunction
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.0
M=D
@2048
D=A
@SP
A=M
M=D
@SP
M=M+1
@Memory.0
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@14334
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@2049
D=A
@SP
A=M
M=D
@SP
M=M+1
@Memory.0
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@2050
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
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
// Begin writeFunction
(Memory.peek)
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
@Memory.0
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
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
// Begin writeFunction
(Memory.poke)
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
@Memory.0
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
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
// Begin writeFunction
(Memory.alloc)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
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
@1
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
@secondArgNeg_7
D;JLT
@SP
M=M-1
A=M
D=M
@IS_LESS_7
D;JLT
@CALC_DIFF_7
0;JMP
(CALC_DIFF_7)
@R13
D=D-M
@IS_LESS_7
D;JLT
@NOT_LESS_7
D;JEQ
@NOT_LESS_7
D;JGT
(secondArgNeg_7)
@SP
M=M-1
A=M
D=M
@NOT_LESS_7
D;JGT
@CALC_DIFF_7
0;JMP
(NOT_LESS_7)
@SP
A=M
M=0
@END_COMP_7
0;JMP
(IS_LESS_7)
@SP
A=M
M=-1
@END_COMP_7
0;JMP
(END_COMP_7)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.alloc$IF_TRUE0
D;JNE
@Memory.alloc$IF_FALSE0
0;JMP
(Memory.alloc$IF_TRUE0)
@5
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Sys.error_12
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
@Sys.error
0;JMP
(RET_Sys.error_12)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.alloc$IF_FALSE0)
@2048
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.alloc$WHILE_EXP0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
@SP
M=M-1
A=M
D=M
@R13
M=D
@secondArgNeg_9
D;JLT
@SP
M=M-1
A=M
D=M
@IS_LESS_9
D;JLT
@CALC_DIFF_9
0;JMP
(CALC_DIFF_9)
@R13
D=D-M
@IS_LESS_9
D;JLT
@NOT_LESS_9
D;JEQ
@NOT_LESS_9
D;JGT
(secondArgNeg_9)
@SP
M=M-1
A=M
D=M
@NOT_LESS_9
D;JGT
@CALC_DIFF_9
0;JMP
(NOT_LESS_9)
@SP
A=M
M=0
@END_COMP_9
0;JMP
(IS_LESS_9)
@SP
A=M
M=-1
@END_COMP_9
0;JMP
(END_COMP_9)
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.alloc$WHILE_END0
D;JNE
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Memory.alloc$WHILE_EXP0
0;JMP
(Memory.alloc$WHILE_END0)
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@16379
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
@secondArgNeg_13
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_GREATER_13
D;JLT
@CALC_DIFF_13
0;JMP
(CALC_DIFF_13)
@R13
D=D-M
@NOT_GREATER_13
D;JLT
@NOT_GREATER_13
D;JEQ
@IS_GREATER_13
D;JGT
(secondArgNeg_13)
@SP
M=M-1
A=M
D=M
@IS_GREATER_13
D;JGT
@CALC_DIFF_13
0;JMP
(NOT_GREATER_13)
@SP
A=M
M=0
@END_COMP_13
0;JMP
(IS_GREATER_13)
@SP
A=M
M=-1
@END_COMP_13
0;JMP
(END_COMP_13)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.alloc$IF_TRUE1
D;JNE
@Memory.alloc$IF_FALSE1
0;JMP
(Memory.alloc$IF_TRUE1)
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Sys.error_13
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
@Sys.error
0;JMP
(RET_Sys.error_13)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.alloc$IF_FALSE1)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
M=M+D
D=A+1
@SP
M=D
@SP
M=M-1
A=M
D=M
@R13
M=D
@secondArgNeg_16
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_GREATER_16
D;JLT
@CALC_DIFF_16
0;JMP
(CALC_DIFF_16)
@R13
D=D-M
@NOT_GREATER_16
D;JLT
@NOT_GREATER_16
D;JEQ
@IS_GREATER_16
D;JGT
(secondArgNeg_16)
@SP
M=M-1
A=M
D=M
@IS_GREATER_16
D;JGT
@CALC_DIFF_16
0;JMP
(NOT_GREATER_16)
@SP
A=M
M=0
@END_COMP_16
0;JMP
(IS_GREATER_16)
@SP
A=M
M=-1
@END_COMP_16
0;JMP
(END_COMP_16)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.alloc$IF_TRUE2
D;JNE
@Memory.alloc$IF_FALSE2
0;JMP
(Memory.alloc$IF_TRUE2)
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
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D
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
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
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
M=M+D
D=A+1
@SP
M=D
@SP
M=M-1
A=M
D=M
@R13
M=D
@secondArgNeg_24
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_24
D;JLT
@CALC_DIFF_24
0;JMP
(CALC_DIFF_24)
@R13
D=D-M
@EQUAL_24
D;JEQ
@NOT_EQUAL_24
D;JNE
(secondArgNeg_24)
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_24
D;JGT
@CALC_DIFF_24
0;JMP
(NOT_EQUAL_24)
@SP
A=M
M=0
@END_EQ_24
0;JMP
(EQUAL_24)
@SP
A=M
M=-1
@END_EQ_24
0;JMP
(END_EQ_24)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.alloc$IF_TRUE3
D;JNE
@Memory.alloc$IF_FALSE3
0;JMP
(Memory.alloc$IF_TRUE3)
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
@3
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
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@4
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
M=M+D
D=A+1
@SP
M=D
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Memory.alloc$IF_END3
0;JMP
(Memory.alloc$IF_FALSE3)
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
@3
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
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.alloc$IF_END3)
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
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
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
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
M=M+D
D=A+1
@SP
M=D
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.alloc$IF_FALSE2)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@LCL
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
(Memory.deAlloc)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
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
A=M-1
D=M
A=A-1
M=M-D
D=A+1
@SP
M=D
@0
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
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
@secondArgNeg_40
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_40
D;JLT
@CALC_DIFF_40
0;JMP
(CALC_DIFF_40)
@R13
D=D-M
@EQUAL_40
D;JEQ
@NOT_EQUAL_40
D;JNE
(secondArgNeg_40)
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_40
D;JGT
@CALC_DIFF_40
0;JMP
(NOT_EQUAL_40)
@SP
A=M
M=0
@END_EQ_40
0;JMP
(EQUAL_40)
@SP
A=M
M=-1
@END_EQ_40
0;JMP
(END_EQ_40)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.deAlloc$IF_TRUE0
D;JNE
@Memory.deAlloc$IF_FALSE0
0;JMP
(Memory.deAlloc$IF_TRUE0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
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
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Memory.deAlloc$IF_END0
0;JMP
(Memory.deAlloc$IF_FALSE0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
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
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
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
M=M+D
D=A+1
@SP
M=D
@SP
M=M-1
A=M
D=M
@R13
M=D
@secondArgNeg_52
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_52
D;JLT
@CALC_DIFF_52
0;JMP
(CALC_DIFF_52)
@R13
D=D-M
@EQUAL_52
D;JEQ
@NOT_EQUAL_52
D;JNE
(secondArgNeg_52)
@SP
M=M-1
A=M
D=M
@NOT_EQUAL_52
D;JGT
@CALC_DIFF_52
0;JMP
(NOT_EQUAL_52)
@SP
A=M
M=0
@END_EQ_52
0;JMP
(EQUAL_52)
@SP
A=M
M=-1
@END_EQ_52
0;JMP
(END_EQ_52)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Memory.deAlloc$IF_TRUE1
D;JNE
@Memory.deAlloc$IF_FALSE1
0;JMP
(Memory.deAlloc$IF_TRUE1)
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@0
D=A
@LCL
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
M=M+D
D=A+1
@SP
M=D
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Memory.deAlloc$IF_END1
0;JMP
(Memory.deAlloc$IF_FALSE1)
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
D=M
A=A-1
M=M+D
D=A+1
@SP
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@THAT
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@1
D=A
@3
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@0
D=A
@5
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@THAT
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Memory.deAlloc$IF_END1)
(Memory.deAlloc$IF_END0)
@0
D=A
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
// Begin writeFunction
(Stata.init)
// End writeFunction
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
@SP
M=M-1
@Stata.0
M=D
@0
D=A
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
// Begin writeFunction
(Stata.setVal)
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
@SP
A=M-1
D=M
@SP
M=M-1
@Stata.0
M=D
@0
D=A
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
// Begin writeFunction
(Stata.getVal)
// End writeFunction
@Stata.0
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
// Begin writeFunction
(Statb.init)
// End writeFunction
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
@SP
M=M-1
@Statb.0
M=D
@0
D=A
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
// Begin writeFunction
(Statb.setVal)
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
@SP
A=M-1
D=M
@SP
M=M-1
@Statb.0
M=D
@0
D=A
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
// Begin writeFunction
(Statb.getVal)
// End writeFunction
@Statb.0
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
// Begin writeFunction
(Sys.init)
// End writeFunction
// Begin writeCall
@RET_Memory.init_14
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
@Memory.init
0;JMP
(RET_Memory.init_14)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
// Begin writeCall
@RET_Main.main_15
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
@Main.main
0;JMP
(RET_Main.main_15)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Sys.init$WHILE_EXP0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.init$WHILE_END0
D;JNE
@Sys.init$WHILE_EXP0
0;JMP
(Sys.init$WHILE_END0)
// Begin writeFunction
(Sys.halt)
// End writeFunction
(Sys.halt$WHILE_EXP0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.halt$WHILE_END0
D;JNE
@Sys.halt$WHILE_EXP0
0;JMP
(Sys.halt$WHILE_END0)
// Begin writeFunction
(Sys.wait)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
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
@0
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
@secondArgNeg_61
D;JLT
@SP
M=M-1
A=M
D=M
@IS_LESS_61
D;JLT
@CALC_DIFF_61
0;JMP
(CALC_DIFF_61)
@R13
D=D-M
@IS_LESS_61
D;JLT
@NOT_LESS_61
D;JEQ
@NOT_LESS_61
D;JGT
(secondArgNeg_61)
@SP
M=M-1
A=M
D=M
@NOT_LESS_61
D;JGT
@CALC_DIFF_61
0;JMP
(NOT_LESS_61)
@SP
A=M
M=0
@END_COMP_61
0;JMP
(IS_LESS_61)
@SP
A=M
M=-1
@END_COMP_61
0;JMP
(END_COMP_61)
@SP
M=M+1
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.wait$IF_TRUE0
D;JNE
@Sys.wait$IF_FALSE0
0;JMP
(Sys.wait$IF_TRUE0)
@1
D=A
@SP
A=M
M=D
@SP
M=M+1
// Begin writeCall
@RET_Sys.error_16
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
@Sys.error
0;JMP
(RET_Sys.error_16)
// End writeCall
@0
D=A
@5
D=D+A
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Sys.wait$IF_FALSE0)
(Sys.wait$WHILE_EXP0)
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
@0
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
@secondArgNeg_62
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_GREATER_62
D;JLT
@CALC_DIFF_62
0;JMP
(CALC_DIFF_62)
@R13
D=D-M
@NOT_GREATER_62
D;JLT
@NOT_GREATER_62
D;JEQ
@IS_GREATER_62
D;JGT
(secondArgNeg_62)
@SP
M=M-1
A=M
D=M
@IS_GREATER_62
D;JGT
@CALC_DIFF_62
0;JMP
(NOT_GREATER_62)
@SP
A=M
M=0
@END_COMP_62
0;JMP
(IS_GREATER_62)
@SP
A=M
M=-1
@END_COMP_62
0;JMP
(END_COMP_62)
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.wait$WHILE_END0
D;JNE
@50
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
(Sys.wait$WHILE_EXP1)
@0
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
@0
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
@secondArgNeg_64
D;JLT
@SP
M=M-1
A=M
D=M
@NOT_GREATER_64
D;JLT
@CALC_DIFF_64
0;JMP
(CALC_DIFF_64)
@R13
D=D-M
@NOT_GREATER_64
D;JLT
@NOT_GREATER_64
D;JEQ
@IS_GREATER_64
D;JGT
(secondArgNeg_64)
@SP
M=M-1
A=M
D=M
@IS_GREATER_64
D;JGT
@CALC_DIFF_64
0;JMP
(NOT_GREATER_64)
@SP
A=M
M=0
@END_COMP_64
0;JMP
(IS_GREATER_64)
@SP
A=M
M=-1
@END_COMP_64
0;JMP
(END_COMP_64)
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.wait$WHILE_END1
D;JNE
@0
D=A
@LCL
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
@0
D=A
@LCL
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Sys.wait$WHILE_EXP1
0;JMP
(Sys.wait$WHILE_END1)
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
@0
D=A
@ARG
D=D+M
@R13
M=D
@SP
A=M-1
D=M
@R14
M=D
@SP
M=M-1
@R14
D=M
@R13
A=M
M=D
@Sys.wait$WHILE_EXP0
0;JMP
(Sys.wait$WHILE_END0)
@0
D=A
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
// Begin writeFunction
(Sys.error)
// End writeFunction
(Sys.error$WHILE_EXP0)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
A=M-1
M=!M
@SP
A=M-1
M=!M
@SP
A=M-1
D=M
@SP
M=M-1
@Sys.error$WHILE_END0
D;JNE
@Sys.error$WHILE_EXP0
0;JMP
(Sys.error$WHILE_END0)
