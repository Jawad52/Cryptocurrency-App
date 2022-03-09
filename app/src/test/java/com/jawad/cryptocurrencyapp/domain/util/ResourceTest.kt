package com.jawad.cryptocurrencyapp.domain.util


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ResourceTest {

    @Test
    fun `Resource should return wrong success type`() {
        val result = Resource.Success(DUMMY_DATA)
        assertThat(result).isNotInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `Resource should return same instance for success type`() {
        val result = Resource.Success(DUMMY_DATA)
        assertThat(result).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `Resource should return same instance for error type with different error message`() {
        val result = Resource.Error<String>(DUMMY_DATA_NETWORK_ERROR)
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.data).isNotEqualTo(DUMMY_DATA_ERROR)
    }

    @Test
    fun `Resource should return same instance for error type with same error message`() {
        val result = Resource.Error<String>(DUMMY_DATA_NETWORK_ERROR)
        assertThat(result).isInstanceOf(Resource.Error::class.java)
        assertThat(result.data).isNotEqualTo(DUMMY_DATA_NETWORK_ERROR)
    }

    companion object {
        private const val DUMMY_DATA = "data"
        private const val DUMMY_DATA_ERROR = "Error data"
        private const val DUMMY_DATA_NETWORK_ERROR = "No network available"
    }
}