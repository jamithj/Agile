Agile Board
===========
This is a simple agile board application with a board with one iteration and several cards, which facilitate to perform
following operations. Refer to the link https://gist.github.com/codingricky/44d0547627e56d9c9a70b14beb9434cb for
requirements.

addCard
moveCard
getVelocity
undoLastMove

Languages and tools used
------------------------
This application has been developed in scala 2.11.8.

Java:
java version "1.8.0_73"
Java(TM) SE Runtime Environment (build 1.8.0_73-b02)
Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)

sbt 0.13.11

Unit Tests
----------
Unit tests are available within /src/test/scala directory.
run test in sbt prompt.

Future directions
=================
Enhance the application as a microservice to enable to consume operations Json base.
Define and object and language bundle to manage error codes and error messages properly
