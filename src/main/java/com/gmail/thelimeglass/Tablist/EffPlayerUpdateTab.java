package com.gmail.thelimeglass.Tablist;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.MainConfig;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.inventivetalent.tabapi.TabAPI;

import javax.annotation.Nullable;

@Syntax("update [the] tab[list] of %players%")
@Config("PluginHooks.TabListAPI")
@FullConfig
@MainConfig
public class EffPlayerUpdateTab extends Effect {

    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
        players = (Expression<Player>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "update [the] tab[list] of %players%";
    }

    @Override
    protected void execute(Event e) {
        for (Player p : players.getAll(e)) {
            TabAPI.updateTab(p);
        }
    }
}
