import java.util.*;

/**
 * This class contains all the functions used to receive hack assembly
 * text and translates it into binary hack machine code
 */
class Parser {

    /**
     *  The current programs symbol hashmap
     */
    private static Map<String, String> symbols;
    /**
     * Next memory address that is free for symbol allocation
     */
    private static int nextAddress;

    /**
     * Resets the symbols hashmap and nextaddress fields to default values
     * And administers the parsing of program by performing the 3 passes
     * described in the README
     * @param text The assembly code to be translated
     * @return The translated code in hack machine language
     */
    static String parse(String text) {
        String result = "";
        nextAddress = 16;
        symbols = new HashMap<>(Consts.DEFAULT_SYMBOL_MAP);
        // Split text into individual lines. \r\n for windows and \n for unix
        String[] split_lines = text.split("\r?\n");
        List<String> lines = new LinkedList<>(Arrays.asList(split_lines));
        // Send the lines for a first pass (removal of comments and spaces)
        lines = cleanLines(lines);
        // Send the lines for a second pass (saving label symbols)
        parseLabels(lines);

        // Third pass - translate all remaining lines into hack machine code
        // And add it to the result string
        for(String line : lines)
        {
            String line_binary = parseLine(line);
            if (line_binary != null) {
            result += line_binary + "\n";
        }
        }
        return result;
        }

    /**
     *   Removes comments and whitespace
     * @param lines The original asm program lines
     * @return A new String linked list of the lines without comments, spaces
     * Or empty lines
     */
    private static List<String> cleanLines(List<String> lines)
    {
    List<String> clean_lines = new LinkedList<>();
    for(String line : lines)
        {
            // Remove comments
            String[] no_comment_line = line.split(Consts.COMMENT_PREFIX);
            // split_line[0] contains any code that might have appeared in
            // The line before the COMMENT_PREFIX. From that text we remove
            // spaces.
            String no_space_line = no_comment_line[0].replace(" ", "");
            // If the remaining text is not empty, we have an actual line of
            // code which we will store in the result
            if(!no_space_line.equals("")) {
                clean_lines.add(no_space_line);
            }
        }
            return clean_lines;
    }

    /**
     * Parses the label symbols and puts them in the current programs
     * Symbols map.
     * @param lines The assembly code after the first pass of cleaning
     */
    private static void parseLabels(List<String> lines) {
        int line_num = 0;
        for(String line : lines) {
            if(line.startsWith(Consts.LABEL_OPEN_PARAN))
            {
                // Take the symbol name without the wrapping parentheses
                String symbol = line.substring(1, line.length() - 1);
                symbols.put(symbol, Integer.toString(line_num));
            }
            else
            {
                line_num++;
            }
        }
    }

    /**
     *  Third pass - translate all remaining lines into hack machine code
     * @param line The current ASM line to translate
     * @return The translated hack machine code of the current line
     */
    private static String parseLine(String line)
    {
        String hack_result_line;
        // If the line is a label definition, we have already handled that line
        // Do not translate
        if(line.startsWith(Consts.LABEL_OPEN_PARAN))
        {
        return null;
        }

        else if(line.startsWith(Consts.A_COMMAND_ASM_PREFIX))
        {
            hack_result_line = aCommand(line);
        }
        else
        {
            hack_result_line = cCommand(line);
        }
        return hack_result_line;
    }

    /**
     * Translates an A command into hack machine code
     * @param line An A command line
     * @return The equivalent hack machine code
     */
    private static String aCommand(String line)
    {
        // We don't need the A command prefix. Let's extract the command
        // Content
        String var_name = line.substring
                (Consts.A_COMMAND_HACK_PREFIX.length());
        // If the variable in the A command is numeric, we need to deal with
        // It as a literal
        if(isNumeric(var_name))
        {
            return Consts.A_COMMAND_HACK_PREFIX + intToBin15(var_name);
        }

        // If we found a new symbol, we must add it to the symbols map and
        // Mark the address as allocated by incrementing the nextAddress
        // Counter
        else if(!symbols.containsKey(var_name))
        {
            symbols.put(var_name, Integer.toString(nextAddress));
            nextAddress++;
        }
        // Return the final A command
        return Consts.A_COMMAND_HACK_PREFIX +
                intToBin15(symbols.get(var_name));
    }

    /**
     * Translates a C command into hack machine code
     * @param line A C command line
     * @return The equivalent hack machine code
     */
    private static String cCommand(String line) {
        // In case the dest or jump commands do not appear, we set the
        // Bits to default
        String dest = Consts.DEFAULT_DEST;
        String jump = Consts.DEFAULT_JUMP;
        String comp = "";

        // Extract jump bits
        String[] split_line = line.split(Consts.JMP_DELIM);
        String split_line2[];
        if(split_line.length == 2) {
            jump = parseJump(split_line[1]);
        }
        //Extract dest bits
            split_line2 = split_line[0].split(Consts.EQUAL_DELIM);
        if(split_line2.length == 2) {
            dest = parseDest(split_line2[0]);

        }
        // Extract comp bits
        if(split_line2.length != 0) {
            comp = parseComp(split_line2[split_line2.length - 1]);
        }
        return Consts.C_COMMAND_HACK_PREFIX + comp + dest + jump;
    }

    /**
     * Translates a jump command into jump bits
     */
    private static String parseJump(String jump) {
        //Returns the binary code of the given jump string
        return Consts.JUMP_MAP.get(jump);
    }

    /**
     * Translates a comp command into comp bits
     */
    private static String parseComp(String comp) {
        //Returns the binary code of the given comp string
        return Consts.COMP_MAP.get(comp);
    }

    /**
     * Translates a dest command into dest bits
     */
    private static String parseDest(String dest) {
        //Returns the binary code of the given dest string
        return Consts.DEST_MAP.get(dest);
    }

    /**
     * Converts a given numeric string into a 15-bit binary number as a String
     * @param x A numeric string
     * @return 15-bit binary number as a String
     */
    private static String intToBin15(String x)
    {
        //Converts a given string which contains an integer to a binary string, padded to 15 bits.
        int num = Integer.parseInt(x);
        return String.format("%15s", Integer.toBinaryString(num)).replace(' ', '0');
    }

    /**
     * Checks if a given string is numeric
     * @param str A string
     * @return True iff the string is numeric
     */
    private static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
