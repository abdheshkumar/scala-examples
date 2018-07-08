object BinaryGap {
  // 100%
  //?= is a positive lookahead, a type of zero-width assertion. What it's saying is that the captured match must be followed by whatever is within the parentheses but that part isn't captured
  //(?<=) Lookbehind Before the match
  def solution(N: Int): Int = {
    val list = "(?<=1)0+(?=1)".r.findAllIn(N.toBinaryString).toList
    if (list.isEmpty) 0
    else {
      println(list)
      list.maxBy(_.length).length
    }
  }

  solution(15) // 1111
  solution(9) // 1001
  solution(529) // 1000010001
  solution(6) // 110
  solution(328) // 101001000
  solution(16) // 10000
  solution(1024) // 10000000000
  solution(51712) // 110010100000000
}

/*

  A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

  For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps.

  Write a function:

  int solution(int N);

  that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

  For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5.

  Assume that:

  N is an integer within the range [1..2,147,483,647].
  Complexity:

  expected worst-case time complexity is O(log(N));
  expected worst-case space complexity is O(1).

 */