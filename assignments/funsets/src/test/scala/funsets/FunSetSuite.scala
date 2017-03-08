package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val empty: Set = x => false
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(contains(union(s, s3), 3), "Union s s3")
      assert(!contains(s, 3), "Union 3")
      assert(contains(union(s1, empty), 1), "Union {1} {} c 1")
    }
  }

  trait TestSetsExtended extends TestSets {
    /*
    Extended test sets, uses union to create some sets
    of two elements. As such, can't be used for the 'union'
    tests
     */
    val s4 = union(s1, s2)
    val s5 = union(s2, s3)
    val s6 = union(s2, s3)
    val s7 = union(s4, s5)
  }

  test("intersect contains all elements in both sets") {
    new TestSetsExtended {
      assert(contains(intersect(s4, s5), 2), "Intersect {1 2} {2 3} c 2")
      assert(!contains(intersect(s4, s5), 1), "Intersect {1 2} {2 3} !c 1")
      assert(contains(intersect(s4, s1), 1), "Intersect {1 2} {1} c 1")
      assert(contains(intersect(s6, s5), 2), "Intersect {2 3} {2 3} c 2")
      assert(contains(intersect(s6, s5), 3), "Intersect {2 3} {2 3} c 3")
      assert(!contains(intersect(s1, empty), 1), "Intersect {1} {} !c 1")
    }
  }

  test("diff contains all elements in first set but not second set") {
    new TestSetsExtended {
      assert(contains(diff(s4, s5), 1), "Diff {1 2} {2 3} c 1")
      assert(!contains(diff(s4, s5), 2), "Diff {1 2} {2 3} !c 2")
      assert(contains(diff(s4, s1), 2), "Diff {1 2} {1} c 2")
      assert(!contains(diff(s4, s1), 1), "Diff {1 2} {1} !c 1")
      assert(!contains(diff(s6, s5), 2), "Diff {2 3} {2 3} !c 2")
      assert(!contains(diff(s6, s5), 3), "Diff {2 3} {2 3} !c 3")
    }
  }

  test("filter contains all elements in set for which a function is true") {
    new TestSetsExtended {
      assert(contains(filter(s4, x => x > 1), 2), "Filter {1 2} (x > 1) c 2")
      assert(!contains(filter(s4, x => x > 1), 1), "Filter {1 2} (x > 1) !c 1")
      assert(contains(filter(s7, x => x % 2 == 1), 1), "Filter {1 2 3} (x odd) c 1")
      assert(contains(filter(s7, x => x % 2 == 1), 3), "Filter {1 2 3} (x odd) c 3")
      assert(!contains(filter(s7, x => x % 2 == 1), 2), "Filter {1 2 3} (x odd) !c 2")
    }
  }

  test("forall returns true if passed func is true for all elements") {
    new TestSetsExtended {
      assert(!forall(s4, x => x > 1), "forall {1 2} (x > 1) false")
      assert(forall(s4, x => x > 0), "forall {1 2} (x > 0) true")
      assert(forall(s1, x => x == 1), "forall {1} (x == 1) true")
      assert(!forall(s1, x => x == 2), "forall {1} (x == 2) false")
    }
  }

}
