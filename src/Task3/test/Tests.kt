import java.time.Instant
import java.util.*

val correctTests = arrayListOf(
    "a = 5;\n"
        + "b = a * 2;\n"
        + "c = a - b / (b - a);\n"
        + "a = 8;\n"
        + "c = a + b * (b - 3);",
    "a = 2;\n"
        + "b = a + 2;\n"
        + "c = a + b * (b - 3);\n"
        + "a = 3;\n"
        + "c = a + b * (b - 3);")

val incorrectTests = arrayListOf(
    "a = 2;\n" +
        "b = 0 | a;\n",
    "a = 5\n" +
        "b = a + (3 + a))")

fun runTests(tests: ArrayList<String>) {
  tests.withIndex().forEach { test ->
    print("\u001B[34m")
    println("Test " + test.index)
    print("\u001B[0m")
    runParser(test.value)
    println("----------------------------------------")
  }
}

fun main(args: Array<String>) {
 /* runTests(correctTests)
  print("\u001B[33m")
  println("DONE CORRECT TESTS")
  print("\u001B[0m")
  println()

  runTests(incorrectTests)
  print("\u001B[33m")
  println("DONE INCORRECT TESTS")
  print("\u001B[0m")*/

  runParser(" 2 ^ 3 ^ 2;")
  runParser("2 * 2 ^ 3 ^ 2;")
  runParser("2 ^ 3 * 3 ^ 2;")
  runParser("1 - 2 - 3;")

}