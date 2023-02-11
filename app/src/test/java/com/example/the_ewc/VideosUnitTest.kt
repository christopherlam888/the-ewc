package com.example.the_ewc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class VideosUnitTest {
    @Test
    fun reverse_isCorrect() {
        val videos = arrayListOf<Video>(Video("first", "first_thumbnail", "first_url"), Video("second", "second_thumbnail", "second_url"))
        videos.reverse()
        assertEquals("second", videos[0].title)
    }
}