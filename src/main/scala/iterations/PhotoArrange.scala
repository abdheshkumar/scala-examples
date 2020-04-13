package iterations

import java.text.SimpleDateFormat
import java.util.Date

object PhotoArrange extends App {

  case class Photo(photo: String, city: String, date: Date)
  val str =
    """photo.jpg, Warsaw, 2013-09-05 14:08:15
    |john.png, London, 2015-6-20 15:13:22
    |myFriends.png, Warsaw, 2013-09-05 14:07:13
    |Eiffel.jpg, Paris, 2015-07-23 08:03:02
    |pisatower.jpg, Paris, 2015-07-22 23:59:59
    |BOB.jpg, London, 2015-08-05 00:02:03
    |notredame.png, Paris, 2015-09-01 12:00:00
    |me.jpg, Warsaw, 2013-09-06 15:40:22
    |a.jpg, Warsaw, 2016-02-13 13:33:50
    |b.jpg, Warsaw, 2016-01-02 15:12:22
    |c.jpg, Warsaw, 2016-01-02 14:34:30
    |d.jpg, Warsaw, 2016-01-02 15:15:01
    |e.jpg, Warsaw, 2016-01-02 09:49:09
    |f.jpg, Warsaw, 2016-01-02 10:55:32
    |g.jpg, Warsaw, 2016-02-29 22:13:11
    |""".stripMargin

  val formatter5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

  val photos = str
    .split("\n")
    .toList
    .flatMap { str =>
      str.split(",") match {
        case Array(photo, city, dateStr) =>
          val d = formatter5.parse(dateStr.trim)
          Some(Photo(photo.trim, city.trim, d))
        case _ => None
      }
    }

  val result: Map[String, List[Photo]] = photos
    .groupBy(_.city)

  val l: Map[String, List[Photo]] = result.map[String, List[Photo]] {
    case ((city: String), (photos: List[Photo])) =>
      val p = photos.sortBy(_.date.getTime)
      val length = p.length.toString.length
      //p.foldLeft(List.em)
      val r = p.zipWithIndex.map {
        case (v, i) =>
          val in = (i + 1)
          val l = length - in.toString.length
          if (l > 0) v.copy(photo = city + Array.fill(l)("0").mkString + in)
          else v.copy(photo = city + in)
      }
      city -> r
  }

  photos.foreach { p =>
    val r = l.get(p.city).flatMap(_.find(_.date.getTime == p.date.getTime))
    println(r)
  }
}
