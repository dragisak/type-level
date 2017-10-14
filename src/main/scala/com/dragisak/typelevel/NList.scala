package com.dragisak.typelevel

import com.dragisak.typelevel.Nat._

/**
 * List that caries size information in the type
 */

sealed trait NList[+A, Size <: Nat] {

  type size = Size

  def +[B >: A](a: B): NList[B, NatN[Size]]
  def ++[B >: A, S <: Nat](l: NList[B, S]): NList[B, Size#plus[S]]

  def ::[B >: A](a: B): NList[B, NatN[Size]]
  def :::[B >: A, S <: Nat](l: NList[B, S]): NList[B, S#plus[Size]]

  def map[B](f: A => B): NList[B, Size]

  def reverse: NList[A, Size]
}


case class NCons[+A, TailSize <: Nat](head: A, tail: NList[A, TailSize]) extends NList[A, NatN[TailSize]] {
  override def +[B >: A](a: B): NList[B, NatN[NatN[TailSize]]] = NCons(head, tail + a)
  override def ++[B >: A, S <: Nat](l: NList[B, S]): NList[B, NatN[TailSize]#plus[S]] = NCons(head, tail ++ l)

  override def ::[B >: A](a: B): NList[B, NatN[NatN[TailSize]]] = NCons(a, this)
  override def :::[B >: A, S <: Nat](l: NList[B, S]): NList[B, S#plus[NatN[TailSize]]] = l ++ this

  override def map[B](f: A => B): NList[B, NatN[TailSize]] = NCons(f(head), tail.map(f))

  override def reverse = tail.reverse + head
}

case object NNil extends NList[Nothing, Nat0] {
  override def +[A](a: A): NList[A, Nat1] = NCons(a, NNil)
  override def ++[A, S <: Nat](l: NList[A, S]):  NList[A, S] = l

  override def ::[A](a: A): NList[A, Nat1] = NCons(a, NNil)
  override def :::[A, S <: Nat](l: NList[A, S]): NList[A, S#plus[Nat0]] = l ++ NNil

  override def map[B](f: Nothing => B): NList[B, Nat0] = NNil

  override val reverse = NNil
}
