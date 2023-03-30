package api;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that models a time interval.
 */
public class TimeInterval {
    private final long start;
    private final long end;

    /**
     * Constructor for interval's bounds (in milliseconds).
     * 
     * @param start interval start time
     * @param end interval end time
     */
    public TimeInterval(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Getter for start of the range.
     * 
     * @return start time
     */
    public long getStart() {
        return this.start;
    }

    /**
     * Getter for the end of the range.
     * 
     * @return end time
     */
    public long getEnd() {
        return this.end;
    }

    /**
     * Randomly extract a value within the range.
     * 
     * @return the drawn value
     */
    public long drawInBetween() {
        return ThreadLocalRandom.current().nextLong(this.start, this.end);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (start ^ (start >>> 32));
        result = prime * result + (int) (end ^ (end >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeInterval other = (TimeInterval) obj;
        if (start != other.start) {
            return false;
        } else if (end != other.end) {
            return false;
        }
        return true;
    }
}
