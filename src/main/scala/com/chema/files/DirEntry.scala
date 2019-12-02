package com.chema.files

abstract class DirEntry(val parentPath: String, val name: String){
  def path: String = {
    val separatorIfNecessary =
      if(Directory.ROOT_PATH.equals(parentPath)) ""
      else Directory.SEPARATOR

    parentPath + separatorIfNecessary + name
  }

  def asDirectory: Directory
  def asFile: File

  def isDirectory: Boolean
  def isFile: Boolean
  def getType: String
}
