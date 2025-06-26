# Shell-on-the-JVM

A POSIX-compliant shell implementation built from scratch in Java .

## 🎯 Overview

This project implements a fully functional command-line shell that can interpret shell commands, execute external programs, and handle builtin commands. The shell supports advanced features like command history, autocompletion, pipelines, redirection, and proper quote handling.

## ✨ Features

### Core Functionality
- **Interactive REPL** - Read-Eval-Print Loop with command prompt
- **Command Execution** - Run external programs and builtin commands
- **Error Handling** - Proper handling of invalid commands

### Builtin Commands
- `exit` - Exit the shell
- `echo` - Display text with support for quoted strings
- `pwd` - Print current working directory
- `cd` - Change directory (absolute paths, relative paths, home directory)
- `type` - Identify command types (builtins vs external executables)
- `history` - Command history management

### Advanced Features
- **Quoting Support**
  - Single quotes (`'text'`)
  - Double quotes (`"text"`)
  - Backslash escaping (inside and outside quotes)
  
- **I/O Redirection**
  - Redirect stdout (`>`, `>>`)
  - Redirect stderr (`2>`, `2>>`)
  
- **Autocompletion**
  - Builtin command completion
  - Executable completion
  - Argument completion
  - Partial and multiple completion support
  
- **Pipelines**
  - Multi-command pipelines (`cmd1 | cmd2 | cmd3`)
  - Pipeline support for builtin commands
  
- **Command History**
  - History navigation (up/down arrows)
  - History persistence to file
  - Configurable history limits
  - Execute commands from history

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Unix-like operating system (Linux, macOS)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/nikhilm25/Shell-on-the-JVM.git
cd Shell-on-the-JVM

A POSIX-compliant shell implementation built from scratch in Java as part of the [CodeCrafters "Build your own Shell" challenge](https://codecrafters.io/challenges/shell).

## 🎯 Overview

This project implements a fully functional command-line shell that can interpret shell commands, execute external programs, and handle builtin commands. The shell supports advanced features like command history, autocompletion, pipelines, redirection, and proper quote handling.

## ✨ Features

### Core Functionality
- **Interactive REPL** - Read-Eval-Print Loop with command prompt
- **Command Execution** - Run external programs and builtin commands
- **Error Handling** - Proper handling of invalid commands

### Builtin Commands
- `exit` - Exit the shell
- `echo` - Display text with support for quoted strings
- `pwd` - Print current working directory
- `cd` - Change directory (absolute paths, relative paths, home directory)
- `type` - Identify command types (builtins vs external executables)
- `history` - Command history management

### Advanced Features
- **Quoting Support**
  - Single quotes (`'text'`)
  - Double quotes (`"text"`)
  - Backslash escaping (inside and outside quotes)
  
- **I/O Redirection**
  - Redirect stdout (`>`, `>>`)
  - Redirect stderr (`2>`, `2>>`)
  
- **Autocompletion**
  - Builtin command completion
  - Executable completion
  - Argument completion
  - Partial and multiple completion support
  
- **Pipelines**
  - Multi-command pipelines (`cmd1 | cmd2 | cmd3`)
  - Pipeline support for builtin commands
  
- **Command History**
  - History navigation (up/down arrows)
  - History persistence to file
  - Configurable history limits
  - Execute commands from history

## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Unix-like operating system (Linux, macOS)

### Installation

1. Clone the repository:
```bash
git clone https://github.com/nikhilm25/Shell-in-Java.git
cd Shell-in-Java
