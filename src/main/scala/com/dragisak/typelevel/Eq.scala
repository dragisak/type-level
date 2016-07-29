package com.dragisak.typelevel

import com.dragisak.typelevel.Nat._

sealed trait Eq[N1 <: Nat, N2 <: Nat]

object Eq {

  private def apply[N1 <: Nat, N2 <: Nat]: Eq[N1, N2] = new Eq[N1, N2]{}

  implicit val eq0: Eq[Nat0, Nat0] = Eq[Nat0, Nat0]

  implicit def eqN[N <: Nat]: Eq[NatN[N], NatN[N]] = Eq[NatN[N], NatN[N]]


}
