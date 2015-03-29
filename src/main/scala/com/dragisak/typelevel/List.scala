package com.dragisak.typelevel

import com.dragisak.typelevel.Nat._

/**
 * List that caries size information in the type
 */
object List {

  sealed trait List[+A, Size <: Nat] {
    def +[B >: A](a: B): List[B, NatN[Size]]
    def ++[B >: A, S <: Nat](l: List[B, S]): List[B, Size#plus[S]]

    def ::[B >: A](a: B): List[B, NatN[Size]]
    def :::[B >: A, S <: Nat](l: List[B, S]): List[B, S#plus[Size]]

    def map[B](f: A => B): List[B, Size]

    def reverse: List[A, Size]
  }


  case class Cons[+A, TailSize <: Nat](head: A, tail: List[A, TailSize]) extends List[A, NatN[TailSize]] {
    override def +[B >: A](a: B): List[B, NatN[NatN[TailSize]]] = Cons(head, tail + a)
    override def ++[B >: A, S <: Nat](l: List[B, S]): List[B, NatN[TailSize]#plus[S]] = Cons(head, tail ++ l)

    override def ::[B >: A](a: B): List[B, NatN[NatN[TailSize]]] = Cons(a, this)
    override def :::[B >: A, S <: Nat](l: List[B, S]): List[B, S#plus[NatN[TailSize]]] = l ++ this

    override def map[B](f: A => B): List[B, NatN[TailSize]] = Cons(f(head), tail.map(f))

    override def reverse = tail.reverse + head
  }

  case object Nil extends List[Nothing, Nat0] {
    override def +[A](a: A): List[A, Nat1] = Cons(a, Nil)
    override def ++[A, S <: Nat](l: List[A, S]):  List[A, S] = l

    override def ::[A](a: A): List[A, Nat1] = Cons(a, Nil)
    override def :::[A, S <: Nat](l: List[A, S]): List[A, S#plus[Nat0]] = l ++ Nil

    override def map[B](f: Nothing => B): List[B, Nat0] = Nil

    override val reverse = Nil
  }
}
