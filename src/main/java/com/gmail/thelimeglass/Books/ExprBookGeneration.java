package com.gmail.thelimeglass.Books;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.gmail.thelimeglass.Utils.Annotations.*;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

@Syntax({"[the] [book['s]] generation %itemstack%", "%itemstack%'s [book] generation"})
@Config("Main.Books")
@FullConfig
@Version("1.9R1")
@PropertyType(ExpressionType.COMBINED)
@RegisterEnum("bookgeneration")
public class ExprBookGeneration extends SimpleExpression<BookMeta.Generation> {

    private Expression<ItemStack> item;

    @Override
    public Class<? extends BookMeta.Generation> getReturnType() {
        return BookMeta.Generation.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        item = (Expression<ItemStack>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[the] [book['s]] generation %itemstack%";
    }

    @Override
    @Nullable
    protected BookMeta.Generation[] get(Event e) {
        BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
        return new BookMeta.Generation[]{book.getGeneration()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
        if (mode == ChangeMode.SET) {
            book.setGeneration((BookMeta.Generation) delta[0]);
        }
        item.getSingle(e).setItemMeta(book);
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET)
            return CollectionUtils.array(BookMeta.Generation.class);
        return null;
    }
}