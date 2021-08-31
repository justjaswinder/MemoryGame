package com.example.homer_memorygame.View

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.homer_memorygame.ViewModel.LobbyViewModel
import com.example.homer_memorygame.databinding.ActivityLobbyBinding


class LobbyActivity : AppCompatActivity() {

    lateinit var viewModel: LobbyViewModel
    lateinit var binding: ActivityLobbyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLobbyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LobbyViewModel::class.java)
        //  binding.txtt
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {

            actionBar.title = "Memory Game"

        }

        viewModel.gridType.observe(this, Observer {
            val intent = Intent(this, GamePlayActivity::class.java)
            intent.putExtra("gridType", it)
            startActivity(intent)
        })



        binding.rg.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            viewModel.radioButtonClicked(radioGroup.checkedRadioButtonId)

        })
    }


}




