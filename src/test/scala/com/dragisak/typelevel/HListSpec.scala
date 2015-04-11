package com.dragisak.typelevel

import org.scalatest._
import HList._

class HListSpec extends WordSpec with Matchers {

  "HList" when {

    "reverting" should {
      "reverse elements" in {

        val l1 = 1 :: "foo" :: true :: HNil

        val l2 = l1.reverse

        l2 shouldBe true :: "foo" :: 1 :: HNil

        l1 shouldBe l2.reverse
      }
    }

    "assigning" should {
      "not compile if types don't match" in {
        """val a: HCons[String, HCons[Int, HNil]] = 1 :: "foo" :: HNil """ shouldNot typeCheck
      }
    }


    "taking" should {
      "take 2" in {
        val l1 = 1 :: "foo" :: true :: HNil

        l1.take(2) shouldBe 1 :: "foo" :: HNil
      }

      "take more" in {
        val l1 = 1 :: "foo" :: true :: HNil

        l1.take(10) shouldBe l1
      }

      "take 0" in {
        val l1 = 1 :: "foo" :: true :: HNil
        l1.take(0) shouldBe HNil
      }

      "take from HNil" in {
        HNil.take(1) shouldBe HNil
      }

      "throw exception if negative count" in {
        val l1 = 1 :: "foo" :: true :: HNil
        an[IllegalArgumentException] shouldBe thrownBy(l1.take(-1))
      }
    }

    "dropping" should {
      "drop 2" in {
        val l1 = 1 :: "foo" :: true :: Some(1L) :: HNil

        l1.drop(2) shouldBe true :: Some(1L) :: HNil
      }

      "drop more" in {
        val l1 = 1 :: "foo" :: true :: HNil

        l1.drop(10) shouldBe HNil
      }

      "drop 0" in {
        val l1 = 1 :: "foo" :: true :: HNil
        l1.drop(0) shouldBe l1
      }

      "drop from HNil" in {
        HNil.drop(1) shouldBe HNil
      }

      "throw exception if negative count" in {
        val l1 = 1 :: "foo" :: true :: HNil
        an[IllegalArgumentException] shouldBe thrownBy(l1.drop(-1))
      }
    }

  }

}
