/* *****************************************************************************
 * Name: Hasit Nanda
 * NetID:
 * Precept:
 *
 * Description: This class defines the tour data type by implementing a Circular
 * Linked List and defining methods to allow for the implementation of two
 * heuristics to find good solutions to the TSP.
 **************************************************************************** */

public class Tour {
    private class Node {
        private Point p; // point value of node
        private Node next; // pointer to next Node
    }

    private Node start; // first Node in Linked List

    // creates an empty tour
    public Tour() {
        start = new Node();
    }

    // creates the 4-point tour a->b->c->d->a (for debugging)
    public Tour(Point a, Point b, Point c, Point d) {
        start = new Node();
        Node b1 = new Node();
        Node c1 = new Node();
        Node d1 = new Node();
        start.p = a;
        b1.p = b;
        c1.p = c;
        d1.p = d;
        start.next = b1;
        b1.next = c1;
        c1.next = d1;
        d1.next = start;
    }

    // returns the number of points in this tour
    public int size() {
        if (start.p == null) {
            return 0;
        }
        else {
            int counter = 0;
            Node current = start;
            do {
                current = current.next;
                counter += 1;
            } while (!current.equals(start));
            return counter;
        }
    }

    // returns the length of this tour
    public double length() {
        if (start.p == null) {
            return 0.0;
        }
        else {
            double distance = 0.0;
            Node current = start;
            do {
                distance += current.p.distanceTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));

            return distance;
        }

    }

    // returns a string representation of this tour
    public String toString() {
        if (start.p == null) {
            return "";
        }
        else {
            Node current = start;
            StringBuilder str = new StringBuilder();
            do {
                str.append(current.p.toString() + "\n");
                current = current.next;
            } while (!current.equals(start));
            return str.toString();
        }
    }

    // draws this tour to standard drawing
    public void draw() {
        if (start.p != null && start.next != null) {
            Node current = start;
            do {
                current.p.drawTo(current.next.p);
                current = current.next;
            } while (!current.equals(start));
        }
    }

    // inserts p using the nearest neighbor heuristic
    public void insertNearest(Point newPoint) {
        Node node = new Node();
        node.p = newPoint;
        if(start.p==null){
            node.next=null;
            start = node;
            return;
        }

        if (start.next==null ){
            start.next=node;
            node.next=start;
            return;
        }

        Node currentNode = start.next;
        Node menorNode = start;
        double menorDistancia = Double.MAX_VALUE;
        while ( currentNode != start ){
	        if (currentNode.p.distanceTo(newPoint)<menorDistancia){
		        menorDistancia=currentNode.p.distanceTo(newPoint);
		        menorNode=currentNode;
            }
	        currentNode = currentNode.next;
        }

        insertNode(menorNode, newPoint);

}


    // inserts p using the smallest increase heuristic
    public void insertSmallest(Point newPoint){
        Node node = new Node();
        node.p = newPoint;
        if(start.p==null){ 
            node.next=null;
            start = node;
            return;
        }

        if (start.next==null ){
            start.next=node;
            node.next=start;
            return;
        }
        Node atual = start;
        Node melhorNode = null;
        double menorAumento = Double.MAX_VALUE;
        do{
            double aumento = atual.p.distanceTo(newPoint) + atual.next.p.distanceTo(newPoint) - atual.p.distanceTo(atual.next.p); 
            if (aumento<menorAumento){
                menorAumento=aumento;
                melhorNode=atual;
            }
            atual = atual.next;
        }while (atual != start);
        
        insertNode(melhorNode, newPoint);

    }

    private void insertNode(Node node,Point newPoint){
        Node newNode = new Node();
        newNode.p = newPoint;
        newNode.next = node.next;
        node.next = newNode;
    }
    
    // tests this class by calling all constructors and instance methods
    public static void main(String[] args) {
        // define 4 points, corners of a square
        Point a = new Point(1.0, 1.0);
        Point b = new Point(1.0, 4.0);
        Point c = new Point(4.0, 4.0);
        Point d = new Point(4.0, 1.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // print the size to standard output
        int size = squareTour.size();
        StdOut.println("# of points = " + size);

        // print the tour length to standard output
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);

        // print the tour to standard output
        StdOut.println(squareTour);

        StdDraw.setXscale(0, 6);
        StdDraw.setYscale(0, 6);

        Point e = new Point(5.0, 6.0);
        squareTour.insertNearest(e);
        squareTour.insertSmallest(e);
        squareTour.draw();


    }
}
