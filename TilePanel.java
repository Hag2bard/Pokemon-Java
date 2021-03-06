 
 ​package​ ​pokemon​; 
  
 ​import​ ​javax.imageio.ImageIO​; 
 ​import​ ​javax.swing.*​; 
 ​import​ ​java.awt.*​; 
 ​import​ ​java.awt.event.MouseEvent​; 
 ​import​ ​java.awt.event.MouseListener​; 
 ​import​ ​java.awt.image.BufferedImage​; 
 ​import​ ​java.io.IOException​; 
 ​import​ ​java.util.ArrayList​; 
  
 ​public​ ​class​ ​TilePanel​ ​extends​ ​JPanel​ ​implements​ ​MouseListener​ { 
  
 ​    ​private​ ​final​ ​ObjectPlace​ objectPlace; 
  
 ​    ​private​ ​int​ selectedX ​=​ ​-​1​; 
 ​    ​private​ ​int​ selectedY ​=​ ​-​1​; 
 ​    ​private​ ​int​ preSelectedX ​=​ ​-​1​; 
 ​    ​private​ ​int​ preSelectedY ​=​ ​-​1​; 
 ​    ​private​ ​int​ tilesize ​=​ ​16​; 
 ​    ​private​ ​String​ filename ​=​ ​"​tileset-advance.png​"​; 
 ​    ​private​ ​BufferedImage​ tilesetBufferedImage; 
 ​    ​private​ ​boolean​ multipleBlockSelected ​=​ ​true​;  ​//​/// auf false setzen 
 ​    ​private​ ​ArrayList<​Integer​>​ selectedBlocks;    ​//​ - Werte sind X und + Werte sind Y 
 ​    ​private​ ​int​ amountOfSelectedBlocks ​=​ ​2​; 
  
 ​    ​public​ ​TilePanel​(​ObjectPlace​ ​objectPlace​) { 
 ​        ​this​.​objectPlace ​=​ objectPlace; 
 ​        selectedBlocks ​=​ ​new​ ​ArrayList<​Integer​>​(); 
  
 ​        ​try​ { 
 ​            loadBufferedImage(); 
 ​        } ​catch​ (​IOException​ e) { 
 ​            e​.​printStackTrace(); 
 ​        } 
 ​    } 
  
 ​    ​@Override 
 ​    ​protected​ ​void​ ​paintComponent​(​Graphics​ ​g​) { 
 ​        ​super​.​paintComponent(g); 
  
 ​        objectPlace​.​pokeEditor2​.​runGetandSet(); 
 ​        g​.​drawImage(tilesetBufferedImage, ​0​, ​0​, ​128​, ​15971​, ​null​); 
 ​        ​if​ (multipleBlockSelected) { 
 ​            g​.​setColor(​Color​.​MAGENTA​); 
 ​            ​int​ selectedBlocksX ​=​ ​20​; 
 ​            ​int​ selectedBlocksY ​=​ ​20​; 
 ​            ​for​ (​int​ i ​=​ ​0​; i ​<​ selectedBlocks​.​size(); i ​=​ i ​+​ ​2​) { 
  
 ​                selectedBlocksX ​=​ selectedBlocks​.​get(i); 
 ​                selectedBlocksY ​=​ selectedBlocks​.​get(i ​+​ ​1​); 
  
 ​                g​.​drawRect(selectedBlocksX ​*​ tilesize, selectedBlocksY ​*​ tilesize, tilesize, tilesize);​//​sollte eigentlich fertig sein an dieser Stelle 
  
  
 ​            } 
 ​        } ​else​ { 
 ​            ​if​ (selectedX ​!=​ ​-​1​ ​&&​ selectedY ​!=​ ​-​1​) { 
 ​                g​.​setColor(​Color​.​RED​); 
 ​                g​.​drawRect(selectedX ​*​ tilesize, selectedY ​*​ tilesize, tilesize, tilesize); 
  
 ​            } 
 ​        } 
 ​    } 
  
 ​    ​private​ ​void​ ​loadBufferedImage​() ​throws​ ​IOException​ { 
 ​//​        tilesetBufferedImage = ImageIO.read(getClass().getResourceAsStream("filename")); 
 ​        tilesetBufferedImage ​=​ ​ImageIO​.​read(getClass()​.​getResource(filename)); 
 ​    } 
  
 ​    ​@Override 
 ​    ​public​ ​void​ ​mouseClicked​(​MouseEvent​ ​e​) { 
 ​        ​if​ (​!​multipleBlockSelected) { 
 ​            ​if​ (selectedX ​!=​ ​-​1​ ​&&​ selectedY ​!=​ ​-​1​) {   ​//​wenn keine Selektion da 
 ​                preSelectedX ​=​ selectedX; 
 ​                preSelectedY ​=​ selectedY; 
 ​            } 
 ​            selectedX ​=​ e​.​getPoint()​.​x; 
 ​            selectedY ​=​ e​.​getPoint()​.​y; 
 ​            selectedX ​=​ selectedX ​/​ ​16​; 
 ​            selectedY ​=​ selectedY ​/​ ​16​; 
 ​            objectPlace​.​logic​.​setDeleteInactive(); 
 ​            objectPlace​.​pokeEditor2​.​btnDeleteBlock​.​setSelected(​false​); 
 ​            repaint(); 
 ​            ​this​.​setFocusable(​true​); 
 ​            objectPlace​.​pokeEditor2​.​btnDeleteBlock​.​setFocusable(​false​); 
 ​        } ​else​ { 
             selectedBlocks = null;
 ​            ​for​ (​int​ y ​=​ ​0​; y ​<​ amountOfSelectedBlocks; y​++​) { 
 ​                ​for​ (​int​ x ​=​ ​0​; x ​<​ amountOfSelectedBlocks; x​++​) { 
 ​                    selectedBlocks​.​add((e​.​getPoint()​.​x ​/​ ​16​) ​+​ x); 
 ​                    selectedBlocks​.​add((e​.​getPoint()​.​y ​/​ ​16​) ​+​ y); 
 ​                } 
 ​            } 
 ​            repaint(); 
 ​            ​this​.​setFocusable(​true​); 
 ​        } 
 ​    } 
  
  
 ​    ​@Override 
 ​    ​public​ ​void​ ​mousePressed​(​MouseEvent​ ​e​) { 
  
 ​    } 
  
 ​    ​@Override 
 ​    ​public​ ​void​ ​mouseReleased​(​MouseEvent​ ​e​) { 
  
 ​    } 
  
 ​    ​@Override 
 ​    ​public​ ​void​ ​mouseEntered​(​MouseEvent​ ​e​) { 
  
 ​    } 
  
 ​    ​@Override 
 ​    ​public​ ​void​ ​mouseExited​(​MouseEvent​ ​e​) { 
  
 ​    } 
  
  
 ​    ​public​ ​void​ ​moveLeft​() { 
 ​        ​if​ (​this​.​selectedX ​>​ ​0​) 
 ​            ​this​.​selectedX​--​; 
 ​    } 
  
 ​    ​public​ ​void​ ​moveRight​() { 
 ​        ​if​ (​this​.​selectedX ​<​ ​7​) 
 ​            ​this​.​selectedX​++​; 
 ​    } 
  
 ​    ​public​ ​void​ ​moveUp​() { 
 ​        ​if​ (​this​.​selectedY ​>​ ​0​) 
 ​            ​this​.​selectedY​--​; 
 ​    } 
  
 ​    ​public​ ​void​ ​moveDown​() { 
 ​        ​if​ (​this​.​selectedY ​<​ ​15971​) 
 ​            ​this​.​selectedY​++​; 
 ​    } 
  
 ​    ​public​ ​int​ ​getSelectedX​() { 
 ​        ​return​ selectedX; 
 ​    } 
  
 ​    ​public​ ​int​ ​getSelectedY​() { 
 ​        ​return​ selectedY; 
 ​    } 
  
 ​    ​public​ ​void​ ​setSelectedX​(​int​ ​selectedX​) { 
 ​        ​this​.​selectedX ​=​ selectedX; 
 ​    } 
  
 ​    ​public​ ​void​ ​setSelectedY​(​int​ ​selectedY​) { 
 ​        ​this​.​selectedY ​=​ selectedY; 
 ​    } 
  
  
 ​}