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
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap();

    }

    private void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/water.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/wall.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/resources/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;

            while (column < gp.maxScreenColumn && row < gp.maxScreenRow){

                String line = br.readLine();

                while (column < gp.maxScreenColumn){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[column]);
                     mapTileNum[column][row] = num;

                     column++;

                }
                if (column == gp.maxScreenColumn){
                    column = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }
    }
    public void draw(Graphics2D g){

        int colum = 0;
        int roww = 0;
        int x = 0;
        int y = 0;

        while (colum < gp.maxScreenColumn && roww < gp.maxScreenRow){

            int tileNum = mapTileNum[colum][roww];
            g.drawImage(tiles[tileNum].image,x,y,gp.tileSize, gp.tileSize, null);

            colum++;
            x += gp.tileSize;

            if (colum == gp.maxScreenColumn){
                colum = 0;
                x = 0;
                roww++;
                y+= gp.tileSize;
            }
        }


    }


}
