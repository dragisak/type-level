package com.dragisak.typelevel


object Iffy {

  sealed trait Iffy {
    type neg <: Iffy
    type and[T <: Iffy] <: Iffy
    type or[T <: Iffy] <: Iffy
  }


  sealed trait Yes extends Iffy {
    override type neg = No
    override type and[T <: Iffy] = T
    override type or[T <: Iffy] = Yes
  }

  sealed trait No extends Iffy {
    override type neg = Yes
    override type and[T <: Iffy] = No
    override type or[T <: Iffy] = T
  }

}
