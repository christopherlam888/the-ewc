package com.example.the_ewc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GlossaryUnitTest {
    @Test
    fun brand_filter_isCorrect() {
        val glossary= arrayListOf<Term>(Term("first", "first_definition", "brand"), Term("second", "second_definition", "general"), Term("third", "third_definition", "general"))
        val selected = glossary.filter { it.category == "brand" } as ArrayList<Term>
        assertEquals(1, selected.size)
    }
    @Test
    fun general_filter_isCorrect() {
        val glossary= arrayListOf<Term>(Term("first", "first_definition", "brand"), Term("second", "second_definition", "general"), Term("third", "third_definition", "general"))
        val selected = glossary.filter { it.category == "general" } as ArrayList<Term>
        assertEquals(2, selected.size)
    }
}