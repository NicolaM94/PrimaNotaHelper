package view

import Logic.DayEntry
import MainView
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class NewMovement :View () {

    val dayInput = SimpleStringProperty()
    val descriptionInput = SimpleStringProperty()
    val codeInput = SimpleStringProperty()
    val outcomeInput = SimpleStringProperty()
    val incomeInput = SimpleStringProperty()

    override val root = form {
        style { baseColor = Color.SLATEBLUE }
        fieldset ("Nuovo movimento") {
            field ("Data") { textfield(dayInput).promptText = "es. 01/01/2021"}
            field ("Descrizione") { textfield (descriptionInput).promptText = "es. Cena di lavoro" }
            field ("Codice") { textfield (codeInput).promptText = "es. PRA" }
            field ("Uscita") { textfield (outcomeInput).promptText = "es. 50.00" }
            field ("Entrata") { textfield (incomeInput).promptText = "es. 0.00" }
        }
        hbox {
            style {
                alignment = Pos.TOP_CENTER
                spacing = Dimension(15.0,Dimension.LinearUnits.px)
            }
            button("Salva") {
                action {
                    val dumper = find<MainView>().entriesList
                    dumper.add(
                        DayEntry(
                            LocalDate.parse(dayInput.value,DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            descriptionInput.value,
                            codeInput.value,
                            outcomeInput.value.toDouble(),
                            incomeInput.value.toDouble()
                        )
                    )
                    close()
                }
            }
            button("Indietro") { action { close() } }
        }
    }
}