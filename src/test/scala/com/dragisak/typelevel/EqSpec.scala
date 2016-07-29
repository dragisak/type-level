package com.dragisak.typelevel

import org.scalatest.{Matchers, WordSpec}
import com.dragisak.typelevel.Nat._
import Eq._

class EqSpec extends WordSpec with Matchers {

  "Eq" should {

    "two same nats should compile" in {
      isEq[Nat0, Nat0]
      isEq[Nat1, Nat1]
      isEq[Nat2, Nat2]
    }

    "two different nats should not compile" in {
      "isEq[Nat0, Nat1]" shouldNot typeCheck
      "isEq[Nat1, Nat0]" shouldNot typeCheck
      "isEq[Nat2, Nat3]" shouldNot typeCheck
      "isEq[Nat5, Nat3]" shouldNot typeCheck

    }
  }

}
