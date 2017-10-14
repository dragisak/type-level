package com.dragisak.typelevel

sealed trait :==:[N1 <: Nat, N2 <: Nat]

object :==: {

  private def apply[N1 <: Nat, N2 <: Nat] = new :==:[N1, N2]{}

  implicit object Eq0 extends :==:[Nat0, Nat0]

  implicit def eqN[N1 <: Nat, N2 <: Nat](implicit eq: N1 :==: N2) = :==:[NatN[N1], NatN[N2]]

}

sealed trait :<:[N1 <: Nat, N2 <: Nat]

object :<: {

  private def apply[N1 <: Nat, N2 <: Nat] = new :<:[N1, N2]{}

  implicit def Lt0[N <: Nat] = :<:[Nat0, NatN[N]]

  implicit def LtN[N1 <: Nat, N2 <: Nat](implicit lt: N1 :<: N2) = :<:[NatN[N1], NatN[N2]]



}

sealed trait :>:[N1 <: Nat, N2 <: Nat]

object :>: {

  private def apply[N1 <: Nat, N2 <: Nat] = new :>:[N1, N2]{}

  implicit def Gt0[N <: Nat] = :>:[NatN[N], Nat0]

  implicit def GtN[N1 <: Nat, N2 <: Nat](implicit gt: N1 :>: N2) = :>:[NatN[N1], NatN[N2]]

}
