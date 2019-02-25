import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun runParser(str: String) {
  val reader = ANTLRInputStream(str)
  val lexer = CalcLexer(reader)
  val tokens = CommonTokenStream(lexer)
  val parser = CalcParser(tokens)
  parser.calc()
}
