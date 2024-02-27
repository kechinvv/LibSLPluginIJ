package com.github.kechinvv.libslpluginij

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.jupiter.api.Test

class LibSLPSIFileRootTests : BasePlatformTestCase() {

    init {
        setUp()
    }

    @Test
    fun testRootT() {
        myFixture.configureByFiles("CRC32.automaton.lsl")
        println(myFixture.file.language)
    }

    override fun getTestDataPath(): String {
        return "src/test/testData"
    }

}