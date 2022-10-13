package com.jh.practice

var data1 : Int = 10
var date2 : Int? =10

fun main(){
    var sum : Int =0
    for (i in 1.. 10){
        sum+=i
    }
    println(sum)
    //반복문 for 이외에도 when/while 있음!

    var list = listOf<Int>(10,20,30)
    println(list)

    var list2 = mutableListOf<Int>(20,30)
    list2.add(30)
    list2.set(1,50)
    println(list2)
    //배열타입중에 Array 도 있음
    //mutableListOf<Int>(20,30) -> mutableListOf<타입>(값1,값2)
    //mutableListOf -> add 나 set을 통해 추가/변경 가능 -> 가변타입
    //listOf<Int>(10,20,30)->listOf<타입>(값1,값2,값3)
    //listOf -> size().get() 이외에 함수 사용 x -> 불변 타입
    //doit 코틀린 page 90~100쪽까지 정독!
}
