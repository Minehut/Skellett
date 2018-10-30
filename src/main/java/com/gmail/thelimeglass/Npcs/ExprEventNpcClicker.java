package com.gmail.thelimeglass.Npcs;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.*;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[the] (citizen|npc) [event[(-| )]](player|clicker)")
@Config("PluginHooks.Npc")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.SIMPLE)
public class ExprEventNpcClicker extends SimpleExpression<Player> {

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[the] (citizen|npc) [event[(-| )]](player|clicker)";
    }

    @Override
    @Nullable
    protected Player[] get(Event e) {
        if (NPCClickEvent.class.isAssignableFrom(e.getClass())) {
            return new Player[]{((NPCClickEvent) e).getClicker()};
        }
        return null;
    }
}