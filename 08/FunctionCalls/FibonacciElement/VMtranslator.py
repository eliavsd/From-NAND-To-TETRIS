__author__ = 'Or Keren'

import sys
import os
from VMparser import *
from VMCodeWriter import *

parsers = []


def main():
    """
    The main function, calls writeCode with the output path for the given file. It handles both a file argument
    and a directory argument

    **IMPORTANT**: The function does not handle incorrect or invalid files\folders
    (such as a directory that does not exist)
    :return: N/A
    """
    fpath = os.path.abspath(sys.argv[1].replace('\n', '').replace('\r', ''))
    if os.path.isfile(fpath):
        parsers.append(Parser(fpath))
        output = os.path.splitext(fpath)[0] + ".asm"

    else:
        fpath += ('/' if not str(fpath).endswith('/') else '')
        output = fpath + "/" + os.path.basename(os.path.normpath(fpath)) + '.asm'
        for file in os.listdir(fpath):
            if file.endswith(".vm") and not file.endswith("Sys.vm"):
                parsers.append(Parser(fpath + '/' + file))
        parsers.append(Parser(fpath + '/' + "Sys.vm"))

    writeCode(output)


def writeCode(output_path):
    """
    :param output_path: The output path that the writer should write the asm files into
    :return: N/A
    """
    writer = Writer(output_path)
    for parser in parsers:
        curr_path = parser.get_file_name()
        file_name = os.path.split(curr_path)[1][:-3]
        # writer = Writer(output_path + "" + file_name + ".asm")
        writer.set_file_name(file_name)

        # writer.write_bootstrap()  # TODO validate proper location - called it within ctor of writer

        while parser.can_continue():
            parser.advance()
            command_type = parser.get_command_type()

            arg1 = parser.get_first_arg()
            arg2 = parser.get_second_arg()

            # Switch - Case on command_type
            if command_type == Command.C_ARITHMETIC:
                # writer.write_arithmetic(parser.get_curr_command()) # OR - Changed it because of whitespace problems
                writer.write_arithmetic(arg1)

            elif command_type == Command.C_PUSH:
                writer.write_push(arg1, arg2)

            elif command_type == Command.C_POP:
                writer.write_pop(arg1, arg2)

            elif command_type == Command.C_LABEL:
                writer.write_label(arg1)

            elif command_type == Command.C_IF:
                writer.write_if(arg1)

            elif command_type == Command.C_GOTO:
                writer.write_goto(arg1)

            elif command_type == Command.C_FUNCTION:
                writer.write_function(arg1, arg2)

            elif command_type == Command.C_RETURN:
                writer.write_return()

            elif command_type == Command.C_CALL:
                writer.write_call(arg1, arg2)


if __name__ == "__main__":
    main()


