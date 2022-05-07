public class App {
    public static void main(String[] args) throws Exception {
        Hexagon hexagon = new Hexagon();
        Hexagon newHexagon = new Hexagon();
        newHexagon.setType(HexagonType.SEA);
        SeaSnake s = new SeaSnake();
        Explorer e = new Explorer(Color.BLUE, 6);
        Boat b = new Boat();
        hexagon.addPawn(s);
        newHexagon.addPawn(e);
        newHexagon.addPawn(b);
        e.move(newHexagon, b, newHexagon);
        System.out.println(e.getStatus());
        hexagon.getSeaSnakeList().get(0).move(hexagon, newHexagon);
        System.out.println(e.getStatus());
    }
}
