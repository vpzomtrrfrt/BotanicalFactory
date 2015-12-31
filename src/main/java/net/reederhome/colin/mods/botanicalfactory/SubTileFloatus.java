package net.reederhome.colin.mods.botanicalfactory;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

import java.util.Iterator;
import java.util.List;

public class SubTileFloatus extends SubTileFunctional {
    private static final int RADIUS = 1;
    private static final int HEIGHT = 16;
    private static final int MANA_USE = 2;

    @Override
    public boolean acceptsRedstone() {return true;}

    @Override
    public LexiconEntry getEntry() {
        return BotanicalFactory.entryFloatus;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(redstoneSignal == 0 && mana > 0) {
            List<Entity> l = supertile.getWorldObj().getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(supertile.xCoord+0.5-RADIUS, supertile.yCoord, supertile.zCoord+0.5-RADIUS, supertile.xCoord+0.5+RADIUS, supertile.yCoord+HEIGHT, supertile.zCoord+0.5+RADIUS));
            Iterator<Entity> it = l.iterator();
            while(it.hasNext() && mana >= MANA_USE) {
                Entity e = it.next();
                e.addVelocity(0, 0.1, 0);
                mana-=MANA_USE;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Circle(toChunkCoordinates(), RADIUS);
    }
}
