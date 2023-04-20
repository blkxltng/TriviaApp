package com.blkxltng.triviaapp.repository

import android.util.Log
import com.blkxltng.triviaapp.data.DataOrException
import com.blkxltng.triviaapp.model.QuestionItem
import com.blkxltng.triviaapp.network.QuestionApi
import javax.inject.Inject

const val TAG = "QuestionRepository"

class QuestionRepository @Inject constructor(
    private val api: QuestionApi) {

    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d(TAG, "getAllQuestions: ${dataOrException.e?.localizedMessage}")
        }
        return dataOrException
    }
}