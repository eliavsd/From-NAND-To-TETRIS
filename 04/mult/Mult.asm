// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

	// Take R2 (the sum register) and set it to 0.
	// In this program we simply take R0 and add it to the sum
	// N times, where N represents the value in R1.
	@R2
	M=0
	
	// If R0 or R1 is zero, the result is zero and therefore
	// No further calculation is necessary
	@R0
	D=M
	@END
	D;JEQ // if R0=0, R2 is already zero so terminate
	
(ADD)
	@R1
	D=M
	@END
	D;JEQ // if R1=0, we have added R0 enough times
	
	//Add R0 to the current sum
	@R0
	D=M
	@R2
	M=M+D //R2 (sum) = R2 + R0
	
	// We've added R0 once, decrement 1 from R1
	// Once again R1 is the number of times we add R0
	// To the sum
	@R1
	M=M-1
	
	// Jump back to the beginning of the ADD loop
	@ADD
	0;JMP
	
(END)
	@END
	0;JMP