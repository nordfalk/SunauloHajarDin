package com.example.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.os.Build
import java.util.*

@Suppress("DEPRECATION")
class UpdatedLocaleHelper {

    companion object {
        private const val PREF_NAME = "language"
        private const val SELECTED_LANGUAGE = "selected_language"

        // persists new language code change in preference manager and updates application default locale
        // returns Context having application default locale
        fun setLanguage(context: Context, language: String): Context {
            persist(context, language)
            val locale = getLocale(language)
            Locale.setDefault(locale)
            val configuration = Configuration()
            val displayMetrics = context.resources.displayMetrics
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                configuration.setLocale(locale)
            } else {
                configuration.locale = locale
            }
            context.resources.updateConfiguration(configuration, displayMetrics)
            context.applicationContext.resources.updateConfiguration(configuration, displayMetrics)
            return context
        }

        // persists new language code in preference manager
        private fun persist(context: Context, language: String) {
            val preferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(SELECTED_LANGUAGE, language)
            editor.apply()
        }

        private fun getLocale(splitLocaleCode: String) =
            if (splitLocaleCode.contains("_")) {
                val arg = splitLocaleCode.split("_".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                Locale(arg[0], arg[1])
            } else {
                Locale(splitLocaleCode)
            }

        fun getCurrentLang(context: Context): String? =
            context.getSharedPreferences(PREF_NAME, MODE_PRIVATE).getString(SELECTED_LANGUAGE, null)
    }
}