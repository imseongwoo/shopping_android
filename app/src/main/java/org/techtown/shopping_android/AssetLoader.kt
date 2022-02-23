package org.techtown.shopping_android

import android.content.Context

class AssetLoader {

    fun getJsonString(context: Context, fileName: String): String?{
        return kotlin.runCatching {
            loadAsset(context,fileName)
        }.getOrNull()       // 성공하면 값 반환 실패하면 null
    }

    // 아래 함수를 통해 사용자가 얻고자하는 데이터는 json 포맷을 string으로 변환한 값이므로 반환 타입을 string으로 변경
    private fun loadAsset(context: Context, fileName: String): String {
        // assets폴더에 접근하려면 context 객체를 통해야함. 리소스나 데이터베이스같은 시스템 자원에도 접근 가능
        return context.assets?.open(fileName)?.use { inputStream ->
            val size = inputStream.available()  // inputstream의 데이터가 실제로 존재하는지
            val bytes = ByteArray(size)     // inputstream에서 전달받는 타입이 bytearray
            inputStream.read(bytes)
            String(bytes)
            //Log.d("homeData",homeData)
        }!!

    }
}