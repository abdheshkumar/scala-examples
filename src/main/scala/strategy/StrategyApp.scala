package strategy

import java.io.{PrintWriter, File}
import java.util.Date

/**
 * Created by abdhesh on 7/5/15.
 */

object Printer {
  def write(file: File, op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }

  def writeExt(file: File)(op: PrintWriter => Unit) = {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}

object StrategyApp {

  import Printer._

  write(new File("data.txt"),
    writer => writer.println(new Date())
  )
  val file = new File("data1.txt")
  writeExt(file)(writer => writer.println(new Date))
  writeExt(file) { writer => writer.println(new Date) }
  val fileWriter = writeExt(file) _
  fileWriter { writer => writer.println(new Date) }
  fileWriter {
    _.println(new Date)
  }
}
