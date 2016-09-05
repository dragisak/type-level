package com.dragisak.typelevel

import org.scalatest.WordSpec
import org.scalatest.Matchers._

class ComparisonSpec extends WordSpec {


  "Eq" should {

    def takesListsOfEqualLength[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit eq: N1 Eq N2) = true

    "should compile if same length" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: NNil

      takesListsOfEqualLength(a, b) shouldBe true


    }

    "not compile if sizes mismatch" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: 5 :: NNil

      "takesListsOfEqualLength(a, b)" shouldNot typeCheck
    }
  }

  "Lt" should {

    def firstListMustBeShorter[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit lt: N1 Lt N2) = true

    "should compile if first is shorter" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: 5 :: NNil

      firstListMustBeShorter(a, b) shouldBe true

    }

    "not compile if same size" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: NNil

      "firstListMustBeShorter(a, b)" shouldNot typeCheck
    }

    "not compile if second list is shorter" in {

      val a = 1 :: 2 :: 3 :: NNil
      val b = 3 :: 4 :: NNil

      "firstListMustBeShorter(a, b)" shouldNot typeCheck
    }

  }


  "Gt" should {

    def firstListMustBeLonger[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit gt: N1 Gt N2) = true

    "should compile if first is shorter" in {

      val a = 1 :: 2 :: 3 ::NNil
      val b = 3 :: 4 :: NNil

      firstListMustBeLonger(a, b) shouldBe true

    }

    "not compile if same size" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: NNil

      "firstListMustBeLonger(a, b)" shouldNot typeCheck
    }

    "not compile if second list is shorter" in {

      val a = 1 :: 2 :: NNil
      val b = 3 :: 4 :: 5 :: NNil

      "firstListMustBeLonger(a, b)" shouldNot typeCheck
    }

  }

}
