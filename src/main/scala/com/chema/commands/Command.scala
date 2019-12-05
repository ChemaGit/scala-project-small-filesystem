package com.chema.commands

import com.chema.filesystem.State

trait Command extends (State => State) {
  /**
   * with "extends (State => State)", we don't need to put the "def apply(state: State): State"
   * because functions already have the apply method on them
   */
  //def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"
  val RM = "rm"
  val ECHO = "echo"
  val EXIT = "exit"
  val CAT = "cat"

  def emptyCommands: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(s"$name [: incomplete command!]")
  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if(input.isEmpty || tokens.isEmpty) emptyCommands
    else tokens(0) match {
      case MKDIR => if(tokens.length < 2) incompleteCommand(MKDIR) else new Mkdir(tokens(1))
      case LS => new Ls
      case PWD => new Pwd
      case TOUCH => if (tokens.length < 2) incompleteCommand(TOUCH) else new Touch(tokens(1))
      case CD => if(tokens.length < 2) incompleteCommand(CD) else new Cd(tokens(1))
      case RM => if(tokens.length < 2) incompleteCommand(RM) else new Rm(tokens(1))
      case ECHO => if (tokens.length < 2) incompleteCommand(ECHO) else new Echo(tokens.tail)
      case CAT => if(tokens.length < 2) incompleteCommand(CAT) else new Cat(tokens(1))
      case EXIT => new Exit
      case _ => new UnknownCommand
    }
  }
}