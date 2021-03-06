package com.gmail.thelimeglass.Npcs;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[the] (npc|citizen) (of|from) entity %entity%")
@Config("PluginHooks.Npc")
@FullConfig
@MainConfig
@PropertyType(ExpressionType.COMBINED)
public class ExprNpcFromEntity extends SimpleExpression<NPC> {

    private Expression<Entity> entity;

    @Override
    public Class<? extends NPC> getReturnType() {
        return NPC.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        entity = (Expression<Entity>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[the] (npc|citizen) (of|from) entity %entity%";
    }

    @Override
    @Nullable
    protected NPC[] get(Event e) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        return new NPC[]{registry.getNPC(entity.getSingle(e))};
    }
}