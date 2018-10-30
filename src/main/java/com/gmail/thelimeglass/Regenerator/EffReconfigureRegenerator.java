package com.gmail.thelimeglass.Regenerator;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("re[ ]configure [the] [skellett] regenerator with ID %string%")
@Config("Main.Regenerator")
@FullConfig
public class EffReconfigureRegenerator extends Effect {

    private Expression<String> ID;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        ID = (Expression<String>) e[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "re[ ]configure [the] [skellett] regenerator with ID %string%";
    }

    @Override
    protected void execute(Event e) {
        RegeneratorManager.reconfigure(ID.getSingle(e), RegeneratorManager.getPos1(ID.getSingle(e)), RegeneratorManager.getPos2(ID.getSingle(e)));
    }
}