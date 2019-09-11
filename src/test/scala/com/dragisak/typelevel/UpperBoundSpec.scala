package com.dragisak.typelevel

import org.scalatest.WordSpec
import org.scalatest.Matchers._

class UpperBoundSpec extends WordSpec {

  trait Animal
  case class Dog(name: String) extends Animal
  case class Cat(name: String) extends Animal

  private def takesAnimals[L <: HList](l: L)(implicit ev: L <<: Animal) = true

  "<<:" should {
    "work" in {
      val l = Cat("foo") :: Dog("bar") :: Cat("felix") :: HNil
      takesAnimals(l) shouldBe true
    }

    "does not allow junk" in {
      val l = Cat("foo") :: Dog("bar") :: Cat("felix") :: 1 :: HNil
      "takesAnimals(l)" shouldNot typeCheck
    }

  }
}
