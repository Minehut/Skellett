package com.gmail.thelimeglass.SkellettProxy;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.SkellettPacket;
import com.gmail.thelimeglass.SkellettPacketType;
import com.gmail.thelimeglass.Sockets;
import com.gmail.thelimeglass.Utils.Annotations.*;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[the] bungee[[ ]cord] version")
@Config("SkellettProxy")
@FullConfig
@SkellettProxy
@PropertyType(ExpressionType.SIMPLE)
public class ExprBungeecordVersion extends SimpleExpression<String> {

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "bungee[[ ]cord] version";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        String version = (String) Sockets.send(new SkellettPacket(true, null, SkellettPacketType.BUNGEEVERSION));
        if (version != null) {
            return new String[]{version};
        }
        return null;
    }
}