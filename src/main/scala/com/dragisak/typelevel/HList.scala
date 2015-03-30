package com.dragisak.typelevel

/**
 * Heterogeneous list.
 *
 * Stick anything in it and it will preserve type information.
 */
object HList {

  sealed trait HList {
    type prepend[A] <: HList
    type append[A] <: HList
    type appendL[L <: HList] <: HList

    def +[A](a: A): append[A]
    def ++[L <: HList](l: L): appendL[L]

    def ::[A](a: A): prepend[A]
    def :::[L <: HList](l: L): L#appendL[_ <: HList ]

    def reverse: HList
  }

  case class HCons[H, Tail <: HList](head: H, tail: Tail) extends HList {
    override type prepend[A] = HCons[A, HCons[H, Tail]]
    override type append[A] = HCons[H, Tail#append[A]]
    override type appendL[L <: HList] = HCons[H, Tail#appendL[L]]

    override def +[A](a: A): append[A] = HCons(head, tail + a)
    override def ++[L <: HList](l: L): appendL[L] = HCons(head, tail ++ l)

    override def ::[A](a: A): prepend[A] = HCons(a, this)
    override def :::[L <: HList](l: L): L#appendL[HCons[H, Tail]] = l ++ this

    override def reverse = tail.reverse + head
  }

  case object HNil extends HList {
    override type prepend[A] = HCons[A, HNil.type]
    override type append[A] = HCons[A, HNil.type]
    override type appendL[L <: HList] = L

    override def +[A](a: A): append[A] = HCons(a, HNil)
    override def ++[L <: HList](l: L): L = l

    override def ::[A](a: A): prepend[A] = HCons(a, this)
    override def :::[L <: HList](l: L): L#appendL[HNil.type ] = l ++ HNil

    override val reverse = HNil
  }

  type HNil = HNil.type

}
