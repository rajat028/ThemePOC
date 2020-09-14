package com.demo.core;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding binding;
    private Object obj;

    public BaseViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Object obj, BaseHandler handler, BaseViewModel baseViewModel, String type, Integer position) {
        this.obj = obj;
        if (obj != null && obj instanceof BaseTModel && isRecyclable() != ((BaseTModel) obj).isRecyclable()) {
            setIsRecyclable(((BaseTModel) obj).isRecyclable());
        }
        if(obj instanceof BaseTModel){
            ((BaseTModel) obj).setAdapterPosition(position);
        }
        binding.setVariable(BR.obj, obj);

        binding.executePendingBindings();
    }

    public ViewDataBinding getBinding() {
        return binding;
    }

    public void onViewDetachedFromWindow() {
        if (obj != null && obj instanceof BaseTModel) {
            ((BaseTModel) obj).onViewDetachedFromWindow();
        }
    }

}