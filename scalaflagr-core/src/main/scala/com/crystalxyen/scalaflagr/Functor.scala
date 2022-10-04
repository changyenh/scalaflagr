package com.crystalxyen.scalaflagr

import scala.language.higherKinds

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {

  def apply[F[_]: Functor](): Functor[F] = implicitly[Functor[F]]

}
