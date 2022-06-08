package xyz.xeonds.mirai.phiutil

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

@Serializable
data class Song(
    @SerialName("name") val name: String,
    @SerialName("rank") val rank: List<Double>,
)

class Util {
    private val apiUrl: String = "http://www.jiujiuer.xyz/pages/pgr/index.php"

    fun getRank(name: String = "Igallta"): String {
        val client = OkHttpClient()
        val request = Request.Builder()
            .method("POST", FormBody.Builder().add("name", name).build())
            .url("$apiUrl?api&rank")
            .build()
        return client.newCall(request).execute().body?.string()?:"[]"
    }
}