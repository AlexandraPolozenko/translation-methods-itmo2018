package Task2

import java.text.ParseException

class Parser(var lex: LexicalAnalyzer) {
  fun parse(): Tree {
    lex.nextToken()
    val parseTree = E()
    if (lex.curToken != Token.END) {
      throw ParseException(" extra ) " + " at position ", lex.curPos)
    }
    return parseTree
  }

  private fun E(): Tree {
    when (lex.curToken) {
      Token.LPAREN, Token.NOT, Token.VAR -> {
        val t = T()
        val ep = EP()
        return Tree("E", listOf(t, ep))
      }
      else -> {
        throw ParseException("expected ! or variable or ( or end of string at position ",
            lex.curPos)
      }
    }
  }

  private fun EP(): Tree {
    return when (lex.curToken) {
      Token.OR -> {
        lex.nextToken()
        val t = T()
        val ep = EP()

        Tree("E'", listOf(Tree("|", listOf()), t, ep))
      }
      Token.END, Token.RPAREN -> {
        Tree("E'", listOf(Tree("eps", listOf())))
      }
      else -> {
        throw ParseException("Expected operation | at position ", lex.curPos)
      }
    }
  }

  private fun T(): Tree {
    when (lex.curToken) {
      Token.NOT, Token.LPAREN, Token.VAR -> {
        val k = K()
        val tp = TP()

        return Tree("T", listOf(k, tp))
      }
      else -> {
        throw ParseException("expected ! or variable or ( or end of string at position ",
            lex.curPos)
      }
    }
  }

  private fun TP(): Tree {
    when (lex.curToken) {
      Token.XOR, Token.PIRS -> {
        val token = lex.curToken
        lex.nextToken()
        val k = K()
        val tp = TP()

        return if (token == Token.XOR) {
          Tree("T'", listOf(Tree("^", listOf()), k, tp))
        } else {
          Tree("T'", listOf(Tree("#", listOf()), k, tp))
        }
      }
      Token.OR, Token.RPAREN, Token.END -> {
        return Tree("T'", listOf(Tree("eps", listOf())))
      }

      else -> {
        throw ParseException("Expected operation ^ at position ", lex.curPos)
      }
    }
  }

  private fun K(): Tree {
    when (lex.curToken) {
      Token.LPAREN, Token.NOT, Token.VAR -> {
        val f = F()
        val kp = KP()
        return Tree("K", listOf(f, kp))
      }

      else -> {
        throw ParseException("expected ! or variable or ( or end of string at position ",
            lex.curPos)
      }
    }
  }

  private fun KP(): Tree {
    return when (lex.curToken) {
      Token.AND -> {
        lex.nextToken()
        val f = F()
        val kp = KP()

        Tree("K'", listOf(Tree("&", listOf()), f, kp))
      }
      Token.END, Token.RPAREN, Token.OR, Token.XOR, Token.PIRS -> {
        Tree("K'", listOf(Tree("eps", listOf())))
      }
      else -> {
        throw ParseException("Expected operation & at position ", lex.curPos)
      }
    }
  }

  private fun F(): Tree {
    when (lex.curToken) {
      Token.VAR -> {
        val v = lex.lastVar.toString()
        lex.nextToken()
        return Tree("F", listOf(Tree(v, listOf())))
      }
      Token.LPAREN -> {
        lex.nextToken()
        val e = E()
        if (lex.curToken != Token.RPAREN) {
          throw ParseException(") expected " + "at position", lex.curPos)
        }
        lex.nextToken()
        return Tree("F", listOf(Tree("(", listOf()), e, Tree(")", listOf())))
      }
      Token.NOT -> {
        lex.nextToken()
        val e = E()
        return Tree("F", listOf<Tree>(Tree("!", listOf()), e))
      }
      else -> {
        throw ParseException("expected ! or variable or ( or end of string at position ",
            lex.curPos)
      }
    }
  }
}