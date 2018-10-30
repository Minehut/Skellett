package com.gmail.thelimeglass.Holograms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.MainConfig;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import com.sainttx.holograms.api.Hologram;
import com.sainttx.holograms.api.line.HologramLine;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[skellett] remove %hologramline% from holo[gram] %hologram%")
@Config("PluginHooks.Holograms")
@FullConfig
@MainConfig
public class EffHologramRemoveLine extends Effect {

    private Expression<HologramLine> hologramline;
    private Expression<Hologram> hologram;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        hologramline = (Expression<HologramLine>) e[0];
        hologram = (Expression<Hologram>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] remove %hologramline% from holo[gram] %hologram%";
    }

    @Override
    protected void execute(Event e) {
        if (hologram != null || hologramline != null) {
            hologram.getSingle(e).removeLine(hologramline.getSingle(e));
        }
    }
}