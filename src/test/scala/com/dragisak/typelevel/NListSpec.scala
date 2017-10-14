package com.dragisak.typelevel

import org.scalatest.WordSpec
import org.scalatest.Matchers._
import Nat._

class NListSpec extends WordSpec {

  "List" should {

    "should compile if types match" in {

      val a: NList[Int, Nat2] = 1 :: 2 :: NNil

    }

    "should have correct size" in {

      val l = 1 :: 2 :: 3 :: NNil
      toInt[l.size] should be (3)

    }


    "not compile if sizes mismatch" in {
      "val a: NList[Int, Nat2] = 1 :: 2 :: 3 :: Nil" shouldNot typeCheck
    }
  }

}
