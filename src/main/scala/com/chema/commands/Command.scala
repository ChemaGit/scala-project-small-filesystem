package com.chema.commands

import com.chema.filesystem.State

trait Command {

  def apply(state: State): State
}

object Command {
  val MKDIR = "mkdir"

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
    } else new UnknownCommand

  }
}