package com.gmail.thelimeglass.MySQL;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExprMySQLQueryInteger extends SimpleExpression<Number> {

    //[skellett] mysql integer[s] %string% (in|from|of) %resultset%

    private Expression<String> string;
    private Expression<ResultSet> rs;

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        string = (Expression<String>) e[0];
        rs = (Expression<ResultSet>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean arg1) {
        return "[skellett] mysql integer %string% (in|from|of) %resultset%";
    }

    @Override
    @Nullable
    protected Number[] get(Event e) {
        ArrayList<Number> data = new ArrayList<Number>();
        try {
            while (rs.getSingle(e).next()) {
                Number name = rs.getSingle(e).getInt(string.getSingle(e));
                data.add(name);
            }
            return data.toArray(new Number[data.size()]);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}