@file:OptIn(ExperimentalForeignApi::class)

import gl.GL
import kotlinx.cinterop.*
import libminifb.*
import utils.Vec2i

@OptIn(ExperimentalForeignApi::class)
fun main() {
    val width = 800
    val height = 600
    val window = Window()

    window.open("kotlin3d window", width, height, WF_RESIZABLE)

    do {
        window.framebuffer!!.bind()
        window.framebuffer!!.clear()

        GL.line(Vec2i(100, 100), Vec2i(300, 200), -1)

//        window.draw()

        val state = window.draw()//mfb_update_ex(window.window, window.framebuffer!!.buffer!!, window.width.toUInt(), window.height.toUInt())

//        window.framebuffer!!.clear()

        if(state < 0) {
            break
        }
    } while(mfb_wait_sync(window.window))
}