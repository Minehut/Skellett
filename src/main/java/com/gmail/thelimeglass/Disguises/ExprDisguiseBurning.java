package com.gmail.thelimeglass.Disguises;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.gmail.thelimeglass.Utils.Annotations.*;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax({"[skellett] [[Libs]Disguises] (burning|ignited|lit) [state] of disguise %disguise%", "[skellett] [[Libs]Disguises] %disguise%'s (burning|ignited|lit) [state]"})
@Config("PluginHooks.LibsDisguises")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprDisguiseBurning extends SimpleExpression<Boolean> {

    private Expression<Disguise> disguise;

    public Class<? extends Boolean> getReturnType() {
        return Boolean.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        disguise = (Expression<Disguise>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[the] (burning|ignited|lit) [state] of disguise %disguise%";
    }

    @Override
    @Nullable
    protected Boolean[] get(Event e) {
        if (disguise != null) {
            return new Boolean[]{disguise.getSingle(e).getWatcher().isBurning()};
        }
        return null;
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            if (disguise != null) {
                disguise.getSingle(e).getWatcher().setBurning((Boolean) delta[0]);
            }
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return CollectionUtils.array(Boolean.class);
        }
        return null;
    }
}