package com.abtechsoft

import java.util
import java.util.{ArrayList, Iterator, List}

/**
  * Created by abdhesh on 19/06/17.
  */
object Test1 extends App {
  /*val S = "1B 2C,2D 4D"
  val T = "2B 2D 3D 4D 4A"
  val N = 4
  val array = Array.ofDim[String](N, N)
  val result = S.split(",").toList
  val map = ('A' to 'Z').toList.map(_.toString).zipWithIndex

  def get(s: String) = map.find(_._1 == s).get

  result.map {
    st =>
      val d = st.split(",")
      val left = d(0)
      val right = d(1)
      val lr = get(left(1).toString)
      array(left(0).toInt)(lr._2) = left
      array(left(0).toInt)(lr._2 + 1) = s"${lr._2}${lr._1}"
      val rl =  get(right(1).toString)
      //array(right(0).toInt)(get(right(1).toString)) = right

  }*/
  val hits: String = "2B 2D 3D 4D 4A"
  val ships: String = "1B 2C, 2D 4D"

  val result = solution(4, ships, hits)
  println(result  )

  class Point(val coord: String) {

    def getX: Int = coord.toUpperCase.charAt(1) - 'A'

    def getY: Int = coord.charAt(0) - '1'

    def greaterOrEqual(other: Point): Boolean = getX >= other.getX && getY >= other.getY

    override def toString: String = "(" + getX + ", " + getY + ")"
  }

  def solution(N: Int, S: String, T: String): String = {
    val ships = parseShips(S, N * N)
    val hits = parseHits(T, N * N)
    var touched = 0
    var sunken = 0
    val shipIt = ships.iterator
    while ( {
      shipIt.hasNext
    }) {
      val current = shipIt.next
      val touching = current.getHits(hits)
      if (touching > 0) if (touching == current.getSize) sunken += 1
      else touched += 1
    }
    "" + sunken + "," + touched
  }

  def parseHits(hits: String, maxHits: Int): util.List[Point] = {
    val hitsList = new util.ArrayList[ Point](maxHits)
    val coords = hits.split(" ")
    for (coord <- coords) {
      hitsList.add(new Point(coord))
    }
    hitsList
  }

  def parseShips(ships: String, maxShips: Int): util.List[Ship] = {
    val shipsList = new util.ArrayList[Ship](maxShips)
    val shipsCoords = ships.split(", ")
    for (shipCoord <- shipsCoords) {
      val coords = shipCoord.split(" ")
      shipsList.add(new Ship(new Point(coords(0)), new Point(coords(1))))
    }
    println(shipsList.size())
    shipsList
  }

  class Ship(topLeft: Point, bottomRight: Point) {
    def getTopLeft: Point = topLeft

    def getBottomRight: Point = bottomRight

    def getSize: Int = (Math.abs(topLeft.getX - bottomRight.getX) + 1) * (Math.abs(topLeft.getY - bottomRight.getY) + 1)

    override def toString: String = "(" + topLeft + ", " + bottomRight + ")"

    def getHits(shots: util.List[Point]): Int = {
      val shotIt: util.Iterator[Point] = shots.iterator
      var hits: Int = 0
      while ( {
        shotIt.hasNext
      }) {
        val shot: Point = shotIt.next
        if (shot.greaterOrEqual(topLeft) && bottomRight.greaterOrEqual(shot)) hits += 1
      }
      hits
    }
  }
}
