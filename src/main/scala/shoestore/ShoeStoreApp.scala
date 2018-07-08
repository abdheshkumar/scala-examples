package shoestore

object ShoeStoreApp extends App {
  def solution(s: String): Int = {
    val listOfShoe: List[String] = s.sliding(1).toList
    val (_, _, count) = listOfShoe.foldLeft((0, 0, 0)) { case ((numberOfLeft, numberOfRight, intervals), b) =>
      b match {
        case "L" =>
          if (numberOfLeft == numberOfRight) (numberOfLeft + 1, numberOfRight, intervals + 1)
          else (numberOfLeft + 1, numberOfRight, intervals)
        case "R" =>
          if (numberOfLeft == numberOfRight) (numberOfLeft, numberOfRight + 1, intervals + 1)
          else (numberOfLeft, numberOfRight + 1, intervals)
      }
    }
    count
  }

  val s = "RLRRLLRLRRLL"
  val s1 = "RLLLRRRLLR"
  val s2 = "LLRLRLRLRLRLRR"
  println(solution(s)) //4
  println(solution(s1)) //4
  println(solution(s2)) //1
}

/*
Ellen is a new Assembly Line Manager in a shoe factory. So far, everything has been going very smoothly for her and N shoes of the same model and size have been produced. Exactly half of them have left shoes and the other half are right shoes.

The freshly sewn shoes are standing in a line, in no particular order (i.e. with no regard to being left or right shoes). They now need to be matched into pairs and packed into boxes.

Ellen would like to assign this task to her subordinate workers. Each worker should get a distinct interval of adjacent shoes, such that the number of left shoes is equal to the number of right shoes. Each shoe must be assigned to exactly one worker.

What is the maximum number of workers that Ellen can assign to this task?

Write a function:

public int solution(String S)
that, given a string S of letters "L" and "R", denoting the types of shoes in line (left or right), returns the maximum number of intervals such that each interval contains an equal number of left and right shoes.

For example, given S = "RLRRLLRLRRLL", the function should return 4, because S can be split into intervals: "RL", "RRLL", "RL" and "RRLL". Note that the intervals do not have to be of the same size.

Given S = "RLLLRRRLLR", the function should return 4, because S can be split into intervals: "RL", "LLRR", "RL" and "LR".

Given S = "LLRLRLRLRLRLRR", the function should return 1.

Assume that:

N is an integer within the range [2..100,000]; String S consists only of the characters "R" and/or "L"; the number of letters "L" and "R" in string S is the same.

Complexity:

expected worst-case time complexity is O(N)
 */