package com.example.apphoroscopo.data

import com.example.apphoroscopo.data.HoroscopeProvider
import com.example.apphoroscopo.R

class HoroscopeProvider {
    companion object {
        private val horoscopeList = listOf(
            Horoscope(
                "aries",
                R.string.horoscope_name_aries,
                R.string.horoscope_date_aries,
                R.drawable.aries_symbol_svgrepo_com
            )
        )

        fun getAll(): List<Horoscope> {
            return horoscopeList
        }

        fun getById(id: String): Horoscope? {
            return horoscopeList.find {
                it.id == id
            }
        }
    }
}

