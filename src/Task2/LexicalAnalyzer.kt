package Task2

import java.text.ParseException

class LexicalAnalyzer(analyzeString: String) {
  lateinit var curToken: Token
  var curChar = analyzeString[0]
  var curPos = 0
  var lastVar = 'a'
  private val stringToAnalyze = analyzeString + "$"

  private fun nextChar() {
    curPos++
    try {
      curChar = stringToAnalyze[curPos]
    } catch (e: Exception) {
      throw ParseException(e.message, curPos)
    }
  }

  fun nextToken() {
    while (curChar.isWhitespace()) {
      nextChar()
    }

    if (curChar.isLetter()) {
      if (curChar in 'A'..'Z') {
        throw ParseException("Incorrect symbol in name of variable \"" + curChar + "\"", curPos)
      }
      lastVar = curChar
      nextChar()
      curToken = Token.VAR
      if (curChar.isLetter()) {
        throw ParseException("Incorrect name of variable \"" + curChar + "\"", curPos)
      }
    } else {
      when (curChar) {
        '(' -> {
          nextChar(); curToken = Token.LPAREN
        }
        ')' -> {
          nextChar(); curToken = Token.RPAREN
        }
        '&' -> {
          nextChar(); curToken = Token.AND
        }
        '|' -> {
          nextChar(); curToken = Token.OR
        }
        '^' -> {
          nextChar(); curToken = Token.XOR
        }
        '!' -> {
          nextChar(); curToken = Token.NOT
        }
        '$' -> {
          curToken = Token.END
        }
        '#' -> {
          nextChar(); curToken = Token.PIRS
        }
        else -> {
          throw ParseException("Illegal character \"" + curChar + "\"", curPos)
        }
      }
    }
  }
}