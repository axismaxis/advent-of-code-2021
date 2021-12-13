
fun main(args: Array<String>) {
    val text = getResourceAsText("input.txt")

    val points = HashSet<Point>()

    //Input
    text.lines()
        .filter { it.isNotBlank() && !it.startsWith("f") && !it.startsWith("#") }
        .forEach {
                val coordinates = it.trimEnd().split(",")
                points.add(Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])))
        }

    val commands = ArrayList<FoldCommand>()

    text.lines()
        .filter { it.startsWith("f") }
        .forEach {
            val args = it.trimEnd().split(" ").last().split("=")
            commands.add(FoldCommand(args[0], Integer.parseInt(args[1])))
        }

    for (command in commands){
        val listOfNewPoints = HashSet<Point>()
        val iter = points.iterator()

        if (command.axis == "x"){
            while (iter.hasNext()){
                val point = iter.next()
                if (point.x > command.coordinate){
                    iter.remove()
                    listOfNewPoints.add(Point(command.coordinate - (point.x - command.coordinate), point.y))
                }
            }
        } else {
            while (iter.hasNext()){
                val point = iter.next()
                if (point.y > command.coordinate){
                    iter.remove()
                    listOfNewPoints.add(Point(point.x, command.coordinate - (point.y - command.coordinate)))
                }
            }
        }

        points.addAll(listOfNewPoints)
    }

    printField(points)
    println("Finished")
    println("Amount of points: ${points.size}")
}

data class Point(val x: Int, val y: Int)
data class FoldCommand(val axis: String, val coordinate: Int)

fun printField(points: HashSet<Point>){
    val field = Array(40) {Array(40) {"."} }
    for(point in points){
        field[point.x][39-point.y] = "#"
    }

    println("||||||||||||")
    for (i in 0 until 40) {
        print("|")
        for (j in 0 until 40) {
            print(field[i][j])
        }
        print("|\n")
    }
    println("||||||||||||")
}

fun getResourceAsText(path: String): String {
    return object {}.javaClass.getResource(path).readText()
}

