package com.chema.filesystem

import java.util.Scanner

import com.chema.commands.Command
import com.chema.files.Directory

object Filesystem {

  def main(args: Array[String]): Unit = {
    val root = Directory.ROOT
//    var state = State(root, root)
//
//    val scanner = new Scanner(System.in)
//
//    while(true) {
//      state.show
//      val input = scanner.nextLine()
//      state = Command.from(input).apply(state)
//    }
    /**
     * We can implement the code above in a more functional style
     * We can eliminate "var" and loops like "while" in this way
     */
    // Standard input line
    System.out.write("Welcome! Press Intro!".getBytes)
    io.Source.stdin.getLines().foldLeft(State(root,root))( (currentState, newLine) => {
      currentState.show
      Command.from(newLine).apply(currentState)
    })

    /**
     * foldLeft example
     * List(1,2,3,4)
     * List(1,2,3,4).foldLeft(0)( (x, y) => x + y)
     * 0 (op) 1 => 1
     * 1 (op) 2 => 3
     * 3 (op) 3 => 6
     * 6 (op) 4 => your last value, 10
     */
  }
}
