package com.chema.commands

import com.chema.filesystem.State

trait Command {

  def apply(state: State): State
}

object Command {
  def from(input: String): Command = new UnknownCommand
}