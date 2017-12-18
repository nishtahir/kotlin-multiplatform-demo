package app

import com.nishtahir.multiplatform.Issue
import kotlinx.html.InputType
import kotlinx.html.id
import kotlinx.serialization.json.JSON
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import onClick
import org.w3c.dom.Element
import org.w3c.fetch.Response
import react.*
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window

class App : RComponent<RProps, AppState>() {

    init {
        state = AppState(Screen.ISSUES)
    }

    override fun RBuilder.render() {
        println("State - ${state.screen}")
        when (state.screen) {
            Screen.ISSUES -> renderIssues(this)
            Screen.CREATE -> renderCreate(this)
        }

        document.getElementById("create-link")?.apply {
            onClick(preventDefault = true) {
                setState {
                    screen = Screen.CREATE
                }
            }
        }
    }

    override fun componentDidUpdate(prevProps: RProps, prevState: AppState) {
        // super.componentDidUpdate(prevProps, prevState)
        // README: Calling super on any of the react lifecycle method doesn't work. Don't do this ^
        MdlComponentHandler.upgradeDom() // you want to do this on the root element
    }

    /**
     *
    <form action="#">
    <div class="mdl-textfield mdl-js-textfield">
    <input class="mdl-textfield__input" type="text" id="sample1">
    <label class="mdl-textfield__label" for="sample1">Text...</label>
    </div>
    </form>

     */
    private fun renderCreate(rBuilder: RBuilder) = with(rBuilder) {
        div("mdl-cell mdl-cell--4-col") {
            form(action = "#") {
                div("mdl-textfield mdl-js-textfield") {
                    input(classes = "mdl-textfield__input", type = InputType.text) {
                        attrs {
                            id = "fname"
                        }
                    }
                    label("mdl-textfield__label") {
                        attrs {
                            htmlFor = "fname"
                        }
                        +"Title"
                    }
                }
            }

            button(classes = "mdl-button mdl-js-button mdl-button--raised") {
                +"Submit"
            }
        }
    }

    private fun renderIssues(rBuilder: RBuilder) = with(rBuilder) {
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
            onClick(preventDefault = true) {
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
            }
        }
    }
}

class IssueState(var items: List<Issue> = emptyList()) : RState

class AppState(var screen: Screen) : RState

enum class Screen {
    ISSUES,
    CREATE
}

fun RBuilder.issues() = child(IssueCard::class) {
}

fun RBuilder.app() = child(App::class) {}


/**
 * Provided by Material design lite
 * https://getmdl.io/started/index.html#dynamic
 */
@JsName("componentHandler")
external class MdlComponentHandler {
    companion object {
        fun upgradeDom(): dynamic = definedExternally
    }
}
