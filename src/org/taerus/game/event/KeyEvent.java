package org.taerus.game.event;


import java.util.EnumSet;
import java.util.Iterator;

public final class KeyEvent extends Event {

    private final EnumSet<Modifier> modifiers;
    public final Key key;

    public KeyEvent(EventType type, Key key, EnumSet<Modifier> modifiers) {
        super(type);
        this.key = key;
        this.modifiers = modifiers;
    }

    public KeyEvent(EventType type, Key key) {
        this(type, key, EnumSet.noneOf(Modifier.class));
    }

    public EnumSet<Modifier> getModifiers() {
        return modifiers.clone();
    }

    public boolean hasModifier() {
        return !modifiers.isEmpty();
    }

    public boolean hasAltModifier() {
        return modifiers.contains(Modifier.ALT);
    }

    public boolean hasShiftModifier() {
        return modifiers.contains(Modifier.SHIFT);
    }

    public boolean hasCtrlModifier() {
        return modifiers.contains(Modifier.CTRL);
    }

    public boolean hasModifier(Modifier first, Modifier... others) {
        boolean ret = modifiers.contains(first);
        for (Modifier modifier : others) {
            ret &= modifiers.contains(modifier);
        }
        return ret;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Modifier modifier : modifiers) {
            sb.append(modifier).append("+");
        }
        sb.append(key);

        return sb.toString();
    }
}
