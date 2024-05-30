public class Ship {

    private int length;
    private int shipX;
    private int shipY;
    private int hits = 0;
    private String name;
    private String description;

    public Ship(int length, String name, String description) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShipX() {
        return shipX;
    }

    public void setShipX(int shipX) {
        this.shipX = shipX;
    }

    public int getShipY() {
        return shipY;
    }

    public void setShipY(int shipY) {
        this.shipY = shipY;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
