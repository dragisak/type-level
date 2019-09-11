package com.dragisak.typelevel

import com.dragisak.typelevel.HList.HNil

import scala.annotation.implicitNotFound

@implicitNotFound("Elements of ${L} are not <: ${A}")
trait <<:[L <: HList, A]

object <<: {
  implicit def NilBound[A]: HNil <<: A                                                    = null
  implicit def HConsBound[A, B <: A, T <: HList](implicit ev: T <<: A): HCons[B, T] <<: A = null
}
