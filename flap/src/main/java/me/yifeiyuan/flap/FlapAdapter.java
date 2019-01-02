package me.yifeiyuan.flap;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static me.yifeiyuan.flap.Preconditions.checkNotNull;

/**
 * Created by 程序亦非猿
 */
public class FlapAdapter extends RecyclerView.Adapter<FlapItem> {

    @NonNull
    private final Flap flap = Flap.getDefault();

    private LifecycleOwner lifecycleOwner;

    @NonNull
    private List<?> models = new ArrayList<>();

    private boolean lifecycleEnable = true;

    @NonNull
    @Override
    public FlapItem onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return flap.onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent, viewType);
    }

    @Override
    public final void onBindViewHolder(@NonNull final FlapItem holder, final int position) {
        //ignore
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull final FlapItem holder, final int position, @NonNull final List<Object> payloads) {
        attachLifecycleOwnerIfNeed(holder);
        flap.onBindViewHolder(holder, getModel(position), this, payloads);
    }

    /**
     * Attaches the holder to lifecycle if need.
     *
     * @param holder
     */
    private void attachLifecycleOwnerIfNeed(final FlapItem holder) {
        if (lifecycleEnable && lifecycleOwner != null && holder instanceof LifecycleObserver) {
            lifecycleOwner.getLifecycle().addObserver((LifecycleObserver) holder);
        }
    }

    @Override
    public int getItemCount() {
        return getModels().size();
    }

    @Override
    public int getItemViewType(final int position) {
        return flap.getItemViewType(getModel(position));
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getContext() instanceof LifecycleOwner && lifecycleOwner == null) {
            setLifecycleOwner((LifecycleOwner) recyclerView.getContext());
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull FlapItem holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow(this);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull FlapItem holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow(this);
    }

    @Override
    public void onViewRecycled(@NonNull final FlapItem holder) {
        super.onViewRecycled(holder);
        holder.onViewRecycled(this);
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull final FlapItem holder) {
        if (holder.onFailedToRecycleView(this)) {
            return true;
        }
        return super.onFailedToRecycleView(holder);
    }

    public FlapAdapter setLifecycleOwner(@NonNull final LifecycleOwner lifecycleOwner) {
        checkNotNull(lifecycleOwner, "lifecycleOwner can't be null.");
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    public FlapAdapter setLifecycleEnable(boolean lifecycleEnable) {
        this.lifecycleEnable = lifecycleEnable;
        return this;
    }

    private Object getModel(final int position) {
        return getModels().get(position);
    }

    public void setModels(@NonNull List<?> models) {
        checkNotNull(models, "models can't be null here");
        this.models = models;
    }

    @NonNull
    public List<?> getModels() {
        return models;
    }
}
