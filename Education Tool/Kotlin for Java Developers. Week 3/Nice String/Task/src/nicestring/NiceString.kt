package nicestring

import java.lang.StringBuilder

fun main(args: Array<String>) {
    println("bac".isNice())
    println("aza".isNice())
    println("abaca".isNice())
    println("baaa".isNice())
    println("aaab".isNice())
}

fun String.isNice(): Boolean {
    val excl = arrayListOf<String>("bu", "ba", "be")
    val vowels = arrayListOf<Char>('a', 'e', 'i', 'o', 'u')

    return arrayListOf<Boolean>(
        this.toList()
            .windowed(2)
            .map {
                var string = StringBuilder()
                it.joinTo(string, "")
                string.toString()
            }
            .none {
                excl.any { s: String -> s == it }
            },

        this.toList().filter {
            vowels.any { vowelsItem ->
                vowelsItem == it
            }
        }.count() > 2,

        this.toList()
            .windowed(2)
            .any {
                var check = false
                if (it.count() == 2) {
                    check = it[0] == it[1]
                }
                check
            }).count { b -> b } >= 2;
}