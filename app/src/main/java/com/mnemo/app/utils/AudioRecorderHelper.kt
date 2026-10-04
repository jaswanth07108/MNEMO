package com.mnemo.app.utils

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import java.io.File

class AudioRecorderHelper(private val context: Context) {

    private var mediaRecorder: MediaRecorder? = null
    private var currentOutputFile: File? = null
    var isRecording = false
        private set

    fun startRecording(outputFileName: String = "mnemo_voice_${System.currentTimeMillis()}.m4a"): File? {
        val outputFile = File(context.cacheDir, outputFileName)
        currentOutputFile = outputFile

        mediaRecorder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            @Suppress("DEPRECATION")
            MediaRecorder()
        }.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(outputFile.absolutePath)
            try {
                prepare()
                start()
                isRecording = true
            } catch (e: Exception) {
                e.printStackTrace()
                isRecording = false
                return null
            }
        }
        return outputFile
    }

    fun stopRecording(): File? {
        if (!isRecording) return currentOutputFile
        return try {
            mediaRecorder?.apply {
                stop()
                release()
            }
            mediaRecorder = null
            isRecording = false
            currentOutputFile
        } catch (e: Exception) {
            e.printStackTrace()
            isRecording = false
            null
        }
    }

    fun getSampleTranscripts(): List<String> = listOf(
        "Voice Note: Remember to compare the ASUS ROG Strix laptop for ₹92,000 against the Lenovo Legion at ₹88,000 with RTX 4060 GPU.",
        "Voice Note: Met with team for Puducherry beach trip planning. Booked villa near Rock Beach for ₹4,500 per night.",
        "Voice Note: Final Year Project report submission deadline confirmed for May 15th. Document submitted to HOD.",
        "Voice Note: Saved warranty receipt for iQOO Neo 9 Pro phone purchased for ₹35,999."
    )
}
