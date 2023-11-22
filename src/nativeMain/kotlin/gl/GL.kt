package gl

import Framebuffer
import utils.ALMOST_ZERO
import utils.Vec2i

class GL {
    companion object {
        private var BOUND_FBO : Framebuffer? = null

        fun bindFramebuffer(
            framebuffer : Framebuffer?
        ) {
            BOUND_FBO = framebuffer
        }

        fun line(
            from : Vec2i,
            to : Vec2i,
            color : Int
        ) {
            if(BOUND_FBO != null) {
                val k = (to.y - from.y) / (to.x - from.x + ALMOST_ZERO)
                val b = to.y - from.y

                for(x in from.x..to.x) {
                    val relativeX = x - from.x
                    val relativeY = (k * relativeX + b).toInt()
                    val y = relativeY + from.y
                    /*val prevY = (k * (relativeX - 1) + b).toInt()
                    val diffY = y - prevY

                    if(diffY > 1) {
                        for(relativeDiffedY in 0..diffY) {
                            val roundedX
                        }
                    }*/

                    BOUND_FBO!!.write(x, y, color)
                }
            }
        }

        /*fun polygon(
            pos1 : Vec2i,
            pos2 : Vec2i,
            pos3 : Vec2i,
            color : Int,
            fill : Boolean,
            outline : Boolean
        ) {
            val side1 = hypotenuse(pos2.x - pos3.x, pos2.y - pos3.y)
            val side2 = hypotenuse(pos1.x - pos3.x, pos1.y - pos3.y)
            val side3 = hypotenuse(pos1.x - pos2.x, pos1.y - pos2.y)
            //TODO: hypotenuse
            val biggestSide = max(side1, max(side2, side3))
            val leftSide = if(biggestSide == side1) side2 else if(biggestSide == side2) side3 else side1
            val rightSide = if(biggestSide == side1) side3 else if(biggestSide == side2) side1 else side2
            val leftSideProjection = (leftSide * leftSide) / biggestSide
            val rightSideProjection = (rightSide * rightSide) / biggestSide
            val height = sqrt(leftSideProjection * rightSideProjection)
            val H =
        }*/
    }
}