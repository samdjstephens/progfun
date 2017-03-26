class TestClassTwo(var x: Int) {
  def modifyX = {
    x = x + 1
    x
  }

  def printX = println(x)
}

val test = new TestClassTwo(1)

test.printX
test.modifyX
test.printX
