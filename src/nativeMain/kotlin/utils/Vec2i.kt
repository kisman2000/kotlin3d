package utils

class Vec2i(
    var x : Int,
    var y : Int
) {
    constructor(
        x : Number,
        y : Number
    ) : this(
        x.toInt(),
        y.toInt()
    )
}