package com.gmail.thelimeglass.Nametags;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[skellett] reset [the] [name][ ]tag prefix [(with|of)] [id] %string%")
@Config("Main.Nametags")
@FullConfig
public class EffResetNametagPrefix extends Effect {

    private Expression<String> nametag;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        nametag = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] reset [the] [name][ ]tag prefix [(with|of)] [id] %string%";
    }

    @Override
    protected void execute(Event e) {
        NametagManager.resetNametagPrefix(nametag.getSingle(e));
    }
}