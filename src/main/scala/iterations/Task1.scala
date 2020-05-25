package iterations



object Task1 extends App {
  /*def main(args: Array[String]): Unit = {
    val s = "we test coders. Give us a try?"
    val s1 = "Forget CVs..Save time . x x"
    println(solution(s))
    println(solution(s1))
  }*/
    def solution(S: String): Int = {
    val separators = Array('.', '?', '!')
    S.split(separators)
      .map(_.split(" ").filterNot(_.isEmpty).length)
      .max
  }
  sealed trait BinaryTree
  case class Node(v: String, left: BinaryTree, right: BinaryTree)
      extends BinaryTree
  case object Empty extends BinaryTree
  val data = Node(
    "A",
    Node("B", Node("D", Empty, Empty), Node("E", Empty, Empty)),
    Node("C", Node("F", Empty, Empty), Node("G", Empty, Empty))
  )

  def getPath(value: String,
              binaryTree: BinaryTree,
              result: (List[String], Boolean)): (List[String], Boolean) = {
    binaryTree match {
      case Node(v, left, right) =>
        val r = v +: result._1
        if (v == value) (r, true)
        else {
          val leftTraverse = getPath(value, left, (r, result._2)) //Search into Left Binary Tree
          if (leftTraverse._2) (leftTraverse._1, true)
          else {
            getPath(value, right, (r, result._2)) //Search into Right Binary Tree
          }
        }
      case Empty =>
        (result._1.tail,false)
    }
  }
  def path(start: String, end: String, root: BinaryTree) = {
    val (leftPath, _) = getPath(start, root, (List.empty, false))
    val (rightPath,_) = getPath(end, root, (List.empty, false))
    val path = leftPath++ rightPath.reverse
    println(path)

  }
  path("D", "G", data)
}
