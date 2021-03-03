import Logic.DayEntry
import javafx.stage.Stage
import tornadofx.App
import tornadofx.find
import tornadofx.launch
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Engine :App(MainView::class) {

    init {
        try {
            with(File("${System.getProperty("user.home")}/PrimaNotaHelper/log.csv")){
                this.readLines().forEach {
                    var substring = it.split(",")
                    val attacher = find<MainView>().entriesList
                    attacher.add(DayEntry(LocalDate.parse(substring[0],DateTimeFormatter.ofPattern("dd/MM/yyyy")),substring[1],substring[2],substring[3].toDouble(),substring[4].toDouble()))
                }
            }
        } catch (error: FileNotFoundException) {
            println("Error: $error")
            if (File("${System.getProperty("user.home")}/PrimaNotaHelper/").exists()) {
                File("${System.getProperty("user.home")}/PrimaNotaHelper/log.csv").createNewFile()
                println("Created log file in File(\"${System.getProperty("user.home")}/PrimaNotaHelper/log.csv\")")
            } else {
                File("${System.getProperty("user.home")}/PrimaNotaHelper/").mkdir()
                File("${System.getProperty("user.home")}/PrimaNotaHelper/log.csv").createNewFile()
                println("Created dir at ${System.getProperty("user.home")}/PrimaNotaHelper/")
                println("Created log file")
            }
        }
    }

    override fun start(stage: Stage) {
        with(stage) {
            this.width = 1280.0
            this.height = 720.0
        }
        super.start(stage)
    }

    override fun stop() {
        with(File("${System.getProperty("user.home")}/PrimaNotaHelper/log.csv")) {
            this.delete()
            find(MainView::class).entriesList.forEach {
                this.appendText("${it.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))},${it.description},${it.code},${it.outcome},${it.income}\n")
            }
        }
        super.stop()
    }

}

fun main() {
    launch<Engine>()
}