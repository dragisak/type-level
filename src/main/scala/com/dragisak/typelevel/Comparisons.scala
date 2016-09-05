package com.dragisak.typelevel

sealed trait Eq[N1 <: Nat, N2 <: Nat]

object Eq {

  private def apply[N1 <: Nat, N2 <: Nat] = new Eq[N1, N2]{}

  implicit object Eq0 extends Eq[Nat0, Nat0]

  implicit def eqN[N1 <: Nat, N2 <: Nat](implicit eq: Eq[N1, N2]) = Eq[NatN[N1], NatN[N2]]

}

sealed trait Lt[N1 <: Nat, N2 <: Nat]

object Lt {

  private def apply[N1 <: Nat, N2 <: Nat] = new Lt[N1, N2]{}

  implicit def Lt0[N <: Nat] = Lt[Nat0, NatN[N]]

  implicit def LtN[N1 <: Nat, N2 <: Nat](implicit lt: Lt[N1, N2]) = Lt[NatN[N1], NatN[N2]]



}

sealed trait Gt[N1 <: Nat, N2 <: Nat]

object Gt {

  private def apply[N1 <: Nat, N2 <: Nat] = new Gt[N1, N2]{}

  implicit def Gt0[N <: Nat] = Gt[NatN[N], Nat0]

  implicit def GtN[N1 <: Nat, N2 <: Nat](implicit gt: Gt[N1, N2]) = Gt[NatN[N1], NatN[N2]]



}
