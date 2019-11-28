package com.chema.files

abstract class DirEntry(val parentPath: String, val name: String){
  val path: String = parentPath + Directory.SEPARATOR + name

  def asDirectory: Directory
  def asFile: File
  def getType: String
}
