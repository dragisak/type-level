package com.dragisak.hlist

object HListOps {


  sealed trait HList {
    type prepend[A] <: HList
    def ::[A](a: A) :prepend[A]
  }

  case class HCons[H, Tail <: HList](head: H, tail: Tail) extends HList  {
    override type prepend[A] = HCons[A, HCons[H, Tail]]
    override def ::[A](a: A): prepend[A] = HCons(a, this)
  }

  case object HNil extends HList {
    override type prepend[A] = HCons[A, HNil.type ]
    override def ::[A](a: A): prepend[A] = HCons(a, this)
  }
}
