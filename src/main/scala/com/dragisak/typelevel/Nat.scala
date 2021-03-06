package com.dragisak.typelevel

/**
  * Natural numbers.
  */
sealed trait Nat {
  type plus[N <: Nat] <: Nat
  type mul[N <: Nat] <: Nat
}

sealed trait NatN[Prev <: Nat] extends Nat {
  override type plus[N <: Nat] = NatN[Prev#plus[N]]
  override type mul[N <: Nat]  = Prev#mul[N]#plus[N]

}

sealed trait Nat0 extends Nat {
  override type plus[N <: Nat] = N
  override type mul[N <: Nat]  = Nat0
}

object Nat {

  type Nat1 = NatN[Nat0]
  type Nat2 = NatN[Nat1]
  type Nat3 = NatN[Nat2]
  type Nat4 = NatN[Nat3]
  type Nat5 = NatN[Nat4]
  type Nat6 = NatN[Nat5]
  type Nat7 = NatN[Nat6]
  type Nat8 = NatN[Nat7]
  type Nat9 = NatN[Nat8]

}
