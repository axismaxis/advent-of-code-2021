fun main(args: Array<String>) {
    val text = getResourceAsText("input.txt")
    val field = ArrayList<ArrayList<Int>>()

    //Input
    text.lines().forEach { line ->
        val numbers = ArrayList<Int>(10)
        line.toCharArray().forEach {
            numbers.add(Character.getNumericValue(it))
        }
        field.add(numbers)
    }

    //Algorithm
    printField(field)
    for (i in 0 until 10) {
        for (j in 0 until 10) {
            field[i][j]++
        }
    }
    printField(field)



    println("Finished")
}

fun printField(field: ArrayList<ArrayList<Int>>){
    println("||||||||||||")
    for (i in 0 until 10) {
        print("|")
        for (j in 0 until 10) {
            print(field[i][j])
        }
        print("|\n")
    }
    println("||||||||||||")
}

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}



