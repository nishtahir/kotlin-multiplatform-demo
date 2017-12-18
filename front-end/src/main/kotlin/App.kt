package app

import com.nishtahir.multiplatform.Issue
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import org.w3c.fetch.Response
import react.*
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window

class App : RComponent<RProps, RState>() {

    override fun RBuilder.render() {
        div("mdl-grid") {
            issues()
        }
    }
}

class IssueCard : RComponent<RProps, IssueState>() {

    init {
        state = IssueState()
    }

    /**
     * Render must always return at least one valid react element
     */
    override fun RBuilder.render() {
        state.items.takeIf { !it.isEmpty() }?.onEach { issue ->
            div("mdl-cell mdl-cell--4-col demo-card-wide mdl-card mdl-shadow--2dp") {
                div("mdl-card__title") {
                    h2("mdl-card__title-text") {
                        +issue.title
                    }
                }
                div("chip-container") {
                    span("mdl-chip") {
                        span("mdl-chip__text") {
                            +issue.severity.name
                        }
                    }
                }
                div("mdl-card__supporting-text") {
                    +issue.description
                }
                div("mdl-card__actions mdl-card--border") {
                    a(classes = "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect") {
                        +"Close"
                    }
                    a(classes = "mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect") {
                        +"Delete"
                    }
                }
            }
        } ?: div { }

        document.getElementById("refresh-link")?.apply {
            addEventListener("click", { event ->
                event.preventDefault()
                window.fetch("/issues")
                        .then(onFulfilled = { response: Response ->
                            response.text().then {
                                val list = JSON.parse(Issue::class.serializer().list, it)
                                list.forEach(::println)
                                setState {
                                    items = list
                                }
                            }
                        }, onRejected = { throwable: Throwable -> println(throwable) })
            })
        }

    }
}

class IssueState(var items: List<Issue> = emptyList()) : RState

fun RBuilder.issues() = child(IssueCard::class) {
}

fun RBuilder.app() = child(App::class) {}
