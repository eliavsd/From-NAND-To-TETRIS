import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Assembler {

    /**
     * Receives a path to an asm file and returns the same path with a .hack
     * extension.
     * @param asm_filename The relative or absolute path to the asm file
     * @return A relative or absolute path, same as above except with a .hack
     * extension instead of .asm
     */
    private static String convert_filename(String asm_filename) {
        int ext_dot = asm_filename.lastIndexOf('.');
        String filename_no_ext = asm_filename.substring(0, ext_dot);
        return filename_no_ext + Consts.HACK_EXTENSION;
    }

    /**
     * The main program function. Attempts to open the asm directory/file
     *
     * @param args Should contain 1 argument: the path to an asm file
     * properly.
     */
    public static void main(String[] args) {
        try {
            if(args.length != 1)
            {
                System.out.println(Consts.MSG_USAGE);
                System.exit(Consts.SUCCESS);
            }
            String path = args[0];
            File file = new File(path);
            if (file.isDirectory()) {
                for (File filename : file.listFiles()) {
                    if (filename.toString().endsWith(Consts.ASM_EXTENSION)) {
                        handle_file(filename.toString());
                    }
                }
            } else {
                handle_file(path);
            }
        }
        catch(Exception e){
                System.err.println(e.getMessage());
                System.exit(Consts.FAILURE);
            }
    }

    /**
     * The file handling function. Opens the asm file, sends the text to
     * The parser, then stores the result in an output hack file.
     *
     * @param asm_filename The relative or absolute path to the asm file
     * @throws Exception I/O errors or bad asm code
     */
    private static void handle_file(String asm_filename) throws Exception {
        byte[] data;
        String text;
        String hack_program;
        String hack_filename;
        // Open the input file and convert the raw byte input to String text
        try {
            File file = new File(asm_filename);
            FileInputStream fis = new FileInputStream(file);
            data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            text = new String(data, "UTF-8");
        }
        catch(Exception e1) {
            System.err.println(Consts.BAD_IN_FILE);
            throw e1;
        }
        // Send the text to the compiler/parser
        try {
            hack_program = Parser.parse(text);
        }
        catch(Exception e2) {
            System.err.println(Consts.INVALID_CODE);
            throw e2;
        }

        // Save output to hack file
        hack_filename = convert_filename(asm_filename);
        try{
            File file = new File(hack_filename);
            FileOutputStream fos = new FileOutputStream(file);
            data = hack_program.getBytes("UTF-8");
            fos.write(data);
            fos.close();
        }
        catch(Exception e3)
        {
            System.err.println(Consts.BAD_OUT_FILE);
            throw e3;
        }
    }
}
