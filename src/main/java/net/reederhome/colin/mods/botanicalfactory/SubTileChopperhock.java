package net.reederhome.colin.mods.botanicalfactory;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileChopperhock extends SubTileFunctional {
	
	private static final int RADIUS = 4;
	private static final int DELAY = 30;
	
	@Override
	public boolean acceptsRedstone() {
		return true;
	}
	
	@Override
	public LexiconEntry getEntry() {
		return BotanicalFactory.entryChopperhock;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(redstoneSignal == 0 && mana > 0 && ticksExisted % DELAY == 0) {
			ChunkCoordinates me = toChunkCoordinates();
			World w = supertile.getWorldObj();
			for(int x = -RADIUS; x < RADIUS+1; x++) {
				for(int z = -RADIUS; z < RADIUS+1; z++) {
					int cx = me.posX+x;
					int cz = me.posZ+z;
					int cy = me.posY;
					while(true) {
						if(cy>255) break;
						Block b = w.getBlock(cx, cy, cz);
						int meta = w.getBlockMetadata(cx, cy, cz);
						if(b instanceof BlockLog) {
							mana--;
							w.func_147480_a(cx, cy, cz, true);
							return;
						}
						cy++;
					}
				}
			}
		}
	}
	
	@Override
	public RadiusDescriptor getRadius() {
		return new RadiusDescriptor.Square(toChunkCoordinates(), RADIUS);
	}
	
	@Override
	public int getColor() {
		return 0x664422;
	}
}