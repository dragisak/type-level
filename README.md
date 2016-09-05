# Examples of type level programming in Scala

[![Build Status](https://travis-ci.org/dragisak/type-level.svg?branch=master)](https://travis-ci.org/dragisak/type-level)

## HList

Heterogeneous list:

```scala
import com.dragisak.typelevel._

val l = false :: 2 :: Some(4) :: "foo" :: HNil 
// HCons(false,HCons(2,HCons(Some(4),HCons(foo,HNil))))

val a = l.head
// a: Boolean = false

val b = l.tail.head
// b: Int = 2

```

## Nat

Natural numbers:

```scala
import com.dragisak.typelevel.Nat._

type Nat13 = Nat8#plus[Nat5] 
```

## List [+A, Size <: Nat]

List with size information encoded in it's type:
 
```scala
import com.dragisak.typelevel._
import Nat._

val l = 1 :: 2 :: 3 :: NNil

val l3 :NList[Int, Nat3] = l

// does not compile, requires list of size 2
val l2 :NList[Int, Nat2] = l

```

## Type level comparison

```scala
import com.dragisak.typelevel._

def firstListMustBeShorter[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit lt: N1 Lt N2) = ...

def firstListMustBeLonger[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit gt: N1 Gt N2) = ...

def listMustHaveSameLengthr[N1 <: Nat, N2 <: Nat](l1: NList[Int, N1], l2: NList[Int, N2])(implicit eq: N1 Eq N2) = ...

```
