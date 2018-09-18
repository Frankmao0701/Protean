package com.frank.protean.mode.builder

interface Builder {
    fun buildDisplay(disply: String?):Builder
    fun buildBoard(board: String?):Builder
    fun buildOs():Builder
    fun build(): Computer?
}