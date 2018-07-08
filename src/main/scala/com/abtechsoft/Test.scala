package com.abtechsoft

import scala.annotation.tailrec

/**
  * Created by abdhesh on 19/06/17.
  */
/*
There is an elevator in a building with M floors. This elevator can take a max of X people at a time or max of total weight Y.
Given that a set of people and their weight and the floor they need to stop, how many stops has the elevator taken to serve all the people?
Consider the elevator serves in "first come first serve" basis and the order for the queue can not be changed.

Example:

Let Array A be the weight of each person A = [60, 80, 40]. Let Array B be the floors where each person needs to be dropped off B = [2, 3, 5].

The building has 5 floors, maximum of 2 persons are allowed in the elevator at a time with max weight capacity being 200.
For this example, the elevator would take total of 5 stops in floors: 2, 3, G, 5 and finally G
 */
object Test extends App {
  /*A(0) = 40 B (0) = 3
  A(1) = 40 B (1) = 3
  A(2) = 100 B (2) = 2
  A(3) = 80 B (3) = 2*/
  val people: _root_.scala.Array[Int] = Array.apply(60, 80, 40)
  val floors: _root_.scala.Array[Int] = Array.apply(2, 3, 5)
  val numberOfPeople: Int = 2
  val maximumWeight: Int = 200
  val numberOfFloors: Int = 5

  @tailrec
  def collectValidPeople(maximumPeopleTaken: List[Int], totalWeight: Int, numberP: Int, maximumWeight: Int): Int = maximumPeopleTaken match {
    case scala.Nil => numberP
    case head :: tail =>
      val s = totalWeight.+(head)
      if (s <= maximumWeight) collectValidPeople(tail, s, numberP + 1, maximumWeight)
      else numberP
  }

      def solution(people: Array[Int], floors: Array[Int], numberOfFloors: Int, numberOfPeople: Int, maximumWeight: Int): Int = {
        @tailrec
        def dropPeopleAtFloor(aa: List[Int], bb: List[Int], count: Int): Int = aa match {
          case scala.Nil => count
          case list =>
            val takeNumberOfPeople = list.take(numberOfPeople)
            val numberOfPeopleToInLift: Int = if (takeNumberOfPeople.sum <= maximumWeight) takeNumberOfPeople.size
            else collectValidPeople(takeNumberOfPeople, 0, 0, maximumWeight)

            dropPeopleAtFloor(aa.drop(numberOfPeopleToInLift), bb.drop(numberOfPeopleToInLift), count + bb.take(numberOfPeopleToInLift).distinct.size.+(1))
        }

    dropPeopleAtFloor(people.toList, floors.toList, 0)
  }

  println(solution(Test.people, Test.floors, Test.numberOfFloors, Test.numberOfPeople, Test.maximumWeight))
}
