package service;

import module.Coord;
import module.Figure;
import module.Mapable;
import ui.Config;

public class FlyFigure {
    private Figure figure;
    private Coord coord;
    private boolean lended;
    private int tiks;
    Mapable map;

    public FlyFigure(Mapable map){
        this.map = map;
        figure = Figure.getRandom();
        coord = new Coord(Config.WIDTH/2-2,-1);
        lended = false;
        tiks = 2;
    }
    public Coord getCoord() {
        return coord;
    }
    public Figure getFigure() {
        return figure;
    }
    public boolean canPlaysFigure(){
        return canMoveFigure(figure,0,0);
    }
    private boolean canMoveFigure(Figure figure,int sx,int sy){
        if(coord.x+sx+figure.top.x<0)return false;
        if(coord.x+sx+figure.bottom.x>= Config.WIDTH)return false;
        if(coord.y+sy+figure.bottom.y>=Config.HEIGHT)return false;
        for(Coord dot: figure.dots)
            if(map.getBoxColor(coord.x+dot.x+sx,coord.y+dot.y+sy)>0)
                return false;
        return true;
    }
    public void moveFigure(int sx,int sy){
        if(canMoveFigure(figure,sx,sy))
            coord = coord.pluse(sx,sy);
        else
            if (sy==1 )
                if (tiks>0)
                    tiks--;
            else
                lended = true;
    }
    public boolean isLanded(){
        return lended;
    }
    public void turnFigure(int direction){
        Figure rotated = direction ==1 ? figure.turnRight():figure.turnLeft();
        if(canMoveFigure(rotated,0,0)){
            figure = rotated;
        }else
        if(canMoveFigure(rotated,1,0)){
            figure = rotated;
            moveFigure(1,0);
        }else
        if(canMoveFigure(rotated,-1,0)) {
            figure = rotated;
            moveFigure(-1, 0);
        }else
        if(canMoveFigure(rotated,0,-1)) {
            figure = rotated;
            moveFigure(0, -1);
        }
    }
}
