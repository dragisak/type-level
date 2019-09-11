package com.dragisak.typelevel

import scala.annotation.implicitNotFound

@implicitNotFound("Can't figure out Int value for ${N}")
trait NatToInt[N <: Nat] {
  def value: Int
}

object NatToInt {

  implicit val natInt0: NatToInt[Nat0] = new NatToInt[Nat0] {
    override val value: Int = 0
  }

  implicit def natIntN[N <: Nat](implicit n: NatToInt[N]): NatToInt[NatN[N]] =
    new NatToInt[NatN[N]] {
      override val value: Int = n.value + 1
    }

  implicit def toInt[N <: Nat](implicit n: NatToInt[N]): Int = n.value

}
