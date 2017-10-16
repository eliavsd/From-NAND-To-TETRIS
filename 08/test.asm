
@256
D=A
@SP
M=D

@Sys.init.return.0
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

            @SP
            D=M
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

(Sys.init.return.0)

@30000
D=A


@SP
A=M
M=D
@SP
M=M+1

@30000
D=A


@SP
A=M
M=D
@SP
M=M+1

@SP
M=M-1
A=M
M=-M
@SP
M=M+1

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
@REG.test.1
D;JEQ
@R14
D=M
@REG.test.1
D;JEQ
@MGZ.test.1
D;JGT
@MLZ.test.1
D;JLT
(MGZ.test.1)
@R13
D=M
@SPECIAL.test.1
D;JLT
@REG.test.1
0;JMP
(MLZ.test.1)
@R13
D=M
@SPECIAL.test.1
D;JGT
@REG.test.1
0;JMP
(SPECIAL.test.1)
@R14
D=M
@TRUE.test.1
D;JGT
@FALSE.test.1
0;JMP
(REG.test.1)
@R13
D=M
@R14
D=M-D
@TRUE.test.1
D;JGT
(FALSE.test.1)
@SP
A=M
M=0
@CONT.test.1
0;JMP
(TRUE.test.1)
@SP
A=M
M=-1
(CONT.test.1)
@SP
M=M+1

@32767
D=A


@SP
A=M
M=D
@SP
M=M+1

@32766
D=A


@SP
A=M
M=D
@SP
M=M+1

@SP
M=M-1
A=M
M=-M
@SP
M=M+1

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
@REG.test.2
D;JEQ
@R14
D=M
@REG.test.2
D;JEQ
@MGZ.test.2
D;JGT
@MLZ.test.2
D;JLT
(MGZ.test.2)
@R13
D=M
@SPECIAL.test.2
D;JLT
@REG.test.2
0;JMP
(MLZ.test.2)
@R13
D=M
@SPECIAL.test.2
D;JGT
@REG.test.2
0;JMP
(SPECIAL.test.2)
@R14
D=M
@TRUE.test.2
D;JEQ
@FALSE.test.2
0;JMP
(REG.test.2)
@R13
D=M
@R14
D=M-D
@TRUE.test.2
D;JEQ
(FALSE.test.2)
@SP
A=M
M=0
@CONT.test.2
0;JMP
(TRUE.test.2)
@SP
A=M
M=-1
(CONT.test.2)
@SP
M=M+1

@32766
D=A


@SP
A=M
M=D
@SP
M=M+1

@32766
D=A


@SP
A=M
M=D
@SP
M=M+1

@SP
M=M-1
A=M
M=-M
@SP
M=M+1

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
@REG.test.3
D;JEQ
@R14
D=M
@REG.test.3
D;JEQ
@MGZ.test.3
D;JGT
@MLZ.test.3
D;JLT
(MGZ.test.3)
@R13
D=M
@SPECIAL.test.3
D;JLT
@REG.test.3
0;JMP
(MLZ.test.3)
@R13
D=M
@SPECIAL.test.3
D;JGT
@REG.test.3
0;JMP
(SPECIAL.test.3)
@R14
D=M
@TRUE.test.3
D;JLT
@FALSE.test.3
0;JMP
(REG.test.3)
@R13
D=M
@R14
D=M-D
@TRUE.test.3
D;JLT
(FALSE.test.3)
@SP
A=M
M=0
@CONT.test.3
0;JMP
(TRUE.test.3)
@SP
A=M
M=-1
(CONT.test.3)
@SP
M=M+1

@32767
D=A


@SP
A=M
M=D
@SP
M=M+1

@32766
D=A


@SP
A=M
M=D
@SP
M=M+1

@SP
M=M-1
A=M
M=-M
@SP
M=M+1

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
@REG.test.4
D;JEQ
@R14
D=M
@REG.test.4
D;JEQ
@MGZ.test.4
D;JGT
@MLZ.test.4
D;JLT
(MGZ.test.4)
@R13
D=M
@SPECIAL.test.4
D;JLT
@REG.test.4
0;JMP
(MLZ.test.4)
@R13
D=M
@SPECIAL.test.4
D;JGT
@REG.test.4
0;JMP
(SPECIAL.test.4)
@R14
D=M
@TRUE.test.4
D;JGT
@FALSE.test.4
0;JMP
(REG.test.4)
@R13
D=M
@R14
D=M-D
@TRUE.test.4
D;JGT
(FALSE.test.4)
@SP
A=M
M=0
@CONT.test.4
0;JMP
(TRUE.test.4)
@SP
A=M
M=-1
(CONT.test.4)
@SP
M=M+1
