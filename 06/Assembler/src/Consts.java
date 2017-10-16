import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class Consts {
    
    static final String MSG_USAGE = "Usage: Assembler <filename.asm>";
    static final String INVALID_CODE = "Compilation failed: Invalid ASM code";
    static final String BAD_IN_FILE = "Failed to read from file";
    static final String BAD_OUT_FILE = "Failed to write to file";


    static final int SUCCESS = 0;
    static final int FAILURE = -1;

    static final String HACK_EXTENSION = ".hack";
    static final String ASM_EXTENSION = ".asm";
    
    static final String A_COMMAND_ASM_PREFIX = "@";
    static final String JMP_DELIM = ";";
    static final String EQUAL_DELIM = "=";
    static final String LABEL_OPEN_PARAN = "(";
    static final String COMMENT_PREFIX = "//";
    static final String A_COMMAND_HACK_PREFIX = "0";
    static final String C_COMMAND_HACK_PREFIX = "1";
    static final String DEFAULT_JUMP = "000";
    static final String DEFAULT_DEST = "000";
    
    static final Map<String, String> JUMP_MAP;
    static final Map<String, String> DEST_MAP;
    static final Map<String, String> COMP_MAP;
    static final Map<String, String> DEFAULT_SYMBOL_MAP;

    /*
     * Initialize the constant maps
     */
    static {
        Map<String, String> map1 = new HashMap<>();
        map1.put("JGT", "001");
        map1.put("JEQ", "010");
        map1.put("JGE", "011");
        map1.put("JLT", "100");
        map1.put("JNE", "101");
        map1.put("JLE", "110");
        map1.put("JMP", "111");
        JUMP_MAP = Collections.unmodifiableMap(map1);
        // Create a map for possible dests
        Map<String, String> map2 = new HashMap<>();
        map2.put("M", "001");
        map2.put("D", "010");
        map2.put("MD", "011");
        map2.put("A", "100");
        map2.put("AM", "101");
        map2.put("AD", "110");
        map2.put("AMD", "111");
        DEST_MAP = Collections.unmodifiableMap(map2);
        
        //// Create a map for computations
        Map<String, String> map3 = new HashMap<>();
        map3.put("0", "110101010");
        map3.put("1", "110111111");
        map3.put("-1", "110111010");
        map3.put("D", "110001100");
        map3.put("A", "110110000");
        map3.put("!D", "110001101");
        map3.put("!A", "110001111");
        map3.put("-D", "110001111");
        map3.put("-A", "110110011");
        map3.put("D+1", "110011111");
        map3.put("A+1", "110110111");
        map3.put("D-1", "110001110");
        map3.put("A-1", "110110010");
        map3.put("D+A", "110000010");
        map3.put("D-A", "110010011");
        map3.put("A-D", "110000111");
        map3.put("D&A", "110000000");
        map3.put("D|A", "110010101");
        map3.put("M", "111110000");
        map3.put("!M", "111110001");
        map3.put("-M", "111110011");
        map3.put("M+1", "111110111");
        map3.put("M-1", "111110010");
        map3.put("D+M", "111000010");
        map3.put("D-M", "111010011");
        map3.put("M-D", "111000111");
        map3.put("D&M", "111000000");
        map3.put("D|M", "111010101");
        map3.put("D<<", "010110000");
        map3.put("A<<", "010100000");
        map3.put("M<<", "011100000");
        map3.put("D>>", "010010000");
        map3.put("A>>", "010000000");
        map3.put("M>>", "011000000");
        COMP_MAP = Collections.unmodifiableMap(map3);
        Map<String, String> map4 = new HashMap<>();
        map4.put("SP", "0");
        map4.put("LCL", "1");
        map4.put("ARG", "2");
        map4.put("THIS", "3");
        map4.put("THAT", "4");
        map4.put("R0", "0");
        map4.put("R1", "1");
        map4.put("R2", "2");
        map4.put("R3", "3");
        map4.put("R4", "4");
        map4.put("R5", "5");
        map4.put("R6", "6");
        map4.put("R7", "7");
        map4.put("R8", "8");
        map4.put("R9", "9");
        map4.put("R10", "10");
        map4.put("R11", "11");
        map4.put("R12", "12");
        map4.put("R13", "13");
        map4.put("R14", "14");
        map4.put("R15", "15");
        map4.put("SCREEN", "16384");
        map4.put("KBD", "24576");
        map4.put("SP", "0");
        DEFAULT_SYMBOL_MAP = Collections.unmodifiableMap(map4);
    }
}
