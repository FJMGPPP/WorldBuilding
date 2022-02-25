package com.fjmg.worldbuilding.ui.login;

public final class Event
{
    String message;
    int type;

    public String getMessage() {
        return message;
    }
    public void setMessage(String msg)
    {
        message = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int error) {
        this.type = error;
    }

    public class TypesErrorEvents
    {
        public static final int INICIO_SESION_FAIL = 1;
        public static final int REGISTER_SESION_FAIL = -1;
    }
}