package twentythree.d5;

class Range implements IRange {
    long start;
    long end;

    public Range(long start, long range) {
        this.start = start;
        this.end = start + range;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
