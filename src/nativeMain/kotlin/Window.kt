import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import libminifb.mfb_open_ex
import libminifb.mfb_update_ex

@OptIn(ExperimentalForeignApi::class)
class Window {
    var width : Int = -1
    var height : Int = -1
    var window : CPointer<cnames.structs.mfb_window>? = null
    var framebuffer : Framebuffer? = null

    fun open(
        title : String,
        width : Int,
        height : Int,
        flags : UInt
    ) {
        this.width = width
        this.height = height

        window = mfb_open_ex(title, width.toUInt(), height.toUInt(), flags)
        framebuffer = Framebuffer.Simple(width, height)
    }

    fun draw() = if(window != null) {
        val state = mfb_update_ex(window, framebuffer!!.buffer!!, width.toUInt(), height.toUInt())

        state
    } else {
        -1
        //TODO: println("Frame is not opened") or something idk
    }

    fun close() {
        //TODO
    }
}