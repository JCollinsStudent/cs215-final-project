// import java.util.Random;
import java.util.UUID;
public class Plane implements Comparable<Plane> {
    UUID flightID;
    enum QueueType {
        APPROACH, READY
    }
    QueueType currentQueue;
    int queuePosition;

    public Plane() {
        this.currentQueue = QueueType.APPROACH;
        this.flightID = UUID.randomUUID();
        this.queuePosition = 0;
    }

    // public Plane() {

    // }

    public UUID getFlightID() {
        return this.flightID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    public int getQueuePosition() {
        return this.queuePosition;
    }

    public void setQueuePosition(int position) {
        this.queuePosition = position;
    }

    public void setCurrentQueue(String type) {
        this.currentQueue = QueueType.valueOf(type);
    }

    @Override
    public int compareTo(Plane newPlane) {
        switch (this.currentQueue) {
            case APPROACH:
                if (newPlane.currentQueue != QueueType.APPROACH) {
                    return -1;
                }
                else {
                    if (newPlane.queuePosition > this.queuePosition) {
                        return -1;
                    }
                    else if (newPlane.queuePosition == this.queuePosition) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
            case READY:
                if (newPlane.currentQueue == QueueType.APPROACH) {
                    return 1;
                }
                else {
                    if (newPlane.queuePosition > this.queuePosition) {
                        return -1;
                    }
                    else if (newPlane.queuePosition == this.queuePosition) {
                        return 0;
                    }
                    else {
                        return 1;
                    }
                }
        }
        return 0;
    }
}
