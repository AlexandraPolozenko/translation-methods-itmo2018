package Task2

enum class Token {
  LPAREN, RPAREN, END, XOR, AND, OR, NOT, VAR, PIRS
}

data class Tree(var node: String,
           var children: List<Tree>)

fun printTree(tree: Tree, deep: Int) {
  var space = ""

  for (i in 0 until deep) {
    space += ' '
  }
  println(space + tree.node)

  for (i in tree.children) {
    println(space + " ->")
    printTree(i, deep + 5)
  }
}