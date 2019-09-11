package com.dragisak.typelevel

import scala.annotation.implicitNotFound

@implicitNotFound("${L} does not contain ${T}")
trait Contains[L <: HList, A]

object Contains {
  implicit def NotFoundYet[A, T <: HList](implicit ev: T Contains A): HCons[_, T] Contains A = null
  implicit def HeadContains[A, B <: A, T <: HList]: HCons[B, T] Contains A                   = null
}
