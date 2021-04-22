package com.example.testapp.features.shibe.ui.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.DiffUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testapp.databinding.ItemShibeBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class ShibeAdapter {

    fun createAdapter(click: (String) -> Unit) = AsyncListDifferDelegationAdapter(
        createDiffCallback(),
        AdapterDelegatesManager<List<String>>()
            .addDelegate(createShibeAdapterDelegate(click))
    )

    private fun createShibeAdapterDelegate(click: (String) -> Unit) =
        adapterDelegateViewBinding<String, String, ItemShibeBinding>(
            { layoutInflater, root -> ItemShibeBinding.inflate(layoutInflater, root, false) }
        ) {
            itemView.setOnClickListener { click.invoke(item) }

            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
                .apply {
                    strokeWidth = 5f
                    centerRadius = 30f
                }

            bind {
                Glide
                    .with(itemView.context)
                    .load(item)
                    .centerCrop()
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            circularProgressDrawable.stop()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            circularProgressDrawable.stop()
                            return false
                        }

                    })
                    .placeholder(circularProgressDrawable)
                    .into(binding.ivItemShibe)

                circularProgressDrawable.start()
            }
        }

    private fun createDiffCallback() =
        object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) =
                newItem == oldItem

            override fun areContentsTheSame(oldItem: String, newItem: String) =
                oldItem == newItem
        }

}