package com.gmail.thelimeglass.Npcs;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.*;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax({"[the] full name (of|from) (npc|citizen) %citizen%", "(npc|citizen) %citizen%'s full name"})
@Config("PluginHooks.Npc")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprNpcFullName extends SimpleExpression<String> {

    private Expression<NPC> npc;

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
        npc = (Expression<NPC>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "full name (of|from) (npc|citizen) %npc%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{npc.getSingle(e).getFullName()};
    }
}