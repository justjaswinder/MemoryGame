package com.example.homer_memorygame.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homer_memorygame.CardAdapter
import com.example.homer_memorygame.R
import com.example.homer_memorygame.ViewModel.GamePlayViewModel
import com.example.homer_memorygame.databinding.ActivityGamePlayBinding


class GamePlayActivity : AppCompatActivity() {
    lateinit var viewModel: GamePlayViewModel
    lateinit var binding: ActivityGamePlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamePlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(GamePlayViewModel::class.java)



        setToolbar()
//        val actionBar: ActionBar? = supportActionBar
//        if (actionBar != null) {
//
//            actionBar.title = "Momory Game"
//
//             var backArrow = getResources().getDrawable(R.drawable.back);
//       //     backArrow .setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
//            actionBar.setHomeAsUpIndicator(backArrow );
//          //  actionBar.setDisplayHomeAsUpEnabled(true);
//
//        }



        var type: String? = intent.getStringExtra("gridType")
        viewModel.sendType(type!!)

    observeData()
}

fun observeData(){
viewModel.count.observe(this, Observer {

    binding.rv.layoutManager = GridLayoutManager(applicationContext,it)
})
    viewModel.imagesListM.observe(this, Observer{
      //  Log.i("data",it.toString())
    binding.rv.adapter= CardAdapter(this, it)

    })
}
    override fun onBackPressed() {
        finish()
    }

    fun setToolbar(){

        val toolbar = findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        binding.layoutToolbar.backBtn.setOnClickListener(View.OnClickListener {
            finish()
        })

    }

}
