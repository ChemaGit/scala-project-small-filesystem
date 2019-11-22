package com.chema.filesystem

import java.util.Scanner

object Filesystem {

  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(System.in)

    while(true) {
      print("$ ")
      println(scanner.nextLine())
    }
  }
}
