package com.frank.protean.mode.builder

class MacbookBuilder : Builder {
    private val macbook: Computer = Macbook()
    override fun buildDisplay(disply: String?): Builder {
        macbook.display = disply
        return this
    }

    override fun buildBoard(board: String?): Builder {
        macbook.board = board
        return this
    }

    override fun buildOs(): Builder {
        macbook.setOs()
        return this
    }

    override fun build(): Computer? {
        return macbook
    }

}