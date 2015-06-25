package stringpattern

/**
 * Created by abdhesh on 6/24/15.
 */
object ReplacingPatternsinStrings {
  val address = "123 Main Street".replaceAll("[0-9]", "x")
  val regex = "[0-9]".r
  val newAddress = regex.replaceAllIn("123 Main Street", "x")
  val result = "123".replaceFirst("[0-9]", "x")
  val regex1 = "H".r

  val result1 = regex.replaceFirstIn("Hello world", "J")
  //Extracting Parts of a String That Match Patterns

  val pattern = "([0-9]+) ([A-Za-z]+)".r
  val pattern(count, fruit) = "100 Bananas"

  val moviesData = """
  "movies near 80301"
  "movies 80301"
  "80301 movies"
  "movie: 80301"
  "movies: 80301"
  "movies near boulder, co"
  "movies near boulder, colorado"
                   """

  // match "movies 80301"
  val MoviesZipRE = "movies (\\d{5})".r
  // match "movies near boulder, co"
  val MoviesNearCityStateRE = "movies near ([a-z]+), ([a-z]{2})".r

  moviesData match {
    case MoviesZipRE(zip) => println(zip)
    case MoviesNearCityStateRE(city, state) => println(city, state)
    case _ => println("did not match a regex")
  }
}
