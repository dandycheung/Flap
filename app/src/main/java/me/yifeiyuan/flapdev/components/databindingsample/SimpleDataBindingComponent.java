package me.yifeiyuan.flapdev.components.databindingsample;

import androidx.annotation.NonNull;

import me.yifeiyuan.flap.Component;
import me.yifeiyuan.flap.annotations.Delegate;
import me.yifeiyuan.flapdev.databinding.FlapItemSimpleDatabindingBinding;

/**
 * Flap Github: <a>https://github.com/AlanCheen/Flap</a>
 *
 * @author 程序亦非猿 [Follow me](<a> https://github.com/AlanCheen</a>)
 * @since 2020/3/26 3:28 PM
 * @since 1.0
 */
//@Delegate(layoutId = R.layout.flap_item_simple_databinding, useDataBinding = true)
@Delegate(layoutName ="flap_item_simple_databinding", useDataBinding = true)
public class SimpleDataBindingComponent extends Component<SimpleDataBindingModel> {

    private FlapItemSimpleDatabindingBinding binding;

    public SimpleDataBindingComponent(@NonNull final FlapItemSimpleDatabindingBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    @Override
    public void onBind(SimpleDataBindingModel model) {
        binding.setModel(model);
        binding.executePendingBindings();
    }
}
