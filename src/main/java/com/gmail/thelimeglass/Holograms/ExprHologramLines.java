package com.gmail.thelimeglass.Holograms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.*;
import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.HologramLine;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[(the|all)] [of] [the] [skellett] holo[gram] lines (in|from|of) %hologram%")
@Config("PluginHooks.Holograms")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprHologramLines extends SimpleExpression<HologramLine> {

    private Expression<Hologram> hologram;

    @Override
    public Class<? extends HologramLine> getReturnType() {
        return HologramLine.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        hologram = (Expression<Hologram>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[(the|all)] [of] [the] [skellett] holo[gram] lines (in|from|of) %hologram%";
    }

    @Override
    @Nullable
    protected HologramLine[] get(Event e) {
        if (hologram != null) {
            return hologram.getSingle(e).getLines().toArray(new HologramLine[hologram.getSingle(e).getLines().size()]);
        }
        return null;
    }
}