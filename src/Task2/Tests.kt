package Task2

val correctTests = arrayListOf(
    "a",
    "! a",
    "! ! a",
    "(a)",
    "a | b",
    "a # b",
    "a ^ b",
    "a & b",
    "a & !(b ^ (d | e & f) # h) | !g",
    "a        ^ b &c         |          d"
)

val incorrectTests = arrayListOf(
    "aaaaaaa | bbbbbb & cc",
    "A & B & C",
    " a & & c || d",
    "a ^ c c",
    "a b",
    "",
    "a | b * c",
    "(a ^ b) & ( )",
    "(a ^ b & (d | e )",
    "a & !b )",
    "a | b (c)",
    "( a ) )",
    "# a | b"
)

fun runTests(tests: ArrayList<String>) {
  tests.withIndex().forEach { test ->
    try {
      println(test.index)
      printTree(Parser(LexicalAnalyzer(test.value)).parse(), 0)
      println("----------------------------------------")
    } catch (e: Exception) {
      print("\u001B[31m")
      println(e.message)
      println("EXCEPTION")
      print("\u001B[0m")
    }
  }
}

fun main(args: Array<String>) {
  runTests(correctTests)
  println("DONE CORRECT TESTS")
  println()

  runTests(incorrectTests)

  println("DONE INCORRECT TESTS")
}