import kotlinx.cinterop.*
import kotlinx.cinterop.nativeHeap.alloc
import kotlinx.cinterop.set
import libminifb.*
import platform.linux.malloc
import platform.posix.alloca

@OptIn(ExperimentalForeignApi::class)
fun main() {
    var window = mfb_open_ex("Kotlin/Native Window", 800.toUInt(), 600.toUInt(), WF_RESIZABLE)

    if(window != null) {
        val buffer = alloca((800 * 600 * 4).toULong()) as CPointer<UIntVarOf<UInt>>
        do {
            val state : mfb_update_state

            buffer[0] = (-1).toUInt()
            buffer[10] = (-1).toUInt()
            buffer[20] = (-1).toUInt()
            buffer[30] = (-1).toUInt()

            buffer[0 + 800] = (-1).toUInt()
            buffer[10 + 800 * 10] = (-1).toUInt()
            buffer[20 + 800 * 10] = (-1).toUInt()
            buffer[30 + 800 * 10] = (-1).toUInt()

            for(relativeX in 0..100) {
                for(relativeY in 0..100) {
                    val x = relativeX + 100
                    val y = relativeY + 100

                    val index = x + y * 800

//                    val ptr = malloc(1.toULong())!!
//                    interpretPointed<UIntVarOf<UInt>>(ptr.rawValue).value = (-1).toUInt()

                    buffer[index] = (-1).toUInt()
                }
            }

            //TODO: render thing

            state = mfb_update_ex(window, buffer, 800.toUInt(), 600.toUInt())

            if(state < 0) {
                window = null
                break
            }
        } while(mfb_wait_sync(window))
    }
}