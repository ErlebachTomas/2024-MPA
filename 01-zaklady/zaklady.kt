// =========================================================
// Základy programování v Kotlinu
// =========================================================

// Tento program demonstruje základní konstrukce jazyka Kotlin,
// včetně proměnných, typové konverze, vstupu a výstupu,
// podmínkových výrazů, cyklů a práce s kolekcemi.

package com.example.ukazka

// Pokud používáte online nástroj https://play.kotlinlang.org/,
// je potřeba definovat vlastní funkci readln() pro simulaci vstupu.
// Pokud používáte Android Studio nebo jiná vývojová prostředí,
// funkce readln() je již k dispozici a není potřeba ji definovat.

/*
val input = mutableListOf("první vstup", "druhý vstup")
fun readln(): String {
    val line = input.first()
    input.removeAt(0)
    return line
}
*/

fun main() {

    // -----------------------------------------
    // 1. Proměnné a datové typy
    // -----------------------------------------

    // Proměnná, kterou lze měnit
    var promennaMenitelna: Int = 10
    promennaMenitelna = 20  // Hodnotu lze změnit

    // Neměnná proměnná (konstanta)
    val konstantaNemennitelna: String = "Toto je konstanta"
    // konstantaNemennitelna = "Nová hodnota"  // Toto by způsobilo chybu

    // Typy lze odvodit automaticky
    var automatickyTyp = 30  // Kotlin odvodí, že jde o Int

    // -----------------------------------------
    // 2. Typová konverze
    // -----------------------------------------

    val celeCislo: Int = 10
    val desetinneCislo: Double = celeCislo.toDouble()  // Převod na Double
    println("Převedené číslo na Double: $desetinneCislo")  // Výstup: 10.0

    // -----------------------------------------
    // 3. Vstup od uživatele
    // -----------------------------------------

    println("Zadejte své jméno:")
    val jmeno = readln()  // Čte vstup od uživatele
    println("Ahoj, $jmeno!")

    // -----------------------------------------
    // 4. Podmínkové výrazy (if-else)
    // -----------------------------------------

    if (jmeno.length > 5) {
        println("Máte dlouhé jméno!")
    } else if (jmeno.isNotEmpty()) {
        println("Máte kratší jméno!")
    } else {
        println("Jméno nebylo zadáno.")
    }

    // -----------------------------------------
    // 5. Výraz when
    // -----------------------------------------

    val denVTydnu = 3

    when (denVTydnu) {
        1 -> println("Pondělí")
        2 -> println("Úterý")
        3 -> println("Středa")
        4 -> println("Čtvrtek")
        5 -> println("Pátek")
        6, 7 -> println("Víkend")  // Pro hodnoty 6 a 7
        else -> println("Neplatný den v týdnu")
    }

    // -----------------------------------------
    // 6. Cykly
    // -----------------------------------------

    // For cyklus - iterace v rozsahu
    for (i in 1..5) {
        println("Cyklus číslo $i")
    }

    // For cyklus - iterace přes pole
    val cisla = arrayOf(1, 2, 3, 4, 5)
    for (cislo in cisla) {
        println("Číslo z pole: $cislo")
    }

    // While cyklus
    var i = 0
    while (i < 5) {
        println("Hodnota i ve while cyklu: $i")
        i++
    }

    // Do-while cyklus
    var j = 0
    do {
        println("Hodnota j v do-while cyklu: $j")
        j++
    } while (j < 5)

    // -----------------------------------------
    // 7. Náhodná čísla
    // -----------------------------------------

    val nahodneCislo = (0..10).random()
    println("Náhodné číslo od 0 do 10: $nahodneCislo")
}