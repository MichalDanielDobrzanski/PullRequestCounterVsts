import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

/**
 * https://docs.microsoft.com/en-us/azure/devops/integrate/get-started/authentication/pats?view=azure-devops
 * https://docs.microsoft.com/pl-pl/rest/api/azure/devops/?view=azure-devops-rest-5.0&viewFallbackFrom=azure-devops
 * https://docs.microsoft.com/en-us/azure/devops/integrate/how-to/call-rest-api?view=azure-devops
 * https://www.json2kotlin.com/results.php
 */
fun main() {
    println("Hello")

    // from Keys.kt
    val encToken = window.btoa(":$token")

    with(XMLHttpRequest()) {
        onload = {
            if (status == 200.toShort()) {
                val res = JSON.parse<Json4Kotlin_Base>(responseText)
                setTextForElement("counter", res.count)

                res.value
                        .sortedWith(compareBy { getDiff(it.creationDate) })
                        .reversed()
                        .forEach {
                            appendRow(getTimespan(it), it.createdBy.displayName, it.title)
                        }
            }
        }
        open("GET", "https://dev.azure.com/dgsit/remote_control_android/_apis/git/pullrequests?api-version=5.0")
        setRequestHeader("Authorization", "Basic $encToken")
        setRequestHeader("Content-Type", "application/json")
        send()
    }
}

private fun appendRow(date: String, author: String, title: String) {
    val row = document.getElementById("pull-requests").asDynamic().insertRow(-1)
    arrayOf(date, author, title).forEachIndexed { index, value ->
        val cell = row.insertCell(index)
        val text = document.createTextNode(value)
        cell.appendChild(text)
    }
}

private fun getDiff(creationDate: String): Double = Date.now() - Date.parse(creationDate)

private fun getTimespan(value: Value): String {
    val diff = Date.now() - Date.parse(value.creationDate)
    val seconds = diff / 1000
    val min = seconds / 60
    val hours = (min / 60).toInt()
    val minInHour = (min % 60).toInt()
    return if (hours < 24) {
        "${hours}h ${minInHour}min"
    } else {
        val days = hours / 24
        "$days days"
    }
}

private fun setTextForElement(element: String, value: Any) {
    document.getElementById(element)?.innerHTML = value.toString()
}