package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
//    var rightPosition = 0
//    var wrongPosition = 0
//
//    var secretList = secret.toMutableList()
//    var guessList = guess.toMutableList()
//
//    for(i in 0 until CODE_LENGTH) {
//        if (secretList[i] == guessList[i]) {
//            rightPosition += 1
//            secretList[i] = ' '
//            guessList[i] = ' '
//        }
//    }
//    for(i in 0 until CODE_LENGTH) {
//        if (guessList[i] != ' ') {
//            val index = secretList.lastIndexOf(guessList[i])
//            if (index != -1) {
//                wrongPosition += 1
//                secretList[index] = ' '
//            }
//        }
//    }
//    return Evaluation(rightPosition, wrongPosition)






    val peopleToAge = mapOf("Alice" to 20, "Bob" to 21)
    val v1 = peopleToAge.map { (name, age) -> "$name is $age years old" } // [Alice is 20 years old, Bob is 21 years old]
    println(peopleToAge.map { it.value }) // [20, 21]

    val rightPositions = secret.zip(guess).count { it.first == it.second }
    val commonLetters = "ABCDEF".sumBy { ch ->

        Math.min(secret.count { secretItem -> secretItem == ch },
                 guess.count { guessItem -> guessItem == ch })
    }
    return Evaluation(rightPositions, commonLetters - rightPositions)
}

fun List<Int>.allNonZero() = this.all { it != 0 }
fun List<Int>.allNonZero1() =  this.none { it == 0 }
fun List<Int>.allNonZero2() =  this.any { it != 0 }

fun List<Int>.containsZero() =  any { TODO() }
fun List<Int>.containsZero1() =  all { TODO() }
fun List<Int>.containsZero2() =  none { TODO() }

fun main(args: Array<String>) {
    val list1 = listOf(1, 2, 3)
//    list1.allNonZero() eq true
//    list1.allNonZero1() eq true
//    list1.allNonZero2() eq true
//
//    list1.containsZero() eq false
//    list1.containsZero1() eq false
//    list1.containsZero2() eq false
//
//    val list2 = listOf(0, 1, 2)
//    list2.allNonZero() eq false
//    list2.allNonZero1() eq false
//    list2.allNonZero2() eq false
//
//    list2.containsZero() eq true
//    list2.containsZero1() eq true
//    list2.containsZero2() eq true
}