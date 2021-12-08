import java.util.PriorityQueue;

public class Airport {
    PriorityQueue<Plane> readyToLand;
    PriorityQueue<Plane> approachQueue;

    public Airport() {
        readyToLand = new PriorityQueue<Plane>();
        approachQueue = new PriorityQueue<Plane>();
    }

    public void queue(Plane plane) {
        switch (plane.currentQueue) {
            case APPROACH:
                plane.setQueuePosition(approachQueue.size() + 1); 
                this.approachQueue.add(plane);
                System.out.println("Added to approach queue. New index: " + approachQueue.size());
                break;
            case READY:
                plane.setQueuePosition(readyToLand.size() + 1);
                readyToLand.add(plane);
                System.out.println("Added to ready queue. New index: " + approachQueue.size());
                break;
        }
        if (this.approachQueue.size() != 0) {
            System.out.println("Current front of queue: " + this.approachQueue.peek().flightID);
        }
    }

    public Plane dequeue(String queueType) {
        System.out.println("Dequeueing from " + queueType);
        Plane currentPlane = null;
        switch (queueType) {
            case "APPROACH":
                if (this.approachQueue.size() != 0) {
                    currentPlane = approachQueue.remove();
                    // for (Plane )
                }
                break;
            case "READY":
                if (this.readyToLand.size() != 0)
                    currentPlane = readyToLand.remove();
                break;
            default:
                break;
        }
        return currentPlane;
    }

    public void addToFront(Plane newPlane) {
        System.out.println("Moving plane back one place in queue with flight ID: " + this.readyToLand.peek().flightID);
        for (Plane currentPlane : this.readyToLand) {
            currentPlane.setQueuePosition(currentPlane.getQueuePosition() + 1);
        }
        newPlane.setQueuePosition(0);
        System.out.println("Next landing plane flight ID: " + this.readyToLand.peek().flightID);
    }

    public int getQueueLength(String queueType) {
        switch (queueType) {
            case "APPROACH":
                return this.approachQueue.size();
            case "READY":
                return this.readyToLand.size();
            default:
                return this.readyToLand.size();
        }
    }
}