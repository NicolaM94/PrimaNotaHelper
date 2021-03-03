package Logic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DayEntry (
    val date :LocalDate,
    val description: String,
    val code: String,
    val outcome :Double = 0.00,
    val income :Double = 0.00
        ) {

    val dateOutcome = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}