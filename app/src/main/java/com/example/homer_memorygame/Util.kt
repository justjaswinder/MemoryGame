package com.example.homer_memorygame

import android.util.Log
import com.example.homer_memorygame.Model.ImageDataClass
import kotlin.random.Random

object Util {

    var imagesList = arrayListOf<ImageDataClass>()


    @JvmStatic
    fun getUpdatedList(count: Int): ArrayList<ImageDataClass> {
        var tempList = ArrayList<ImageDataClass>()
        imagesList.clear()
        addData()
        for (i in 0..(count - 1)) {
            tempList.add(imagesList.get(i))
        }
        return shuffle(tempList)
    }

    fun shuffle(list: ArrayList<ImageDataClass>): ArrayList<ImageDataClass> {
        // start from the beginning of the list
        for (i in 0 until list.size - 1) {
            // get a random index `j` such that `i <= j <= n`
            val j = i + Random.nextInt(list.size - i)

            // swap element at i'th position in the list with the element at j'th position
            val temp = list[i]
            list[i] = list[j]
            list[j] = temp
        }
        return list
    }

    fun isGameOver(
        list: ArrayList<ImageDataClass>
    ): Boolean {

        list.forEach {

            if (!it.isClicked) {
                return false
            }
        }
        return true
    }

   fun  notifyCard( list: ArrayList<ImageDataClass>,
                    item: ImageDataClass): Int{
       for (index in list.indices) {
          if(list.get(index).isClicked==true && list.get(index).isMatched==false){
              Log.e("index",index.toString())
              return  index
          }

       }

   return 0
   }
    fun checkIfMatches(
        list: ArrayList<ImageDataClass>,
        item: ImageDataClass
    ): Boolean {

        list.forEach {
            if (item.title == it.title) {
                if (it.isClicked || item.isClicked) {
                    it.isMatched = true

                    return true
                }

            }
        }

        return false
    }


    fun addData() {
        imagesList.add(ImageDataClass("bat", R.drawable.bat, false, false))
        imagesList.add(ImageDataClass("bat", R.drawable.bat, false, false))

        imagesList.add(ImageDataClass("cat", R.drawable.cat, false, false))
        imagesList.add(ImageDataClass("cat", R.drawable.cat, false, false))

        imagesList.add(ImageDataClass("cow", R.drawable.cow, false, false))
        imagesList.add(ImageDataClass("cow", R.drawable.cow, false, false))

        imagesList.add(ImageDataClass("dragon", R.drawable.dragon, false, false))
        imagesList.add(ImageDataClass("dragon", R.drawable.dragon, false, false))

        imagesList.add(ImageDataClass("ghost", R.drawable.ghost, false, false))
        imagesList.add(ImageDataClass("ghost", R.drawable.ghost, false, false))

        imagesList.add(ImageDataClass("hen", R.drawable.hen, false, false))
        imagesList.add(ImageDataClass("hen", R.drawable.hen, false, false))

        imagesList.add(ImageDataClass("horse", R.drawable.horse, false, false))
        imagesList.add(ImageDataClass("horse", R.drawable.horse, false, false))

        imagesList.add(ImageDataClass("man", R.drawable.man, false, false))
        imagesList.add(ImageDataClass("man", R.drawable.man, false, false))

        imagesList.add(ImageDataClass("pig", R.drawable.pig, false, false))
        imagesList.add(ImageDataClass("pig", R.drawable.pig, false, false))

        imagesList.add(ImageDataClass("spider", R.drawable.spider, false, false))
        imagesList.add(ImageDataClass("spider", R.drawable.spider, false, false))


    }

}