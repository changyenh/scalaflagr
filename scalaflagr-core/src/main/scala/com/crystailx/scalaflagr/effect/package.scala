package com.crystailx.scalaflagr

import com.crystailx.scalaflagr.syntax.EffectSyntax

import scala.language.higherKinds

package object effect {

  implicit class RichImplicitFunctor[A, F[_]](val instance: F[A]) extends AnyVal {

    def map[B](f: A => B)(implicit functor: Functor[F]): F[B] =
      functor.map(instance)(f)

  }

  implicit class RichImplicitMonad[A, F[_]](val instance: F[A]) extends AnyVal {

    def flatMap[B](f: A => F[B])(implicit monad: Monad[F]): F[B] =
      monad.flatMap(instance)(f)
  }
}