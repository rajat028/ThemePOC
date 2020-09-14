package com.demo.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class BaseAdapter<T : BaseTModel> : RecyclerView.Adapter<BaseViewHolder> {

    private var layoutResource = 0
    var list: MutableList<T> = ArrayList()
    var handler: BaseHandler<*>? = null
    private var viewModel: BaseViewModel<T>? = null
    private var isDummyCardPresent = false
    private var lastPosition = -1
    private var isAnimationEnabled: Int? = null
    private var type: String? = null

    /**
     * basic function can make use of all below method if required
     *
     * @param
     */

    val items: List<T>?
        get() = list

    val firstItem: T?
        get() = if (list.size > 0) list[0] else null

    constructor(layoutResource: Int, anim: Int? = null, type: String? = null) {
        this.layoutResource = layoutResource
        this.isAnimationEnabled = anim
        this.type = type
    }

    constructor(
        layoutResource: Int,
        handler: BaseHandler<*>?,
        anim: Int? = null,
        type: String? = null
    ) {
        this.layoutResource = layoutResource
        this.handler = handler
        this.isAnimationEnabled = anim
        this.type = type
    }

    constructor(
        layoutResource: Int,
        viewModel: BaseViewModel<T>?,
        handler: BaseHandler<*>? = null,
        anim: Int? = null,
        type: String? = null
    ) {
        this.layoutResource = layoutResource
        this.viewModel = viewModel
        this.handler = handler
        this.isAnimationEnabled = anim
        this.type = type
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            layoutInflater, viewType, parent, false
        )
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        val obj = list[position]
        holder.bind(obj, handler, viewModel, type, position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutResource != 0) {
            /**
             * for single view type
             */
            layoutResource
        } else {
            /**
             * for different view types
             */
            list[position].layoutResId
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * adding data for the first time in the list and scroll if required to last position
     *
     * @param li
     */
    open fun updateList(li: List<T>?) {
        if (li != null) {
            this.list.clear()
            this.list = ArrayList(li)
            notifyDataSetChanged()
        }
    }

    open fun replaceObjectAtIndex(obj: T?, index: Int?) {
        if (obj != null && index != null) {
            list.removeAt(index)
            list.add(index, obj)
            notifyItemChanged(index)
        }
    }

    open fun updateObjectAtIndex(obj: T?, index: Int?) {
        if (obj != null && index != null) {
            notifyItemChanged(index, obj)
            notifyItemChanged(index)
        }
    }

    /**
     * insert data at the bottom of the list
     *
     * @param dataList
     */
    fun insertIntoBottomList(dataList: List<T>?) {
        var position = list.size
        if (dataList == null) {
            return
        }
        dataList.forEach { datumList ->
            list.add(position, datumList)
            notifyItemInserted(position)
            position++
        }
    }

    /**
     * inserting list data at the top of the list
     *
     * @param obj
     */
    fun insertDataAtPosition(obj: T?, index: Int) {
        if (obj == null) {
            return
        }
        list.add(index, obj)
        notifyItemInserted(index)
    }

    /**
     * inserting object at the top of the list
     *
     * @param datum
     */
    fun insertIntoTopDatum(datum: T?) {

        if (datum != null) {
            list.add(0, datum)
            notifyItemInserted(0)
        }
    }

    /**
     * inserting object at the Bottom of the list
     *
     * @param datum
     */
    fun insertIntoBottomDatum(datum: T?) {
        if (datum != null) {
            list.add(datum)
            notifyItemInserted(list.size - 1)
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }

    fun clearItems() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T? {
        return if (list.size > position) list[position] else null
    }

    fun isEmpty() = items?.isNotEmpty() != true

    /**
     * remove item at particular position
     *
     * @param position
     */
    @Synchronized
    fun removeItem(position: Int) {
        if (list.size > 0) {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount - position)
        }
    }
}
