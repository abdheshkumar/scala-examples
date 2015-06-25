package exceptions

import java.io.FileNotFoundException
import java.net.{MalformedURLException, URL}

import scala.util.{Failure, Success, Try}

/**
 * Created by abdhesh on 6/19/15.
 */
object TryApp {

  import scala.io.Source

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  import java.io.InputStream

  def inputStreamForURL(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u =>
    Try(u.openConnection()).map(conn => Try(conn.getInputStream))
  }

  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)
    } yield source.getLines()


  getURLContent("http://danielwestheide.com/foobar") match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
  }


  val content = getURLContent("garbage") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    case _ => Iterator("An unexpected error has occurred. We are so sorry!")
  }
}
