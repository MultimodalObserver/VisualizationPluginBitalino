package vispluginbitalino;

import java.io.File;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import mo.core.ui.dockables.DockableElement;
import mo.core.ui.dockables.DockablesRegistry;
import mo.visualization.Playable;

public class BitalinoPlayer implements Playable{

    private long start;
    private long end;
    private Panel ap;
    private String path;
    

    private static final Logger logger = Logger.getLogger(BitalinoPlayer.class.getName());

    public BitalinoPlayer(File file,int sensor,String id) {
        ap = new Panel(file,sensor);
        start=ap.start;
        end=ap.end;
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DockableElement e = new DockableElement();
                    e.setTitleText(id);
                    e.add(ap);
                    DockablesRegistry.getInstance().addAppWideDockable(e);
                }
            }); 
    }
        
    
    @Override
    public void pause() {
       
    }

    @Override
    public void seek(long desiredMillis) {
        ap.play((int)(desiredMillis-start));
    }
    
    @Override
    public long getStart() {        
        return start;
    }

    @Override
    public long getEnd() {
        return end;
    }   

    @Override
    public void play(long millis) {
        ap.play((int)((millis-start)));
    }

    @Override
    public void stop() {
        ap.stop();
    }

    @Override
    public void sync(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
