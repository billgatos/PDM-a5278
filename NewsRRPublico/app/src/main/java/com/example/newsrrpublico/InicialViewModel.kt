package com.example.newsrrpublico

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject


data class ArticleState (
    val articles : ArrayList<Artigo> = arrayListOf<Artigo>(),
    val isLoading  : Boolean = false,
    val errorMessage: String = "",
)

class InicialViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticleState())
    val uiState: StateFlow<ArticleState> = _uiState.asStateFlow()


    fun fetchArticles() {
        _uiState.value = ArticleState(
            isLoading = true
        )
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://www.publico.pt/api/list/ultimas")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _uiState.value = ArticleState(
                    errorMessage = e.message ?: "",
                    isLoading = false
                )
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var articlesResult = arrayListOf<Artigo>()
                    val result = response.body!!.string()

                    //val jsonObject = JSONObject(result)
                    //val status = "ok" //jsonObject.getString("status")
                    //if (status == "ok") {
                        val articlesArray = JSONArray(result)
                        for (index in 0 until articlesArray.length()) {
                            val articleObject = articlesArray.getJSONObject(index)
                            val article = Artigo.fromJson(articleObject)
                            articlesResult.add(article)
                            //println(article.urlToImage)
                        }
                        _uiState.value = ArticleState(
                            articles = articlesResult,
                            isLoading = false
                        )
                    //}
                }
            }
        })
    }
}