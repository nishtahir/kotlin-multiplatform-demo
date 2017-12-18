import org.w3c.dom.Element
import org.w3c.dom.events.Event

inline fun Element.onClick(preventDefault: Boolean = false, crossinline listener: (Event) -> Unit) {
    addEventListener("click", { event: Event ->
        if(preventDefault) { event.preventDefault() }
        listener(event)
    })
}