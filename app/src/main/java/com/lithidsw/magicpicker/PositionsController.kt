package com.lithidsw.magicpicker

import java.util.*

class PositionsController(var mainArray : List<Int>) {

    private val COUNT_FOR_GUESS = 3

    private val leftPositions = listOf(0,3,6,9,12,15,18)
    private val middlePositions = listOf(1,4,7,10,13,16,19)
    private val rightPositions = listOf(2,5,8,11,14,17,20)

    private var positionCount = 0

    fun valueInLeftPosition() : List<Int> {
        val list = arrayListOf<Int>()
        for (i in rightPositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in leftPositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in middlePositions) {
            mainArray[i].let { list.add(it) }
        }

        positionCount += 1

        mainArray = list

        return list
    }

    fun valueInMiddlePosition() : List<Int> {
        val list = arrayListOf<Int>()
        for (i in rightPositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in middlePositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in leftPositions) {
            mainArray[i].let { list.add(it) }
        }

        positionCount += 1

        mainArray = list

        return list
    }

    fun valueInRightPosition() : List<Int> {
        val list = arrayListOf<Int>()
        for (i in middlePositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in rightPositions) {
            mainArray[i].let { list.add(it) }
        }

        for (i in leftPositions) {
            mainArray[i].let { list.add(it) }
        }

        positionCount += 1

        mainArray = list

        return list
    }

    private fun canGuessNumber() : Boolean {
        return positionCount == COUNT_FOR_GUESS
    }

    fun getNumberGuess() : Int? {
        return when(canGuessNumber()) {
            true -> mainArray[10]
            false -> null
        }
    }

    fun shufflePositions() : List<Int> {
        Collections.shuffle(mainArray)
        return mainArray
    }
}