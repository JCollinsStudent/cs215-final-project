public class Application {
    public static void main(String[] args) {
        Plane plane = new Plane();
        Plane secondPlane = new Plane();

        plane.setQueuePosition(1);
        secondPlane.setQueuePosition(3);

        plane.setCurrentQueue("APPROACH");
        secondPlane.setCurrentQueue("READY");
        System.out.println(plane.compareTo(secondPlane));
    }
}