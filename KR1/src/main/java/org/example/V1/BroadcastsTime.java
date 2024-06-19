package org.example.V1;

public class BroadcastsTime implements Comparable<BroadcastsTime> {
    private byte hour;
    private byte minutes;

    public BroadcastsTime(byte hour, byte minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    public byte getHour() {
        return hour;
    }

    public byte getMinutes() {
        return minutes;
    }

    public boolean after(BroadcastsTime time) {
        return this.hour > time.hour ||
                (this.hour == time.hour && this.minutes > time.minutes);
    }

    public boolean before(BroadcastsTime time) {
        return this.hour < time.hour ||
                (this.hour == time.hour && this.minutes < time.minutes);
    }

    public boolean between(BroadcastsTime time1, BroadcastsTime time2) {
        return this.after(time1) && this.before(time2);
    }

    @Override
    public int compareTo(BroadcastsTime time) {
        if (this.hour == time.hour) {
            return Byte.compare(this.minutes, time.minutes);
        }
        return Byte.compare(this.hour, time.hour);
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }

}