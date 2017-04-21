package com.dragisak.typelevel


sealed trait Bool {

  type neg <: Bool
  type and[T <: Bool] <: Bool
  type or[T <: Bool] <: Bool
}


sealed trait Tru extends Bool {

  override type neg = Fls
  override type and[T <: Bool] = T
  override type or[T <: Bool] = Tru
}

sealed trait Fls extends Bool {

  override type neg = Tru
  override type and[T <: Bool] = Fls
  override type or[T <: Bool] = T
}
