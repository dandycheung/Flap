package me.yifeiyuan.flap;

import android.support.annotation.NonNull;

/**
 * Created by 程序亦非猿
 *
 * A manager for managing ItemFactory.
 */
public interface ItemFactoryManager {

    ItemFactoryManager registerItemFactory(@NonNull FlapItemFactory itemFactory);

    ItemFactoryManager unregisterItemFactory(@NonNull FlapItemFactory itemFactory);
}
