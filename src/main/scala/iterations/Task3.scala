package iterations

import java.text.SimpleDateFormat
import java.util.Date

import scala.annotation.tailrec

case class Photo(id: Int,
                 name: String,
                 extension: String,
                 city: String,
                 date: Date,
                 seq: String = "")
    extends Ordered[Photo] {
  override def toString: String = city.trim + seq + "." + extension

  override def compare(that: Photo): Int =
    that.date.getTime compare date.getTime
}
object Task3 {
  val S =
    """photo.jpg, Warsaw, 2013-09-05 14:08:15
      |john.png, London, 2015-6-20 15:13:22
      |myFriends.png, Warsaw, 2013-09-05 14:07:13
      |Eiffel.jpg, Paris, 2015-07-23 08:03:02
      |pisatower.jpg, Paris, 2015-07-22 23:59:59
      |BOB.jpg, London, 2015-08-05 00:02:03
      |notredame.png, Paris, 2015-09-01 12:00:00
      |me.jpg, Warsaw, 2013-09-06 15:40:22
      |a.png, Warsaw, 2016-02-13 13:33:50
      |b.jpg, Warsaw, 2016-01-02 15:12:22
      |c.jpg, Warsaw, 2016-01-02 14:34:30
      |d.jpg, Warsaw, 2016-01-02 15:15:01
      |e.png, Warsaw, 2016-01-02 09:49:09
      |f.png, Warsaw, 2016-01-02 10:55:32
      |g.jpg, Warsaw, 2016-02-29 22:13:11
      |""".stripMargin

   val formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  def main(args: Array[String]): Unit = {
    val s = System.currentTimeMillis()
    val result = solution(S)
    val e = System.currentTimeMillis()
    println(e - s)
    println(result)
  }

  def solution(S: String): String = {
    val photosMap = parseLinesToPhotos(parseLines(S))
    renamePhotos(photosMap).mkString("\n")
  }

  /**
    * Rename photo by
    * @param photosMap: Map[String, List[Photo]]
    * @return
    */
  def renamePhotos(photosMap: Map[String, List[Photo]]): List[Photo] = {
    photosMap
      .foldLeft(List.empty[Photo]) {
        case (acc, (city, photosByCity)) =>
          val size = photosByCity.length
          val largestInTheGroup = (Math.log10(size) + 1).toInt
          val (photos, _) = photosByCity
            .sortBy(_.date.getTime)
            .foldRight((List.empty[Photo], size)) {
              case (photo, (result, index)) =>
                val format =
                  String.format("%str0" + largestInTheGroup + "d", index)
                val updated = photo.copy(seq = format)
                (updated +: result, index - 1)

            }
          acc ++ photos
      }
      .sortBy(_.id)
  }

  /**
    * Parse string into List of string by \n
    * @param string: String
    * @return
    */
  def parseLines(string: String): Array[String] = string.split("\n")

  /**
    * Convert string representation to Photo
    * @param id: Int
    * @param string: String
    * @return Option[Photo]
    */
  def stringToPhoto(id: Int, string: String): Option[Photo] = {
    val result = string.split(",")
    val size = result.length
    if (size >= 3) {
      val photo = result(0).split('.')
      val (name, ext) =
        if (photo.length >= 2) (photo(0), photo(1)) else (photo(0), "")
      Some(
        Photo(
          id = id,
          name = name,
          extension = ext,
          city = result(1),
          date = formatter.parse(result(2))
        )
      )
    } else None
  }

  /**
    * Arrange photos by city
    * @param lines: Array[String]
    * @return ListMap[String, SortedSet[Photo]]
    */
  def parseLinesToPhotos(lines: Array[String]): Map[String, List[Photo]] = {
    val size = lines.length
    @tailrec
    def go(
      index: Int,
      result: Map[String, List[Photo]]
    ): Map[String, List[Photo]] = {
      if (index >= size) result
      else {
        val next = index + 1
        val photoOpt = stringToPhoto(index, lines(index))
        photoOpt match {
          case Some(photo) =>
            result.get(photo.city) match {
              case Some(value) =>
                go(next, result.updated(photo.city, photo +: value))
              case None =>
                go(next, result + (photo.city -> List(photo)))
            }
          case None =>
            go(next, result) //Ignore if data is not correctly parsed
        }
      }
    }
    go(0, Map.empty)
  }

}
