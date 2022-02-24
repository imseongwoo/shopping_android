package org.techtown.shopping_android

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
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

        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)



        val assetLoader = AssetLoader()
        val homeData= assetLoader.getJsonString(requireContext(),"home.json")
        Log.d("homeData", homeData ?:"")

        if (!homeData.isNullOrEmpty()){

            val jsonObject = JSONObject(homeData)
            val title = jsonObject.getJSONObject("title")
            val text = title.getString("text")
            val iconUrl = title.getString("icon_url")
            Log.d("iconurl",iconUrl)


            toolbarTitle.text = text
            Glide.with(this)
                .load(iconUrl)
                .error(R.drawable.ic_gnb_back)
                .fallback(R.drawable.ic_tabbar_cart_off)
                .into(toolbarIcon)




        }
    }

}