import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        shipPlacement();

    }

    public static void shipPlacement() {

        // Skapa objekt

        Ship ship1 = new Ship(5, "s0", "hangarfartyg");
        Ship ship2 = new Ship(4, "s1", "slagskepp");
        Ship ship3 = new Ship(4, "s2", "slagskepp");
        Ship ship4 = new Ship(3, "s3", "kryssare");
        Ship ship5 = new Ship(3, "s4", "kryssare");
        Ship ship6 = new Ship(3, "s5", "kryssare");
        Ship ship7 = new Ship(2, "s6", "ubåt");
        Ship ship8 = new Ship(2, "s7", "ubåt");
        Ship ship9 = new Ship(2, "s8", "ubåt");
        Ship ship10 = new Ship(2, "s9", "ubåt");


        // Skapa lista + addera objekt

        List<Ship> shipList = new ArrayList<>();
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);
        shipList.add(ship4);
        shipList.add(ship5);
        shipList.add(ship6);
        shipList.add(ship7);
        shipList.add(ship8);
        shipList.add(ship9);
        shipList.add(ship10);


        // Varibler

        int reset = 0;
        int placementTries = 0;
        int mapSizeX = 10;
        int mapSizeY = 10;
        String water = "▓";


        // Skapa karta (2D-array)

        String[][] map = new String[mapSizeY][mapSizeX];


        // Skriva ut tecken för vatten på kartan

        for (int i = 0; i < mapSizeY; i++) {
            for (int j = 0; j < mapSizeX; j++) {
                map[i][j] = water;
            }
        }


        // Placera Skepp

        for (int i = 0; i < shipList.size(); i++) {

            Random random = new Random();

            int shipX;
            int shipY;
            int checkXStart;
            int checkXEnd;
            int checkYStart;
            int checkYEnd;
            boolean successfulPlacement = false;

            while (!successfulPlacement) {
                int collision = 0;
                boolean horizontalAlignment = random.nextBoolean();
                placementTries ++;
                if (placementTries > 1000) {
                    i = -1;
                    reset ++;
                    placementTries = 0;
                    for (int j = 0; j < mapSizeY; j++) {
                        for (int k = 0; k < mapSizeX; k++) {
                            map[j][k] = water;
                        }
                    }
                    break;
                }


                // Kodfält vid horisontell utplacering

                if (horizontalAlignment) {


                    // Slumpmässig placering och grundsökområde

                    shipX = random.nextInt(mapSizeX-shipList.get(i).getLength() + 1);           // Kan ej placera ett skepp utanför kartan i X-led.
                    shipY = random.nextInt(mapSizeY);
                    checkXStart = shipX;
                    checkXEnd = shipX + 1 + (shipList.get(i).getLength() - 1);
                    checkYStart = shipY;
                    checkYEnd = shipY + 1;


                    // Eventuell utökning av sökområdet beroende på placering

                    if (shipX > 0) checkXStart --;
                    if (shipX + (shipList.get(i).getLength() - 1) < mapSizeX - 1) checkXEnd ++;
                    if (shipY > 0) checkYStart --;
                    if (shipY < mapSizeY - 1) checkYEnd ++;


                    // Kontrollerar sökområdet

                    for (int j = checkYStart; j < checkYEnd; j++) {
                        for (int k = checkXStart; k < checkXEnd; k++) {
                            if (!Objects.equals(map[j][k], "▓")) collision++;
                        }
                    }


                    // Placerar skepp om det inte är någon kollision

                    if (collision == 0) {
                        for (int j = shipY; j < shipY+1; j++) {
                            for (int k = shipX; k < (shipX + 1) + (shipList.get(i).getLength()-1); k++) {
                                map[j][k] = String.valueOf(shipList.get(i).getLength());
                            }
                        }
                        successfulPlacement = true;
                    }


                    // Kodfält vid vertikal utplacering

                } else {


                    // Slumpmässig placering och grundsökområde

                    shipX = random.nextInt(mapSizeX);
                    shipY = random.nextInt(mapSizeY-shipList.get(i).getLength() + 1);           // Kan ej placera ett skepp utanför kartan i Y-led.
                    checkXStart = shipX;
                    checkXEnd = shipX + 1;
                    checkYStart = shipY;
                    checkYEnd = shipY + 1 + (shipList.get(i).getLength() - 1);


                    // Eventuell utökning av sökområdet beroende på placering

                    if (shipX > 0) checkXStart --;
                    if (shipX < (mapSizeX - 1)) checkXEnd ++;
                    if (shipY > 0) checkYStart --;
                    if (shipY + (shipList.get(i).getLength() - 1) < mapSizeY - 1) checkYEnd ++;


                    // Kontrollerar sökområdet

                    for (int j = checkYStart; j < checkYEnd; j++) {
                        for (int k = checkXStart; k < checkXEnd; k++) {
                            if (!Objects.equals(map[j][k], "▓")) collision++;
                        }
                    }


                    // Placerar skepp om det inte är någon kollision

                    if (collision == 0) {
                        for (int j = shipY; j < shipY + 1 + (shipList.get(i).getLength() - 1); j++) {
                            for (int k = shipX; k < shipX + 1; k++) {
                                map[j][k] = String.valueOf(shipList.get(i).getLength());
                            }
                        }
                        successfulPlacement = true;
                    }


                }
            }
        }


        // Skriva ut kartan

        for (int i = 0; i < mapSizeY; i++) {
            for (int j = 0; j < mapSizeX; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\nUtplacering " + "färdig efter " + (reset * 100 + placementTries) + " st försök (" + reset + " nollställningar).");
    }
}