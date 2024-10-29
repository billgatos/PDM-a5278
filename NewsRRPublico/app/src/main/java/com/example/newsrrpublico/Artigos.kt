package com.example.newsrrpublico

import org.json.JSONObject
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.Date


fun String.encodeURL() : String{
    return  URLEncoder.encode(this, "UTF-8")
}


fun String.toDate(): Date {
    //"2024-10-20T17:30:00Z"
    //"2024-10-28T18:25:51+00:00"
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return dateFormat.parse(this)
}

fun Date.toStringDate(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd")
    return dateFormat.format(this)
}

data class Artigo(
    //val id: Long,
    var titulo: String?,
    //val tituloNoticia: String,
    var descricao: String?,
    var url: String,
    //val multimediaPrincipal: String,
    //val rubrica: String,
    //val tipo: String,
    var urlToImage: String?,
    var data: Date?
    //val autores: List<Author>,
    //val tags: List<Tag>,

){
    companion object{

        fun fromJson(articleObject: JSONObject):Artigo {
            val title = articleObject.getString("titulo")
            val description = articleObject.getString("descricao")
            val url = articleObject.getString("url")
            val urlToImage = articleObject.getString("multimediaPrincipal")
            val publishedAt = articleObject.getString("data").toDate()
            return Artigo(title, description, url, urlToImage, publishedAt)
        }
    }

}

