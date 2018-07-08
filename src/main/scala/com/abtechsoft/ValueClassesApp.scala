package com.abtechsoft

/**
  * Created by abdhesh on 19/06/17.
  */
object ValueClassesApp {

  case class User(id: Int, name: String)

  case class Tweet(id: Int, content: String)

}


object ValueClassesAppType {

  case class User(id: UserId, name: UserName)

  case class UserId(id: Int)

  case class UserName(name: String)

  case class TweetId(id: Int)

  case class TweetContent(content: String)

  case class Tweet(id: TweetId, content: TweetContent)

  object Test {

  }

}
