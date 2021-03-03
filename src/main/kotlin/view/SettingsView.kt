package view

import MainView
import tornadofx.*

class SettingsView :View () {
    override val root = form {
        fieldset("Impostazioni") {
            field("Indirizzo email") { textfield().promptText = "indirizzo@email.com"}
            field("Password") { passwordfield().promptText = "******"}
            field ("Server SMTP") {textfield().promptText = "smtp.gmail.com"}
            field ("Porta SMTP") {textfield().promptText = "456"}
            field ("Destinatario") {textfield().promptText = "destinatario@gmail.com"}
            button ("Indietro") {action { replaceWith<MainView>() }}
        }
    }
}