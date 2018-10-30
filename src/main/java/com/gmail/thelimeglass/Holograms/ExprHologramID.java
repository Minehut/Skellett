package com.gmail.thelimeglass.Holograms;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.*;
import com.sainttx.holograms.api.Hologram;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax({"[the] [skellett] holo[gram] ID of %hologram%", "[skellett] holo[gram] %hologram%'s ID", "[skellett] id of holo[gram] %hologram%"})
@Config("PluginHooks.Holograms")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprHologramID extends SimpleExpression<String> {

    private Expression<Hologram> hologram;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        hologram = (Expression<Hologram>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett] holo[gram] ID of %hologram%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        if (hologram != null) {
            return new String[]{hologram.getSingle(e).getId()};
        }
        return null;
    }
}