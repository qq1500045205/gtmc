package com.tlan.plugins.lllagel.vo;

public class Pair<T1, T2>
{
    private T1 first;
    private T2 second;

    public T1 getFirst()
    {
        return first;
    }

    public void setFirst(T1 first)
    {
        this.first = first;
    }

    public T2 getSecond()
    {
        return second;
    }

    public void setSecond(T2 second)
    {
        this.second = second;
    }

    public Pair()
    {

    }

    public Pair(T1 t1, T2 t2)
    {
        first = t1;
        second = t2;
    }

    public boolean equals(Pair<T1, T2> other)
    {
        if (other == null)
        {
            return false;
        }

        if (this.first == null)
        {
            if (other.getFirst() != null)
                return false;
        }
        else
        {
            if (!this.first.equals(other.getFirst()))
                return false;
        }

        if (this.second == null)
        {
            if (other.getSecond() != null)
                return false;
        }
        else
        {
            if (!this.second.equals(other.getSecond()))
                return false;
        }

        return this.first.equals(other.getFirst())
            && this.second.equals(other.getSecond());
    }
}