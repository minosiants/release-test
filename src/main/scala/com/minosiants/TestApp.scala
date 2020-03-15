package com.minosiants

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object TestApp extends IOApp{

  override def run(args: List[String]): IO[ExitCode] = {

    IO{
      println("hello")
    }.as(ExitCode.Success)
  }
}
