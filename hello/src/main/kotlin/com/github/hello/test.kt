package com.github.hello

//Implement the function that checks whether a string is a valid identifier.
// A valid identifier is a non-empty string that starts with a
// letter or underscore and consists of only letters, digits and underscores.
fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty())
        return false;

    if (s[0] in '0'..'9')
        return false;
    for (char in s) {
        if (char !in '0'..'9' && char !in 'a'..'z' && char !in 'A' .. 'Z' && char != '_')
            return false;
    }
    return true;
}

fun test() {
    check("name", true)
    check("_name", true)
    check("_12", true)
    check("", false)
    check("012", false)
    check("no$", false)
}

fun check(s: String, v: Boolean) {
    when (isValidIdentifier(s)) {
        v -> println("${s} is correct")
        else -> println("${s} error!")
    }
}

fun main(args: Array<String>) {
    test()

    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}

fun sum(list: List<Int>): Int {
    var result = 0
    for (i in list) {
        result += i
    }
    return result
}

fun main(args: Array<String>) {

}