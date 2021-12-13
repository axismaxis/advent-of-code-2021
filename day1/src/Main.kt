import java.io.File


    fun main(args: Array<String>) {
        val text = getResourceAsText("input.txt")

        val commands = ArrayList<Command>()

        text.lines().forEach {
                line -> val lineSplit = line.trimEnd().split(" ").map { it -> it.trim() }.toTypedArray()
                        commands.add(Command(lineSplit[0], lineSplit[1].toInt()))
        }

        var curX = 0;
        var curDepth = 0
        var curAim = 0

        for (command in commands){
            when(command.name){
                "forward" ->    {curX += command.moveAmount
                                curDepth += (curAim * command.moveAmount)}
                "down" -> curAim += command.moveAmount
                "up" -> curAim -= command.moveAmount
            }
            if(curX < 0){
                curX = 0;
            }
        }

        val ans: Long = curX * curDepth * 1L;
        println("Hello")
    }

    data class Command(val name: String, val moveAmount: Int)

    fun getResourceAsText(path: String): String {
        return object {}.javaClass.getResource(path).readText()
    }



