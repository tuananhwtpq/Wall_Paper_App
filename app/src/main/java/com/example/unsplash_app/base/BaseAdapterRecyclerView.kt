package com.example.unsplash_app.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.unsplash_app.utils.ex.clickAnimation

abstract class BaseAdapterRecyclerView<T, VB : ViewBinding>(
    dataList: MutableList<T>? = null
) : RecyclerView.Adapter<BaseViewHolder<VB>>() {

    // ViewBinding instance for creating ViewHolder
    private var binding: VB? = null

    private var enableClickAnimation = false

    // Click listeners for item interactions
    private var onClickItem: ((item: T?, position: Int) -> Unit)? = null

    var dataList: MutableList<T> = dataList ?: arrayListOf()
        internal set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        binding = inflateBinding(LayoutInflater.from(parent.context), parent)
        return BaseViewHolder(requireNotNull(binding)).apply {
            bindViewClick(this, viewType)
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindData(holder.binding, dataList[position], position)
    }

    protected abstract fun inflateBinding(inflater: LayoutInflater, parent: ViewGroup): VB

    open fun bindViewClick(viewHolder: BaseViewHolder<VB>, viewType: Int) {
        // Setup normal click listener
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                // Apply animation if enabled
                if (enableClickAnimation) {
                    it.clickAnimation()
                }
                onClickItem?.invoke(dataList.getOrNull(position), position)
            }
        }
    }

    fun setOnClickItem(
        enableAnimation: Boolean = false,
        listener: ((item: T?, position: Int) -> Unit)? = null
    ) {
        this.enableClickAnimation = enableAnimation
        this.onClickItem = listener
    }


    protected abstract fun bindData(binding: VB, item: T, position: Int)

    /**
     * Update data tại vị trí cụ thể
     * @param index vị trí cần update (>= 0)
     * @param data data mới
     */
    open fun setData(@IntRange(from = 0) index: Int, data: T) {
        if (index < dataList.size) {
            dataList[index] = data
            notifyItemChanged(index)
        }
    }

    /**
     * Xóa item tại vị trí cụ thể
     * @param position vị trí cần xóa (>= 0)
     */
    open fun removeAt(@IntRange(from = 0) position: Int) {
        if (position < dataList.size) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    /**
     * Xóa item theo data object
     * @param data data object cần xóa
     */
    open fun remove(data: T) {
        val index = dataList.indexOf(data)
        if (index != -1) {
            removeAt(index)
        }
    }

    /**
     * Clear tất cả data trong adapter
     */
    @SuppressLint("NotifyDataSetChanged")
    open fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }

    /**
     * Set danh sách data mới (replace toàn bộ)
     * @param data collection data mới
     */
    @SuppressLint("NotifyDataSetChanged")
    open fun setDataList(data: Collection<T>) {
        dataList.clear()
        dataList.addAll(data)
        notifyDataSetChanged()
    }

    /**
     * Thêm data vào cuối danh sách
     * Thường dùng cho load more functionality
     * @param data collection data cần thêm
     */
    open fun addDataList(data: Collection<T>) {
        val start = dataList.size
        dataList.addAll(data)
        notifyItemRangeInserted(start, data.size)
    }
}