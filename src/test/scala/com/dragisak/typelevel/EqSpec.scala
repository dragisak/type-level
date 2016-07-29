package com.dragisak.typelevel

import org.scalatest.{Matchers, WordSpec}
import com.dragisak.typelevel.Nat._
import Eq._

class EqSpec extends WordSpec with Matchers {

  "Eq" should {

    "two same nats should compile" in {
      def foo0(implicit eq: Eq[Nat0, Nat0]): Unit = ()
      def foo1(implicit eq: Eq[Nat1, Nat1]): Unit = ()
      def foo2(implicit eq: Eq[Nat2, Nat2]): Unit = ()
      def foo3(implicit eq: Eq[Nat3, Nat3]): Unit = ()
    }

    "two different nats should not compile" in {
      def foo0(implicit eq: Eq[Nat0, Nat1]): Unit = ()
      def foo1(implicit eq: Eq[Nat1, Nat0]): Unit = ()
      def foo2(implicit eq: Eq[Nat1, Nat2]): Unit = ()

    }
  }

}
