package com.example.homer_memorygame.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homer_memorygame.Model.ImageDataClass
import com.example.homer_memorygame.R
import com.example.homer_memorygame.Util
import kotlin.random.Random

class GamePlayViewModel : ViewModel() {

    var imagesListM = MutableLiveData<ArrayList<ImageDataClass>>()

    var count = MutableLiveData<Int>()
    fun sendType(type: String) {

        if (type == "3x4") {
            count.value = 3
            imagesListM.value = Util.getUpdatedList(12)

        } else if (type == "5x2") {
            count.value = 5
            imagesListM.value = Util.getUpdatedList(10)
        } else if (type == "4x4") {
            count.value = 4
            imagesListM.value = Util.getUpdatedList(16)
        } else if (type == "4x5") {
            count.value = 4
            imagesListM.value = Util.getUpdatedList(20)
        }

    }


}