package com.example.homer_memorygame.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homer_memorygame.R


class LobbyViewModel: ViewModel() {

    val gridType = MutableLiveData<String>()

    fun radioButtonClicked(radiobuttonID: Int) {
 if(radiobuttonID == R.id.rb_1) {
   gridType.value = "3x4"
}
else if(radiobuttonID == R.id.rb_2) {
  gridType.value = "5x2"
}
 else if(radiobuttonID == R.id.rb_3) {
     gridType.value = "4x4"
 }
 else if(radiobuttonID == R.id.rb_4) {
     gridType.value = "4x5"
 }

}

}