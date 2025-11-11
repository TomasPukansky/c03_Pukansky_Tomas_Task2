package fill;


import rasterize.Raster;

public class SeedFiller implements Filler {
    private Raster raster;
    private int fillColor;
    private int backgroundColor;
    private int startX, startY;
    private int boundaryColor;

  public SeedFiller(Raster raster, int fillColor, int startX, int startY, int boundaryColor) {

      this.raster = raster;
      this.startX = startX;
      this.startY = startY;
      this.fillColor = fillColor;
      this.backgroundColor = raster.getPixel(startX,startY);
      this.boundaryColor = boundaryColor;
  }

    @Override
    public void fill() {
      seedFill(startX, startY);
    }

    private void seedFill(int x, int y){
        int pixelColor = raster.getPixel(x,y);

        //todo: nacitam farbu pixelu, na ktory som kikol(starX, startY)
        if (pixelColor != backgroundColor ) {
            return;
        }


        // todo: podmienka, ci mam alebo nemam ofarbit
        // ak nie koncim

        int currentColor = raster.getPixel(x, y);

        if (currentColor == boundaryColor || currentColor == fillColor) {
            return;
        }

        raster.setPixel(x, y, fillColor);



        // todo: ofarbim
        // todo: zavolam seedfill pre susedov
        seedFill(x + 1, y); // doprava
        seedFill(x - 1, y); // doľava
        seedFill(x, y + 1); // dole
        seedFill(x, y - 1); //hore

    }
}
