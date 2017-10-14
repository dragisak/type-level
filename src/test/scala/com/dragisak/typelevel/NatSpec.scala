package com.dragisak.typelevel

import org.scalatest.WordSpec
import org.scalatest.Matchers._
import Nat._

class NatSpec extends WordSpec {

  "Nat converts to Int" in {

    implicitly[NatInt[Nat9]] should be(9)

  }

}
