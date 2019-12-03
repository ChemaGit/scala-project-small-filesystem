package com.chema.commands

import com.chema.filesystem.State

trait Command {

  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"
  val LS = "ls"
  val PWD = "pwd"
  val TOUCH = "touch"
  val CD = "cd"
  val RM = "rm"

  def emptyCommands: Command = new Command {
    override def apply(state: State): State = state
  }

  def incompleteCommand(name: String): Command = new Command {
    override def apply(state: State): State = state.setMessage(s"$name [: incomplete command!]")
  }

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if(input.isEmpty || tokens.isEmpty) emptyCommands
    else if(MKDIR.equals(tokens(0))) {
      if(tokens.length < 2) incompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    } else if(LS.equals(tokens(0))) new Ls
      else if(PWD.equals(tokens(0))) new Pwd
      else if(TOUCH.equals(tokens(0))) {
        if (tokens.length < 2) incompleteCommand(TOUCH)
        else new Touch(tokens(1))
    } else if(CD.equals(tokens(0))) {
      if(tokens.length < 2) incompleteCommand(CD)
      else new Cd(tokens(1))
    } else if(RM.equals(tokens(0))) {
      if(tokens.length < 2) incompleteCommand(RM)
      else new Rm(tokens(1))
    } else new UnknownCommand
  }
}