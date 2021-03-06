package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.PropertyType;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import com.gmail.thelimeglass.Utils.Annotations.Version;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

@Syntax({"[skellett] [a[n]] %itemstacks% [to be] unbreakable", "[skellett] [a[n]] unbreak(ing|able) %itemstacks%"})
@Config("Unbreakable")
@Version("1.11.2")
@PropertyType(ExpressionType.PROPERTY)
public class ExprUnbreakable extends SimplePropertyExpression<ItemStack, ItemStack> {

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    protected String getPropertyName() {
        return "[skellett] [a[n]] unbreak(ing|able) %itemstacks%";
    }

    @Override
    @Nullable
    public ItemStack convert(ItemStack item) {
        ItemMeta metadata = item.getItemMeta();
        metadata.setUnbreakable(true);
        item.setItemMeta(metadata);
        return item;
    }
}