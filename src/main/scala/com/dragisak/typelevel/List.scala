package com.dragisak.typelevel

import com.dragisak.typelevel.Nat._

/**
 * List that caries size information in the type
 */
object List {

  sealed trait List[+A, Size <: Nat] {

    def ::[B >: A](a: B): List[B, NatN[Size]]

  }

  case class Cons[+A, TailSize <: Nat](head: A, tail: List[A, TailSize]) extends List[A, NatN[TailSize]] {
    override def ::[B >: A](a: B): List[B, NatN[NatN[TailSize]]] = Cons(a, this)
  }

  case object Nil extends List[Nothing, Nat0] {
    override def ::[A](a: A): List[A, NatN[Nat0]] = Cons(a, Nil)
  }
}
