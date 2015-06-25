package looping

/**
 * Created by abdhesh on 6/25/15.
 */
object LoopingApp {
  val a = Array("apple", "banana", "orange")
  for (e <- a) println(e)
  a.foreach(f => println(f))
  a.foreach { f => println(f) }
  a.foreach(println)
  //Returning values from a for loop
  val newArray = for (e <- a) yield e.toUpperCase

  //for loop counters
  for (i <- 0 until a.length) {
    println(s"$i is ${a(i)}")
  }

  for ((e, count) <- a.zipWithIndex) {
    println(s"$count is $e")
  }

  for (i <- 1 to 10 if i < 4) println(i)

  //Looping over a Map
  val names = Map("fname" -> "Robert",
    "lname" -> "Goren")
  for ((k, v) <- names) println(s"key: $k, value: $v")

  //the methods foreach, map,flatMap, collect, reduce, etc.
  // can often be used to solve your problem without requiring an explicit for loop.
  //How for loops are translated
  /*
  1. A simple for loop that iterates over a collection is translated to a foreach method call on the collection.
  2. A for loop with a guard (see Recipe 3.3) is translated to a sequence of a withFilter
  method call on the collection followed by a foreach call.
  3. A for loop with a yield expression is translated to a map method call on the collection.
  4. A for loop with a yield expression and a guard is translated to a withFilter method call on the collection, followed by a map method call.
   */
}
