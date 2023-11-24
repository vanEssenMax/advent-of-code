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
 * @param prefix Prefix as string (default: `null`)
 * @param suffix Suffix as string (default: `null`)
 *
 * @return `List<String>`
 * ---
 * @author Max van Essen
 * @see <a href="https://github.com/vanEssenMax/advent-of-code">★</a> <a href="https://adventofcode.com">AdventOfCode.com</a>
 */
fun get(
    day: Int,
    year: Int = Year.now().value,
    targetDir: String = "src/y$year/data",
    format: String = "Day$day",
    extension: String = "txt",
    prefix: String? = null,
    suffix: String? = null,
): List<String> {
    val file = File(targetDir, "${prefix.orEmpty()}$format${suffix.orEmpty()}.$extension")


    // Check if input already exists, if so don't import
    if (!file.exists()) {
        // If suffix or prefix was used while locating the file throw an error
        if (!suffix.isNullOrEmpty() || !prefix.isNullOrEmpty()) {
            throw FileNotFoundException("Could not find ${file.path}")
        }

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
