package com.example.homer_memorygame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homer_memorygame.Model.ImageDataClass
import com.example.homer_memorygame.Util.isGameOver
import com.example.homer_memorygame.Util.notifyCard
import com.example.homer_memorygame.ViewModel.GamePlayViewModel
import com.example.homer_memorygame.databinding.CardItemBinding


class CardAdapter(
    private val context: Context,
    private val list: ArrayList<ImageDataClass>

) :
    RecyclerView.Adapter<CardAdapter.ViewHolder>() {
    var isReset: Boolean = true

    var mMediaPlayer: MediaPlayer? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])


    inner class ViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageDataClass) {
            with(binding) {


                imgb.setImageResource(item.imageResource)
                imgb.visibility = View.GONE

                if (item.isClicked) {
                    img.setImageResource(item.imageResource)
                } else {
                    img.setImageResource(com.example.homer_memorygame.R.drawable.background)
                }



                img.setOnClickListener(View.OnClickListener {


                    val oa1 =
                        ObjectAnimator.ofFloat(img, "scaleX", 1f, 0f)
                    val oa2 =
                        ObjectAnimator.ofFloat(img, "scaleX", 0f, 1f)

                    oa1.interpolator = DecelerateInterpolator()
                    oa2.interpolator = AccelerateDecelerateInterpolator()
                    oa1.addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)


                            imgb.visibility = View.GONE

                            if (isReset) {

                                isReset = false;
                                item.isClicked = true

                                img.setImageResource(item.imageResource)

                            } else {
                                if (Util.checkIfMatches(list, item)) {

                                    item.isClicked = true
                                    item.isMatched = true
                                    isReset = true;
                                    img.setImageResource(item.imageResource)
                                    playSound("matched")

                                    if (isGameOver(list)) {

                                        Toast.makeText(context, "Victory!!!!!", Toast.LENGTH_SHORT)
                                            .show()

                                        playSound("victory")

                                        Handler().postDelayed({
                                            (context as Activity).finish()
                                        }, 3000)
                                    }
                                } else {

                                    var index=notifyCard(list,item)
                                    var resetItem=list.get(index)
                                    resetItem.isClicked=false
                                    resetItem.isMatched=false

                                 list.set(index,resetItem)
                                    notifyItemChanged(index)

                                    isReset = true;
                                    imgb.visibility = View.VISIBLE
                                    img.setImageResource(com.example.homer_memorygame.R.drawable.background)
                                    playSound("wrong")

                                }
                            }

                            oa2.start()
                        }
                    })
                    oa1.start()


                })

            }
        }

    }

    fun playSound(type: String) {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.stop()
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        if (type == "victory")
            mMediaPlayer = MediaPlayer.create(context, R.raw.winner)
        else if (type == "wrong") {
            mMediaPlayer = MediaPlayer.create(context, R.raw.wrong)
        } else if (type == "matched") {
            mMediaPlayer = MediaPlayer.create(context, R.raw.matched)
        }
        mMediaPlayer!!.isLooping = false
        mMediaPlayer!!.start()
    }
}