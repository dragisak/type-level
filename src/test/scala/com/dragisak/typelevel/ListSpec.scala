package com.dragisak.typelevel

import org.scalatest._
import List._
import Nat._

class ListSpec extends WordSpec with Matchers {

  "List" should {

    "should compile if types match" in {

      val a: List[Int, Nat2] = 1 :: 2 :: Nil

    }

    "not compile if sizes mismatch" in {
      "val a: List[Int, Nat2] = 1 :: 2 :: 3 :: Nil" shouldNot typeCheck
    }
  }

}
