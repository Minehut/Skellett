package com.gmail.thelimeglass.Expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.PropertyType;
import com.gmail.thelimeglass.Utils.Annotations.RegisterEnum;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import javax.annotation.Nullable;

@Syntax("[the] [skellett] click[ed] type")
@Config("ClickedType")
@PropertyType(ExpressionType.SIMPLE)
@RegisterEnum("clickedtype")
public class ExprClickedType extends SimpleExpression<ClickType> {

    public Class<? extends ClickType> getReturnType() {
        return ClickType.class;
    }

    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] args, int arg1, Kleenean arg2, SkriptParser.ParseResult arg3) {
        if (!ScriptLoader.isCurrentEvent(InventoryClickEvent.class)) {
            Skript.error("You can not use clicked type expression in any event but inventory click!");
            return false;
        }
        return true;
    }

    public String toString(@Nullable Event arg0, boolean arg1) {
        return "clicked type";
    }

    @Nullable
    protected ClickType[] get(Event e) {
        return new ClickType[]{((InventoryClickEvent) e).getClick()};
    }
}