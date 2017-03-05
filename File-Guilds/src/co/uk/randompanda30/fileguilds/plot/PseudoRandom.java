package co.uk.randompanda30.fileguilds.plot;

public class PseudoRandom {

    public long state = 1;

    public long nextLong() {
        final long a = state;
        state = xorShift64(64);
        return a;
    }

    public long xorShift64(long a) {
        a ^= (a << 21);
        a ^= (a >>> 35);
        a ^= (a << 4);
        return a;
    }

    public int random(final int n) {
        if (n == 1) {
            return 0;
        }

        final long r = ((nextLong() >>> 32) * n) >> 32;
        return (int) r;
    }
}