import java.lang.Math;

public class Application {
    public static void main(String[] args) {

        Airport dylanPort = new Airport();

        for (int i=0; i<100; i++) {
            System.out.println("Current Timestamp: " + i);
            double randomNum = Math.random() * 100;
            Plane newPlane = new Plane(dylanPort.getQueueLength("APPROACH") == 0 ? 0 : dylanPort.getQueueLength("APPROACH" + 1), "APPROACH");

            if (randomNum <= 90.0) {
                dylanPort.queue(newPlane);
                System.out.println("New plane approaching. Queue position: " + newPlane.queuePosition);
            }
            else {
                // System.out.println("no plane");
            }

            // Land plane at front of ready queue
            double randomLanding = Math.random() * 100;
            if (!dylanPort.readyToLand.isEmpty() && randomLanding <= 55.0) {
                Plane landingPlane = dylanPort.dequeue("READY");
                if (landingPlane != null) {
                    System.out.println("Current landing plane ID: " + landingPlane.flightID);
                }
                
            }

            // Emergency landing
            double emergencyNum = Math.random() * 100;
            if (emergencyNum <= 5.0) {
                if (dylanPort.readyToLand.size() != 0) {
                    for (Plane plane : dylanPort.readyToLand) {
                        if (plane.getQueuePosition() == (int)emergencyNum) {
                            System.out.println("In emergency landing. Moving plane with flight ID: " + plane.flightID);
                            System.out.println("Moving closest plane to next position with flight ID: " + dylanPort.readyToLand.peek().flightID);
                            dylanPort.addToFront(plane);
                        }
                    }
                }


            }

            // Move one plane from Approach Queue to Ready Queue
            double randomReady = Math.random() * 100;
            if (randomReady <= 60.0) {
                Plane readyPlane = dylanPort.dequeue("APPROACH");
                if (readyPlane != null) {
                    readyPlane.setCurrentQueue("READY");
                    readyPlane.setQueuePosition(dylanPort.getQueueLength("READY") == 0 ? 0 : dylanPort.getQueueLength("READY") + 1);
                    dylanPort.queue(readyPlane);
                    System.out.println("New plane ready to land. Ready Queue Position: " + readyPlane.queuePosition);
                }    
            }
            System.out.println("Number of planes approaching: " + dylanPort.getQueueLength("APPROACH"));            
            System.out.println("Number of planes ready to land: " + dylanPort.getQueueLength("READY")  + "\n\n");

        }
    }
}