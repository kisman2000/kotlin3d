import gl.GL
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UIntVarOf
import kotlinx.cinterop.set
import platform.linux.calloc
import platform.linux.free
import platform.linux.malloc

@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalForeignApi::class)
open class Framebuffer {
    private var prevWidth = -1
    private var prevHeight = -1

    var buffer : CPointer<UIntVarOf<UInt>>? = null

    fun resize(
        width : Int,
        height : Int
    ) {
        if(width != prevWidth || height != prevHeight) {
            buffer = malloc((width * height).toULong())!! as CPointer<UIntVarOf<UInt>>
            prevWidth = width
            prevHeight = height
        }
    }

    fun clear() {
        if(prevWidth != -1 && prevHeight != -1) {
            //TODO: clearing buffer
//            free(buffer)
//            buffer = malloc((prevWidth * prevHeight).toULong())!! as CPointer<UIntVarOf<UInt>>
        } else {
            //TODO: println("Cannot clear empty fbo") or something idk
        }
    }

    fun bind() {
        GL.bindFramebuffer(this)
    }

    fun unbind() {
        GL.bindFramebuffer(null)
    }

    fun write(
        x : Int,
        y : Int,
        color : Int
    ) {
        buffer!![x + y * prevWidth] = color.toUInt()
    }

    class Simple(
        width : Int,
        height : Int
    ) : Framebuffer() {
        init {
            resize(width, height)
        }
    }
}