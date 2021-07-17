package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun main() {
    val l = ArrayList<Char?>()
    l.add('a')
    l.add(null)
    l.add('a')
    l.add('a')

    val b = l.filterNotNull().windowed(2, 1, true, { list -> print(list); 'c' })
    print(b)
}

fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> {
//        = this.filterNotNull()
//        .windowed(2,2, true)
//        .map { if (it.size < 2) arrayListOf<T>(it[0])
//               else if (it[0] != it[1]) arrayListOf<T>(it[0],it[1])
//               else arrayListOf(merge(it[0])) }
//        .flatMap { it.toList() }
    var i: Int = 0
    var ret = ArrayList<T>()
    val source = this.filterNotNull()
    if (source.size > 1) {
        while (i <= source.size - 1) {
            if (i + 1 <= source.size - 1) {
                //есть следующий элемент
                if (source[i] == source[i + 1]) {
                    ret.add(merge(source[i]))
                    i += 2
                } else {
                    ret.add(source[i])
                    i++
                }
            } else {
                ret.add(source[i])
                break;
            }
        }
    }
    else {
        ret = source as ArrayList<T>
    }
    return ret
}