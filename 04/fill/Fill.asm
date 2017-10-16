// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.

	// In this program we use a neat trick: In binary -1 is 
	// The same as 11111...111. Therefore we can change the color of
	// Each 16 pixel line with one command.
	// The number of pixel lines (each pixel line has 16 pixels, 32 pixel lines
	// in a row on the screen, 256 rows on the screen. Total 32 * 256 = 8192)
	// Essentially we iterate over each 16-bit pixel block and operate on it.
	
	// We will set a variable called limit to 8192, so when we loop through
	// All the pixel lines and we reach 8192, we will know to reset the pixel
	// Index to 0.
	@8192		
	D = A
	@limit
	M = D

// Resets the pixel index to 0
(RESET)
	@i
	M = 0

(ITERATE)
	@limit
	D = M
	
	@i
	D = D - M // i - limit
	
	// If i >= limit (8192), we will reset i to 0
	@RESET
	D; JLE
	
	// Listen to keyboard
	@KBD
	D = M
	
	// If the bit at the keyboard addres is 0, clear the screen
	@CLEAR
	D; JEQ
	
	// Else, blacken the screen
	@BLACKEN
	0; JMP
	
(CLEAR)
	//Load the screen
	@SCREEN
	D = A
	              
	@i        
	A = D + M            // SCREEN + index = current pixel block
	M = 0                // Sets the pixel block to 0...000
	
	// end of current iteration of loop, i++
	@i
	M = M + 1 
	
	@ITERATE           // Continue the loop
	0; JMP

(BLACKEN)
	// Load the screen          
	@SCREEN
	D = A
	
	@i
	A = D + M            // SCREEN + index = current pixel block
	M = -1               // Sets the pixel block to 1...111
	
	// end of current iteration of loop, i++	
	@i
	M = M + 1
	
	@ITERATE              // Continue the loop
	0; JMP