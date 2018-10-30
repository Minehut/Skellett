package com.gmail.thelimeglass.Books;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.PropertyType;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

@Syntax("[(the|all)] [of] [the] [book] pages [(from|of)] [book] %itemstack%")
@Config("Main.Books")
@FullConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprBookPages extends SimpleExpression<String> {

    private Expression<ItemStack> item;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        item = (Expression<ItemStack>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[(the|all)] [of] [the] [book] pages [(from|of)] [book] %itemstack%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
        return book.getPages().toArray(new String[book.getPages().size()]);
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
        if (mode == ChangeMode.SET) {
            book.setPages((String) delta[0]);
        }
        item.getSingle(e).setItemMeta(book);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}