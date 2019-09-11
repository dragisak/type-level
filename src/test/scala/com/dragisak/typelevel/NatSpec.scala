package com.dragisak.typelevel

import org.scalatest.WordSpec
import org.scalatest.Matchers._
import NatToInt._
import Nat._

class NatSpec extends WordSpec {

  "toInt converts Nat to Int" in {

    toInt[Nat9] should be(9)

  }

}
