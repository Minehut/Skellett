package com.gmail.thelimeglass.Npcs;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.gmail.thelimeglass.Utils.Annotations.*;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax({"[the] name (of|from) (npc|citizen) %citizen%", "(npc|citizen) %citizen%'s name"})
@Config("PluginHooks.Npc")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprNpcName extends SimpleExpression<String> {

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
        return "[the] name (of|from) (npc|citizen) %npc%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        return new String[]{npc.getSingle(e).getName()};
    }

    @Override
    public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            npc.getSingle(e).setName((String) delta[0]);
        }
    }

    @Override
    public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
        if (mode == ChangeMode.SET) {
            return CollectionUtils.array(String.class);
        }
        return null;
    }
}