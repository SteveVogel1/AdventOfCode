package twentythree.d5;

class RangeFix implements IRange {
    long start;
    long end;

    public RangeFix(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
