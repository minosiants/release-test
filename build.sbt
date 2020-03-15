scalaVersion := "2.13.1"

val catsVersion           = "2.1.0"
val catsEffectVersion     = "2.1.2"
val specs2Version         = "4.8.3"
val logbackVersion        = "1.2.3"
val scalacheckVersion     = "1.14.1"
val catsEffectTestVersion = "0.3.0"

lazy val root = (project in file("."))
  .settings(
    organization := "com.minosiatns",
    name := "release-test",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "org.typelevel"   %% "cats-core"                  % catsVersion,
      "org.typelevel"   %% "cats-effect"                % catsEffectVersion,
      "org.scalacheck"  %% "scalacheck"                 % scalacheckVersion % "test",
      "com.codecommit"  %% "cats-effect-testing-specs2" % catsEffectTestVersion % "test",
      "ch.qos.logback"  % "logback-classic"             % logbackVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")
  ).settings(releaseProcessSettings)
   .settings(ghRepoSettings)


lazy val ghRepoSettings = Seq(
  publishTo := Some("Githab packages" at "https://maven.pkg.github.com/minosiants"),
  credentials ++= {
    (sys.env.get("GITHUB_ACTOR"), sys.env.get("GITHUB_TOKEN")) match {
      case (Some(user), Some(pass)) =>
        Seq(Credentials("GitHub Package Registry", "maven.pkg.github.com", user, pass))
      case _ => Nil
    }
    })

import ReleaseTransformations._
lazy val releaseProcessSettings = Seq(
  releaseIgnoreUntrackedFiles := true,
  releaseProcess := Seq[ReleaseStep](checkSnapshotDependencies,
    inquireVersions,
    runClean,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    pushChanges))



