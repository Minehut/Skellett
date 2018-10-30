package com.gmail.thelimeglass.BossBars;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BossBar;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffBarAddFlag extends Effect {

    //[skellett] add [boss[ ]][bar] [flag] %barflag% to [the] [boss[ ]][bar] %bossbar%

    private Expression<BarFlag> flag;
    private Expression<BossBar> bar;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        flag = (Expression<BarFlag>) e[0];
        bar = (Expression<BossBar>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] add [boss[ ]][bar] [flag] %barflag% to [the] [boss[ ]][bar] %bossbar%";
    }

    @Override
    protected void execute(Event e) {
        if (bar == null) {
            return;
        }
        bar.getSingle(e).addFlag(flag.getSingle(e));
    }
}