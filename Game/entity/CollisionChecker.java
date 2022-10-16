package Game.entity;

import Game.panel.GamePanel;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX/ gp.tileSize;
        int entityRightColumn = entityRightWorldX/ gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightColumn][entityTopRow];

                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true ){
                    entity.onCollision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightColumn][entityBottomRow];

                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true ){
                    entity.onCollision = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftColumn][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftColumn][entityBottomRow];

                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true ){
                    entity.onCollision = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX - entity.speed)/ gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightColumn][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightColumn][entityBottomRow];

                if (gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true ){
                    entity.onCollision = true;
                }
                break;

        }

    }
}
