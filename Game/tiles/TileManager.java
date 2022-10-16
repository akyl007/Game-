package Game.tiles;

import Game.panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;

    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;


        this.tiles = new Tile[10];
        mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];

        getTileImage();
        loadMap("/resources/maps/world01.txt");

    }

    private void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/water.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/sand.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/earth.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tree.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String map){
        try{
            InputStream is = getClass().getResourceAsStream(map);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;

            while (column < gp.maxWorldColumn && row < gp.maxWorldRow){

                String line = br.readLine();

                while (column < gp.maxWorldColumn){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                     mapTileNum[column][row] = num;

                     column++;

                }
                if (column == gp.maxWorldColumn){
                    column = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g){

        int worldColum = 0;
        int worldRow = 0;


        while (worldColum < gp.maxWorldColumn && worldRow < gp.maxWorldRow){

            int worldX = gp.tileSize * worldColum;
            int worldY = gp.tileSize * worldRow;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;


            int tileNum = mapTileNum[worldColum][worldRow];

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
               g.drawImage(tiles[tileNum].image,screenX,screenY,gp.tileSize, gp.tileSize, null);
            }
            worldColum++;


            if (worldColum == gp.maxWorldColumn){
                worldColum = 0;

                worldRow++;

            }
        }


    }


}
