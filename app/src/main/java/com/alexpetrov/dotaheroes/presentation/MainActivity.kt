package com.alexpetrov.dotaheroes.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexpetrov.dotaheroes.data.HeroModel
import com.alexpetrov.dotaheroes.databinding.ActivityMainBinding
import com.alexpetrov.dotaheroes.domain.Listener
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity(), Listener {

    private lateinit var binding: ActivityMainBinding
    private val urlHeroInfo = "https://api.opendota.com/api/heroStats"
    private val okHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getHeroInfo()
        while (heroInfo.isEmpty()) {
            continue
        }

        initRecycler()
    }

    private fun getHeroInfo() {
        val request = Request.Builder()
            .url(urlHeroInfo)
            .build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val json: String = response.body.string()
                val moshi = Moshi.Builder().build()
                val listType = Types.newParameterizedType(List::class.java, HeroModel::class.java)
                val adapter: JsonAdapter<List<HeroModel>> = moshi.adapter(listType)
                heroInfo = adapter.fromJson(json)!!
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        })
    }

    private fun initRecycler() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = HeroAdapter(this@MainActivity, heroInfo)
    }

    override fun onClickItem(heroModel: List<HeroModel>, position: Int) {
        val intentHero = Intent(this, SecondActivity::class.java)
        intentHero.putExtra("id", position)
        startActivity(intentHero)
    }

    companion object {
        var heroInfo = listOf<HeroModel>()
    }
}