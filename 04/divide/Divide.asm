// Performs the division R13/R14 and stores the quotient
// In R15.
// Based on the binary division algorithm explained
// At https://courses.cs.vt.edu/~cs1104/BuildingBlocks/divide.030.html

// Set quotient (R15) to 0
@R15
M=0

// Base case - if R13 < R14 then the quotient is 0.
// In this case the answer is already in the quotient which was
// Set to 0.

@R14
D = M

@R13
D = M - D

@END
D; JLT

// We aren't supposed to change R13 or R14 so we'll use "variables"
@R13
D = M
@dividend
M = D

@R14
D = M
@divisor
M = D

(ALIGN)

    // Align leftmost digits in dividend and divisor
    @divisor
    D = M
    @dividend
    D = M - D // dividend-divisor
    @SHIFT
    D; JGE

    // If the shifted divisor is greater than the dividend,
    // We must shift right once in order to finish aligning the digits.
    @divisor
    M = M>>
    // The digits are aligned, we can now begin dividing
    @DIVIDE
    0; JMP

(SHIFT)
    // Shift divisor left once
    @divisor
    M = M<<
    @ALIGN
    0; JMP

(DIVIDE)

    // If the current divisor is less than the original divisor
    // We are done
    @R14
    D = M
    @divisor
    D = M - D //currentDivisor - originalDivisor
    @END
    D; JLT

    // If the current dividend is greater than the current
    // Divisor, append 1 to the LSB of the quotient.
    // Else, append 0
    @divisor
    D = M
    @dividend
    D = M - D // dividend-divisor

    // Append 0 to the quotient
    @APPEND_ZERO
    D; JLT

    // Append 1 to the quotient
    @APPEND_ONE
    D; JGE


(APPEND_ZERO)
    // Bitwise multiply quotient by 2
    @R15
    M = M<<

	// Divide the current divisor by 2
    @RSHIFT
    0; JMP


(APPEND_ONE)
    // Bitwise multiply quotient by 2 and add 1
    @R15
    M = M<<
    M = M + 1

    // Subtract the current divisor from the dividend
    @divisor
    D = M
    @dividend
    M = M - D

	// Divide the current divisor by 2
    @RSHIFT
    0; JMP

(RSHIFT)
    // Bitwise divide the current divisor by 2
    @divisor
    M = M>>

	// Perform the next portion of division
    @DIVIDE
    0; JMP

(END)