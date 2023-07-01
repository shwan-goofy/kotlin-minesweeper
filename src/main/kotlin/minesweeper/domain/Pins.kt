package minesweeper.domain

class Pins(private val values: MutableList<Pin>) {
    fun getPinsSize(): Int {
        return values.size
    }

    fun getPinAt(index: Int): Pin {
        require(index <= getPinsSize()) { "$index 는 올바른 위치가 아닙니다" }
        return values[index]
    }

    fun changeMine(index: Int) {
        require(index <= getPinsSize()) { "$index 는 올바른 위치가 아닙니다" }

        values[index] = MinePin()
    }

    fun addMineNumber(index: Int) {
        require(index <= getPinsSize()) { "$index 는 올바른 위치가 아닙니다" }
        require(values[index] is NormalPin) { "$index 는 지뢰입니다" }

        (values[index] as NormalPin).addsurroundMineNumber()
    }

    companion object {
        fun of(size: GameBoardSize): Pins {
            val totalSize = size.height * size.width
            val values = mutableListOf<Pin>()
            repeat(totalSize) {
                values.add(NormalPin())
            }
            return Pins(values)
        }
    }
}
