//package com.github.kechinvv.libslpluginij
//
//import com.github.kechinvv.libslpluginij.language.LibSL
//import com.github.kechinvv.libslpluginij.language.psi.PsiElementFactory
//import com.intellij.psi.impl.source.tree.LeafPsiElement
//import com.intellij.psi.tree.IElementType
//import com.intellij.testFramework.fixtures.BasePlatformTestCase
//
//class LibSLPSIFileRootTests : BasePlatformTestCase() {
//
//    init {
//        setUp()
//    }
//
//    @Test
//    fun testRootT() {
//        myFixture.configureByFiles("CRC32.automaton.lsl")
//        println(myFixture.file.language)
//    }
//
//    @Test
//    fun testCreate() {
//        PsiElementFactory.INSTANCE.createElement(LeafPsiElement(IElementType("s", LibSL.INSTANCE), "sdfdsf"))
//    }
//
//    override fun getTestDataPath(): String {
//        return "src/test/testData"
//    }
//
//}