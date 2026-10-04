package com.example.audio

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class KidAudioEngine(context: Context) : TextToSpeech.OnInitListener {

    private val appContext = context.applicationContext
    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    private var toneGenerator: ToneGenerator? = null
    private val audioScope = CoroutineScope(Dispatchers.Default)

    var voiceEnabled: Boolean = true
    var soundEffectsEnabled: Boolean = true
    var currentLanguageCode: String = "en" // "en", "hi", "hinglish"

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (e: Exception) {
            Log.w("KidAudioEngine", "ToneGenerator could not be created", e)
        }
        try {
            tts = TextToSpeech(appContext, this)
        } catch (e: Exception) {
            Log.w("KidAudioEngine", "TTS could not be started", e)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            isTtsReady = true
            updateTtsLocale()
            tts?.setPitch(1.25f) // Warm, friendly, higher pitch suited for children
            tts?.setSpeechRate(0.88f) // Clear, slightly slower paced for toddlers
        }
    }

    fun setLanguage(langCode: String) {
        currentLanguageCode = langCode
        updateTtsLocale()
    }

    private fun updateTtsLocale() {
        if (!isTtsReady || tts == null) return
        val targetLocale = when (currentLanguageCode) {
            "hi" -> Locale("hi", "IN")
            else -> Locale.ENGLISH
        }
        val result = tts?.setLanguage(targetLocale)
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            // Fallback to English if Hindi voice packs aren't pre-installed on this device
            tts?.setLanguage(Locale.ENGLISH)
        }
    }

    fun speak(text: String, onSpeechStart: (() -> Unit)? = null) {
        if (!voiceEnabled || !isTtsReady || tts == null) return
        try {
            tts?.stop()
            onSpeechStart?.invoke()
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "KID_UTTERANCE_${System.currentTimeMillis()}")
        } catch (e: Exception) {
            Log.w("KidAudioEngine", "Speech failed", e)
        }
    }

    fun stopSpeaking() {
        try {
            tts?.stop()
        } catch (e: Exception) {
            // ignore
        }
    }

    // --- Procedural Kid Sound Effects ---

    fun playTap() {
        if (!soundEffectsEnabled) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 30)
        } catch (e: Exception) {
            // ignore
        }
    }

    fun playSparkle() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, 50)
                delay(60)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_PROMPT, 60)
                delay(70)
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE, 80)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun playWaterDrop() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_PIP, 40)
                delay(90)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 60)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun playHeartbeat() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                // Lub-Dub pulse
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 80)
                delay(120)
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 100)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun playCelebration() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                // Musical arpeggio cheer
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_1, 80)
                delay(90)
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_3, 80)
                delay(90)
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_5, 80)
                delay(90)
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_8, 160)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun playSirenChirp() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK, 120)
                delay(140)
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK, 120)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun playBell() {
        if (!soundEffectsEnabled) return
        try {
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_PROMPT, 150)
        } catch (e: Exception) {
            // ignore
        }
    }

    fun playCuriousHmm() {
        if (!soundEffectsEnabled) return
        audioScope.launch {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_4, 70)
                delay(80)
                toneGenerator?.startTone(ToneGenerator.TONE_DTMF_6, 90)
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    fun release() {
        try {
            tts?.stop()
            tts?.shutdown()
            toneGenerator?.release()
        } catch (e: Exception) {
            // ignore
        }
    }
}
