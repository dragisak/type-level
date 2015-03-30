package com.dragisak.typelevel

import org.scalatest._
import HList._

class HListSpec extends WordSpec with Matchers {

  "HList" should {

    "reverse elements" in {

      val l1 = 1 :: "foo" :: true :: HNil

      val l2 = l1.reverse

      l2 should be (true :: "foo" :: 1 :: HNil)

      l1 should be (l2.reverse)
    }

    "not compile if types don't match" in {
      """val a: HCons[String, HCons[Int, HNil]] = 1 :: "foo" :: HNil """ shouldNot typeCheck
    }

  }

}
