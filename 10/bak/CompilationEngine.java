import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CompilationEngine {

    private ArrayList<Tuple> tokens;
    private Tuple currToken;
    private static final String XML_EXTENSION = "xml";
    private static final String DOT = ".";
    private Writer outputWriter;
    private boolean isValid;
    private int indentCount;

    public CompilationEngine(JackTokenizer tokenizer) {

         /*   """
    Constructor for compilation engine class
        :param tokenizer: The tokenizer of the jack file
        :return: A compilationEngine class
        """*/

        isValid =true;
        tokens = tokenizer.getTokens();
        String curr_path = tokenizer.getFileName();
        String file_name = 	curr_path.substring(0, curr_path.lastIndexOf
                (DOT)) + DOT + XML_EXTENSION;
        File outputFile = new File(file_name);
        try {
            FileOutputStream outStream = new FileOutputStream(outputFile);
            outputWriter = new BufferedWriter(new OutputStreamWriter(outStream, Charset.forName("UTF-8")));
        }
        catch (Exception e)
        {
            isValid = false;
        }
        indentCount = 0;
        currToken = new Tuple<>("","");
        advance();
   // Read the
    //first token
}
  /*  def __del__(self):
            """
    This is the destructor, closes the file
        :return:
                """
                self._output_file.close()*/


    public void compileClass() throws Exception {
       /*     """
    Compiles a class. This function recursively compiles a class file into
            an XML file
        :return: N/A
        """*/
        open_tag("class");
        skip("keyword", "class"); //#Write the appropriate tags and skip
        //in the tokenizer
        write_token(); //  #Write identifier ( class name)
        skip("symbol", "{");

        while(is_class_var_dec())
            compile_class_var_dec();

        while(is_subroutine())
            compile_subroutine();

        skip("symbol", "}");
        close_tag("class");
    }

    public void compile_class_var_dec() throws Exception {
       /* "" "
        This function compiles a class ' variable declaration block
            :return:N / A
        "" "*/
        open_tag("classVarDec");
        write_token(); // #( static |field)
        write_token(); // #type(int | char | boolean)
        write_token(); // #identifier

        while(is_token("symbol", ",")) {
            write_token();  //#","
            write_token(); // #variable name
        }

        skip("symbol", ";");
        close_tag("classVarDec");
    }

    public void compile_subroutine() throws Exception {
       /* "" "
        This function compiles a subroutine(including body and parameter list)
        :return:
        "" "*/
        open_tag("subroutineDec");
        write_token(); // #('constructor' | 'function' | 'method')
        write_token();  //#( void |type )
        write_token(); // #subroutine name
        compile_param_list();
        compile_subroutine_body();
        close_tag("subroutineDec");
    }

    public void compile_param_list() throws Exception{
      /*  "" "
        This function compiles a subroutine 's parameter list
            :return:
        "" "*/
        skip("symbol", "(");
        open_tag("parameterList");

           // #Check if there are parameters:
        if(!curr_value().contains(")")) {
            write_token(); // #type
            write_token(); // #varName
        }

        while(is_token("symbol", ",")) {
            write_token(); // #",'
            write_token(); // #type
            write_token(); // #varName
        }

        close_tag("parameterList");
        skip("symbol", ")");
    }

    public void compile_subroutine_body() throws Exception {
       /* "" "
        This function compiles a subroutine 's body
            :return:
        "" "*/
        open_tag("subroutineBody");
        skip("symbol", "{");

        while(curr_value().contains("var"))
            compile_var_dec();

        compile_statements();

        skip("symbol", "}");
        close_tag("subroutineBody");
    }

    public void compile_var_dec() throws Exception {
       /* "" "
        This function compiles a variable 's declaration
            :return:
        "" "*/
        open_tag("varDec");
        write_token(); // #var
        write_token();//  #type
        write_token();//  #varName

        while(is_token("symbol", ",")) {
            write_token();//  #",'
            write_token();//  #varName
        }

        skip("symbol", ";");
        close_tag("varDec");
    }

    public void compile_statements() throws Exception {
         /*   """
    This function compiles statements - this function sends to other functions that compile specific statements such as
        if, let, while, do return
            :return:
            """*/
        open_tag("statements");

        while(is_statement()) {
            String value = curr_value();
            switch (value) {
                case "let":
                    compile_let();
                    break;
                case "if":
                    compile_if();
                    break;
                case "while":
                    compile_while();
                    break;
                case "do":
                    compile_do();
                    break;
                case "return":
                    compile_return();
                    break;
            }
        }

        close_tag("statements");
    }

    public void compile_let() throws Exception {
       /* "" "
        This function compiles a let statement
        :return:
        "" "*/
        open_tag("letStatement");
        skip("keyword", "let");
        write_token();  //# varName

        if(curr_value().contains("[")){
            write_token();  //# "["
            compile_expression();
            write_token(); // # "]"
        }
        skip("symbol", "=");
        compile_expression();
        skip("symbol", ";");

        close_tag("letStatement");
    }

    public void compile_if() throws Exception {
          /*  """
    This function compiles an if(and else) statement
        :return:
                """*/
        open_tag("ifStatement");
        skip("keyword", "if");

             //   #expression
        skip("symbol", "(");
        compile_expression();
        skip("symbol", ")");

               // #if body
        skip("symbol", "{");
        compile_statements();
        skip("symbol", "}");

        if(curr_value().contains("else")) {
            write_token();//  # else
            skip("symbol", "{");
            compile_statements();
            skip("symbol", "}");
        }
        close_tag("ifStatement");
    }
    public void compile_while() throws Exception {
       /* "" "
        This function compiles a while statement
        :
        return:
        "" "*/
        open_tag("whileStatement");
        skip("keyword", "while");

               // #expression
        skip("symbol", "(");
        compile_expression();
        skip("symbol", ")");

               // #While Body
        skip("symbol", "{");
        compile_statements();
        skip("symbol", "}");

        close_tag("whileStatement");
    }

    public void compile_do() throws Exception {
       /* "" "
        This function compiles a do statement
                :return:
        "" "*/
        open_tag("doStatement");
        skip("keyword", "do");
        write_token();//  #(subroutineName | className | varName);

        if(curr_value().contains(".")) {
            write_token();//  #"."
            write_token();//  #subroutineName
        }
        
        skip("symbol", "(");
        compile_expression_list();
        skip("symbol", ")");
        skip("symbol", ";");

        close_tag("doStatement");
    }

    public void compile_return() throws Exception {
       /* "" "
        This function compiles a return statement
        :return:
        "" "*/
        open_tag("returnStatement");
        skip("keyword", "return");

        compile_expression();
        skip("symbol", ";");

        close_tag("returnStatement");

    }

    public void compile_expression() throws Exception {
      /*  "" "
        This function compiles an expression
        :
        return:
        "" "*/
        if(is_value_in_list(")", ";"))
            return;
        open_tag("expression");
        compile_term();

        while(is_value_in_list("+", "-", "*", "/", "&amp;", "|", "&lt;",
                "&gt;", "="))
        {
            write_token(); // #operation
            compile_term();
        }
        close_tag("expression");
    }

    public void compile_term() throws Exception {
       /* "" "
        This function compiles a term
        :
        return:
        "" "*/
        open_tag("term");

        if (is_tag_of_type("identifier")) {
            write_token(); // #identifier

            if (is_token("symbol", "[")) {
                write_token();//  #"["
                compile_expression();
                skip("symbol", "]");
            }
            else if(is_token ("symbol", "(")){
                write_token();//  #"("
                compile_expression_list();
                skip("symbol", ")");
            }
            else if(is_token ("symbol", ".")){
                write_token();//  #"."
                write_token();//  #subroutine name
                skip("symbol", "(");
                compile_expression_list();
                skip("symbol", ")");
            }
        }
        else if(is_tag_of_type("integerConstant")) {
            write_token();
        }
        else if(is_tag_of_type("stringConstant")) {
            write_token();
        }
        else if(is_value_in_list("true", "false", "null", "this")){
            write_token();
        }
        else if(is_token("symbol", "(")){
            write_token();
            compile_expression();
            skip("symbol", ")");
        }
        else if(is_value_in_list("-", "~")){
            write_token();
            compile_term();
        }
        close_tag("term");
    }
    public void compile_expression_list() throws Exception {
        /*"" "
        This function compiles a list of expressions(expressionList)
        :return:
        "" "*/
        open_tag("expressionList");
        compile_expression();
        while(is_token("symbol", ","))
        {
            write_token();
            compile_expression();
        }
        close_tag("expressionList");
    }
    
    public void advance() {
         /*   """
    This function advances the current token
        :return:
                """*/

        currToken = tokens.listIterator().next();
    }

    public void skip(String tag, String value) throws Exception {
       /*     """
    This function checks that the current token is equal to the values given
    If it is the same - the token is written and then advanced
    If it isn't the same - it is currently unhandled and was used in debugging only (should throw an error)

            :return:
        "" "*/
        if((tag.equals(curr_token())) && (value.equals(curr_value())))
            advance();
        else
            throw new Exception("token error");
        write_tag(tag, value);
    }

    public void write_token() throws Exception {
       /*     """
    This function writes a token (the current token's tag and value)
            it is shown in the xml as <tag> value </tag>
            :return:
            """*/
        String tag = curr_token();
        String value = curr_value();
        advance();
           /* #if value == "&":
            #value = "&amp;"
            #elif value =="<":
            #value = "&lt;"
            #elif value ==">":
            #value = "&gt;"*/
        write_tag(tag, value);
    }

    public void open_tag(String tag) throws IOException {
        /*"" "
        This function opens an xml tag -in the xml it is <tag >
                It increases the indent counter by 1
            :return:
        "" "*/
        outputWriter.write(indentation() + "<" + tag + ">"+ "\n");
        indentCount++;
    }

    public void close_tag(String tag) throws IOException {
      /*      """
    This function closes an xml tag - in the xml it is </tag>
    This function first reduces the indent counter by 1 and then writes the tag
        :return:
                """*/
        indentCount--;
        outputWriter.write(indentation() + "</"+ tag +">" +"\n");
    }

    public void write_tag(String tag, String value) throws IOException {
        /*    """
    This function writes an xml tag - {indent} <tag> value </tag>
            :return:
            """*/

        outputWriter.write(indentation() + "<"+ tag +">" + value + "</" +
                tag + ">" +"\n");
    }

    public boolean is_class_var_dec() {
        /*"" "
        returns true if the current token is a class
        ' variable declaration - static or field
            :return:
        "" "*/
        boolean isStatic = curr_value().contains("static");
        boolean isField = curr_value().contains("field");
        return (isStatic || isField);
    }

    public boolean is_subroutine() {
       /* "" "
        returns true if the current token is a subroutine
        -constructor, function or method
        :return:
        "" "*/
        boolean isConstructor = curr_value().contains("constructor");
        boolean isFunction = curr_value().contains("function");
        boolean isMethod = curr_value().contains("method");
        return (isConstructor || isFunction || isMethod);
    }

    public boolean is_token(String tag, String value) {
       /* "" "
        returns true if the current token is equal to the tag and value
        :return:
        "" "*/
        return ((tag.equals(curr_token())) && (value.equals(curr_value())));
    }
        public boolean is_tag_of_type (String... types) {
            /*"" "
            Returns true if the token 's type is equal to one of the types sent
            :return:
            "" "*/
            for(String typeT : types) {
                if (curr_token().equals(typeT))
                    return true;
            }
            return false;
        }

    public boolean is_value(String value) {
          /*  """
    Returns true if the value in the current token is equal to the value sent
        :return:
                """*/
        return value.equals(curr_value());
    }

    public boolean is_value_in_list(String... values) {
       /* "" "
        Returns true if the value in the current token is equal to one of the
        values sent
        :return:
        "" "*/
        for(String value : values) {
            if(curr_value().equals(value))
            return true;
        }
        return false;
    }
    public boolean is_statement() {
       /* "" "
        returns true if the current token is a statement -i.e let,if,while,do,
        return
            :return:
        "" "*/
        boolean isLet = curr_value().contains("let");
        boolean isIf = curr_value().contains("if");
        boolean isWhile = curr_value().contains("while");
        boolean isDo = curr_value().contains("do");
        boolean isReturn = curr_value().contains("return");
        return(isLet || isIf || isWhile || isDo || isReturn);
    }
    public String indentation() {
     /*   "" "
        returns the current indentation (a multiple of the "\t" char)
        :return:
        "" "*/
        return new String(new char[indentCount]).replace("\0", "\t");
    }

    public String curr_value() {
        /*"" "
        returns the current token 's value
            :return:
        "" "*/
        return currToken.getSecond().toString();
    }

    public String curr_token() {
        /*"" "
        returns the current token 's tag
            :return:
        "" "*/
        return currToken.getFirst().toString();
    }

    public boolean isValid()
    {
        return this.isValid;
    }
}
