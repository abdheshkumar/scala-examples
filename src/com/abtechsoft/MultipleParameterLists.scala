package com.abtechsoft

/**
 * Created by abdhesh on 6/10/15.
 */
object MultipleParameterLists extends App {

  def functionWithMultipleParamLists(x: String, f: String => Unit): String = {
    f(x)
    x
  }


  def functionWithMultipleParamListsV(x: String)(f: String => Unit): String = {
    f(x)
    x
  }

  functionWithMultipleParamLists("Hello", s => println(s.reverse))
  
  functionWithMultipleParamListsV("Hello")(s => println(s.reverse))

  functionWithMultipleParamListsV("Hello") { s => println(s.reverse) }
}
