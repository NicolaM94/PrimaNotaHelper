package view

import Logic.Packer
import MainView
import javafx.geometry.Pos
import javafx.scene.chart.CategoryAxis
import javafx.scene.chart.NumberAxis
import javafx.scene.paint.Color
import tornadofx.*
import kotlin.math.round

class ResumeView :View() {

    val totals :MutableMap<String,Double> = mutableMapOf()
    val observableTotals :MutableList<Packer> = mutableListOf()
    var result = 0.0

    init {
        val database = find(MainView::class).entriesList
        for (entry in database) {
            if (entry.code in totals.keys) {
                totals[entry.code] =+ entry.outcome
            } else {
                totals[entry.code] = entry.outcome
            }
        }
        println(totals)

        for (el in totals) {
            observableTotals.add(Packer(el.key,el.value))
            result += el.value
        }

    }

    override val root = borderpane {

        style { baseColor = Color.LIGHTBLUE }

        left = tableview ( observableTotals.asObservable() ) {

            readonlyColumn("Codice",Packer::code)
            readonlyColumn("Totale",Packer::outCome)

        }

        center = vbox {
            spacing = 25.0
            piechart ("Composizione dei costi"){
                for (entry in totals) {
                    data("${entry.key} (${entry.value/result})",entry.value)
                }
            }
            barchart("Comparazione dei costi",NumberAxis(),CategoryAxis()) {
                for (entry in totals) {
                    series(entry.key) {data(entry.value,"")}
                }

            }
        }

        bottom = hbox {
            style {
                alignment = Pos.CENTER
            }
            button ("Indietro"){
                style {
                    baseColor = Color.SALMON
                    this.maxWidth = Dimension(100.0,Dimension.LinearUnits.px)

                }
                action {
                    val tochange = find(ResumeView::class)
                    tochange.replaceWith<MainView>()
                }
            }
        }

    }

}