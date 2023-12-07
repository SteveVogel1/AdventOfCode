package twentythree.d5;

class Converter {
    long startDestination;
    long startSource;
    long range;
    long dif;

    public Converter(String startDestination, String startSource, String range) {
        this.startDestination = Long.parseLong(startDestination);
        this.startSource = Long.parseLong(startSource);
        this.range = Long.parseLong(range);
        this.dif = this.startDestination - this.startSource;
    }

    public boolean isInSourceRange(long value) {
        return value >= startSource && value <= startSource + range;
    }

    public long getStart() {
        return startSource;
    }

    public long getEnd() {
        return startSource + range -1;
    }

    public long convert(long value) {
        return value + this.dif;
    }
}
