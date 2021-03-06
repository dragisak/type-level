package com.dragisak.typelevel

/**
  * Heterogeneous list.
  *
  * Stick anything in it and it will preserve type information.
  */
sealed trait HList {
  type prepend[A] <: HList
  type append[A] <: HList
  type appendL[L <: HList] <: HList
  type size <: Nat
  def +[A](a: A): append[A]
  def ++[L <: HList](l: L): appendL[L]
  def ::[A](a: A): prepend[A]
  def :::[L <: HList](l: L): L#appendL[_ <: HList]
  def take(n: Int): HList
  def drop(n: Int): HList
  def reverse: HList
}

case object HNil extends HList {
  import HList._
  override type prepend[A]          = HCons[A, HNil]
  override type append[A]           = HCons[A, HNil]
  override type appendL[L <: HList] = L
  override type size                = Nat0
  override def +[A](a: A): append[A]                  = HCons(a, HNil)
  override def ++[L <: HList](l: L): L                = l
  override def ::[A](a: A): prepend[A]                = HCons(a, this)
  override def :::[L <: HList](l: L): L#appendL[HNil] = l ++ HNil
  override def take(n: Int): HList                    = HNil
  override def drop(n: Int): HList                    = HNil
  override val reverse: HNil                          = HNil
}

case class HCons[H, Tail <: HList](head: H, tail: Tail) extends HList {
  override type prepend[A]          = HCons[A, HCons[H, Tail]]
  override type append[A]           = HCons[H, Tail#append[A]]
  override type appendL[L <: HList] = HCons[H, Tail#appendL[L]]
  override type size                = NatN[Tail#size]
  override def +[A](a: A): append[A]                            = HCons(head, tail + a)
  override def ++[L <: HList](l: L): appendL[L]                 = HCons(head, tail ++ l)
  override def ::[A](a: A): prepend[A]                          = HCons(a, this)
  override def :::[L <: HList](l: L): L#appendL[HCons[H, Tail]] = l ++ this
  override def take(n: Int): HList = {
    require(n >= 0, "Can't take negative count")
    n match {
      case 0 => HNil
      case j => head :: tail.take(j - 1)
    }
  }

  override def drop(n: Int): HList = {
    require(n >= 0, "Can't drop negative count")
    n match {
      case 0 => this
      case j => tail.drop(j - 1)
    }
  }

  override def reverse = tail.reverse + head
}

object HList {

  type HNil              = HNil.type
  type ::[A, H <: HList] = H#prepend[A]

}
