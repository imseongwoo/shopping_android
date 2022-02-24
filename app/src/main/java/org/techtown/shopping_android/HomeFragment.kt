package org.techtown.shopping_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import org.json.JSONObject

class HomeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.add(R.id.container_main,ProductDetailFragment())
            transaction.commit()
        }

        val assetLoader = AssetLoader()
        val homeData= assetLoader.getJsonString(requireContext(),"home.json")
        Log.d("homeData", homeData ?:"")

        if (!homeData.isNullOrEmpty()){

            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")

            //Title 데이터클래스 적용
            val titleValue = Title(text,iconUrl)
            titleValue.text

            val topBanners = jsonObject.getJSONArray("top_banners")
            val firstBanner = topBanners.getJSONObject(0)
            firstBanner.getString("label")
            val produstDetail = firstBanner.getJSONObject("product_detail")
            val price = produstDetail.getInt("price")

            Log.d("title","text=${text}, iconUrl=${iconUrl}")

        }
    }

}