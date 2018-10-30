package com.gmail.thelimeglass.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import com.gmail.thelimeglass.Utils.Annotations.Config;
import com.gmail.thelimeglass.Utils.Annotations.FullConfig;
import com.gmail.thelimeglass.Utils.Annotations.Syntax;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Syntax("[skellett] copy file [path] %string% to [path] %string%")
@Config("Main.Files")
@FullConfig
public class EffCopyFile extends Effect {

    private Expression<String> file1, file2;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        file1 = (Expression<String>) e[0];
        file2 = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[skellett] copy file [path] %string% to [path] %string%";
    }

    @Override
    protected void execute(Event e) {
        throw new UnsupportedOperationException("copy file not supported");
    }
}