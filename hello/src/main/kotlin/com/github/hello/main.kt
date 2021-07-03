package com.github.hello;

fun main(args: Array<String>) {
    // использование усовий как выражения
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("1. Hello, $name")

    // выражения внутри строковых шаблонов
    println("2. Hello, ${if (args.isNotEmpty()) args[0] else "Kotlin"}")

    // использование функций в строках
    if (args.isNotEmpty())
        println("2. firstArgs, ${printFirstArgs(args[0])}")

    // использование функций в строках
    if (args.isNotEmpty())
        println("3. firstArgs, ${printFirstArgs(args[0])}")

    // определяем константу
    val title = "Константа"
    // title = 1 <- не можем поменять
    println(title)

    // определяем переменную
    var nameTitle = "Переменная"
    nameTitle += " , #1"
    println(nameTitle)

    // вызов функции
    println(checkEmptyArgs(args[0]))

    for(ch in "abc") {
        print(ch + 1)
    }
}

// обычная функция
fun printFirstArgs(arg: String): String {
    return "Args[0] = $arg";
}

// функция с expression body (можно не описывать возвращаемый тип)
fun printFirstArgsDirect(arg: String) = "Args[0] = $arg"

// вложенные функции
fun checkEmptyArgs(arg: String): String {
    fun isEmpty(value: String) = value.isEmpty()
    return if (isEmpty(arg)) "пусто" else "не пусто"
}