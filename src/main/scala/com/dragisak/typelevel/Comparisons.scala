package com.dragisak.typelevel

import scala.annotation.implicitNotFound

@implicitNotFound("${N1} is not equal to ${N2}")
sealed trait :==:[N1 <: Nat, N2 <: Nat]

object :==: {
  implicit val Eq0: Nat0 :==: Nat0                                                        = null
  implicit def EqN[N1 <: Nat, N2 <: Nat](implicit eq: N1 :==: N2): NatN[N1] :==: NatN[N2] = null
}

@implicitNotFound("${N1} is not less than ${N2}")
sealed trait :<:[N1 <: Nat, N2 <: Nat]

object :<: {
  implicit def Lt0[N <: Nat]: Nat0 :<: NatN[N]                                          = null
  implicit def LtN[N1 <: Nat, N2 <: Nat](implicit lt: N1 :<: N2): NatN[N1] :<: NatN[N2] = null
}

@implicitNotFound("${N1} is not greater than ${N2}")
sealed trait :>:[N1 <: Nat, N2 <: Nat]

object :>: {
  implicit def Gt0[N <: Nat]: NatN[N] :>: Nat0                                          = null
  implicit def GtN[N1 <: Nat, N2 <: Nat](implicit gt: N1 :>: N2): NatN[N1] :>: NatN[N2] = null
}
