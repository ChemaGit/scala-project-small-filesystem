package com.chema.filesystem

import java.util.Scanner

import com.chema.commands.Command
import com.chema.files.Directory

object Filesystem {

  def main(args: Array[String]): Unit = {
    val root = Directory.ROOT
    var state = State(root, root)

    val scanner = new Scanner(System.in)

    while(true) {
      state.show
      val input = scanner.nextLine()
      state = Command.from(input).apply(state)
    }
  }
}
