__author__ = 'Or Keren'


class Command():
    """
    An enum class that represents tokens for each type of vm command
    """
    C_NULL = 0
    C_ARITHMETIC = 1
    C_PUSH = 2
    C_POP = 3
    C_LABEL = 4
    C_GOTO = 5
    C_IF = 6
    C_FUNCTION = 7
    C_RETURN = 8
    C_CALL = 9


class Parser:
    """
    The Parser class, the class holds all the lines of code that are in the VM file. It allows the translator to
    traverse the file line-by-line
    """
    def __init__(self, file_name):
        """
        The constructor of the class, initializes the internal parameters
        :param file_name: The file name the parser is attached to
        :return: N/A
        """
        file = open(file_name, 'r')
        code = file.readlines()
        file.close()

        self._code = []
        self.remove_excess(code)
        self._num_of_commands = len(self._code)
        self._counter = 0
        self._command = None
        self._file_name = file_name

    def remove_excess(self, code):
        """
        Removes excess comments from all the code
        :param code: All the lines of code in the file
        :return: N/A
        """
        for line in code:
            line.replace('\r', '')
            line = ' '.join(line.rsplit())

            if '//' in line:
                line = line[:line.find('//')]
            if line != '':
                self._code.append(line)

    def can_continue(self):
        """
        Returns if there are more lines that the parser can traverse over
        :return: true if the number of total lines in the VM file is bigger than the current line counter
        """
        # Returns whether we still have commands that need to be read
        return self._num_of_commands > self._counter

    def advance(self):
        """
        Advances the parser by one line
        :return: N/A
        """
        # Advances the "pointer" that points to the current command we are reading in the file
        self._command = self._code[self._counter]
        self._counter += 1

    def get_command_type(self):
        """
        Tokenizes the current command line into the relevant enum
        :return: The enum representing the command of the current VM line
        """
        return {
        "add": Command.C_ARITHMETIC,
        "sub": Command.C_ARITHMETIC,
        "neg": Command.C_ARITHMETIC,
        "eq": Command.C_ARITHMETIC,
        "gt": Command.C_ARITHMETIC,
        "lt": Command.C_ARITHMETIC,
        "and": Command.C_ARITHMETIC,
        "or": Command.C_ARITHMETIC,
        "not": Command.C_ARITHMETIC,
        "pop": Command.C_POP,
        "push": Command.C_PUSH,
        "label": Command.C_LABEL,
        "goto": Command.C_GOTO,
        "if-goto": Command.C_IF,
        "function": Command.C_FUNCTION,
        "call": Command.C_CALL,
        "return": Command.C_RETURN
    }.get(self._command.rsplit()[0], Command.C_NULL)

    def get_file_name(self):
        """
        :return: Returns the file name the parser is attached to
        """
        return self._file_name

    def get_curr_command(self):
        """
        :return: Returns the current command (i.e the line of code in the vm file)
        """
        return self._command

    def get_first_arg(self):
        """
        :return: Returns the first argument of the VM command
        """
        split_line = self._command.rsplit()
        command_type = self.get_command_type()

        if command_type in [Command.C_ARITHMETIC,Command.C_RETURN]:
            return split_line[0]
        else:
            return split_line[1]

    def get_second_arg(self):
        """
        :return: Returns the second argument of the VM command
        """
        split_line = self._command.rsplit()

        # If there is a second command it is always an integer value
        return int(split_line[2] if len(split_line)>=3 else -1)