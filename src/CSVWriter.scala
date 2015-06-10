import scala.io.Source

import scala.util.{Failure, Success, Try}

/**
 * Created by abdhesh on 5/25/15.
 */
object CSVWriter extends App {
  //Dummy data

  Try(Source.fromFile("csv/data.txt")) match {
    case Success(fileReader) => reProcessCSV(fileReader.getLines.toList)
    case Failure(ex) => println(s"Error:${ex}")
  }

  def reProcessCSV(lines: List[String]): Unit = {
    def innerReprocess(_lines: List[String], result: List[String], repeater: Int, totalRepeat: Int = 2): Unit = {
      _lines match {
        case head :: tails if (repeater <= totalRepeat) =>
          val row: List[String] = head.split(",").toList
          row match {
            case _tail :+ lastColumn =>
              val processedRow = head.split(lastColumn).filterNot(_ == ",").toList
              println(processedRow.mkString("\t"))
              innerReprocess(tails, result :+ processedRow.mkString, repeater)
            case _ =>
          }
        case Nil if repeater == 1 =>
          println("Reprocess CSV:" + "-" * 30)
          innerReprocess(result, Nil, repeater + 1)
        case _ => println("End::")
      }
    }
    println("Original:" + "-" * 30)
    lines foreach (println)
    println("Process CSV:" + "-" * 30)
    innerReprocess(lines, Nil, 1)
  }

}
