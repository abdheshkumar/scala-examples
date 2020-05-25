package functionalcollections

//Set instances are callable(they have apply) like set(1) return true, set(2) return false
//Set instances are callable like functions. The apply function always returns a value true/false
//Set behave like actual functions
trait MySet[A] extends (A => Boolean) {
  override def apply(v1: A): Boolean = contains(v1)
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(anotherSet: MySet[A]): MySet[A] //union
  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(f: A => Boolean): MySet[A]
  def foreach(f: A => Unit): Unit
  //Exercise-2
  def -(elem: A): MySet[A]
  def --(anotherSet: MySet[A]): MySet[A] //difference
  def intersect(anotherSet: MySet[A]): MySet[A]
  //Exercise-3
  //implement a unary_! =NEGATION of a set
  def unary_! : MySet[A]

}

object MySet {
  def apply[A](values: A*): MySet[A] = {
    def buildSet(valueSeq: Seq[A], acc: MySet[A]): MySet[A] = {
      if (valueSeq.isEmpty) acc
      else buildSet(valueSeq.tail, acc + valueSeq.head)
    }
    buildSet(values, new EmptySet[A])
  }
}

class EmptySet[A] extends MySet[A] {
  override def contains(elem: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet[A](elem, this)

  override def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter(f: A => Boolean): MySet[A] = this

  override def foreach(f: A => Unit): Unit = ()

  override def -(elem: A): MySet[A] = this

  override def --(anotherSet: MySet[A]): MySet[A] = this

  override def intersect(anotherSet: MySet[A]): MySet[A] = this

  override def unary_! : MySet[A] = new PropertyBasedSet[A](_ => true)
}
class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {
  override def contains(elem: A): Boolean =
    elem == head || tail.contains(elem)

  override def +(elem: A): MySet[A] = {
    if (this contains elem) this
    else new NonEmptySet[A](elem, this)
  }

  /*
  [1,2,3] ++ [4,5]
  [2,3] ++ [4,5]+1
  [3]++[4,5]+1+2
  []++[4,5]+1+2+3
  [] is a empty set to it will call EmptySet#++
  [4,5]+1+2+3 = [4,5,1,2,3]
   */
  override def ++(anotherSet: MySet[A]): MySet[A] =
    tail ++ anotherSet + head

  override def map[B](f: A => B): MySet[B] =
    tail.map(f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] =
    tail.flatMap(f) ++ f(head)

  override def filter(predicate: A => Boolean): MySet[A] = {
    val filteredSet = tail.filter(predicate)
    if (predicate(head)) filteredSet + head
    else filteredSet
  }

  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def -(elem: A): MySet[A] = {
    if (head == elem) tail
    else tail - elem + head
  }

  override def --(anotherSet: MySet[A]): MySet[A] =
    filter(!anotherSet)
    //filter(!anotherSet(_)) //calls apply

  override def intersect(anotherSet: MySet[A]): MySet[A] = //intersecting==filtering
    this filter (anotherSet) //calling apply which calls contains
  override def unary_! : MySet[A] =
    new PropertyBasedSet[A](x => !this.contains(x))
}

//Need to learn Property based set
class PropertyBasedSet[A](property: A => Boolean) extends MySet[A] {
  def contains(elem: A): Boolean = property(elem)
  //{x in A | property(x)}+ elem = {x in A | property(x) || x == elem}
  def +(elem: A): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || x == elem)
  //union
  def ++(anotherSet: MySet[A]): MySet[A] =
    new PropertyBasedSet[A](x => property(x) || anotherSet(x))

  def map[B](f: A => B): MySet[B] = politelyFail
  def flatMap[B](f: A => MySet[B]): MySet[B] = politelyFail
  def filter(f: A => Boolean): MySet[A] =
    new PropertyBasedSet[A](x => property(x) && f(x))
  def foreach(f: A => Unit): Unit = politelyFail
  //Exercise-2
  def -(elem: A): MySet[A] = filter(x => x != elem)
  //difference
  def --(anotherSet: MySet[A]): MySet[A] = filter(!anotherSet)
  def intersect(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
  def politelyFail =
    throw new IllegalArgumentException("Really deep rabbit hole!")

  override def unary_! : MySet[A] = new PropertyBasedSet[A](x => !property(x))
}
object MySetPlay extends App {
  val s = MySet(1, 2)
  (s + 4 + 4) ++ MySet(-1, -4) + 5 flatMap (x => MySet(x, x * 2)) foreach (println)
  val negative = !s
  println(negative(2))
}
