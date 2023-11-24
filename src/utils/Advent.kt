package utils

import io.github.cdimascio.dotenv.dotenv
import java.io.File
import java.io.FileNotFoundException
import java.net.HttpURLConnection
import java.net.URL
import java.time.Year

/**
 * Utilizes the `day` parameter (and optionally the `year` parameter) to
 * retrieve local input data if available; otherwise, it requests,
 * saves, and returns the input data from AdventOfCode.com.
 *
 * @param day Day as integer (min: `1`, max: `31`)
 * @param year Year as integer (format: `2023`, default: current year)
 * @param targetDir Path as string (default: `src/y$year/data`)
 * @param format Format as string (default: `day$day`)
 * @param extension Extension as string (default: `txt`)
 *
 * @return `List<String>`
 * ---
 * @author Max van Essen
 * @see <a href="https://github.com/vanEssenMax/advent-of-code">★</a> <a href="https://adventofcode.com">AdventOfCode.com</a>
 */
class Advent(
    private val day: Int,
    private val year: Int = Year.now().value,
    private val targetDir: String = "src/y$year/data",
    private val format: String = "Day$day",
    private val extension: String = "txt",
) {
    fun data(): List<String> {
        val file = File(targetDir, "$format.$extension")

        // Check if input already exists, if so don't import
        if (!file.exists()) {
            println("\uD83C\uDF84\u001b[38;5;220mDay $day (y$year) missing, downloading from AdventOfCode.com...\u001B[0m")
            // Create directories if none exist
            File(file.parent).mkdirs()

            // Get day specific input data from AdventOfCode.com
            try {
                val url = URL("https://adventofcode.com/$year/day/$day/input")
                with(url.openConnection() as HttpURLConnection) {
                    // Authenticate using session token in .env file
                    setRequestProperty("Cookie", "session=${dotenv().get("SESSION_TOKEN")}")
                    inputStream.bufferedReader().use {
                        file.writeText(it.readText())
                    }
                }
                println("\uD83C\uDF84\u001b[32mDay $day (y$year) saved!\u001b[0m\n")
            } catch (_: Exception) {
                throw Exception("Could not get input for Day $day (y$year) from AdventOfCode.com")
            }
        }
        return file.readLines()
    }

    fun data(suffix: String): List<String> {
        val file = File(targetDir, "$format${suffix}.$extension")

        if (!file.exists())
            throw FileNotFoundException("Could not find ${file.path}")

        return file.readLines()
    }
}
