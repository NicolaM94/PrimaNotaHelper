import Logic.DayEntry
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import view.NewMovement
import view.ResumeView
import view.SettingsView

class MainView :View () {

    val entriesList = mutableListOf<DayEntry>().asObservable()


    override val root = borderpane () {
        title = "PrimaNotaEasy"
        style { baseColor = Color.LIGHTCYAN }

        center = tableview (entriesList){

            readonlyColumn("Data",DayEntry::dateOutcome)
            readonlyColumn("Descrizione",DayEntry::description)
            readonlyColumn("Codice",DayEntry::code)
            readonlyColumn("Uscite",DayEntry::outcome)
            readonlyColumn("Entrate",DayEntry::income)

            contextmenu {
                item ("Elimina").action {
                    entriesList.removeIf {
                        it == selectedItem
                    }
                }
            }
        }
        right = vbox {
            style {
                alignment = Pos.TOP_LEFT
                baseColor = Color.SLATEBLUE
                this.spacing = Dimension(10.0,Dimension.LinearUnits.px)
                this.padding = CssBox (
                    Dimension(10.0,Dimension.LinearUnits.px),
                    Dimension(10.0,Dimension.LinearUnits.px),
                    Dimension(10.0,Dimension.LinearUnits.px),
                    Dimension(10.0,Dimension.LinearUnits.px)
                )
            }
            button ("Aggiungi movimento") {
                action { openInternalWindow<NewMovement>() }
            }
            button ("Resoconto movimenti") {
                action {
                    val toChange = find(MainView::class)
                    toChange.replaceWith<ResumeView>()
                }
            }
            button ("Invia resoconto"){ action {

                }
            }
            button ("Impostazioni") { action {
                val toChange = find(MainView::class)
                toChange.replaceWith<SettingsView>()
            } }
            button ("Guida"){ }
            button ("Contatti") { }
            }
        }
    }